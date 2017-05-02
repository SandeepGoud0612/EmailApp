package com.bluespacetech.security.repository;

import java.util.List;

import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria;


/**
 * @author pradeep created date 30-Jan-2015
 */
public interface UserAccountRepositoryCustom {

	List<UserAccount> findUserAccountsBySearchCriteria(final UserAccountSearchCriteria userAccountSearchCriteria);

}
