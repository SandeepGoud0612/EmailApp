/**
 * This document is a part of the source code and related artifacts for ContactService. www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 */
package com.bluespacetech.contactgroup.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.entity.ContactGroupPK;

/**
 * class for ContactGroupRepository
 *
 * @author sandeep created date 20-August-2015
 */
@Repository
public interface ContactGroupRepository extends JpaRepository<ContactGroup, ContactGroupPK> {

}
