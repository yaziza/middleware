package de.tud.in.middleware.dao;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.tud.in.middleware.order.CustomerOrder;
import de.tud.in.middleware.shipment.Shipment;
import de.tud.in.middleware.shipment.ShipmentPosition;

/**
 * Session Bean implementation class ShipmentDAO
 */
@Stateless
@LocalBean
public class ShipmentDAO {

	@PersistenceContext
	private EntityManager entityManager;

	@EJB
	private CustomerDAO customerDAO;

	/**
	 * Default constructor.
	 */
	public ShipmentDAO() {
		// TODO Auto-generated constructor stub
	}

	public long getOrderId(final long shipmentId) {
		final Shipment shipment = entityManager.find(Shipment.class, (int) shipmentId);
		return shipment.getOrderId();
	}

	public ShipmentPosition getShipmentPosition(final long shipmentId) {
		final Shipment shipment = entityManager.find(Shipment.class, (int) shipmentId);
		return shipment.getPosition();
	}

	public void setShipmentPosition(final long shipmentId, final ShipmentPosition position) {
		final Shipment shipment = entityManager.find(Shipment.class, (int) shipmentId);

		shipment.setPosition(position);
		entityManager.persist(shipment);
		entityManager.flush();
	}

	public void addOrderForShipment(final long shipmentId, final CustomerOrder order) {
		final Shipment shipment = entityManager.find(Shipment.class, (int) shipmentId);
		entityManager.persist(order);
		shipment.setId(order.getId());
		entityManager.persist(shipment);
		entityManager.flush();
	}

}
