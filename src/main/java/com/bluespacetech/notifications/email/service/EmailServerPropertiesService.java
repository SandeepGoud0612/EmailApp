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
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;

/**
 * class for EmailServerService
 *
 * @author pradeep created date 25-June-2015
 */
public interface EmailServerPropertiesService {

	EmailServerProperties createEmailServerProperty(final EmailServerProperties emailServerProperties)
			throws BusinessException;

	void deleteEmailServerProperty(final Long emailServerPropertiesId) throws BusinessException;

	EmailServerProperties updateEmailServerProperty(final EmailServerProperties emailServerProperties)
			throws BusinessException;

	List<EmailServerProperties> findByEmailServer(final EmailServer emailServer);

	EmailServerProperties getEmailServerPropertiesById(final Long id) throws BusinessException;

	List<EmailServerProperties> findByEmailServers(final List<EmailServer> emailServers);

}
