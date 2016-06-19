package mailer;

import java.util.*; 
import javax.mail.*; 
import javax.mail.internet.*;

/**
 * Classe Mailer
 * Si occupa di mandare un'email all'utente che ha appena effettuato la registrazione
 */
public class mailer {

	static Session session;
	static Properties props; 
	
	static {
		props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	}
	
	/**
	 * Il metodo permette di inviare una mail ad un indirizzo di posta 
	 * 
	 * @param to Stringa che rappresenta l'indirizzo e-mail destinatario 
	 * @param nome Stringa che rappresenta il nome del destinatario
	 * @param userPassword Stringa che rappresenta la password del destinatario 
	 * @return Booleano che mostra se l'operazione è andata a buon fine 
	 */
	public boolean sendMail(String to, String nome, String userPassword){
		final String username = "sportnetmail@gmail.com";
		final String password = "SportNetyolo";
		final String from = "sportnetmail@gmail.com"; 
		boolean success = false; 
			
			//creazione sessione con dati SMTP + username e password 
			session = Session.getInstance(props,
			  new javax.mail.Authenticator() {
				/**
				 * Autenticatore 
				 */
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
	        		+ "<p>La registrazione al sistema \"Library\" è avvenuta con successo.</p>"
	        		+ "<p>Le sue credenziali di accesso sono: </p>"
	        		+ "<ul>"
	        		+ "<li>E-mail: " + to + "</li>"
	        		+ "<li>Password: " + userPassword + "</li>"
	        		+ "</ul>"
	        		+ "<br>"
	        		+ "<footer>" + "<small>&copy; Copyright 2016, Library Group</small>"
	        		+ "</footer>", "text/html" );
			
			//send PlaneText 
			//message.setText("Questa E-mail e' stata inviata tramite java.\nSaluti.");

			Transport.send(message);
			
			success = true; 
			return success; 
			
		} catch (MessagingException e) {
			return false; 
		} 
	}

}
