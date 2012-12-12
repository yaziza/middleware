package de.tud.in.middleware.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHandler {
	
	public static synchronized void sendMail(String customerName, String msg) {
/*

		Properties props = new Properties();
		props.put("mail.debug", Boolean.valueOf(CentralInstance.eMailDebug));
		props.put("mail.smtp.host", "smtp.strato.de");
		props.put("mail.smtp.auth", "true");
		props.put("mail.from", CentralInstance.eMailFrom);

		Session session = Session.getInstance(props, null);
		try {
			MimeMessage msg = new MimeMessage(session);

			msg.setRecipients(Message.RecipientType.TO, to);
			msg.setSubject("PROASS Cloud App Bestellung");
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress(CentralInstance.eMailFrom,
					"PROASS Cloud App"));

			BodyPart messageBodyPart = new MimeBodyPart();

			String datum = ConsoleHelper.getFormattedTimestamp();
			String htmlText = "<H2>PROASS Cloud App</H2>Folgende Bestellung wurde vom Benutzer "
					+ username
					+ " am "
					+ datum
					+ " versendet.<br><br><br>Dies ist eine automatisch generierte eMail von<br>"
					+ "<img src=\"http://www.proass.de/images/proass.jpg\">";

			//messageBodyPart.setText(htmlText);
			messageBodyPart.setContent(htmlText, "text/html");

			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(messageBodyPart);
			MimeBodyPart attachmentBodyPart = new MimeBodyPart();

			DataSource source = new FileDataSource(filename);
			attachmentBodyPart.setDataHandler(new DataHandler(source));
			attachmentBodyPart.setFileName("Bestellung.pdf");
			multipart.addBodyPart(attachmentBodyPart);
			msg.setContent(multipart);

			Transport t = session.getTransport("smtp");
			t.connect(CentralInstance.eMailFrom, CentralInstance.eMailFromPwd);
			t.sendMessage(msg, msg.getAllRecipients());

			ConsoleHelper.println("Bestellung wurde für Benutzer " + username
					+ " per eMail an " + to + " versendet");
		} catch (MessagingException mex) {
			ConsoleHelper.println("send failed, exception: " + mex);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			ConsoleHelper.println("Mail error");
			e.printStackTrace();
		}*/
	}
}