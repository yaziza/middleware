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

		// truckId einlesen
		String truckIdStr = request.getParameter("truckId");
		String latitudeStr = request.getParameter("latitude");
		String longitudeStr = request.getParameter("longitude");

		try {
			truckId = Long.parseLong(truckIdStr);
			latitude = Double.parseDouble(latitudeStr);
			longitude = Double.parseDouble(longitudeStr);

		} catch (NumberFormatException e) {
			out.println(ERROR_MSG + "(Invalid TruckId Format)");
			return;
		}

		ShipmentPosition position = new ShipmentPosition(latitude,
				longitude);
		truckManagement.changeTruckPosition(truckId, position);

	} catch (NamingException e) {
		e.printStackTrace();
		out.println(ERROR_MSG);
		return;
	}
%>
<H2>Truck Position Changed</H2>
<p>
	The Truck with the TruckId
	<%=truckId%>
	has the new Latitude
	<%=latitude%>
	and the new Longitude
	<%=longitude%></p>

<a href="index.jsp">Home</a>
<%@ include file="/FootFile.jsp"%>
</body>
</html>