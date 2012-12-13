package de.tud.in.middleware.jms;

import java.io.IOException;
import java.io.StringReader;

import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import de.tud.in.middleware.order.OrderManagement;
import de.tud.in.middleware.order.OrderState;
import de.tud.in.middleware.shipment.ShipmentManagement;
import de.tud.in.middleware.shipment.ShipmentPosition;
import de.tud.in.middleware.shipment.TruckManagement;

@MessageDriven(mappedName = "jms/PositionQueue")
public class PositionEventMessageDrivenBean implements MessageListener {

	@EJB
	private TruckManagement truckManagement;

	@Override
	public void onMessage(Message message) {

		try {

			String msgText = message.toString();
			System.out.println(msgText);

			Document document = getXMLDocument(msgText);

			// TODO Hier noch ein wenig das Format überprüfen (heist das feld
			// wirklich deliveryevent...)
			String truckIdStr = document.getElementById("truckId")
					.getTextContent();
			String longStr = document.getElementById("long").getTextContent();
			String latStr = document.getElementById("lat").getTextContent();

			long truckId = Long.parseLong(truckIdStr);
			double longitude = Double.parseDouble(longStr);
			double latitude = Double.parseDouble(latStr);

			ShipmentPosition position = new ShipmentPosition(latitude,
					longitude);
			truckManagement.changeTruckPosition(truckId, position);

			System.out.println("Message handled successfullly");
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
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

}
