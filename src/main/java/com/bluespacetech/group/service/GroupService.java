/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2016 bluespacetech
 *
 */
package com.bluespacetech.group.service;

import java.util.List;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.searchcriteria.GroupSearchCriteria;

/**
 * class for GroupService
 *
 * @author pradeep created date 25-June-2015
 */
public interface GroupService {

	Group createGroup(final Group group) throws BusinessException;

	void deleteGroup(final Long groupId) throws BusinessException;

	List<Group> findByName(final String name);

	List<Group> findAll();

	Group getGroupById(final Long groupId);

	Group updateGroup(final Group group) throws BusinessException;
	
	List<Group> findBySearchCriteria(final GroupSearchCriteria groupSearchCriteria);
}
