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
	OrderState orderState = null;
	
	try {
		ctx = new InitialContext();
	
		omr = (OrderManagementRemote) ctx
			.lookup("de.tud.in.middleware.order.OrderManagementRemote#de.tud.in.middleware.order.OrderManagementRemote");
		
		//pmr = (ProductManagementRemote) ctx
		//		.lookup("de.tud.in.middleware.products.ProductManagementRemote#de.tud.in.middleware.products.ProductManagementRemote");
	
		// OrderId einlesen
		String orderIdStr = request.getParameter("orderId");
		
		try {
			orderId = Integer.parseInt(orderIdStr);
		} catch(NumberFormatException e) {
			out.println(ERROR_MSG + "(Invalid OrderId Format)");
			return;
		}
	   		
		try {
		orderState = omr.getOrderState(orderId);
		} catch(NullPointerException e) {
			out.println("Null...");
			return;
		}
	} catch (NamingException e) {
		e.printStackTrace();
		out.println(ERROR_MSG);
		return;
	}
%>
<H2>Current Order State</H2>
<p>The Order with the OrderId <%=orderId %> has the OrderState <%= orderState %></p>
<H2>Change Order State</H2>
<p>Please select the new OrderState</p>
<form action="ChangeOrderState.jsp">
<%
	for(OrderState s:OrderState.values()) {
%>
	<p><input type="radio" value="<%=s %>" name="orderState"><%=s %></p>
<%
	}
%>
<input type="hidden" value="<%=orderId %>" name="orderId">
<input type="submit" value="Continue">
</form>
<a href="index.jsp">Home</a>
<%@ include file="/FootFile.jsp" %>
</body>
</html>