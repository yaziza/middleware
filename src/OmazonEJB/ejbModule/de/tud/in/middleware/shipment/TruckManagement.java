package de.tud.in.middleware.shipment;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import de.tud.in.middleware.dao.TruckDAO;

/**
 * Session Bean implementation class TruckManagement
 */
@Stateless
@LocalBean
public class TruckManagement implements TruckManagementRemote,
		TruckManagementLocal {

	@EJB
	TruckDAO truckDAO;

	/**
	 * Default constructor.
	 */
	public TruckManagement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addShipmentToTruck(long truckId, Shipment shipment) {
		truckDAO.addShipmentToTruck(truckId, shipment);
	}

	@Override
	public void removeShipmentFromTrack(long truckId, long shipmentId) {
		truckDAO.removeShipmentFromTrack(truckId, shipmentId);
	}

	@Override
	public void changeTruckPosition(long truckId, ShipmentPosition position) {
		truckDAO.changeTruckPosition(truckId, position);
	}
}
