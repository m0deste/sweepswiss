/**
 * 
 */
package org.sweep.swiss.divers;

import javax.mail.Session;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.InternetAddress;
import javax.mail.Transport;
import java.util.Properties;
/**
 * @author Modeste KOUASSI
 *
 */
public class EnvoyerEmail {
	
	
	
	private static final String SMTP_HOST1 = "smtp.gmail.com";
	//private static final String SMTP_HOST1 = "localhost";
	private static final String LOGIN_SMTP1 = "modeste090@gmail.com";
	private static final String IMAP_ACCOUNT1 = "modeste090@gmail.com";
	private static final String PASSWORD_SMTP1 = "03046936";

	public static void sendMessage(String subject, String text, String destinataire, String copyDest) {
	   
		// 1 -> Création de la session
	    Properties properties = new Properties();
	    properties.setProperty("mail.transport.protocol", "smtp");
	    properties.setProperty("mail.smtp.host", SMTP_HOST1);
	    properties.setProperty("mail.smtp.user", LOGIN_SMTP1);
	    properties.setProperty("mail.from", IMAP_ACCOUNT1);
	    properties.setProperty("mail.smtp.starttls.enable", "true");
	    properties.put("mail.smtp.socketFactory.port", "465");
	    properties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	    properties.put("mail.smtp.auth", "true");
	    properties.put("mail.smtp.port", "465");
	    Session session = Session.getDefaultInstance(properties);
	    
	    // 2 -> Création du message
	    MimeMessage message = new MimeMessage(session);
	    try {
	        message.setText(text);
	        message.setSubject(subject);
	        message.addRecipients(Message.RecipientType.TO, destinataire);
	        message.addRecipients(Message.RecipientType.CC, copyDest);
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    }
	    
	    // 3 -> Envoi du message
	    Transport transport = null;
	    try {
	        transport = session.getTransport("smtp");
	        transport.connect(LOGIN_SMTP1, PASSWORD_SMTP1);
	        transport.sendMessage(message, new Address[] {new InternetAddress(destinataire), new InternetAddress(copyDest) });
	    } catch (MessagingException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (transport != null) {
	                transport.close();
	            }
	        } catch (MessagingException e) {
	            e.printStackTrace();
	        }
	    }
	}
}
