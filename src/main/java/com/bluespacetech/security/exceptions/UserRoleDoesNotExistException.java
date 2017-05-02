/**
 * This document is a part of the source code and related artifacts for Emilent.
 * www.brilapps.com Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.exceptions;

/**
 * @author pradeep created date 28-Jan-2015
 */
public class UserRoleDoesNotExistException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6139375536512870147L;

	public UserRoleDoesNotExistException(final Throwable cause) {
		super(cause);
	}

	public UserRoleDoesNotExistException(final String message,
			final Throwable cause) {
		super(message, cause);
	}

	public UserRoleDoesNotExistException(final String message) {
		super(message);
	}

	public UserRoleDoesNotExistException() {
	}
}
