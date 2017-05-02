/**
 * This document is a part of the source code and related artifacts for
 * bluespacetech.
 * www.bluespacetech.com
 * Copyright © 2015 bluespacetech
 *
 */
package com.bluespacetech.group.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.group.entity.Group;
import com.bluespacetech.group.searchcriteria.GroupSearchCriteria;
import com.bluespacetech.group.service.GroupService;

/**
 * @author pradeep created date 30-Jan-2015
 */
@RestController
@RequestMapping("/groups")
@CrossOrigin
public class GroupController {

	@Autowired
	private GroupService groupService;

	/**
	 * Retrieve All Financial Years.
	 *
	 * @return
	 */
	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Group>> getGroups() {
		final List<Group> groups = groupService.findAll();
		//groups.parallelStream().forEach(group -> group.setContactCount(group.getContactGroups().size()));
		return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
	}

	/**
	 * Retrieve All Financial Years.
	 *
	 * @return
	 */
	@RequestMapping(value = "/searchCriteria", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Group>> getGroupsBySearchCriteria(@RequestBody GroupSearchCriteria groupSearchCriteria) {
		if (groupSearchCriteria.getName() != null) {
			groupSearchCriteria.setName(groupSearchCriteria.getName().trim());
			if (groupSearchCriteria.getName().trim().equals("")) {
				groupSearchCriteria.setName(null);
			}
		}
		if (groupSearchCriteria.getComments() != null) {
			groupSearchCriteria.setComments(groupSearchCriteria.getComments().trim());
			if (groupSearchCriteria.getComments().trim().equals("")) {
				groupSearchCriteria.setComments(null);
			}
		}
		final List<Group> groups = groupService.findBySearchCriteria(groupSearchCriteria);
		return new ResponseEntity<List<Group>>(groups, HttpStatus.OK);
	}

	/**
	 * Retrieve Financial year by Id.
	 *
	 * @param id
	 *            id of Financial year to be retrieved.
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Group> getGroupById(@PathVariable final Long id) throws BusinessException {
		Group group = groupService.getGroupById(id);
		if (group == null) {
			throw new BusinessException("Supplied Group ID is invalid.");
		}else{
			group.setContactCount(group.getContactGroups().size());
		}
		return new ResponseEntity<Group>(group, HttpStatus.OK);

	}

	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> create(@RequestBody final Group group) throws BusinessException {
		groupService.createGroup(group);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Group> update(@PathVariable final Long id, @RequestBody final Group group) throws BusinessException {

		// Get existing Financial Year
		final Group currentGroup = groupService.getGroupById(id);
		if (currentGroup == null) {
			throw new BusinessException("Supplied Group does not exist.");
		}
		if (!currentGroup.getVersion().equals(group.getVersion())) {
			throw new BusinessException("Stale Group. Please update.");
		}

		Group groupUpdated = groupService.updateGroup(group);
		return new ResponseEntity<Group>(groupUpdated, HttpStatus.OK);
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable final Long id) throws BusinessException {
		groupService.deleteGroup(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@ExceptionHandler(BusinessException.class)
	ResponseEntity<String> handleGroupNotFoundException(final Exception e) {
		return new ResponseEntity<String>(String.format("{\"reason\":\"%s\"}", e.getMessage()), HttpStatus.NOT_FOUND);
	}
}
