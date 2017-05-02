package com.bluespacetech.notifications.email.batch;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;

public class ContactGroupEmailRowMapper implements RowMapper<EmailContactGroupVO> {

	private String message;
	private String subject;
	private Long emailId;

	@Override
	public EmailContactGroupVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		final EmailContactGroupVO emailContactGroupVO = new EmailContactGroupVO();
		emailContactGroupVO.setContactFirstName(rs.getString("FIRST_NAME"));
		emailContactGroupVO.setContactLastName(rs.getString("LAST_NAME"));
		emailContactGroupVO.setContactEmail(rs.getString("EMAIL"));
		emailContactGroupVO.setMessage(getMessage());
		emailContactGroupVO.setSubject(getSubject());
		emailContactGroupVO.setEmailId(getEmailId());
		emailContactGroupVO.setGroupId(rs.getLong("GROUP_ID"));
		emailContactGroupVO.setContactId(rs.getLong("CONTACT_ID"));
		return emailContactGroupVO;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * @return the subject
	 */
	public String getSubject() {
		return subject;
	}

	/**
	 * @param subject
	 *            the subject to set
	 */
	public void setSubject(String subject) {
		this.subject = subject;
	}

	/**
	 * @return the emailId
	 */
	public Long getEmailId() {
		return emailId;
	}

	/**
	 * @param emailId
	 *            the emailId to set
	 */
	public void setEmailId(Long emailId) {
		this.emailId = emailId;
	}


}
