package de.tud.in.middleware.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import de.tud.in.middleware.shipment.Shipment;
import de.tud.in.middleware.shipment.ShipmentPosition;

/**
 * Session Bean implementation class TruckDAO
 */
@Stateless
@LocalBean
public class TruckDAO {

	/**
	 * Default constructor.
	 */
	public TruckDAO() {
		// TODO Auto-generated constructor stub
	}

	public void addShipmentToTruck(long truckId, Shipment shipment) {
		// TODO Auto-generated method stub

	}

	public void removeShipmentFromTrack(long truckId, long shipmentId) {
		// TODO Auto-generated method stub

	}

	public void changeTruckPosition(long truckId, ShipmentPosition position) {
		// TODO Auto-generated method stub

	}

}
