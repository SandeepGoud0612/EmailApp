/**
 * This document is a part of the source code and related artifacts for ContactService. www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 */
package com.bluespacetech.contactgroup.repository;

import com.bluespacetech.contactgroup.entity.ContactGroup;

/**
 * class for ContactGroupRepository
 *
 * @author sandeep created date 20-August-2015
 */
public interface ContactGroupRepositoryCustom {

	ContactGroup getContactGroupByContactIdAndGroupId(Long contactId, Long groupId);

}
