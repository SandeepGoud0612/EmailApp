package com.bluespacetech.notifications.email.valueobjects;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.bluespacetech.notifications.email.entity.Email;

public class EmailVO implements Serializable{

	private static final long serialVersionUID = -3305258001034299815L;

	private List<String> toAddress = new ArrayList<String>();
	private List<String> ccAddress = new ArrayList<String>();
	private List<String> bccAddress = new ArrayList<String>();
	private String fromAddress;
	private String message;
	private String subject;
	private Email email;
	private Long groupId;
	private boolean personalizedEmail;
	private final List<Long> groupIdList = new ArrayList<Long>();

	/**
	 * @return the toAddress
	 */
	public List<String> getToAddress() {
		return toAddress;
	}
	/**
	 * @param toAddress the toAddress to set
	 */
	public void setToAddress(List<String> toAddress) {
		this.toAddress = toAddress;
	}
	/**
	 * @return the ccAddress
	 */
	public List<String> getCcAddress() {
		return ccAddress;
	}
	/**
	 * @param ccAddress the ccAddress to set
	 */
	public void setCcAddress(List<String> ccAddress) {
		this.ccAddress = ccAddress;
	}
	/**
	 * @return the bccAddress
	 */
	public List<String> getBccAddress() {
		return bccAddress;
	}
	/**
	 * @param bccAddress the bccAddress to set
	 */
	public void setBccAddress(List<String> bccAddress) {
		this.bccAddress = bccAddress;
	}
	/**
	 * @return the fromAddress
	 */
	public String getFromAddress() {
		return fromAddress;
	}
	/**
	 * @param fromAddress the fromAddress to set
	 */
	public void setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
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
	 * @param subject the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the email
	 */
	public Email getEmail() {
		return email;
	}

	/**
	 * @param email
	 *            the email to set
	 */
	public void setEmail(Email email) {
		this.email = email;
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
	 * @return the groupIdList
	 */
	public List<Long> getGroupIdList() {
		return groupIdList;
	}

	/**
	 * @return the personalizedEmail
	 */
	public boolean isPersonalizedEmail() {
		return personalizedEmail;
	}

	/**
	 * @param personalizedEmail
	 *            the personalizedEmail to set
	 */
	public void setPersonalizedEmail(boolean personalizedEmail) {
		this.personalizedEmail = personalizedEmail;
	}

}
