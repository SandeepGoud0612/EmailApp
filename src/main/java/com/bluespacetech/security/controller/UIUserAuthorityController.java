package com.bluespacetech.security.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.core.controller.AbstractBaseController;
import com.bluespacetech.core.utility.ViewUtil;
import com.bluespacetech.security.constants.AuthorityConstant;
import com.bluespacetech.security.constants.GrantConstant;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.resources.UIModuleRolesResource;

@RestController
@RequestMapping("/uiUserAuthorities")
public class UIUserAuthorityController extends AbstractBaseController {

	/**
	 * Retrieve All Financial Years.
	 *
	 * @return
	 */
	@RequestMapping(value = "/module", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UIModuleRolesResource> getUserUIUserAuthorities(@RequestBody final UIModuleRolesResource uiUserRole) {

		final List<String> uiUserRoles = new ArrayList<String>();

		if(ViewUtil.getAuthentication()!=null && uiUserRole!=null && uiUserRole.getModuleNames()!=null&& uiUserRole.getModuleNames().size()>0) {
			if(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.equals(ViewUtil.getUserAccountType())){
				for (final String moduleName : uiUserRole.getModuleNames()) {
					for(final GrantConstant grantConstant:AuthorityConstant.getGrantsForModule(moduleName)){
						uiUserRoles.add(grantConstant.getGrantUI());
					}
				}
			}else{
				final List<String> currentUserAuthorities = new ArrayList<String>();
				for(final GrantedAuthority grantedAuthority:ViewUtil.getAuthentication().getAuthorities()){
					currentUserAuthorities.add(grantedAuthority.getAuthority());
				}
				for (final String moduleName : uiUserRole.getModuleNames()) {
					for(final GrantConstant grantConstant:AuthorityConstant.getGrantsForModule(moduleName)){
						if(currentUserAuthorities.contains(grantConstant.toString())){
							uiUserRoles.add(grantConstant.getGrantUI());
						}
					}
				}
			}
		}else if(ViewUtil.getAuthentication()!=null && uiUserRole!=null && uiUserRole.getModuleName()!=null) {
			if(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.equals(ViewUtil.getUserAccountType())){
				for(final GrantConstant grantConstant:AuthorityConstant.getGrantsForModule(uiUserRole.getModuleName())){
					uiUserRoles.add(grantConstant.getGrantUI());
				}
			}else{
				final List<String> currentUserAuthorities = new ArrayList<String>();
				for(final GrantedAuthority grantedAuthority:ViewUtil.getAuthentication().getAuthorities()){
					currentUserAuthorities.add(grantedAuthority.getAuthority());
				}
				for(final GrantConstant grantConstant:AuthorityConstant.getGrantsForModule(uiUserRole.getModuleName())){
					if(currentUserAuthorities.contains(grantConstant.toString())){
						uiUserRoles.add(grantConstant.getGrantUI());
					}
				}
			}
		}
		final UIModuleRolesResource uiModuleRolesResource = new UIModuleRolesResource();
		uiModuleRolesResource.setUiUserRoles(uiUserRoles);
		uiModuleRolesResource.setModuleName(uiUserRole.getModuleName());
		return new ResponseEntity<UIModuleRolesResource>(
				uiModuleRolesResource, HttpStatus.OK);
	}

}
