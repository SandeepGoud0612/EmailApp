package com.bluespacetech.security.constants;

import java.util.HashMap;
import java.util.Map;

import com.bluespacetech.core.constants.Labeled;

/**
 * Enums for Authority constants
 *
 * @author Pradeep
 */
public enum GrantConstant implements Labeled {

	NONE("none", "NONE"),
	ACCESS_DASHBOARD("access_dashboard", "UI_ACCESS_DASHBOARD"),
	ACCESS_REPORTS("access_reports", "UI_ACCESS_REPORTS"),
	ACCESS_SEND_EMAIL("access_send_email", "UI_ACCESS_SEND_EMAIL"),

	ACCESS_CONTACTS("access_contacts","UI_ACCESS_CONTACTS"),
	CREATE_CONTACTS("create_contacts","UI_CREATE_CONTACTS"),
	UPDATE_CONTACTS("update_contacts","UI_UPDATE_CONTACTS"),
	DELETE_CONTACTS("delete_contacts","UI_DELETE_CONTACTS"),

	ACCESS_GROUPS("access_groups","UI_ACCESS_GROUPS"),
	CREATE_GROUPS("create_groups","UI_CREATE_GROUPS"),
	UPDATE_GROUPS("update_groups","UI_UPDATE_GROUPS"),
	DELETE_GROUPS("delete_groups","UI_DELETE_GROUPS"),

	ACCESS_SERVERS("access_servers","UI_ACCESS_SERVERS"),
	CREATE_SERVERS("create_servers","UI_CREATE_SERVERS"),
	UPDATE_SERVERS("update_servers","UI_UPDATE_SERVERS"),
	DELETE_SERVERS("delete_servers","UI_DELETE_SERVERS"),

	ACCESS_USER_ROLES("access_user_roles","UI_USER_ROLES_ACCESS"),
	CREATE_USER_ROLES("create_user_roles","UI_USER_ROLES_CREATE"),
	UPDATE_USER_ROLES("update_user_roles","UI_USER_ROLES_UPDATE"),
	DELETE_USER_ROLES("delete_user_roles","UI_USER_ROLES_DELETE"),

	ACCESS_USER_GROUPS("access_user_groups","UI_USER_GROUPS_ACCESS"),
	CREATE_USER_GROUPS("create_user_groups","UI_USER_GROUPS_CREATE"),
	UPDATE_USER_GROUPS("update_user_groups","UI_USER_GROUPS_UPDATE"),
	DELETE_USER_GROUPS("delete_user_groups","UI_USER_GROUPS_DELETE"),

	ACCESS_USERS("access_users","UI_USERS_ACCESS"),
	CREATE_USERS("create_users","UI_USERS_CREATE"),
	UPDATE_USERS("update_users","UI_USERS_UPDATE"),
	DELETE_USERS("delete_users", "UI_USERS_DELETE");

	// User Authorities
	private String label;
	private String grantUI;

	GrantConstant(final String label,final String grantUI) {
		this.label = label;
		this.grantUI = grantUI;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(final String label) {
		this.label = label;

	}

	/**
	 * @return the grantUI
	 */
	public String getGrantUI() {
		return grantUI;
	}

	/**
	 * @param grantUI the grantUI to set
	 */
	public void setGrantUI(final String grantUI) {
		this.grantUI = grantUI;
	}

	public static Map<String, String> getUIGrantsByGrants() {
		final Map<String, String> uiGrantsByGrantsMap = new HashMap<String, String>();
		for (final GrantConstant grantConstant : GrantConstant.values()) {
			uiGrantsByGrantsMap.put(grantConstant.getLabel().toUpperCase(), grantConstant.getGrantUI().toUpperCase());
		}
		return uiGrantsByGrantsMap;
	}
}
