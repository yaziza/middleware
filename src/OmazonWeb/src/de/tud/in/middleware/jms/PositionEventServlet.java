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
 * Servlet implementation class PositionEventServlet
 */
@WebServlet("/PositionEventServlet")
public class PositionEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Resource(mappedName = "jms/SimpleConnectionFactory")
	// = connection factorie's JNDI name
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "jms/PositionQueue")
	// = Queue's JNDI name
	private Queue queue;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PositionEventServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = new PrintWriter(response.getOutputStream());

		String truckId = request.getParameter("truckId");
		String laltitude = request.getParameter("latitude");
		String longitude = request.getParameter("longitude");

		javax.jms.Connection connection;

		try {
			connection = connectionFactory.createConnection();
			javax.jms.Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);

			TextMessage message = session.createTextMessage();

			String xmlStr = "<positionevent><truckId>" + truckId
					+ "</truckId><long>" + laltitude + "</long><lat>"
					+ longitude + "</lat></positionevent>";
			
			message.setText(xmlStr);
			messageProducer.send(message);

			pw.println("JMS Message produced");
			pw.println(xmlStr);

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pw.flush();
		pw.close();
	}

}
