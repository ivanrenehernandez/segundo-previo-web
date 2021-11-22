package co.web.elecciones.util;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class SendMail {
	public static void main(String[] args) {

		final String user = "9bfcb33a06b246";
		final String password = "172d0d58810855";

		String to = "email.example.com";

		Properties props = new Properties();
		props.put("mail.smtp.auth", true);
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.mailtrap.io");
		props.put("mail.smtp.port", "2525");
		props.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
		
		Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(user, password);
			}
		});

		try {
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(user));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject("javatpoint");
			message.setText("This is simple program of sending email using JavaMail API");

			// send the message
			Transport.send(message);

			System.out.println("message sent successfully...");

		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
}
