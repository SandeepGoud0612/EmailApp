/**
 * This document is a part of the source code and related artifacts for
 * Emilent.
 * www.brilapps.com
 * Copyright © 2015 brilapps
 *
 */
package com.bluespacetech.security.constants;

import com.bluespacetech.core.constants.Labeled;

public enum PageLinkTypeConstant implements Labeled {
	LINK("link"), TOGGLE("toggle");

	private String label;

	PageLinkTypeConstant(final String label) {
		this.label = label;
	}

	@Override
	public String getLabel() {
		return label;
	}

	@Override
	public void setLabel(final String label) {
		this.label = label;

	}

}
