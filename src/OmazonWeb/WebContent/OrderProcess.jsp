<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
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
<h2>Willkommen</h2>

<%
	String customerNumberStr = request.getParameter("customer");
	out.println("Kundennummer: " + customerNumberStr);
%>

<H2>Select Products</H2>


<%
Context ctx;

CustomerManagementRemote cmr;
ProductManagementRemote pmr;
try {
	ctx = new InitialContext();
	
	pmr = (ProductManagementRemote) ctx
			.lookup("de.tud.in.middleware.products.ProductManagementRemote#de.tud.in.middleware.products.ProductManagementRemote");

	pmr.getProducts();	
	cmr = (CustomerManagementRemote) ctx
			.lookup("de.tud.in.middleware.customers.CustomerManagementRemote#de.tud.in.middleware.customers.CustomerManagementRemote");

	
} catch (NamingException e) {
	e.printStackTrace();
}


%>
</body>
</html>