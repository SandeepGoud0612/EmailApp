package com.bluespacetech.security.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.bluespacetech.security.exceptions.UserRoleDoesNotExistException;
import com.bluespacetech.security.model.UserRole;
import com.bluespacetech.security.resources.UserRoleResource;
import com.bluespacetech.security.resources.assembler.UserRoleResourceAssembler;
import com.bluespacetech.security.searchcriterias.UserRoleSearchCriteria;
import com.bluespacetech.security.service.UserRoleService;

@RestController
@RequestMapping("/userRoles")
public class UserRoleController extends AbstractBaseController {

	@Autowired
	UserRoleService userRoleService;

	/**
	 * Retrieve All Financial Years.
	 *
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserRoleResource>> getUserRoles() {
		final List<UserRole> userRoles = userRoleService
				.getAllUserRoles();

		final List<UserRoleResource> userRoleResources = new UserRoleResourceAssembler()
				.toResources(userRoles);
		return new ResponseEntity<List<UserRoleResource>>(
				userRoleResources, HttpStatus.OK);
	}

	/**
	 * Retrieve Financial year by Id.
	 *
	 * @param id
	 *            id of Financial year to be retrieved.
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserRoleResource> getUserRoleById(
			@PathVariable final Long id) {
		final UserRole userRole = userRoleService
				.getUserRoleById(id);
		if (userRole == null) {
			throw new UserRoleDoesNotExistException(
					"Supplied Financial year is invalid.");
		}
		final UserRoleResource userRoleResource = new UserRoleResourceAssembler()
				.toCompleteResource(userRole);
		return new ResponseEntity<UserRoleResource>(userRoleResource,
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
	public ResponseEntity<List<UserRoleResource>> getUserRoles(@RequestBody final UserRoleSearchCriteria userRoleSearchCriteria) throws BusinessException {

		final List<UserRole> userRoles =  userRoleService.findUserRolesBySearchCriteria(userRoleSearchCriteria);

		List<UserRoleResource> userRoleResources = new ArrayList<UserRoleResource>();
		if (userRoleResources != null) {
			userRoleResources = new UserRoleResourceAssembler().toResources(userRoles);
		}
		return new ResponseEntity<List<UserRoleResource>>(userRoleResources,
				HttpStatus.OK);
	}


	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(
			@RequestBody final UserRoleResource userRoleResource)
					throws BusinessException {

		final UserRole userRole = UserRoleResourceAssembler
				.getUserRoleFromResource(userRoleResource);

		final UserRole result = userRoleService.createUserRole(userRole);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@PathVariable final Long id,
			@RequestBody final UserRoleResource userRoleResource)
					throws BusinessException {

		// Get existing Financial Year
		final UserRole currentUserRole = userRoleService
				.getUserRoleById(id);
		if (currentUserRole == null) {
			throw new UserRoleDoesNotExistException(
					"Supplied User Role is invalid.");
		}
		if (!currentUserRole.getVersion().equals(
				userRoleResource.getVersion())) {
			throw new BusinessException("Stale User Role. Please update.");
		}
		// Extract the Financial Year from Resource
		final UserRole sourceUserRole = UserRoleResourceAssembler
				.getUserRoleFromResource(userRoleResource);

		// Copy changes from source to destination including version so that if
		// some one updated the branch then JPA will throw exception
		UserRoleResourceAssembler.copyUserRoleInto(
				sourceUserRole, currentUserRole);
		userRoleService.updateUserRole(currentUserRole);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Long id) {
		userRoleService.deleteUserRole(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ExceptionHandler(UserRoleDoesNotExistException.class)
	ResponseEntity<String> handleUserRoleNotFoundException(
			final Exception e) {
		return new ResponseEntity<String>(String.format("{\"reason\":\"%s\"}",
				e.getMessage()), HttpStatus.NOT_FOUND);
	}

}
