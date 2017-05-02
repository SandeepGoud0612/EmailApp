package com.bluespacetech.notifications.email.batch;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.jdbc.core.PreparedStatementSetter;

public class ContactGroupEmailPreparedStatementSetter implements PreparedStatementSetter {

	private Long groupId;

	@Override
	public void setValues(PreparedStatement ps) throws SQLException {
		ps.setLong(1, this.groupId);
	}

	/**
	 * @param groupId
	 *            the groupId to set
	 */
	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}

}
