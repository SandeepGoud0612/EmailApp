/**
 * This document is a part of the source code and related artifacts for Emilent.
 * www.brilapps.com Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.exceptions;

/**
 * @author pradeep created date 28-Jan-2015
 */
public class UserAccountDoesNotExistException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = -6139375536512870147L;

	public UserAccountDoesNotExistException(final Throwable cause) {
		super(cause);
	}

	public UserAccountDoesNotExistException(final String message,
			final Throwable cause) {
		super(message, cause);
	}

	public UserAccountDoesNotExistException(final String message) {
		super(message);
	}

	public UserAccountDoesNotExistException() {
	}
}
