
/**
 * This document is a part of the source code and related artifacts for Emilent.
 * www.bluespacetech.com Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.core.model;


/**
 * @author pradeep created date 25-June-2015
 *
 */
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.Version;

import com.bluespacetech.core.exceptions.InvalidArgumentException;
import com.bluespacetech.core.utility.ViewUtil;

@MappedSuperclass
public abstract class BaseEntity implements Serializable {

	/**
	 * Universal serial version id for this class.
	 */
	private static final long serialVersionUID = -246686943403665670L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Version
	@Column(name = "VERSION", nullable = false)
	private Long version;

	@Column(name = "LAST_UPDATE_DATE", nullable = false)
	private Timestamp lastUpdatedDate;

	@Column(name = "LAST_UPDATED_USER", nullable = false)
	private String lastUpdatedUser;

	@Column(name = "CREATION_DATE", nullable = false)
	private Timestamp creationDate;

	@Column(name = "CREATED_USER", nullable = false)
	private String createdUser;


	transient private Long resourceObjectId;

	/**
	 * Creates a clone of the given date. Returns null when the given date is
	 * null.
	 *
	 * @param date
	 *            Date The date for which to create a clone.
	 * @return Date The clone of the given date. Null when the given date is
	 *         null.
	 */
	public Date cloneDate(final Date date) {
		return date != null ? (Date) date.clone() : null;
	}

	/**
	 * Creates a clone of the given time stamp. Returns null when the given time
	 * stamp is null.
	 *
	 * @param timestamp
	 *            Time stamp the time stamp for which to create a clone.
	 * @return Time stamp The clone of the given time stamp. Null when the given
	 *         time stamp is null.
	 */
	public Timestamp cloneTimestamp(final Timestamp timestamp) {
		return timestamp != null ? (Timestamp) timestamp.clone() : null;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		final BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the createdUser
	 */
	public String getCreatedUser() {
		return createdUser;
	}

	/**
	 * @return the creationDate
	 */
	public Timestamp getCreationDate() {
		return creationDate;
	}

	public Long getId() {
		return id;
	}

	/**
	 * @return the lastUpdatedDate
	 */
	public Timestamp getLastUpdatedDate() {
		return lastUpdatedDate;
	}

	/**
	 * @return the lastUpdatedUser
	 */
	public String getLastUpdatedUser() {
		return lastUpdatedUser;
	}

	/**
	 * @return the resourceObjectId
	 */
	public Long getResourceObjectId() {
		return resourceObjectId;
	}

	public Long getVersion() {
		return version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (id == null ? 0 : id.hashCode());
		return result;
	}

	@PrePersist
	public void prePersist() {

		// Created time stamp
		final java.util.Date date = new java.util.Date();
		if (creationDate == null) {
			creationDate = new Timestamp(date.getTime());
		}

		if (createdUser == null) {
			// Created user name;
			createdUser = ViewUtil.getPrincipal();
		}

		// last updated time stamp
		lastUpdatedDate = new Timestamp(date.getTime());

		if (lastUpdatedUser == null) {
			// last updated user name;
			lastUpdatedUser = ViewUtil.getPrincipal();
		}
	}

	/**
	 * @param createdUser
	 *            the createdUser to set
	 */
	public void setCreatedUser(final String createdUser) {
		this.createdUser = createdUser;
	}

	/**
	 * @param creationDate
	 *            the creationDate to set
	 */
	public void setCreationDate(final Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * @param lastUpdatedDate
	 *            the lastUpdatedDate to set
	 */
	public void setLastUpdatedDate(final Timestamp lastUpdatedDate) {
		this.lastUpdatedDate = lastUpdatedDate;
	}

	/**
	 * @param lastUpdatedUser
	 *            the lastUpdatedUser to set
	 */
	public void setLastUpdatedUser(final String lastUpdatedUser) {
		this.lastUpdatedUser = lastUpdatedUser;
	}

	/**
	 * @param resourceObjectId the resourceObjectId to set
	 */
	public void setResourceObjectId(final Long resourceObjectId) {
		this.resourceObjectId = resourceObjectId;
	}

	public void validate() throws InvalidArgumentException {

	}

}
