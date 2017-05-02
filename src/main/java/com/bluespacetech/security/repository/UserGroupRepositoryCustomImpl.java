package com.bluespacetech.security.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.searchcriterias.UserGroupSearchCriteria;


/**
 * @author pradeep created date 30-Jan-2015
 */
@Repository
public class UserGroupRepositoryCustomImpl implements UserGroupRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserGroup> findUserGroupsBySearchCriteria(final UserGroupSearchCriteria userGroupSearchCriteria){
		StringBuilder queryString = new StringBuilder("select userGroup from UserGroup userGroup ");
		boolean whereClasuseAdded = false;

		if (userGroupSearchCriteria.getGroupName() != null) {
			if (!whereClasuseAdded) {
				queryString = queryString.append(" where ");
			} else {
				queryString = queryString.append(" and ");
			}
			queryString = queryString.append(" userGroup.groupName like '%" + userGroupSearchCriteria.getGroupName() + "%'");
			whereClasuseAdded = true;
		}

		if (userGroupSearchCriteria.getDescription() != null) {
			if (!whereClasuseAdded) {
				queryString = queryString.append(" where ");
			} else {
				queryString = queryString.append(" and ");
			}
			queryString = queryString.append(" upper(userGroup.description) like '%" + userGroupSearchCriteria.getDescription().toUpperCase() + "%'");
			whereClasuseAdded = true;
		}

		final TypedQuery<UserGroup> query = entityManager.createQuery(queryString.toString(), UserGroup.class);

		return query.getResultList();
	}

}
