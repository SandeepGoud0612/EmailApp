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

import com.bluespacetech.contact.service.ContactService;
import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.exceptions.UserAccountDoesNotExistException;
import com.bluespacetech.security.model.UserAccount;
import com.bluespacetech.security.model.UserAccountUserGroup;
import com.bluespacetech.security.model.UserGroup;
import com.bluespacetech.security.resources.UserAccountChangePasswordResource;
import com.bluespacetech.security.resources.UserAccountResource;
import com.bluespacetech.security.resources.assembler.UserAccountResourceAssembler;
import com.bluespacetech.security.searchcriterias.UserAccountSearchCriteria;
import com.bluespacetech.security.service.BlueSpaceTechUserAccountService;
import com.bluespacetech.security.service.UserGroupService;

@RestController
@RequestMapping("/userAccounts")
public class UserAccountController extends AbstractBaseController {

	@Autowired
	BlueSpaceTechUserAccountService blueSpaceTechUserAccountService;

	@Autowired
	UserGroupService userGroupService;

	@Autowired
	ContactService contactService;

	/**
	 * Retrieve All Financial Years.
	 *
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserAccountResource>> getUserAccounts() {
		final List<UserAccount> userAccounts = blueSpaceTechUserAccountService
				.getAllUserAccounts();

		final List<UserAccountResource> userAccountResources = new UserAccountResourceAssembler()
				.toResources(userAccounts);
		return new ResponseEntity<List<UserAccountResource>>(
				userAccountResources, HttpStatus.OK);
	}

	/**
	 * Retrieve Financial year by Id.
	 *
	 * @param id
	 *            id of Financial year to be retrieved.
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserAccountResource> getUserAccountById(
			@PathVariable final Long id) {
		final UserAccount userAccount = blueSpaceTechUserAccountService
				.getUserAccountById(id);
		if (userAccount == null) {
			throw new UserAccountDoesNotExistException(
					"Supplied Financial year is invalid.");
		}

		final List<Long> userGroupIds = new ArrayList<Long>();

		for(final UserAccountUserGroup userAccountUserGroup : userAccount.getUserAccountUserGroups()){
			userGroupIds.add(userAccountUserGroup.getUserGroupId());
		}
		final List<UserGroup> userGroups = userGroupService.getUserGroupByIds(userGroupIds);
		final Map<Long,UserGroup> userGroupsMap = new HashMap<Long,UserGroup>();
		for (final UserGroup userGroup : userGroups) {
			userGroupsMap.put(userGroup.getId(), userGroup);
		}
		final UserAccountResource userAccountResource = new UserAccountResourceAssembler()
				.toCompleteResource(userAccount,userGroupsMap);
		return new ResponseEntity<UserAccountResource>(userAccountResource,
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
	public ResponseEntity<List<UserAccountResource>> getUserAccounts(@RequestBody final UserAccountSearchCriteria userAccountSearchCriteria) throws BusinessException {

		final List<UserAccount> userAccounts = blueSpaceTechUserAccountService
				.findUserAccountsBySearchCriteria(userAccountSearchCriteria);

		List<UserAccountResource> userAccountResources = new ArrayList<UserAccountResource>();
		if (userAccounts != null) {
			userAccountResources = new UserAccountResourceAssembler().toResources(userAccounts);
		}
		return new ResponseEntity<List<UserAccountResource>>(userAccountResources,
				HttpStatus.OK);
	}


	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(
			@RequestBody final UserAccountResource userAccountResource)
					throws BusinessException {

		final UserAccount userAccount = UserAccountResourceAssembler
				.getUserAccountFromResource(userAccountResource);
		userAccount.setUserAccountType(UserAccountTypeConstant.ACC_TYPE_USER);
		blueSpaceTechUserAccountService.createUserAccount(userAccount);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> update(@PathVariable final Long id,
			@RequestBody final UserAccountResource userAccountResource)
					throws BusinessException {

		// Get existing Financial Year
		final UserAccount currentUserAccount = blueSpaceTechUserAccountService
				.getUserAccountById(id);
		if (currentUserAccount == null) {
			throw new UserAccountDoesNotExistException(
					"Supplied Financial year is invalid.");
		}
		if (!currentUserAccount.getVersion().equals(
				userAccountResource.getVersion())) {
			throw new BusinessException("Stale Financial Year. Please update.");
		}
		// Extract the Financial Year from Resource
		final UserAccount sourceUserAccount = UserAccountResourceAssembler
				.getUserAccountFromResource(userAccountResource);

		// Copy changes from source to destination including version so that if
		// some one updated the branch then JPA will throw exception
		UserAccountResourceAssembler.copyUserAccountInto(
				sourceUserAccount, currentUserAccount);
		blueSpaceTechUserAccountService.updateUserAccount(currentUserAccount);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	@RequestMapping(value = "/changepassword", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> updatePassword(
			@RequestBody final UserAccountChangePasswordResource userAccountChangePasswordResource)
					throws BusinessException {
		blueSpaceTechUserAccountService.changePasswordUserAccount(userAccountChangePasswordResource.getOldPassword(),
				userAccountChangePasswordResource.getNewPassword(),
				userAccountChangePasswordResource.getConfirmPassword());
		return new ResponseEntity<Void>(HttpStatus.OK);
	}


	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Long id) {
		blueSpaceTechUserAccountService.deleteUserAccount(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ExceptionHandler(UserAccountDoesNotExistException.class)
	ResponseEntity<String> handleUserAccountNotFoundException(
			final Exception e) {
		return new ResponseEntity<String>(String.format("{\"reason\":\"%s\"}",
				e.getMessage()), HttpStatus.NOT_FOUND);
	}

}
