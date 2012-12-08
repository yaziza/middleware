<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="de.tud.in.middleware.order.*"%>
<%@ page import="de.tud.in.middleware.products.*"%>
    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Execute Order</title>
</head>
<body>

<%
	String ERROR_MSG = "Sorry, something went wrong..";

	Context ctx;

	OrderManagementRemote omr = null;
	ProductManagementRemote pmr = null;
	long orderId = 0;
	
	try {
		ctx = new InitialContext();
	
		omr = (OrderManagementRemote) ctx
			.lookup("de.tud.in.middleware.order.OrderManagementRemote#de.tud.in.middleware.order.OrderManagementRemote");
		
		pmr = (ProductManagementRemote) ctx
				.lookup("de.tud.in.middleware.products.ProductManagementRemote#de.tud.in.middleware.products.ProductManagementRemote");
	
		// Kundennummer einlesen
		String customerIdStr = request.getParameter("customerId");
		long customerId;
		try {
			customerId = Long.parseLong(customerIdStr);
		} catch(NumberFormatException e) {
			out.println(ERROR_MSG + "(Invalid CustomerId Format)");
			return;
		}
		
		// Produktinstanzen einlesen
   		List<ProductInstance> productInstances = new ArrayList<ProductInstance>();
   		Map<String, String[]> paras = request.getParameterMap();
   		for(String s:paras.keySet()) {
   			if(!s.equals("customerId")) {
   				ProductInstance productInstance = new ProductInstance();
   				
   				long productId = Long.parseLong(s); //TODO catch number ex
   				Product product = pmr.getProduct(productId);
   				productInstance.setProduct(product);
   				
   				String amountStr = paras.get(s)[0];
   				int amount = Integer.parseInt(amountStr); //TODO catch number ex
   				productInstance.setAmount(amount);
   				
   				productInstances.add(productInstance);
   			}
   		}
   		
		orderId = omr.addOrderForCustomer(productInstances, customerId);
	} catch (NamingException e) {
		e.printStackTrace();
		out.println(ERROR_MSG);
		return;
	}
%>
<h1>Order has been executed...</h1>
<p>Your order with the orderId <%=orderId %> has been executed</p>
<a href="index.jsp">Home</a>

</body>
</html>