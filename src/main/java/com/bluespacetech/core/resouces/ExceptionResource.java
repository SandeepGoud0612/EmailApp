/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 *
 */
package com.bluespacetech.core.resouces;


/**
 * @author pradeep created date 30-Jan-2015
 */
public class ExceptionResource  {

    private String exceptiontype;

    private String message;

    /**
     * @return the exceptiontype
     */
    public String getExceptiontype() {
	return exceptiontype;
    }

    /**
     * @return the message
     */
    public String getMessage() {
	return message;
    }

    /**
     * @param exceptiontype the exceptiontype to set
     */
    public void setExceptiontype(final String exceptiontype) {
	this.exceptiontype = exceptiontype;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(final String message) {
	this.message = message;
    }



}
