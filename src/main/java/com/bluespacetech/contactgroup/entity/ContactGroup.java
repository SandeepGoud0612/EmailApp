package com.bluespacetech.contactgroup.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.bluespacetech.contact.entity.Contact;
import com.bluespacetech.group.entity.Group;

@Entity
@Table(name = "CONTACT_GROUP")
public class ContactGroup implements Serializable {

	private static final long serialVersionUID = 8104121333570891530L;

	@EmbeddedId
	private ContactGroupPK contactGroupPK = new ContactGroupPK();

	@Column(name = "ISACTIVE")
	private boolean isActive = true;

	@Column(name = "UNSUBSCRIBED")
	private boolean unSubscribed = false;
	
	public ContactGroupPK getContactGroupPK() {
		return contactGroupPK;
	}

	public void setContactGroupPK(ContactGroupPK contactGroupPK) {
		this.contactGroupPK = contactGroupPK;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public boolean isUnSubscribed() {
		return unSubscribed;
	}

	public void setUnSubscribed(boolean unSubscribed) {
		this.unSubscribed = unSubscribed;
	}

	@Transient
	public Contact getContact() {
		return this.getContactGroupPK().getContact();
	}

	public void setContact(Contact contact) {
		this.getContactGroupPK().setContact(contact);
	}

	@Transient
	public Group getGroup() {
		return this.getContactGroupPK().getGroup();
	}

	public void setGroup(Group group) {
		this.getContactGroupPK().setGroup(group);
	}
	
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		ContactGroup that = (ContactGroup) o;

		if (getContactGroupPK() != null ? !getContactGroupPK().equals(that.getContactGroupPK())
				: that.getContactGroupPK() != null)
			return false;

		return true;
	}

	public int hashCode() {
		return (getContactGroupPK() != null ? getContactGroupPK().hashCode() : 0);
	}

}
