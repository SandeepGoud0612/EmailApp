/**
 * This document is a part of the source code and related artifacts for
 * SMSystem.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.core.exceptions;

/**
 * Exception for invalid argument.
 *
 * @author Pradeep
 *
 */
public class InvalidArgumentException extends ApplicationException {

	/**
	 *
	 */
	private static final long serialVersionUID = -3057500960082633455L;

	/**
	 * Constructs an exception using the supplied message.
	 *
	 * @param message
	 *            message with additional information about why the argument is
	 *            invalid.
	 */
	public InvalidArgumentException(final String message) {
		super(message);
	}

}
