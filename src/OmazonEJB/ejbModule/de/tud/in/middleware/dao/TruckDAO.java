package de.tud.in.middleware.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

	public void addShipmentToTruck(final long truckId, final Shipment shipment) {
		final Truck truck = entityManager.find(Truck.class, (int) truckId);
		truck.addShipmentToTruck(shipment);

		entityManager.persist(shipment);
		entityManager.persist(truck);
		entityManager.flush();
	}

	public void removeShipmentFromTrack(final long truckId, final long shipmentId) {
		final Truck truck = entityManager.find(Truck.class, (int) truckId);
		final Shipment shipment = entityManager.find(Shipment.class, shipmentId);

		truck.removeShipmentFromTruck(shipment);
		entityManager.persist(truck);
	}

	public void changeTruckPosition(final long truckId, final ShipmentPosition position) {
		final Truck truck = entityManager.find(Truck.class, (int) truckId);
		truck.setPosition(position);
		entityManager.persist(truck);
	}

	public double getTruckLatitude(final long truckId) {
		final Truck truck = entityManager.find(Truck.class, (int) truckId);
		return truck.getPosition().getLastLatitude();
	}

	public double getTruckLongitude(final long truckId) {
		final Truck truck = entityManager.find(Truck.class, (int) truckId);
		return truck.getPosition().getLastLongitude();
	}

	public long createTruck() {
		final Truck truck = new Truck();

		entityManager.persist(truck);
		entityManager.flush();

		return truck.getId();
	}

	@SuppressWarnings("unchecked")
	public List<Truck> getTrucks() {
		final Query query = entityManager.createQuery("SELECT e FROM Truck e");
		return query.getResultList();
	}
}
