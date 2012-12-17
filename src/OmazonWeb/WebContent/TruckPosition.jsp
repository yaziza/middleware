<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="javax.naming.*"%>
<%@ page import="de.tud.in.middleware.shipment.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="/HeadFile.jsp"%>
<%
	String ERROR_MSG = "Sorry, something went wrong..";

	Context ctx;

	TruckManagementRemote truckManagement = null;

	long truckId = 0;
	double latitude = 0;
	double longitude = 0;

	try {
		ctx = new InitialContext();

		truckManagement = (TruckManagementRemote) ctx
				.lookup("de.tud.in.middleware.shipment.TruckManagementRemote#de.tud.in.middleware.shipment.TruckManagementRemote");

		// TruckId einlesen
		String truckIdStr = request.getParameter("truckId");


		try {
			truckId = Long.parseLong(truckIdStr);
		} catch (NumberFormatException e) {
			out.println(ERROR_MSG + "(Invalid TruckId Format)");
			return;
		}

		try {

			latitude = truckManagement.getTruckLatitude(truckId);
			longitude = truckManagement.getTruckLongitude(truckId);


		} catch (NullPointerException e) {
			out.println("Null...");
			return;
		}
	} catch (NamingException e) {
		e.printStackTrace();
		out.println(ERROR_MSG);
		return;
	}
%>
<H2>Current Truck Position</H2>
<p>
	The Truck with the TruckId
	<%=truckId%>
	has the Latitude
	<%=latitude%>
	and the longitude
	<%=longitude%></p>

<H2>Change Truck Position</H2>
<p>Please enter the new Position</p>
<form action="ChangeTruckPosition.jsp">
	<input type="text"  name="latitude"> 
	<input type="text"  name="longitude"> 
	<input type="hidden" value="<%=latitude%>" name="latitude"> 
	<input type="hidden" value="<%=longitude%>" name="longitude">
	<input type="hidden" value="<%=truckId%>" name="truckId">  
	<input type="submit" value="Continue">
</form>
<a href="index.jsp">Home</a>
<%@ include file="/FootFile.jsp"%>
</body>
</html>