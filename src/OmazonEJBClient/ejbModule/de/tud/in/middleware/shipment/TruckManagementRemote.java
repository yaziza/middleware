package de.tud.in.middleware.shipment;

import javax.ejb.Remote;

@Remote
public interface TruckManagementRemote {

	public long createTruck();

	public void addShipmentToTruck(Integer truckId, Shipment shipment);

	public void removeShipmentFromTrack(Integer truckId, Integer shipmentId);

	public void changeTruckPosition(Integer truckId, ShipmentPosition position);

	public double getTruckLatitude(Integer truckId);

	public double getTruckLongitude(Integer truckId);

}
