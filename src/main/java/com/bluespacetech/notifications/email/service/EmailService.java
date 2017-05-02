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
import com.bluespacetech.notifications.email.entity.Email;
import com.bluespacetech.notifications.email.valueobjects.EmailVO;

/**
 * class for EmailService
 *
 * @author pradeep created date 25-June-2015
 */
public interface EmailService {

	Email createEmail(final Email email) throws BusinessException;

	Email createEmail(final EmailVO emailVO) throws BusinessException;

	List<Email> findAll();

}
