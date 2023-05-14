package com.example.demo.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

@Service
public class EmailSenderService {
	private static final Logger log = LoggerFactory.getLogger(EmailSenderService.class);

	@Autowired
	private Environment env;

	public void sendEmail(String toEmail, String subject, String body) throws IOException {
		Email from = new Email("biblioteca.daw@myyahoo.com");
		Email to = new Email(toEmail);
		Content content = new Content("text/plain", body);
		Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(env.getProperty("spring.sendgrid.api-key"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			log.info("SendGrid API response - Status code: {}, Body: {}, Headers: {}", response.getStatusCode(),
					response.getBody(), response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}
}
