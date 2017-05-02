package com.bluespacetech.security.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bluespacetech.security.constants.GrantConstant;
import com.bluespacetech.security.constants.PageLinkConstant;
import com.bluespacetech.security.constants.UserAccountTypeConstant;

@Service
@Transactional
public class PageLinksServiceImpl implements PageLinksService  {

	@Override
	@Transactional
	public Set<PageLinkConstant> getPageLinksAllowedForUser() {

		final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		final Set<String> authorities = new HashSet<String>();
		UserAccountTypeConstant userAccountType = UserAccountTypeConstant.ACC_TYPE_USER;
		if(authentication!= null && authentication.getAuthorities()!=null){
			for(final GrantedAuthority grantedAuthority:authentication.getAuthorities()){
				authorities.add(grantedAuthority.getAuthority());
				if (UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN.getAccountType()
						.equals(grantedAuthority.getAuthority())) {
					userAccountType = UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN;
				} else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType()
						.equals(grantedAuthority.getAuthority())) {
					userAccountType = UserAccountTypeConstant.ACC_TYPE_ADMIN;
				} else if (UserAccountTypeConstant.ACC_TYPE_EMPLOYEE.getAccountType()
						.equals(grantedAuthority.getAuthority())) {
					userAccountType = UserAccountTypeConstant.ACC_TYPE_EMPLOYEE;
				} else if (UserAccountTypeConstant.ACC_TYPE_ADMIN.getAccountType()
						.equals(grantedAuthority.getAuthority())) {
					userAccountType = UserAccountTypeConstant.ACC_TYPE_ADMIN;
				}
			}
		}

		final Set<PageLinkConstant> linksToBeDisplayed =  new HashSet<PageLinkConstant>();
		for (final PageLinkConstant topLevelPage : PageLinkConstant.getTogglepages()) {
			// Check if user is allowed to access the page link
			boolean topLinkAllowedForUser = false;
			for (final UserAccountTypeConstant userAccountTypeConstant : topLevelPage.getUserAccountTypes()) {
				if (userAccountTypeConstant.equals(userAccountType)) {
					topLinkAllowedForUser = true;
					break;
				}
			}
			if (topLinkAllowedForUser) {
				for (final GrantConstant grantConstant : topLevelPage.getGrants()) {
					if (userAccountType.equals(UserAccountTypeConstant.ACC_TYPE_SUPER_ADMIN)
							|| authorities.contains(grantConstant.toString().toUpperCase())
							|| GrantConstant.NONE.equals(grantConstant)) {
						linksToBeDisplayed.add(topLevelPage);
						break;
					}
				}
			}
		}
		return linksToBeDisplayed;
	}
}
