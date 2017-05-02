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

/**
 * class for EmailServerService
 *
 * @author pradeep created date 25-June-2015
 */
public interface EmailServerService {

	EmailServer createEmailServer(final EmailServer emailServer) throws BusinessException;

	void deleteEmailServer(final Long emailServerId) throws BusinessException;

	EmailServer updateEmailServer(final EmailServer emailServer) throws BusinessException;

	EmailServer findEmailServerByName(final String emailServerName) throws BusinessException;

	EmailServer getEmailServerById(final Long id) throws BusinessException;

	EmailServer getDefaultEmailServer() throws BusinessException;

	List<EmailServer> findAll();

}
