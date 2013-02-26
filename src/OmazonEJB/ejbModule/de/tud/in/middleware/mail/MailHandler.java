package de.tud.in.middleware.mail;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;

public class MailHandler {

	public static void main(final String[] args) {
		sendMail("alexander@froemmgen.de", "Omazon Notification", "Hallo");
	}

	public static synchronized void sendMail(final String destAddr, final String subStr, final String msgStr) {
		final String user = "Omazon@gmx.de";
		final String pwd = "Middleware";

		final Properties props = new Properties();
		props.put("mail.debug", true);
		props.put("mail.smtp.host", "smtp.gmx.net");
		props.put("mail.smtp.auth", "true");
		props.put("mail.from", user);

		final Session session = Session.getInstance(props, null);
		try {
			final MimeMessage msg = new MimeMessage(session);

			msg.setRecipients(Message.RecipientType.TO, destAddr);
			msg.setSubject(subStr);
			msg.setSentDate(new Date());
			msg.setFrom(new InternetAddress(user, "Omazon Inc"));

			final BodyPart messageBodyPart = new MimeBodyPart();

			final String htmlText = msgStr;

			messageBodyPart.setContent(htmlText, "text/html");

			msg.setContent(htmlText, "text/html");
			final Transport t = session.getTransport("smtp");
			t.connect("smtp.gmx.net", 587, user, pwd);
			t.sendMessage(msg, msg.getAllRecipients());
		} catch (final MessagingException mex) {
			System.out.println("send failed, exception: " + mex);
		} catch (final UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (final Exception e) {
			System.out.println("Mail error");
			e.printStackTrace();
		}
	}
}