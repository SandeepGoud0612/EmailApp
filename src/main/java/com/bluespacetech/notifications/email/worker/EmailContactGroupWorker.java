package com.bluespacetech.notifications.email.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.bluespacetech.contactgroup.entity.ContactGroup;
import com.bluespacetech.notifications.email.valueobjects.EmailVO;

@Component
public class EmailContactGroupWorker{

	private final JavaMailSender javaMailSender;

	@Autowired
	public EmailContactGroupWorker(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	@Async
	void sendEmail(final ContactGroup contactGroup, final EmailVO emailVO) throws MailException, InterruptedException {
		final SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(contactGroup.getContact().getEmail());
		mail.setFrom(emailVO.getFromAddress());
		mail.setSubject(emailVO.getSubject());
		mail.setText(emailVO.getMessage());
		javaMailSender.send(mail);
	}

}
