/**
 * This document is a part of the source code and related artifacts for
 * SMSystem.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.core.exceptions;

public class ApplicationException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 3212309298381775252L;

	public ApplicationException(final String message) {
		super(message);
	}

	/**
	 * Constructs a new exception with the specified cause and a detail message.
	 *
	 * @param cause
	 *            the cause.
	 */
	public ApplicationException(final Throwable cause) {
		super(cause);
	}

}
