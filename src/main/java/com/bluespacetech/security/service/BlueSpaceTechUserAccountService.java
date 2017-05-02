package com.bluespacetech.security.service;

import java.util.List;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria;

public interface BlueSpaceTechUserAccountService {

	UserAccount findUserAccountByUsername(final String username);

	List<UserAccount> getAllUserAccounts();

	UserAccount getUserAccountById(final Long userAccountId);

	List<UserAccount> getUserAccountByIds(final List<Long> userAccountIds);

	UserAccount createUserAccount(final UserAccount userAccount)
			throws BusinessException;

	UserAccount updateUserAccount(final UserAccount userAccount)
			throws BusinessException;

	void deleteUserAccount(final Long userAccountId) ;

	List<UserAccount> findUserAccountsBySearchCriteria(final UserAccountSearchCriteria userAccountSearchCriteria);


	void changePasswordUserAccount(final String oldPassword,final String newPassword,final String confirmPassword)
			throws BusinessException;

}