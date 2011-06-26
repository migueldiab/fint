package edu.ort.common.mail;

import java.util.Properties;
import javax.ejb.Asynchronous;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author migueldiab
 */
@Stateless
public class Mailer {

  @Asynchronous
  public void sendMail(final String toMail, final String subject, final String textMessage) {
    String host = "adinet.com.uy";
		String username = "basura@adinet.com.uy";
		String password = "";
    Integer port = 25;
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", port);
		Session session = Session.getDefaultInstance(props, new MailAuthenticator(username, password));
		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("basura@adinet.com.uy"));
      InternetAddress[] to = InternetAddress.parse(toMail);
			message.setRecipients(Message.RecipientType.TO, to);
			message.setSubject(subject);
			message.setText(textMessage);
			Transport.send(message);
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
  }

  private class MailAuthenticator extends javax.mail.Authenticator {
    private String username;
    private String password;

    public MailAuthenticator(String SMTP_AUTH_USER, String SMTP_AUTH_PWD) {
      this.username = SMTP_AUTH_USER;
      this.password = SMTP_AUTH_PWD;
    }

    @Override
    public PasswordAuthentication getPasswordAuthentication() {
       return new PasswordAuthentication(username, password);
    }
  }

}

