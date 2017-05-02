/**
 * This document is a part of the source code and related artifacts for
 * SMSystem.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.core.exceptions;

/**
 * This exception class is used to handle business cases validations in the
 * application
 *
 * @author Pradeep
 *
 */
public class BusinessException extends ApplicationException {

	private static final long serialVersionUID = 2156225803330994760L;

	public BusinessException(final Throwable cause) {
		super(cause);
		initCause(cause);
	}

	public BusinessException(final String message) {
		super(message);
	}
}
