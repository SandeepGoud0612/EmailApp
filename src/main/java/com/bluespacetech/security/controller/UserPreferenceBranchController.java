package com.bluespacetech.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.security.model.UserPreference;
import com.bluespacetech.security.service.UserPreferenceService;

@RestController
@RequestMapping("/userPreferenceBranch")
public class UserPreferenceBranchController extends AbstractBaseController {

	@Autowired
	UserPreferenceService userPreferenceService;


	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserPreferenceBranch> getUserPreference() throws Exception {
		final UserPreference userPreference = userPreferenceService
				.getCurrentUserPreference();
		final UserPreferenceBranch userPreferenceBranch = new UserPreferenceBranch();
		// Set null to organization and address as it is not required to be send
		// back in address and it may cause infinite loop while converting into
		// JSON objects
		return new ResponseEntity<UserPreferenceBranch>(userPreferenceBranch, HttpStatus.OK);
	}


	class UserPreferenceBranch{

		Long branchId;
		String branchName;
		private boolean decreasePercentageInd;

		/**
		 * @return the branchId
		 */
		public Long getBranchId() {
			return branchId;
		}
		/**
		 * @param branchId the branchId to set
		 */
		public void setBranchId(final Long branchId) {
			this.branchId = branchId;
		}
		/**
		 * @return the branchName
		 */
		public String getBranchName() {
			return branchName;
		}
		/**
		 * @param branchName the branchName to set
		 */
		public void setBranchName(final String branchName) {
			this.branchName = branchName;
		}

		/**
		 * @return the decreasePercentageInd
		 */
		public boolean isDecreasePercentageInd() {
			return decreasePercentageInd;
		}
		/**
		 * @param decreasePercentageInd the decreasePercentageInd to set
		 */
		public void setDecreasePercentageInd(final boolean decreasePercentageInd) {
			this.decreasePercentageInd = decreasePercentageInd;
		}


	}



}
