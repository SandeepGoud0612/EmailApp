/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.group.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.searchcriteria.GroupSearchCriteria;

/**
 * @author sandeep created date 24-Aug-2016
 */
@Repository
public class GroupRepositoryCustomImpl implements GroupRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Group> findGroupsBySearchCriteria(GroupSearchCriteria groupSearchCriteria) {
		StringBuilder queryString = new StringBuilder("select G from Group G ");
		boolean whereClasuseAdded = false;
		if (groupSearchCriteria.getName() != null) {
			if (!whereClasuseAdded) {
				queryString = queryString.append(" where ");
			} else {
				queryString = queryString.append(" and ");
			}
			queryString = queryString.append(" G.name like :name ");
		}
		if (groupSearchCriteria.getComments() != null) {
			if (!whereClasuseAdded) {
				queryString = queryString.append(" where ");
			} else {
				queryString = queryString.append(" and ");
			}
			queryString = queryString.append(" G.comments like :comments ");
		}
		TypedQuery<Group> query = entityManager.createQuery(queryString.toString(), Group.class);
		if (groupSearchCriteria.getName() != null) {
			query.setParameter("name", "%" + groupSearchCriteria.getName() + "%");
		}
		if (groupSearchCriteria.getComments() != null) {
			query.setParameter("comments", groupSearchCriteria.getComments());
		}
		return query.getResultList();
	}

}
