package com.bluespacetech.security.service;

import com.bluespacetech.security.model.UserAccount;

public interface UserAccountService {

	UserAccount findUserAccountByUsername(final String username);

}