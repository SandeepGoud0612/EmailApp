package com.bluespacetech.notifications.email.batch;

import javax.sql.DataSource;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;

import com.bluespacetech.notifications.email.service.EmailContactGroupService;
import com.bluespacetech.notifications.email.service.EmailServerPropertiesService;
import com.bluespacetech.notifications.email.service.EmailServerService;
import com.bluespacetech.notifications.email.util.ContactGroupMailMessage;
import com.bluespacetech.notifications.email.valueobjects.EmailContactGroupVO;
import com.bluespacetech.security.service.UserAccountService;

@Configuration
@EnableBatchProcessing
public class EmailBatchConfiguration {

	@Autowired
	public JobBuilderFactory jobBuilderFactory;

	@Autowired
	public StepBuilderFactory stepBuilderFactory;

	@Autowired
	public EmailContactGroupService emailContactGroupService;

	@Autowired
	public JavaMailSender javaMailSender;

	@Autowired
	public DataSource dataSource;

	@Autowired
	public EmailServerService emailServerService;

	@Autowired
	public EmailServerPropertiesService emailServerPropertiesService;

	@Autowired
	private VelocityEngine velocityEngine;

	@Autowired
	private UserAccountService userAccountService;

	private static String QUERY_FIND_CONTACTS = "SELECT first_name, last_name, email, group_id, contact_id FROM contacts "
			+ "C, contact_group CG WHERE CG.contact_id = C.ID AND CG.unsubscribed = 0";

	@Bean
	@StepScope
	JdbcCursorItemReader<EmailContactGroupVO> databaseItemReader(DataSource dataSource,
			@Value("#{jobParameters[groupId]}") Long groupId,@Value("#{jobParameters[emailId]}") Long emailId, @Value("#{jobParameters[message]}") String message,
			@Value("#{jobParameters[subject]}") String subject) {
		final JdbcCursorItemReader<EmailContactGroupVO> databaseReader = new JdbcCursorItemReader<EmailContactGroupVO>();
		databaseReader.setDataSource(dataSource);
		final ContactGroupEmailRowMapper emailContactGroupRowMapper = new ContactGroupEmailRowMapper();
		emailContactGroupRowMapper.setMessage(message);
		emailContactGroupRowMapper.setSubject(subject);
		if (emailId != null) {
			emailContactGroupRowMapper.setEmailId(emailId);
		}
		databaseReader.setRowMapper(emailContactGroupRowMapper);
		QUERY_FIND_CONTACTS = QUERY_FIND_CONTACTS + " AND CG.group_id = " + groupId;
		databaseReader.setSql(QUERY_FIND_CONTACTS);
		return databaseReader;
	}

	@Bean
	public ItemWriter<ContactGroupMailMessage> simpleEmailWriter(
			final EmailContactGroupService emailContactGroupService) {
		final ContactGroupMailMessageItemWriter writer = new ContactGroupMailMessageItemWriter();
		writer.setMailSender(javaMailSender);
		writer.setEmailServerService(emailServerService);
		writer.setEmailServerPropertiesService(emailServerPropertiesService);
		writer.setEmailContactGroupService(emailContactGroupService);
		writer.setUserAccountService(userAccountService);
		return writer;
	}

	@Bean
	@StepScope
	public GroupContactEmailItemProcessor processor(
			@Value("#{jobParameters[emailRequestURL]}") String emailRequestURL) {
		final GroupContactEmailItemProcessor processor = new GroupContactEmailItemProcessor();
		processor.setEmailRequestURL(emailRequestURL);
		processor.setMailSender(javaMailSender);
		processor.setVelocityEngine(velocityEngine);
		return processor;
	}


	@Bean(name = "groupEmailJob")
	public Job groupEmailJob() {
		return jobBuilderFactory.get("groupEmailJob").incrementer(new RunIdIncrementer()).flow(step1()).end().build();
	}

	@Bean
	public Step step1() {
		return stepBuilderFactory.get("step1").<EmailContactGroupVO, ContactGroupMailMessage> chunk(10000)
				.reader(databaseItemReader(dataSource, null, null, null, null))
				.processor(processor(null))
				.writer(simpleEmailWriter(emailContactGroupService)).build();
	}

}
