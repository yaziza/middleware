package de.tud.in.middleware.shipment;

import javax.ejb.Remote;

@Remote
public interface TruckManagementRemote {

	public long createTruck();

	public void addShipmentToTruck(long truckId, Shipment shipment);

	public void removeShipmentFromTrack(long truckId, long shipmentId);

	public void changeTruckPosition(long truckId, ShipmentPosition position);

	public double getTruckLatitude(long truckId);

	public double getTruckLongitude(long truckId);

}
