package de.tud.in.middleware.shipment;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.tud.in.middleware.dao.TruckDAO;

/**
 * Session Bean implementation class TruckManagement
 */
@Stateless
@WebService
@LocalBean
public class TruckManagement implements TruckManagementRemote, TruckManagementLocal {

	@EJB
	TruckDAO truckDAO;

	/**
	 * Default constructor.
	 */
	public TruckManagement() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void addShipmentToTruck(final Integer truckId, final Shipment shipment) {
		truckDAO.addShipmentToTruck(truckId, shipment);
	}

	@Override
	public void removeShipmentFromTrack(final Integer truckId, final Integer shipmentId) {
		truckDAO.removeShipmentFromTrack(truckId, shipmentId);
	}

	@Override
	public void changeTruckPosition(final Integer truckId, final ShipmentPosition position) {
		truckDAO.changeTruckPosition(truckId, position);
	}

	@Override
	public double getTruckLatitude(final Integer truckId) {
		return truckDAO.getTruckLatitude(truckId);
	}

	@Override
	public double getTruckLongitude(final Integer truckId) {
		return truckDAO.getTruckLongitude(truckId);
	}

	@Override
	public long createTruck() {
		return truckDAO.createTruck();
	}
}
