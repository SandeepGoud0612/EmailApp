package com.bluespacetech.notifications.email.util;

import java.io.Serializable;

import javax.mail.internet.MimeMessage;

import com.bluespacetech.notifications.email.entity.EmailContactGroup;

public class ContactGroupMailMessage implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6064992838049760633L;

	private EmailContactGroup emailContactGroup;

	private MimeMessage mimeMessage;

	/**
	 * @return the mimeMessage
	 */
	public MimeMessage getMimeMessage() {
		return mimeMessage;
	}

	/**
	 * @param mimeMessage
	 *            the mimeMessage to set
	 */
	public void setMimeMessage(MimeMessage mimeMessage) {
		this.mimeMessage = mimeMessage;
	}

	/**
	 * @return the emailContactGroup
	 */
	public EmailContactGroup getEmailContactGroup() {
		return emailContactGroup;
	}

	/**
	 * @param emailContactGroup
	 *            the emailContactGroup to set
	 */
	public void setEmailContactGroup(EmailContactGroup emailContactGroup) {
		this.emailContactGroup = emailContactGroup;
	}



}
