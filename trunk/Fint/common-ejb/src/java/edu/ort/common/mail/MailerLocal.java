package edu.ort.common.mail;

import javax.ejb.Asynchronous;
import javax.ejb.LocalBean;

/**
 *
 * @author migueldiab
 */
@LocalBean
public interface MailerLocal {

  @Asynchronous
  void sendMail(final String toMail, final String subject, final String textMessage);

}
