package edu.ort.common.mail;

import javax.ejb.Asynchronous;
import javax.ejb.Local;

/**
 *
 * @author migueldiab
 */
@Local
public interface MailerLocal {

  @Asynchronous
  void sendMail(final String toMail, final String subject, final String textMessage);

}
