package com.bluespacetech.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.security.model.UserAccount;


@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long> {

	UserAccount findUserAccountByUsername(final String username);

}
