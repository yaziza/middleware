package de.tud.in.middleware.jms;

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
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
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
	public void onMessage(Message message) {
		try {

			String msgText = ((TextMessage) message).getText();
			System.out.println(msgText);

			Document document = getXMLDocument(msgText);
			handleRequest(document);

			System.out.println("Message handled successfullly");
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	private Document getXMLDocument(String msgText)
			throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();

		InputSource is = new InputSource(new StringReader(msgText));
		Document document = builder.parse(is);
		return document;
	}

	private void handleRequest(Document document) {
		long truckId;
		Double latitude;
		Double longitude;
		// TODO Hier noch ein wenig das Format überprüfen (heist das feld
		// wirklich positionevent...)
		String truckIdStr = getNodeContent(document, "truckId");
		String latStr = getNodeContent(document, "lat");
		String longStr = getNodeContent(document, "long");

		truckId = Long.parseLong(truckIdStr);
		longitude = Double.parseDouble(longStr);
		latitude = Double.parseDouble(latStr);

		ShipmentPosition position = new ShipmentPosition(latitude, longitude);
		truckManagement.changeTruckPosition(truckId, position);
	}

	private String getNodeContent(Document document, String name) {
		return document.getElementsByTagName(name).item(0).getTextContent();
	}

}
