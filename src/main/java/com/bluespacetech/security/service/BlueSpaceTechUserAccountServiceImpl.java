package com.bluespacetech.security.service;

import java.util.List;

import javax.mail.MessagingException;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.utility.ViewUtil;
import com.bluespacetech.notifications.email.worker.EmailUserAccountWorker;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserAccountUserGroup;
import com.bluespacetech.security.repository.UserAccountRepository;
import com.bluespacetech.security.repository.UserAccountRepositoryCustom;
import com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria;

@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
		ApplicationException.class })
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class BlueSpaceTechUserAccountServiceImpl implements BlueSpaceTechUserAccountService {

	@Autowired
	UserAccountRepository userAccountRepository;

	@Autowired
	UserAccountRepositoryCustom userAccountRepositoryCustom;

	@Autowired
	EmailUserAccountWorker emailUserAccountWorker;

	@Override
	@PreAuthorize("permitAll")
	public UserAccount findUserAccountByUsername(final String username) {
		return userAccountRepository.findUserAccountByUsername(username);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
	public List<UserAccount> getAllUserAccounts() {
		return userAccountRepository.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
	public UserAccount getUserAccountById(final Long userAccountId) {
		return userAccountRepository.findOne(userAccountId);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
	public List<UserAccount> getUserAccountByIds(final List<Long> userAccountIds) {
		return userAccountRepository.findAll(userAccountIds);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('CREATE_USERS')")
	public UserAccount createUserAccount(final UserAccount userAccount)
			throws BusinessException {
		final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		final String randomPassword = RandomStringUtils.randomAlphanumeric(8);
		final String hashedPassword = passwordEncoder.encode(randomPassword);
		userAccount.setPassword(hashedPassword);

		for (final UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups()) {
			userAccountUserGroup.setUserAccount(userAccount);
		}
		final UserAccount newUserAccount = userAccountRepository.save(userAccount);
		try {
			emailUserAccountWorker.sendEmail(newUserAccount, randomPassword);
		} catch (MailException | InterruptedException | MessagingException e) {
			throw new BusinessException(e);
		}
		return newUserAccount;
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('UPDATE_USERS')")
	public UserAccount updateUserAccount(final UserAccount userAccount)
			throws BusinessException {
		for (final UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups()) {
			userAccountUserGroup.setUserAccount(userAccount);
		}
		return userAccountRepository.save(userAccount);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('DELETE_USERS')")
	public void deleteUserAccount(final Long userAccountId) {
		userAccountRepository.delete(userAccountId);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACCESS_USERS')")
	public List<UserAccount> findUserAccountsBySearchCriteria(
			final UserAccountSearchCriteria userAccountSearchCriteria) {
		return userAccountRepositoryCustom.findUserAccountsBySearchCriteria(userAccountSearchCriteria);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or hasAuthority('ACC_TYPE_ADMIN') or hasAuthority('ACC_TYPE_USER') or hasAuthority('ACC_TYPE_EMPLOYEE')")
	public void changePasswordUserAccount(final String oldPassword,final String newPassword,
			final String confirmPassword) throws BusinessException {

		if(!newPassword.equals(confirmPassword)){
			throw new BusinessException(
					"New Password and Confirm Password do not match.");
		}

		if(newPassword.equals(oldPassword)){
			throw new BusinessException(
					"New Password and Old Password cannot be same.");
		}

		final String userName = ViewUtil.getPrincipal();
		final UserAccount  userAccount = userAccountRepository.findUserAccountByUsername(userName);
		final PasswordEncoder encoder = new BCryptPasswordEncoder();
		final boolean oldPasspordMatched = encoder.matches(oldPassword, userAccount.getPassword());
		if(!oldPasspordMatched){
			throw new BusinessException(
					"Current Password is not correct.");
		}
		userAccount.setPassword(encoder.encode(newPassword));
		this.updateUserAccount(userAccount);
	}

}