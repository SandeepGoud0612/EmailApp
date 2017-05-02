package com.bluespacetech.notifications.email.worker;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;

import com.bluespacetech.notifications.email.valueobjects.EmailVO;

public class EmailWorker {

	private final JavaMailSender  javaMailSender;

	public EmailWorker(JavaMailSender javaMailSender){
		this.javaMailSender = javaMailSender;
	}

	@Async
	void sendEmail(final EmailVO email){
		final SimpleMailMessage mail = new SimpleMailMessage();
		mail.setTo(email.getToAddress().toArray(new String[email.getToAddress().size()]));
		mail.setFrom(email.getFromAddress());
		mail.setSubject(email.getSubject());
		mail.setText(email.getMessage());
		javaMailSender.send(mail);
	}

}
