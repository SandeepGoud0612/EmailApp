/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.service;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserPreference;

public interface UserPreferenceService {


	UserPreference saveUserPreference(final UserPreference userPreference)
			throws Exception;

	UserPreference findUserPreferenceByUserAccount(
			final UserAccount userAccount) ;

	UserPreference getCurrentUserPreference() throws Exception ;

}
