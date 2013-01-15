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
	
	public static void main(String[] args) {
		sendMail("alexander@froemmgen.de" , "Omazon Notification", "Hallo");
	}
	
	public static synchronized void sendMail(String destAddr, String subStr, String msgStr) {
		final String user = "Omazon@gmx.de";
		final String pwd = "Middleware";

		Properties props = new Properties();
		props.put("mail.debug", true);
		props.put("mail.smtp.host", "smtp.gmx.net");
		props.put("mail.smtp.auth", "true");
		props.put("mail.from", user);

		Session session = Session.getInstance(props, null);
		try {
			MimeMessage msg = new MimeMessage(session);

			msg.setRecipients(Message.RecipientType.TO, destAddr);
			msg.setSubject(subStr);
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress(user,
					"Omazon Inc"));

			BodyPart messageBodyPart = new MimeBodyPart();

			
			String htmlText = msgStr;

			messageBodyPart.setContent(htmlText, "text/html");

			msg.setContent(htmlText, "text/html");
			Transport t = session.getTransport("smtp");
			t.connect("smtp.gmx.net", 587, user, pwd);
			t.sendMessage(msg, msg.getAllRecipients());
		} catch (MessagingException mex) {
			System.out.println("send failed, exception: " + mex);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println("Mail error");
			e.printStackTrace();
		}
	}
}