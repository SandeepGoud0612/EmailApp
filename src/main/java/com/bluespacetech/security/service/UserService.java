package com.bluespacetech.security.service;

/**
 * Service implementation interface for user.
 *
 * @author pradeep
 *
 */
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserAccountUserGroup;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.model.UserGroupUserRole;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.model.UserRoleAuthority;
import com.bluespacetech.security.repository.UserGroupRepository;
import com.bluespacetech.security.repository.UserRoleRepository;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserService implements UserDetailsService {

	@Autowired
	UserAccountService userAccountService;

	// @Autowired
	// PageLinksService pageLinksService;

	@Autowired
	UserGroupRepository userGroupRepository;

	@Autowired
	UserRoleRepository userRoleRepository;


	/**
	 *
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(final String username)
			throws UsernameNotFoundException {
		UserAccount userAccount = null;

		try {
			userAccount = userAccountService
					.findUserAccountByUsername(username);
		} catch (final RuntimeException exception) {
			throw new UsernameNotFoundException(exception.getMessage());
		}

		if (userAccount == null) {
			throw new UsernameNotFoundException("User not found by username");
		}


		final List<Long> userGroupIds = new ArrayList<Long>();
		final Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();

		for(final UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups()){
			userGroupIds.add(userAccountUserGroup.getUserGroupId());
		}
		if(userGroupIds.size()>0){
			final List<Long> userRoleIds = new ArrayList<Long>();
			final List<UserGroup> userGroups = userGroupRepository.findAll(userGroupIds);
			for(final UserGroup userGroup : userGroups){
				for(final UserGroupUserRole userGroupUserRole : userGroup.getUserGroupUserRoles()){
					userRoleIds.add(userGroupUserRole.getUserRoleId());
				}
			}
			if(userRoleIds.size()>0){
				final List<UserRole> userRoles = userRoleRepository.findAll(userRoleIds);
				for(final UserRole userRole : userRoles){
					for(final UserRoleAuthority userRoleAuthority : userRole.getUserRoleAuthorities()){
						grantedAuthorities.add(new SimpleGrantedAuthority(userRoleAuthority.getAuthorityGrant().getLabel().toUpperCase()));
					}
				}
			}
		}

		if (UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.equals(userAccount
				.getUserAccountType())) {
			grantedAuthorities.add(new SimpleGrantedAuthority(
					UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN
					.getAccountType()));
		} else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.equals(userAccount
				.getUserAccountType())) {
			grantedAuthorities.add(new SimpleGrantedAuthority(
					UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType()));
		} else if (UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.equals(userAccount
				.getUserAccountType())) {
			grantedAuthorities.add(new SimpleGrantedAuthority(
					UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType()));
		} else if (UserAccountTypeConstant.ACC_TYPE_USER.equals(userAccount.getUserAccountType())) {
			grantedAuthorities.add(new SimpleGrantedAuthority(UserAccountTypeConstant.ACC_TYPE_USER.getAccountType()));
		}
		// System.out.println("inside loadUserByUsername() :grantedAuthorities "
		// + grantedAuthorities);
		if (grantedAuthorities.isEmpty()) {
			throw new UsernameNotFoundException(
					"User does not have granted authorities");
		}

		final String password = userAccount.getPassword();

		final boolean isActive = userAccount.isActive();

		final boolean isAccountNotExpried = !userAccount.isAccountExpired();

		final boolean isCredentialsNotExpired = !userAccount
				.isCredentialsExpired();

		final boolean isAccountNotLocked = !userAccount.isAccountLocked();

		final UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				username, password, isActive, isAccountNotExpried,
				isCredentialsNotExpired, isAccountNotLocked, grantedAuthorities);
		//pageLinksService.getPageLinksAllowedForUser();
		return userDetails;
	}

	public void updatePasswordForUserAccount(final String username,
			final String password) {

		/*
		 * final UserAccount userAccount = findUserAccountByUsername(username);
		 * final PasswordEncoder encoder = new PasswordEncoder(); final String
		 * encodedPassword = encoder.encodePassword(password, null);
		 * userAccount.setPassword(encodedPassword);
		 * updateUserAccount(userAccount);
		 */

	}

}
