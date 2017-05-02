/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.core.utility.ViewUtil;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserPreference;
import com.bluespacetech.security.repository.UserPreferenceRepository;

@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class,
		BusinessException.class })
public class UserPreferenceServiceImpl implements UserPreferenceService {

	@Autowired
	private UserPreferenceRepository userPreferenceRepository;

	@Autowired
	private UserAccountService userAccountService;

	@Override
	public UserPreference saveUserPreference(final UserPreference userPreference)
			throws Exception {
		final UserPreference result = userPreference;
		final UserAccount userAccount = userAccountService
				.findUserAccountByUsername(ViewUtil.getPrincipal());
		if (userAccount == null) {
			throw new Exception(
					"Account does not exist for user. Cannot save preferences.");
		}
		result.setUserAccount(userAccount);
		return userPreferenceRepository.save(result);
	}

	@Override
	public UserPreference findUserPreferenceByUserAccount(
			final UserAccount userAccount) {
		UserPreference userPreference = userPreferenceRepository
				.findUserPreferenceByUserAccount(userAccount);
		if (userPreference == null) {
			userPreference = new UserPreference();
			userPreference.setUserAccount(userAccount);
			userPreference = userPreferenceRepository.save(userPreference);
		}
		return userPreference;
	}

	@Override
	public UserPreference getCurrentUserPreference() throws Exception {
		final UserAccount userAccount = userAccountService
				.findUserAccountByUsername(ViewUtil.getPrincipal());
		if (userAccount == null) {
			throw new Exception("No current user.");
		}
		return this.findUserPreferenceByUserAccount(userAccount);
	}

}
