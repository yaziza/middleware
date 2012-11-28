package de.tud.in.middleware.products;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import de.tud.in.middleware.customers.CustomerManagementRemote;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ProductServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		try {
			printContent(response);

		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	private void printContent(HttpServletResponse response)
			throws NamingException, IOException {
		Context ctx;
		ProductManagementRemote pmr;
		ctx = new InitialContext();
		pmr = (ProductManagementRemote) ctx
				.lookup("de.tud.in.middleware.products.ProductManagementRemote#de.tud.in.middleware.products.ProductManagementRemote");

		PrintWriter pw = new PrintWriter(response.getOutputStream());
		pw.println("<html><head><title>Product Overview</title></head><body><h1>Product Overview</h1>");
		pw.println("<p>Anzahl Produkte: " + pmr.getNumberOfProducts() + "</p>");
		pw.println("</body></html>");
		pw.flush();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
