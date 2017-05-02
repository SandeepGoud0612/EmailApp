/**
 * This document is a part of the source code and related artifacts for
 * EmailService.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 */
package com.bluespacetech.notifications.email.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;

/**
 * @author pradeep created date 24-Aug-2016
 */
@Repository
public interface EmailServerPropertiesRepository extends JpaRepository<EmailServerProperties, Long> {

	List<EmailServerProperties> findEmailServerPropertiesByEmailServer(final EmailServer emailServer);

	List<EmailServerProperties> findByEmailServerIn(final List<EmailServer> emailServers);
}
