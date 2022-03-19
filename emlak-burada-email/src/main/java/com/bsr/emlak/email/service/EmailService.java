package com.bsr.emlak.email.service;

import com.bsr.emlak.commons.dto.request.EmailMessageRequestDTO;
import com.bsr.emlak.commons.repository.EmailRepository;
import com.bsr.emlak.commons.transformers.EmailTransformer;
import com.bsr.emlak.email.config.EmailConfig;
import com.bsr.emlak.email.util.EmailContentBuilder;
import com.sun.mail.smtp.SMTPTransport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;
import java.util.Properties;

@Service
@Slf4j
public class EmailService {

	public static final String SMTP = "smtp";
	private final EmailConfig config;
	private final EmailRepository emailRepository;
	private final EmailTransformer emailTransformer;

	@Autowired
	public EmailService(EmailConfig config, EmailRepository emailRepository, EmailTransformer emailTransformer) {
		this.config = config;
		this.emailRepository = emailRepository;
		this.emailTransformer = emailTransformer;
	}

	public void send(EmailMessageRequestDTO emailDTO) {
		Properties properties = prepareSmtpServer();
		Session session = prepareSessionWithCredentials(properties);

		int sendMessage = sendMessage(emailDTO, session);
		if (sendMessage == 0) {
			log.info("Mail başarıyla gönderildi! -> " + emailDTO.getToEmail());
		}
		/* save sent email to db */
		emailRepository.save(emailTransformer.transform(emailDTO));
	}

	private Session prepareSessionWithCredentials(Properties prop) {
		return Session.getInstance(prop, new javax.mail.Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(config.getUsername(), config.getPassword());
			}
		});
	}

	private String getBody(EmailMessageRequestDTO email){
		EmailContentBuilder builder = new EmailContentBuilder().
				setGreeting(email.getUserName())
				.addBody(email.getBody());
		return builder.build();
	}

	private int sendMessage(EmailMessageRequestDTO email, Session session) {
		Message message = new MimeMessage(session);
		int lastServerResponse = 0;
		try {
			message.setFrom(new InternetAddress(config.getFrom()));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getToEmail(), false));
			message.setSubject(email.getSubject());
			message.setDataHandler(new DataHandler(new HTMLDataSource(getBody(email))));
			message.setSentDate(new Date());
			SMTPTransport transport = (SMTPTransport) session.getTransport(SMTP);
			transport.connect(config.getSmtpServer(), config.getUsername(), config.getPassword());
			transport.sendMessage(message, message.getAllRecipients());
			lastServerResponse = transport.getLastReturnCode();
			transport.close();

		} catch (MessagingException e) {
			log.error(e.getMessage());
		}
		return lastServerResponse;
	}

	private Properties prepareSmtpServer() {
		Properties properties = System.getProperties();
		properties.put("mail.smtp.host", config.getSmtpServer());
		properties.put("mail.smtp.port", config.getSmtpPort());
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.socketFactory.port", config.getSmtpPort());
		properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.put("mail.smtp.starttls.enable", "true");
		return properties;
	} 

	static class HTMLDataSource implements DataSource {

		private String html;

		public HTMLDataSource(String htmlString) {
			html = htmlString;
		}

		@Override
		public InputStream getInputStream() throws IOException {
			if (html == null)
				throw new IOException("html message is null!");
			return new ByteArrayInputStream(html.getBytes());
		}

		@Override
		public OutputStream getOutputStream() throws IOException {
			throw new IOException("This DataHandler cannot write HTML");
		}

		@Override
		public String getContentType() {
			return "text/html";
		}

		@Override
		public String getName() {
			return "HTMLDataSource";
		}
	}

}
