package de.tud.in.middleware.jms;

import java.io.IOException;
import java.io.StringReader;

import javax.jms.JMSException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public final class EventUtil {

	public static Document getXMLDocument(final String msgText) throws JMSException, ParserConfigurationException, SAXException, IOException {
		final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		builder = factory.newDocumentBuilder();

		final InputSource is = new InputSource(new StringReader(msgText));
		final Document document = builder.parse(is);
		return document;
	}

	/**
	 * @return Returns the value of given attribute in given document.
	 */
	public static String getNodeContent(final Document document, final String attributeName) {
		return document.getElementsByTagName(attributeName).item(0).getTextContent();
	}
}
