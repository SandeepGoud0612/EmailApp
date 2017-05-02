package com.bluespacetech.notifications.email.worker;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.bluespacetech.notifications.email.service.EmailServerService;
import com.bluespacetech.security.model.UserAccount;

@Component
public class EmailUserAccountWorker {

	@Autowired
	private JavaMailSender javaMailSender;

	@Autowired
	private EmailServerService emailServerService;

	@Autowired
	private VelocityEngine velocityEngine;

	@Async
	public void sendEmail(final UserAccount userAccount, final String userPassword)
			throws MailException, InterruptedException, MessagingException {
		final Map<String, Object> model = new HashMap<String, Object>();
		model.put("userName", userAccount.getUsername());
		model.put("password", userPassword);
		final String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine,
				"velocityTemplates/NewUserAccountEmail.vm", model);
		final MimeMessage mimeMessage = javaMailSender.createMimeMessage();
		final MimeMessageHelper simpleMailMessage = new MimeMessageHelper(mimeMessage, true);
		simpleMailMessage.setTo(userAccount.getEmail());
		simpleMailMessage.setSubject("New Account created");
		simpleMailMessage.setSentDate(new Date());
		simpleMailMessage.setText(text, true);
		javaMailSender.send(mimeMessage);
	}

}
