/**
 * This document is a part of the source code and related artifacts for
 * EmailService.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 */
package com.bluespacetech.notifications.email.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.notifications.email.entity.EmailContactGroup;

/**
 * @author pradeep created date 24-Aug-2016
 */
@Repository
public interface EmailContactGroupRepository extends JpaRepository<EmailContactGroup, Long> {
	EmailContactGroup findByContactIdAndGroupIdAndRandomNumber(final Long contactId, final Long groupId,
			final Long randomNumber);
}
