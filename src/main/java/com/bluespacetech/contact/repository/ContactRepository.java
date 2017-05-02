/**
 * This document is a part of the source code and related artifacts for
 * ContactService.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 */
package com.bluespacetech.contact.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.contact.entity.Contact;

/**
 * @author sandeep created date 24-Aug-2016
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

    List<Contact> findByFirstNameLikeOrLastNameLike(final String firstName,final String lastName);

    List<Contact> findByEmailLike(final String email);
}
