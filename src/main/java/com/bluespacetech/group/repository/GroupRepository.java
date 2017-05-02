/**
 * This document is a part of the source code and related artifacts for
 * GroupService.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.group.entity.Group;

/**
 * class for GroupRepository
 *
 * @author pradeep created date 25-June-2015
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    List<Group> findByNameLike(final String name);
    
}
