package com.bluespacetech.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.security.model.UserPreference;
import com.bluespacetech.security.service.UserPreferenceService;

@RestController
@RequestMapping("/userPreference")
public class UserPreferenceController extends AbstractBaseController {

	@Autowired
	UserPreferenceService userPreferenceService;

	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserPreference> getUserPreference() throws Exception {
		final UserPreference userPreference = userPreferenceService
				.getCurrentUserPreference();
		userPreference.setUserAccount(null);
		// Set null to organization and address as it is not required to be send
		// back in address and it may cause infinite loop while converting into
		// JSON objects
		return new ResponseEntity<UserPreference>(userPreference, HttpStatus.OK);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserPreference> update(
			@RequestBody final UserPreference userPreference) throws Exception {
		final UserPreference result = userPreferenceService
				.saveUserPreference(userPreference);
		result.setUserAccount(null);
		return new ResponseEntity<UserPreference>(result, HttpStatus.OK);
	}

}
