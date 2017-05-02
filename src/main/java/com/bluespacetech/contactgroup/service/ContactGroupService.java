/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 */
package com.bluespacetech.contactgroup.service;

import java.util.List;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.contactgroup.entity.ContactGroupPK;
import com.bluespacetech.core.exceptions.BusinessException;

/**
 * Class for ContactGroupService
 *
 * @author Sandeep created date 25-June-2015
 */
public interface ContactGroupService {

	ContactGroup getContactGroupById(final ContactGroupPK contactGroupPK);

	ContactGroup createContactGroup(final ContactGroup contactGroup) throws BusinessException;

	void deleteContactGroup(final ContactGroupPK contactGroupPK) throws BusinessException;

	List<ContactGroup> findAll();

	ContactGroup updateContactGroup(final ContactGroup contactGroup) throws BusinessException;

	ContactGroup unsubscribeContactGroup(final Long contactId, final Long groupId) throws BusinessException;

}
