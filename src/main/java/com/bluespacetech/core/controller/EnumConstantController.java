package com.bluespacetech.core.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bluespacetech.security.constants.AuthorityConstant;
import com.bluespacetech.security.constants.UserAccountTypeConstant;
import com.bluespacetech.security.resources.AuthoritiesByModuleResource;
import com.bluespacetech.security.resources.AuthorityResource;

@RestController
@RequestMapping("/enumConstants")
public class EnumConstantController extends AbstractBaseController {



	@RequestMapping(value = "/authorities", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AuthorityConstant[] getAuthorities() {
		return AuthorityConstant.values();
	}

	@RequestMapping(value = "/authorityConstants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody AuthoritiesByModuleResource[] getAuthorityConstants() {

		final Map<String,List<AuthorityResource>> authorityConstantMap = new HashMap<String,List<AuthorityResource>>();
		for(final AuthorityConstant authorityConstant:AuthorityConstant.values()){
			if(authorityConstantMap.get(authorityConstant.getModuleName())==null){
				final List<AuthorityResource> authorityConstants = new ArrayList<AuthorityResource>();
				authorityConstants.add(AuthorityResource.getAuthorityResource(authorityConstant));
				authorityConstantMap.put(authorityConstant.getModuleName(), authorityConstants);
			}else{
				authorityConstantMap.get(authorityConstant.getModuleName()).add(AuthorityResource.getAuthorityResource(authorityConstant));
			}
		}

		final AuthoritiesByModuleResource[] authoritiesByModuleResources = new AuthoritiesByModuleResource[authorityConstantMap.keySet().size()];
		int i=0;
		for(final Map.Entry<String,List<AuthorityResource>> entry:  authorityConstantMap.entrySet()){
			final AuthoritiesByModuleResource authoritiesByModuleResource = new AuthoritiesByModuleResource();
			authoritiesByModuleResource.setModuleName(entry.getKey());
			authoritiesByModuleResource.setAuthorityResources(entry.getValue());
			authoritiesByModuleResources[i]= authoritiesByModuleResource ;
			i++;
		}
		return authoritiesByModuleResources;
	}


	@RequestMapping(value = "/userAccountTypeConstants", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody UserAccountTypeConstant[] getUserAccountTypeConstants() {
		return UserAccountTypeConstant.getUserAccountTypesForNewUser();
	}


}
