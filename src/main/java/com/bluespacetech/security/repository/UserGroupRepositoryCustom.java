
package com.bluespacetech.security.repository;

import java.util.List;

import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.searchcriterias.UserGroupSearchCriteria;


/**
 * @author pradeep created date 30-Jan-2015
 */
public interface UserGroupRepositoryCustom {

	List<UserGroup> findUserGroupsBySearchCriteria(final UserGroupSearchCriteria sectionSearchCriteria);

}
