<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="de.tud.in.middleware.order.*"%>
<%@ page import="de.tud.in.middleware.products.*"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/HeadFile.jsp" %>
<%
	String ERROR_MSG = "Sorry, something went wrong..";

	Context ctx;

	OrderManagementRemote omr = null;
	ProductManagementRemote pmr = null;
	Integer orderId = 0;
	OrderState newOrderState = null;
	
	try {
		ctx = new InitialContext();
	
		omr = (OrderManagementRemote) ctx
			.lookup("de.tud.in.middleware.order.OrderManagementRemote#de.tud.in.middleware.order.OrderManagementRemote");
		
		//pmr = (ProductManagementRemote) ctx
		//		.lookup("de.tud.in.middleware.products.ProductManagementRemote#de.tud.in.middleware.products.ProductManagementRemote");
	
		// OrderId einlesen
		String orderIdStr = request.getParameter("orderId");
		String orderStateStr =  request.getParameter("orderState");
		
		try {
			orderId = Integer.parseInt(orderIdStr);
		} catch(NumberFormatException e) {
			out.println(ERROR_MSG + "(Invalid OrderId Format)");
			return;
		}
		
		newOrderState = OrderState.valueOf(orderStateStr);
	   		
		omr.changeOrderState(orderId, newOrderState);		
	} catch (NamingException e) {
		e.printStackTrace();
		out.println(ERROR_MSG);
		return;
	}
%>
<H2>Order State Changed</H2>
<p>The Order with the OrderId <%=orderId %> has the new OrderState <%= newOrderState %></p>

<a href="index.jsp">Home</a>
<%@ include file="/FootFile.jsp" %>
</body>
</html>