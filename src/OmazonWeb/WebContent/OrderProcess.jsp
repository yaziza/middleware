<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="java.util.*"%>    
<%@ page import="javax.naming.*"%>
<%@ page import="de.tud.in.middleware.customers.*"%>
<%@ page import="de.tud.in.middleware.products.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Order Process</title>
</head>
<body>

<%
	String ERROR_MSG = "Sorry, something went wrong..";

	Context ctx;

	CustomerManagementRemote cmr = null;
	ProductManagementRemote pmr = null;
	try {
		ctx = new InitialContext();
	
		pmr = (ProductManagementRemote) ctx
			.lookup("de.tud.in.middleware.products.ProductManagementRemote#de.tud.in.middleware.products.ProductManagementRemote");
	
		cmr = (CustomerManagementRemote) ctx
			.lookup("de.tud.in.middleware.customers.CustomerManagementRemote#de.tud.in.middleware.customers.CustomerManagementRemote");
	} catch (NamingException e) {
		e.printStackTrace();
		out.println(ERROR_MSG);
		return;
	}
%>

<h2>Create Order</h2>

<%
	String customerIdStr = request.getParameter("customerId");
	long customerId;
	try {
		customerId = Long.parseLong(customerIdStr);
	} catch(NumberFormatException e) {
		out.println(ERROR_MSG + "(Invalid CustomerId Format)");
		return;
	}
			
	String customerName = cmr.getCustomerName(customerId);
%>
<table>
	<tr>
		<td>Customer Id:</td>
		<td><%=customerIdStr %></td>
	</tr>
	<tr>
		<td>Customer Name:</td>
		<td><%=customerName %></td>
	</tr>	
</table>

<H2>Products</H2>
<%
	List<Product> products = pmr.getProducts();
%>

<form action="ExecuteOrder.jsp?customerId=<%= customerId %>" method="POST">

<table>
	<tr>
		<th>Name</th>
		<th>Amount</th>
	</tr>
	<% for(Product product:products) {  %>
		<tr>
			<td><%=product.getDescription() %></td>
			<td><input type="text" name="<%= product.getId() %>"></td>
		</tr>
	<% } %>
</table>

<button type="submit">Order now</button>
</form>


</body>
</html>