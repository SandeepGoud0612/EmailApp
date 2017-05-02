package com.bluespacetech.security.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.security.constants.GrantConstant;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.dao.UserDAO;

@RestController
@RequestMapping("/user")
public class UserController extends AbstractBaseController {


	@RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDAO> user() {
		final UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
				.getPrincipal();
		final UserDAO user = new UserDAO();
		user.setLoggedInUserName(userDetails.getUsername());
		final Collection<String> roles = new ArrayList<String>();
		final Collection<String> uiRoles = new ArrayList<String>();
		final Map<String, String> uiGrantsByGrantsMap = GrantConstant.getUIGrantsByGrants();
		for (final GrantedAuthority grantedAuthority : userDetails.getAuthorities()) {

			if (UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.getAccountType().equals(grantedAuthority.getAuthority())) {
				user.setUserType(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.getAccountType());
			} else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType()
					.equals(grantedAuthority.getAuthority())) {
				user.setUserType(UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType());
			} else if (UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType()
					.equals(grantedAuthority.getAuthority())) {
				user.setUserType(UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType());
			} else if (UserAccountTypeConstant.ACC_TYPE_USER.getAccountType().toString()
					.equals(grantedAuthority.getAuthority())) {
				user.setUserType(UserAccountTypeConstant.ACC_TYPE_USER.getAccountType());
			}

			roles.add(grantedAuthority.getAuthority());
			if (uiGrantsByGrantsMap.get(grantedAuthority.getAuthority().toUpperCase()) != null) {
				uiRoles.add(uiGrantsByGrantsMap.get(grantedAuthority.getAuthority().toUpperCase()));
			}
		}
		// user.setRoles(roles);
		user.setUiRoles(uiRoles);
		return new ResponseEntity<UserDAO>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public ResponseEntity<Void> logoutPage(HttpServletRequest request, HttpServletResponse response) {
		final Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
