package com.bluespacetech.security.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bluespacetech.security.model.UserGroup;

@Repository
public interface UserGroupRepository extends JpaRepository<UserGroup, Long> {

	UserGroup findUserGroupByGroupName(final String groupName);

	List<UserGroup> findByDescriptionLike(final String description);

	List<UserGroup> findByGroupNameLike(final String groupName);
}
