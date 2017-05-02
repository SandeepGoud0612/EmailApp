/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.notifications.email.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.core.exceptions.ApplicationException;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.repository.EmailServerRepository;

/**
 * class for EmailServerService
 *
 * @author pradeep created date 25-June-2015
 */
@Service
@Transactional(rollbackFor = { Exception.class, RuntimeException.class, BusinessException.class,
		ApplicationException.class })
@PreAuthorize("hasAuthority('EXCLUDE_ALL')")
public class EmailServerServiceImpl implements EmailServerService {

	@Autowired
	private EmailServerRepository emailServerRepository;

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('CREATE_SERVERS'))")
	public EmailServer createEmailServer(final EmailServer emailServer) throws BusinessException {
		validateEmailServer(emailServer);
		final EmailServer newEmailServer = emailServerRepository.save(emailServer);
		return newEmailServer;
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS'))")
	public List<EmailServer> findAll() {
		return emailServerRepository.findAll();
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('DELETE_SERVERS'))")
	public void deleteEmailServer(Long emailServerId) throws BusinessException {
		emailServerRepository.delete(emailServerId);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('UPDATE_SERVERS'))")
	public EmailServer updateEmailServer(EmailServer emailServer) throws BusinessException {
		validateEmailServer(emailServer);
		final EmailServer newEmailServer = emailServerRepository.save(emailServer);
		return newEmailServer;
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS'))")
	public EmailServer findEmailServerByName(String emailServerName) throws BusinessException {
		return emailServerRepository.findByName(emailServerName);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS'))")
	public EmailServer getEmailServerById(Long id) throws BusinessException {
		return emailServerRepository.findOne(id);
	}

	@Override
	@PreAuthorize("hasAuthority('ACC_TYPE_SUPER_ADMIN') or (hasAuthority('ACCESS_SERVERS'))")
	public EmailServer getDefaultEmailServer() throws BusinessException {
		final List<EmailServer> emailServers = emailServerRepository.findByDefaultServer(true);
		return emailServers != null && emailServers.size() > 0 ? emailServers.get(0) : null;
	}

	private void validateEmailServer(EmailServer emailServer) throws BusinessException {
		if (emailServer.getDefaultServer() != null && emailServer.getDefaultServer() != null) {
			if (getDefaultEmailServer() != null) {
				throw new BusinessException("Default server already exists.");
			}
		}
	}

}
