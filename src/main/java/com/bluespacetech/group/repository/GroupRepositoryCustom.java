/**
 * This document is a part of the source code and related artifacts for bluespacetech. www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 */
package com.bluespacetech.group.repository;

import java.util.List;

import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.searchcriteria.GroupSearchCriteria;

/**
 * @author sandeep created date 24-Aug-2016
 */
public interface GroupRepositoryCustom {
	
	List<Group> findGroupsBySearchCriteria(GroupSearchCriteria groupSearchCriteria);

}
