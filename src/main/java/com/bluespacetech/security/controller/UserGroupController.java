package com.bluespacetech.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.exceptions.UserGroupDoesNotExistException;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.model.UserGroupUserRole;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.resources.UserGroupResource;
import com.bluespacetech.security.resources.assembler.UserGroupResourceAssembler;
import com.bluespacetech.security.searchcriterias.UserGroupSearchCriteria;
import com.bluespacetech.security.service.UserGroupService;
import com.bluespacetech.security.service.UserRoleService;

@RestController
@RequestMapping("/userGroups")
public class UserGroupController extends AbstractBaseController {

	@Autowired
	UserGroupService userGroupService;

	@Autowired
	UserRoleService userRoleService;

	/**
	 * Retrieve All Financial Years.
	 *
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserGroupResource>> getUserGroups() {
		final List<UserGroup> userGroups = userGroupService
				.getAllUserGroups();

		final List<UserGroupResource> userGroupResources = new UserGroupResourceAssembler()
				.toResources(userGroups);
		return new ResponseEntity<List<UserGroupResource>>(
				userGroupResources, HttpStatus.OK);
	}

	/**
	 * Retrieve Financial year by Id.
	 *
	 * @param id
	 *            id of Financial year to be retrieved.
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserGroupResource> getUserGroupById(
			@PathVariable final Long id) {
		final UserGroup userGroup = userGroupService
				.getUserGroupById(id);
		if (userGroup == null) {
			throw new UserGroupDoesNotExistException(
					"Supplied Financial year is invalid.");
		}

		final List<Long> userRoleIds = new ArrayList<Long>();

		for(final UserGroupUserRole userGroupUserRole : userGroup.getUserGroupUserRoles()){
			userRoleIds.add(userGroupUserRole.getUserRoleId());
		}
		final List<UserRole> userRoles = userRoleService.getUserRoleByIds(userRoleIds);
		final Map<Long,UserRole> userRolesMap = new HashMap<Long,UserRole>();
		for (final UserRole userRole : userRoles) {
			userRolesMap.put(userRole.getId(), userRole);
		}
		final UserGroupResource userGroupResource = new UserGroupResourceAssembler()
				.toCompleteResource(userGroup,userRolesMap);
		return new ResponseEntity<UserGroupResource>(userGroupResource,
				HttpStatus.OK);

	}

	/**
	 * Retrieve Financial year by Id.
	 *
	 * @param id
	 *            id of Financial year to be retrieved.
	 * @return
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserGroupResource>> getUserGroups(@RequestBody final UserGroupSearchCriteria userGroupSearchCriteria) throws BusinessException {

		final List<UserGroup> userGroups =  userGroupService.findUserGroupsBySearchCriteria(userGroupSearchCriteria);

		List<UserGroupResource> userGroupResources = new ArrayList<UserGroupResource>();
		if (userGroupResources != null) {
			userGroupResources = new UserGroupResourceAssembler().toResources(userGroups);
		}
		return new ResponseEntity<List<UserGroupResource>>(userGroupResources,
				HttpStatus.OK);
	}


	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(
			@RequestBody final UserGroupResource userGroupResource)
					throws BusinessException {

		final UserGroup userGroup = UserGroupResourceAssembler
				.getUserGroupFromResource(userGroupResource);

		userGroupService.createUserGroup(userGroup);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@PathVariable final Long id,
			@RequestBody final UserGroupResource userGroupResource)
					throws BusinessException {

		// Get existing Financial Year
		final UserGroup currentUserGroup = userGroupService
				.getUserGroupById(id);
		if (currentUserGroup == null) {
			throw new UserGroupDoesNotExistException(
					"Supplied Financial year is invalid.");
		}
		if (!currentUserGroup.getVersion().equals(
				userGroupResource.getVersion())) {
			throw new BusinessException("Stale Financial Year. Please update.");
		}
		// Extract the Financial Year from Resource
		final UserGroup sourceUserGroup = UserGroupResourceAssembler
				.getUserGroupFromResource(userGroupResource);

		// Copy changes from source to destination including version so that if
		// some one updated the branch then JPA will throw exception
		UserGroupResourceAssembler.copyUserGroupInto(
				sourceUserGroup, currentUserGroup);
		userGroupService.updateUserGroup(currentUserGroup);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Long id) {
		userGroupService.deleteUserGroup(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ExceptionHandler(UserGroupDoesNotExistException.class)
	ResponseEntity<String> handleUserGroupNotFoundException(
			final Exception e) {
		return new ResponseEntity<String>(String.format("{\"reason\":\"%s\"}",
				e.getMessage()), HttpStatus.NOT_FOUND);
	}

}
