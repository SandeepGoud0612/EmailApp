/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.notifications.email.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.bluespacetech.core.model.BaseEntity;
import com.bluespacetech.notifications.email.util.EmailServerPropertyValueTypeConstant;

/**
 * @author pradeep created date 13-Sept-2016
 */
@Entity
@Table(name = "EMAIL_SERVER_PROPERTIES")
public class EmailServerProperties extends BaseEntity implements Serializable {


	private static final long serialVersionUID = -8068866936838039346L;

	@NotEmpty(message = "Host is mandatory.")
	@Column(name = "NAME")
	private String propertyName;

	@NotEmpty(message = "Host is mandatory.")
	@Column(name = "VALUE")
	private String value;

	@NotNull(message = "Email server mandatory.")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "EMAIL_SERVER_ID", nullable = false)
	private EmailServer emailServer;

	@NotNull(message = "Email server property value type is mandatory.")
	@Basic
	@Column(name = "BRANCH_TYPE", length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private EmailServerPropertyValueTypeConstant emailServerPropertyValueTypeConstant;

	/**
	 * @return the propertyName
	 */
	public String getPropertyName() {
		return propertyName;
	}

	/**
	 * @param propertyName
	 *            the propertyName to set
	 */
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value
	 *            the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the emailServer
	 */
	public EmailServer getEmailServer() {
		return emailServer;
	}

	/**
	 * @param emailServer
	 *            the emailServer to set
	 */
	public void setEmailServer(EmailServer emailServer) {
		this.emailServer = emailServer;
	}

	/**
	 * @return the emailServerPropertyValueTypeConstant
	 */
	public EmailServerPropertyValueTypeConstant getEmailServerPropertyValueTypeConstant() {
		return emailServerPropertyValueTypeConstant;
	}

	/**
	 * @param emailServerPropertyValueTypeConstant
	 *            the emailServerPropertyValueTypeConstant to set
	 */
	public void setEmailServerPropertyValueTypeConstant(
			EmailServerPropertyValueTypeConstant emailServerPropertyValueTypeConstant) {
		this.emailServerPropertyValueTypeConstant = emailServerPropertyValueTypeConstant;
	}

}
