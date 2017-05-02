package com.bluespacetech.notifications.email.batch;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.mail.internet.MimeMessage;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.util.Assert;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailContactGroup;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;
import com.bluespacetech.notifications.email.service.EmailContactGroupService;
import com.bluespacetech.notifications.email.service.EmailServerPropertiesService;
import com.bluespacetech.notifications.email.service.EmailServerService;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;
import com.bluespacetech.security.service.UserAccountService;

public class ContactGroupMailMessageItemWriter implements ItemWriter<ContactGroupMailMessage>, InitializingBean {

	Logger logger = Logger.getLogger(ContactGroupMailMessageItemWriter.class);

	private JavaMailSender mailSender;

	private EmailContactGroupService emailContactGroupService;

	private EmailServerService emailServerService;

	private EmailServerPropertiesService emailServerPropertiesService;

	private UserAccountService userAccountService;

	/**
	 * A {@link JavaMailSender} to be used to send messages in
	 * {@link #write(List)}.
	 * 
	 * @param mailSender
	 */
	public void setMailSender(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	/**
	 * The handler for failed messages. Defaults to a
	 * {@link DefaultMailErrorHandler}.
	 * 
	 * @param mailErrorHandler
	 *            the mail error handler to set
	 * 
	 *            public void setMailErrorHandler(MailErrorHandler
	 *            mailErrorHandler) { this.mailErrorHandler = mailErrorHandler;
	 *            }
	 */

	/**
	 * @param emailContactGroupService
	 *            the emailContactGroupService to set
	 */
	public void setEmailContactGroupService(EmailContactGroupService emailContactGroupService) {
		this.emailContactGroupService = emailContactGroupService;
	}

	/**
	 * Check mandatory properties (mailSender).
	 * 
	 * @throws IllegalStateException
	 *             if the mandatory properties are not set
	 * 
	 * @see InitializingBean#afterPropertiesSet()
	 */
	@Override
	public void afterPropertiesSet() throws IllegalStateException {
		Assert.state(mailSender != null, "A MailSender must be provided.");
	}

	/**
	 * @param items
	 *            the items to send
	 * @see ItemWriter#write(List)
	 */
	@Override
	public void write(List<? extends ContactGroupMailMessage> items) throws MailException {
		try {
			final List<EmailServer> emailServers = emailServerService.findAll();
			final Map<Long, Properties> emailServerPropertiesByServers = new HashMap<Long, Properties>();
			Properties oldMailProperties = null;
			if (mailSender instanceof JavaMailSenderImpl) {
				oldMailProperties = ((JavaMailSenderImpl) mailSender).getJavaMailProperties();
				// final String userName = ViewUtil.getPrincipal();
				// final UserAccount userAccount =
				// userAccountService.findUserAccountByUsername(userName);
				// final String userEmail = userAccount.getEmail();
				if (emailServers != null) {
					final List<EmailServerProperties> emailServerPropertiesOfServers = emailServerPropertiesService
							.findByEmailServers(emailServers);
					for (final EmailServerProperties emailServerProperty : emailServerPropertiesOfServers) {
						if (emailServerPropertiesByServers.get(emailServerProperty.getEmailServer().getId()) != null) {
							emailServerPropertiesByServers.get(emailServerProperty.getEmailServer().getId())
							.put(emailServerProperty.getPropertyName(), emailServerProperty.getValue());
						} else {
							final Properties properties = new Properties();
							properties.put(emailServerProperty.getPropertyName(), emailServerProperty.getValue());
							emailServerPropertiesByServers.put(emailServerProperty.getEmailServer().getId(),
									properties);
						}
					}
					for (final Map.Entry<Long, Properties> entry : emailServerPropertiesByServers.entrySet()) {
						final Properties properties = entry.getValue();
						if (oldMailProperties != null && oldMailProperties.size() > 0) {
							for (final Object key : oldMailProperties.keySet()) {
								if (properties.get(key) == null) {
									properties.put(key, oldMailProperties.get(key));
								}
							}
						}
					}
				}
			}

			final List<EmailContactGroup> emailContactGroups = new ArrayList<EmailContactGroup>();
			int count = 0, toltalMessagesCount = 0;
			int mailServerCount = 0;
			MimeMessage[] messages = null;
			for (final ContactGroupMailMessage contactGroupMailMessage : items) {
				emailContactGroups.add(contactGroupMailMessage.getEmailContactGroup());
				if (emailServers != null) {
					// Loop though all email servers
					if (mailServerCount < emailServers.size()) {
						// get each configured email server and send mails
						final EmailServer emailServer = emailServers.get(mailServerCount);
						if (mailSender instanceof JavaMailSenderImpl) {
							contactGroupMailMessage.getMimeMessage().setFrom(emailServer.getFromAddress());
							if (emailServerPropertiesByServers.get(emailServer.getId()) != null) {
								((JavaMailSenderImpl) mailSender)
								.setJavaMailProperties(emailServerPropertiesByServers.get(emailServer.getId()));
							} else {
								((JavaMailSenderImpl) mailSender).setJavaMailProperties(oldMailProperties);
							}
						}
						// Initialize the messages array to mail server capacity for
						// sending.
						if (count == 0) {
							messages = new MimeMessage[emailServer.getMailsPerSession()];
						}

						messages[count] = contactGroupMailMessage.getMimeMessage();
						// Once number of messages reach mail server capacity or all
						// the messages in the batch are completed, mail/send the
						// messages.
						if (count == emailServer.getMailsPerSession() - 1 || toltalMessagesCount == items.size() - 1) {
							mailSender.send(messages);
							mailServerCount++;
							count = 0; 
						} else {
							count++;
						}
						toltalMessagesCount++;
						if (mailServerCount == emailServers.size() && toltalMessagesCount < items.size()) {
							mailServerCount = 0;
						}
					}
				} else {
					messages = new MimeMessage[items.size()];
					messages[count] = contactGroupMailMessage.getMimeMessage();
					count++;
				}
			}
			emailContactGroupService.createEmailContactGroups(emailContactGroups);
			if (emailServers == null && messages != null) {
				mailSender.send(messages);
			}
			if (oldMailProperties != null && oldMailProperties.size() > 0 && mailSender instanceof JavaMailSenderImpl) {
				((JavaMailSenderImpl) mailSender).setJavaMailProperties(oldMailProperties);
			}
		} catch (final MailSendException e) {
			logger.error(e.getMessage());
		} catch (final BusinessException e) {
			logger.error(e.getMessage());
		} catch (final Exception e) {
			logger.error(e.getMessage());
		}
	}

	/**
	 * @param emailServerService
	 *            the emailServerService to set
	 */
	public void setEmailServerService(EmailServerService emailServerService) {
		this.emailServerService = emailServerService;
	}

	/**
	 * @param emailServerPropertiesService
	 *            the emailServerPropertiesService to set
	 */
	public void setEmailServerPropertiesService(EmailServerPropertiesService emailServerPropertiesService) {
		this.emailServerPropertiesService = emailServerPropertiesService;
	}

	/**
	 * @param userAccountService
	 *            the userAccountService to set
	 */
	public void setUserAccountService(UserAccountService userAccountService) {
		this.userAccountService = userAccountService;
	}

}
