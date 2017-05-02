package com.bluespacetech.notifications.email.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bluespacetech.core.exceptions.BusinessException;
import com.bluespacetech.notifications.email.entity.EmailServer;
import com.bluespacetech.notifications.email.entity.EmailServerProperties;
import com.bluespacetech.notifications.email.util.EmailServerPropertyValueTypeConstant;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class EmailServerServiceTest {

	@Autowired
	private EmailServerService emailServerService;

	@Autowired
	private EmailServerPropertiesService emailServerPropertiesService;

	// @Test
	public void createEmailServer() throws BusinessException {
		final EmailServer emailServer = new EmailServer();
		emailServer.setName("Email Server Name2");
		emailServer.setProtocol("smtp");
		emailServer.setHost("smtp.gmail.com");
		emailServer.setPort("587");
		emailServer.setMailsPerSession(100);
		emailServer.setFromAddress("sam@gmail.com");
		emailServer.setEmailUsername("bst.emailapp@gmail.com");
		emailServer.setEmailPassword("BSTEMAILAPP");
		emailServerService.createEmailServer(emailServer);

		final EmailServerProperties emailServerProperties1 = new EmailServerProperties();
		emailServerProperties1.setPropertyName("Property1");
		emailServerProperties1.setValue("Value1");
		emailServerProperties1.setEmailServer(emailServer);
		emailServerProperties1.setEmailServerPropertyValueTypeConstant(EmailServerPropertyValueTypeConstant.STRING);
		emailServerPropertiesService.createEmailServerProperty(emailServerProperties1);

		final EmailServerProperties emailServerProperties2 = new EmailServerProperties();
		emailServerProperties2.setPropertyName("Property2");
		emailServerProperties2.setValue("Value2");
		emailServerProperties2.setEmailServer(emailServer);
		emailServerProperties2.setEmailServerPropertyValueTypeConstant(EmailServerPropertyValueTypeConstant.NUMBER);
		emailServerPropertiesService.createEmailServerProperty(emailServerProperties2);

		final EmailServerProperties emailServerProperties3 = new EmailServerProperties();
		emailServerProperties3.setPropertyName("Property3");
		emailServerProperties3.setValue("Value3");
		emailServerProperties3.setEmailServer(emailServer);
		emailServerProperties3.setEmailServerPropertyValueTypeConstant(EmailServerPropertyValueTypeConstant.BOOLEAN);
		emailServerPropertiesService.createEmailServerProperty(emailServerProperties3);
	}

}
