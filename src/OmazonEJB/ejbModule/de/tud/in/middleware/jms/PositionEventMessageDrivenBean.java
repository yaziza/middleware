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

import de.tud.in.middleware.shipment.ShipmentPosition;
import de.tud.in.middleware.shipment.TruckManagement;

@MessageDriven(mappedName = "jms/PositionQueue")
public class PositionEventMessageDrivenBean implements MessageListener {

	/**
	 * Um die Queue im Glassfish anzulegen: asadmin create-jms-resource
	 * --restype javax.jms.Queue jms/PositionQueue
	 */

	@EJB
	private TruckManagement truckManagement;

	@Override
	public void onMessage(final Message message) {
		try {

			final String msgText = ((TextMessage) message).getText();
			System.out.println(msgText);

			final Document document = EventUtil.getXMLDocument(msgText);
			handleRequest(document);

			System.out.println("Message handled successfullly");
		} catch (final ParserConfigurationException e) {
			e.printStackTrace();
		} catch (final SAXException e) {
			e.printStackTrace();
		} catch (final IOException e) {
			e.printStackTrace();
		} catch (final JMSException e) {
			e.printStackTrace();
		}
	}

	private void handleRequest(final Document document) {
		long truckId;
		Double latitude;
		Double longitude;
		// TODO Hier noch ein wenig das Format überprüfen (heist das feld
		// wirklich positionevent...)
		final String truckIdStr = EventUtil.getNodeContent(document, "truckId");
		final String latStr = EventUtil.getNodeContent(document, "lat");
		final String longStr = EventUtil.getNodeContent(document, "long");

		truckId = Long.parseLong(truckIdStr);
		longitude = Double.parseDouble(longStr);
		latitude = Double.parseDouble(latStr);

		final ShipmentPosition position = new ShipmentPosition(latitude, longitude);
		truckManagement.changeTruckPosition(truckId, position);
	}
}
