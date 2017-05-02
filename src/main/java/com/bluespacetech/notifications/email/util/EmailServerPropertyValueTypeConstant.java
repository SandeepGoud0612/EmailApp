package com.bluespacetech.notifications.email.util;


public enum EmailServerPropertyValueTypeConstant {

	BOOLEAN("boolean"), STRING("string"), NUMBER("number");

	private String label;

	EmailServerPropertyValueTypeConstant(final String label) {
		this.label = label;
	}

	public String getLabel() {
		return this.label;
	}

	public void setLabel(final String label) {
		this.label = label;
	}
}
