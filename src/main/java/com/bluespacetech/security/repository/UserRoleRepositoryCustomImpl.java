package com.bluespacetech.security.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria;


/**
 * @author pradeep created date 30-Jan-2015
 */
@Repository
public class UserRoleRepositoryCustomImpl implements UserRoleRepositoryCustom{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<UserRole> findUserRolesBySearchCriteria(final UserRoleSearchCriteria userRoleSearchCriteria){
		StringBuilder queryString = new StringBuilder("select userRole from UserRole userRole ");
		boolean whereClasuseAdded = false;

		if (userRoleSearchCriteria.getRoleName() != null) {
			if (!whereClasuseAdded) {
				queryString = queryString.append(" where ");
			} else {
				queryString = queryString.append(" and ");
			}
			queryString = queryString.append(" userRole.roleName like '%" + userRoleSearchCriteria.getRoleName() + "%'");
			whereClasuseAdded = true;
		}

		if (userRoleSearchCriteria.getDescription() != null) {
			if (!whereClasuseAdded) {
				queryString = queryString.append(" where ");
			} else {
				queryString = queryString.append(" and ");
			}
			queryString = queryString.append(" upper(userRole.description) like '%" + userRoleSearchCriteria.getDescription().toUpperCase() + "%'");
			whereClasuseAdded = true;
		}

		final TypedQuery<UserRole> query = entityManager.createQuery(queryString.toString(), UserRole.class);

		return query.getResultList();
	}

}
