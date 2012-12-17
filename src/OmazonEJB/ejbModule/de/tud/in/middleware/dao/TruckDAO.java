package de.tud.in.middleware.dao;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.tud.in.middleware.shipment.Shipment;
import de.tud.in.middleware.shipment.ShipmentPosition;
import de.tud.in.middleware.shipment.Truck;

/**
 * Session Bean implementation class TruckDAO
 */
@Stateless
@LocalBean
public class TruckDAO {

	@PersistenceContext
	EntityManager entityManager;

	/**
	 * Default constructor.
	 */
	public TruckDAO() {
		// TODO Auto-generated constructor stub
	}

	public void addShipmentToTruck(long truckId, Shipment shipment) {
		Truck truck = (Truck) entityManager.find(Truck.class, (int) truckId);
		truck.addShipmentToTruck(shipment);

		entityManager.persist(shipment);
		entityManager.persist(truck);
		entityManager.flush();
	}

	public void removeShipmentFromTrack(long truckId, long shipmentId) {
		Truck truck = (Truck) entityManager.find(Truck.class, (int) truckId);
		Shipment shipment = (Shipment) entityManager.find(Shipment.class,
				shipmentId);

		truck.removeShipmentFromTruck(shipment);
		entityManager.persist(truck);
	}

	public void changeTruckPosition(long truckId, ShipmentPosition position) {
		Truck truck = (Truck) entityManager.find(Truck.class, (int) truckId);
		truck.setPosition(position);
		entityManager.persist(truck);
	}

	public double getTruckLatitude(long truckId) {
		Truck truck = (Truck) entityManager.find(Truck.class, (int) truckId);
		return truck.getPosition().getLastLatitude();
	}

	public double getTruckLongitude(long truckId) {
		Truck truck = (Truck) entityManager.find(Truck.class, (int) truckId);
		return truck.getPosition().getLastLongitude();
	}

	public long addTruck(double latitude, double longitude) {
		ShipmentPosition position = new ShipmentPosition(latitude, longitude);
		Truck truck = new Truck();
		truck.setPosition(position);
		
		entityManager.persist(truck);
		entityManager.flush();
		
		return truck.getId();
	}
}
