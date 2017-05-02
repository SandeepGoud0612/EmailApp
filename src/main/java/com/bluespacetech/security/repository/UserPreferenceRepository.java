package com.bluespacetech.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserPreference;

@Repository
public interface UserPreferenceRepository extends
JpaRepository<UserPreference, Long> {

	UserPreference findUserPreferenceByUserAccount(final UserAccount userAccount);

}
