/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.notifications.email.service;

import java.util.List;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;

/**
 * class for EmailContactGroupService
 *
 * @author pradeep created date 25-June-2015
 */
public interface EmailContactGroupService {

	EmailContactGroup createEmailContactGroup(final EmailContactGroup emailContactGroup) throws BusinessException;

	List<EmailContactGroup> createEmailContactGroups(final List<EmailContactGroup> emailContactGroups)
			throws BusinessException;

	List<EmailContactGroup> findAll();

	EmailContactGroup findByContactIdAndGroupIdAndRandomNumber(final Long contactId, final Long groupId,
			final Long randomNumber);

	EmailContactGroup updateEmailContactGroup(final EmailContactGroup emailContactGroup) throws BusinessException;

}
