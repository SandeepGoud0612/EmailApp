/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.notifications.email.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.bluespacetech.core.model.BaseEntity;

/**
 * @author pradeep created date 13-Jul-2016
 */
@Entity
@Table(name = "EMAIL_CONTACT_GROUP")
public class EmailContactGroup extends BaseEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7245273784297025334L;

	@Column(name = "EMAIL_ID", nullable = false)
	private Long emailId;

	@Column(name = "CONTACT_ID", nullable = false)
	private Long contactId;

	@Column(name = "GROUP_ID", nullable = false)
	private Long groupId;

	@Column(name = "TEXT")
	private String message;

	@Column(name = "SUBJECT")
	private String subject;

	@Column(name = "RANDOM_NUMBER", nullable = false)
	private Long randomNumber;

	@Column(name = "READ_COUNT")
	private Integer readCount;

	/**
	 * @return the emailId
	 */
	public Long getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the contactId
	 */
	public Long getContactId() {
		return contactId;
	}

	/**
	 * @param contactId
	 *            the contactId to set
	 */
	public void setContactId(Long contactId) {
		this.contactId = contactId;
	}

	/**
	 * @return the groupId
	 */
	public Long getGroupId() {
		return groupId;
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the randomNumber
	 */
	public Long getRandomNumber() {
		return randomNumber;
	}

	/**
	 * @param randomNumber
	 *            the randomNumber to set
	 */
	public void setRandomNumber(Long randomNumber) {
		this.randomNumber = randomNumber;
	}

	/**
	 * @return the readCount
	 */
	public Integer getReadCount() {
		return readCount;
	}

	/**
	 * @param readCount
	 *            the readCount to set
	 */
	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

}
