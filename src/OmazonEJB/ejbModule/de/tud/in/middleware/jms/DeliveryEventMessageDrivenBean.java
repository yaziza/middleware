package de.tud.in.middleware.jms;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import de.tud.in.middleware.order.OrderManagement;
import de.tud.in.middleware.order.OrderState;

/**
 * Simle message driven bean.
 */
@MessageDriven(mappedName = "jms/DeliveryQueue")
public class DeliveryEventMessageDrivenBean implements MessageListener {
	/*
	 * Um die Queue im Glassfish anzulegen: asadmin create-jms-resource
	 * --restype javax.jms.ConnectionFactory jms/SimpleConnectionFactory asadmin
	 * create-jms-resource --restype javax.jms.Queue jms/DeliveryQueue
	 */

	@EJB
	private OrderManagement orderManagement;

	/**
	 * Default constructor.
	 */
	public DeliveryEventMessageDrivenBean() {
	}

	/**
	 * @see MessageListener#onMessage(Message)
	 */
	@Override
	public void onMessage(final Message message) {

		try {
			final String msgText = ((TextMessage) message).getText();
			System.out.println("Got message: " + msgText);

			final Document document = EventUtil.getXMLDocument(msgText);

			// TODO Hier noch ein wenig das Format überprüfen (heist das feld
			// wirklich deliveryevent...)
			final String shipmentIdStr = document.getFirstChild().getTextContent();
			final Integer shipmentId = Integer.parseInt(shipmentIdStr);

			orderManagement.changeOrderState(shipmentId, OrderState.DELIVERED);

			System.out.println("Message handled successfullly");
		} catch (final JMSException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (final ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}