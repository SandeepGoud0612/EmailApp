package com.bluespacetech.group.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.core.model.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * @author pradeep created date 13-Jul-2016
 */
@Entity
@Table(name = "GROUPS")
public class Group extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -9115675653111826751L;

	@NotEmpty(message = "Name is mandatory.")
	@Column(name = "NAME")
	private String name;

	@Column(name = "COMMENTS")
	private String comments;

	@JsonIgnore
	@OneToMany(mappedBy = "contactGroupPK.group", cascade = CascadeType.ALL, orphanRemoval = true)
	private Collection<ContactGroup> contactGroups = new ArrayList<>();

	@Transient
	private Integer contactCount;

	public Integer getContactCount() {
		return contactCount;
	}

	public void setContactCount(Integer contactCount) {
		this.contactCount = contactCount;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Collection<ContactGroup> getContactGroups() {
		return contactGroups;
	}

	public void setContactGroups(Collection<ContactGroup> contactGroups) {
		this.contactGroups = contactGroups;
	}

	public String getName() {
		return name;
	}

	public void setName(final String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Group [name=" + name + ", Comments=" + comments + "]";
	}
}
