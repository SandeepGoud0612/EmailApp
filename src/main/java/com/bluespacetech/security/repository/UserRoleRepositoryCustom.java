package com.bluespacetech.security.repository;

import java.util.List;

import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria;


/**
 * @author pradeep created date 30-Jan-2015
 */
public interface UserRoleRepositoryCustom {

	List<UserRole> findUserRolesBySearchCriteria(final UserRoleSearchCriteria sectionSearchCriteria);

}
