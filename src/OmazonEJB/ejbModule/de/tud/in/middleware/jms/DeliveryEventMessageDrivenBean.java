package de.tud.in.middleware.jms;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.tud.in.middleware.dao.OrderDAO;
import de.tud.in.middleware.order.OrderManagement;
import de.tud.in.middleware.order.OrderState;

/**
 * Simle message driven bean.
 */
@MessageDriven(mappedName = "jms/DeliveryQueue")
public class DeliveryEventMessageDrivenBean implements MessageListener {
/* Um die Queue im Glassfish anzulegen:
 * asadmin create-jms-resource --restype javax.jms.ConnectionFactory jms/SimpleConnectionFactory
 asadmin create-jms-resource --restype javax.jms.Queue jms/DeliveryQueue
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
	public void onMessage(Message message) {

		try {
			String msgText = ((TextMessage) message).getText();
			System.out.println("Got message: " + msgText);

			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource is = new InputSource(new StringReader(msgText));
			Document document = builder.parse(is);
			
			// TODO Hier noch ein wenig das Format überprüfen (heist das feld wirklich deliveryevent...)
			String shipmentIdStr = document.getFirstChild().getTextContent();
			long shipmentId = Long.parseLong(shipmentIdStr);
			
			orderManagement.changeOrderState(shipmentId, OrderState.DELIVERED);
			
			System.out.println("Message handled successfullly");
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}