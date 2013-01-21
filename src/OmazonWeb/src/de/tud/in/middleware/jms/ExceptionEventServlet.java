package de.tud.in.middleware.jms;

import java.io.IOException;
import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet for ExceptionEvent creation.
 */
@WebServlet("/ExceptionEventServlet")
public class ExceptionEventServlet extends HttpServlet {
	private static final long serialVersionUID = -3256446671248485803L;
	private static final String xmlFormat = "<exceptionEvent><truckId>%s</truckId>" +
			"<exceptionDescription>%s</exceptionDescription></exceptionEvent>";

	@Resource(mappedName = "jms/SimpleConnectionFactory")
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "jms/ExceptionQueue")
	private Queue queue;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException,
			IOException {
		final PrintWriter pw = new PrintWriter(response.getOutputStream());

		final String truckId = request.getParameter("truckId");
		final String description = request.getParameter("exceptionDescription");

		javax.jms.Connection connection;
		try {
			connection = connectionFactory.createConnection();
			final javax.jms.Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			final MessageProducer messageProducer = session.createProducer(queue);

			final TextMessage message = session.createTextMessage();
			final String xmlStr = String.format(xmlFormat, truckId, description);
			message.setText(xmlStr);
			messageProducer.send(message);

			pw.println("JMS exception message produced.");
			pw.println(xmlStr);
		} catch (final JMSException e) {
			pw.println("Somesthing went wrong: " + e);
		}

		pw.flush();
		pw.close();
	}
}
