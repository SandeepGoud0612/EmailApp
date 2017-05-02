package com.bluespacetech.core.exceptions;

/**
 * This exception class is used to handle application or system errors in the
 * application
 *
 * @author Pradeep
 *
 */
public class SystemException extends ApplicationException {

	private static final long serialVersionUID = -7459803588866142666L;

	public SystemException(final Throwable cause) {
		super(cause);
	}

	public SystemException(final String message) {
		super(message);
	}

}
