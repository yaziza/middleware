<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <%@ include file="/HeadFile.jsp" %>
    <p>Please enter your customerId</p>
    <form action="OrderProcess.jsp" method="GET">
    	<input type="text" name="customerId">
    	<input type="submit" value="Continue">
    </form>
    
    <p>Please enter your orderId to change the orderState</p>
    <form action="OrderState.jsp" method="GET">
    	<input type="text" name="orderId">
    	<input type="submit" value="Continue">
    </form>
    
    <p>Please enter your orderId to trigger JMS to set the orderState to "delivered"</p>
    <form action="DeliveryEventServlet" method="GET">
    	<input type="text" name="shipmentId">
    	<input type="submit" value="Continue">
    </form>
    <%@ include file="/FootFile.jsp" %>
  </body>
</html> 
