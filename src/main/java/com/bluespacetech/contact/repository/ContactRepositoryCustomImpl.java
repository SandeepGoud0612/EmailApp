/**
 * This document is a part of the source code and related artifacts for
 * ContactService.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 */
package com.bluespacetech.contact.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.contact.searchcriteria.ContactSearchCriteria;

/**
 * @author sandeep created date 24-Aug-2016
 */
@Repository
public class ContactRepositoryCustomImpl implements ContactRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<Contact> findContactsBySearchCriteria(ContactSearchCriteria contactSearchCriteria) {
		StringBuilder queryString = new StringBuilder("select DISTINCT C from Contact C JOIN C.contactGroups CG ");
		boolean whereClasuseAdded = false;
		if (contactSearchCriteria.getFirstName() != null) {
			if (!whereClasuseAdded) {
				queryString = queryString.append(" where ");
			} else {
				queryString = queryString.append(" and ");
			}
			queryString = queryString.append(" C.firstName like :firstName ");
		}
		if (contactSearchCriteria.getLastName() != null) {
			if (!whereClasuseAdded) {
				queryString = queryString.append(" where ");
			} else {
				queryString = queryString.append(" and ");
			}
			queryString = queryString.append(" C.lastName like :lastName ");
		}
		if (contactSearchCriteria.getEmail() != null) {
			if (!whereClasuseAdded) {
				queryString = queryString.append(" where ");
			} else {
				queryString = queryString.append(" and ");
			}
			queryString = queryString.append(" C.email like :email ");
		}
		if (contactSearchCriteria.getGroupIds() != null && contactSearchCriteria.getGroupIds().size() > 0) {
			if (!whereClasuseAdded) {
				queryString = queryString.append(" where ");
			} else {
				queryString = queryString.append(" and ");
			}
			queryString = queryString.append(" CG.contactGroupPK.group.id in :groupIds ");
		}
		//int pageNumber = 1;
		//int pageSize = 100;
		TypedQuery<Contact> query = entityManager.createQuery(queryString.toString(), Contact.class);
		/*query.setFirstResult((pageNumber - 1) * pageSize);
		query.setMaxResults(pageSize);*/
		if (contactSearchCriteria.getFirstName() != null) {
			query.setParameter("firstName", "%" + contactSearchCriteria.getFirstName() + "%");
		}
		if (contactSearchCriteria.getLastName() != null) {
			query.setParameter("lastName", contactSearchCriteria.getLastName());
		}
		if (contactSearchCriteria.getEmail() != null) {
			query.setParameter("email", contactSearchCriteria.getEmail());
		}
		if (contactSearchCriteria.getGroupIds() != null && contactSearchCriteria.getGroupIds().size() > 0) {
			query.setParameter("groupIds", contactSearchCriteria.getGroupIds());
		}
		 List<Contact> contacts = query.getResultList();
		return contacts;
	}

}
