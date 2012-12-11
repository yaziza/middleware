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
 * Servlet implementation class TestServlet
 */
@WebServlet("/DeliveryEventServlet")
public class DeliveryEventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    @Resource(mappedName = "jms/SimpleConnectionFactory")
	// = connection factorie's JNDI name
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "jms/DeliveryQueue")
	// = Queue's JNDI name
	private Queue queue;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter pw = new PrintWriter(response.getOutputStream());

		String shipmentIdStr = request.getParameter("shipmentId");
		javax.jms.Connection connection;
		try {
			connection = connectionFactory.createConnection();
			javax.jms.Session session = connection.createSession(false,
					Session.AUTO_ACKNOWLEDGE);
			MessageProducer messageProducer = session.createProducer(queue);
			
			TextMessage message = session.createTextMessage();
			
			String xmlStr = "<deliveryevent><shipmentId>" + shipmentIdStr + "</shipmentId></deliveryevent>";
			message.setText(xmlStr);
			messageProducer.send(message);
			
			pw.println("JMS Message produced");
			pw.println(xmlStr);
		} catch (JMSException e) {
			pw.println("Fehler " + e);
		}
		
		pw.flush();
		pw.close();
	}

}
