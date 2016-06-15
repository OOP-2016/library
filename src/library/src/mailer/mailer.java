package mailer;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*;

public class mailer {

	public void sendMail(String to, String nome){
		final String username = "sportnetmail@gmail.com";
		final String password = "SportNetyolo";
		final String from = "sportnetmail@gmail.com"; 
		
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		//creazione sessione con dati SMTP + username e password 
		Session session = Session.getInstance(props,
		  new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		  });

		try {
			
			//message creation
			Message message = new MimeMessage(session);
			// Set From: header field of the header.
			message.setFrom(new InternetAddress(from));
			// Set To: header field of the header.
			message.setRecipients(Message.RecipientType.TO,
				InternetAddress.parse(to));
			// Set Subject: header field
			message.setSubject("Registrazione avvenuta");
			
			// Send the actual HTML message, as big as you like
	        message.setContent("<h1>Library</h1>"
	        		+ "<p>Ciao " + nome + "</p>"
	        		+ "<p>La registrazione a library è avvenuta con successo.</p>"
	        		+ "<footer>" + "<small>© Copyright 2016, Library Group</small>"
	        		+ "</footer>", "text/html" );
			
			//send PlaneText 
			//message.setText("Questa E-mail e' stata inviata tramite java.\nSaluti.");

			Transport.send(message);

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}

}
