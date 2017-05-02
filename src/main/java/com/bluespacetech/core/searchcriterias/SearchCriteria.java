
/**
 * This document is a part of the source code and related artifacts for
 * SMSystem.
 * www.apeironsol.com
 * Copyright © 2013 apeironsol
 *
 */
package com.bluespacetech.core.searchcriterias;

import java.io.Serializable;

/**
 * Search criteria interface.
 *
 * @author Pradeep
 */
public interface SearchCriteria extends Serializable {

	/**
	 * Return branch.
	 *
	 * @return branch.
	 */
	Long getBranchId();

	/**
	 * Return branch.
	 *
	 * @return branch.
	 */
	void setBranchId(final Long branchId);

}
