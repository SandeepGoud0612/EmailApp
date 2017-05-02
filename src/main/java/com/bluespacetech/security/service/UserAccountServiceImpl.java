package com.bluespacetech.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.repository.UserAccountRepository;


@Service
@Transactional
public class UserAccountServiceImpl implements UserAccountService {

	@Autowired
	UserAccountRepository userAccountRepository;

	@Override
	@Transactional
	public UserAccount findUserAccountByUsername(final String username) {
		return userAccountRepository.findUserAccountByUsername(username);
	}

}