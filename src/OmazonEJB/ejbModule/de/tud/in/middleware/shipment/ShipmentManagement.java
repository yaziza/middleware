package de.tud.in.middleware.shipment;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.tud.in.middleware.dao.ShipmentDAO;
import de.tud.in.middleware.order.CustomerOrder;

/**
 * Session Bean implementation class ShipmentManagement
 */
@Stateless
@WebService
@LocalBean
public class ShipmentManagement implements ShipmentManagementRemote {

	@EJB
	ShipmentDAO shipmentDAO;

	/**
	 * Default constructor.
	 */
	public ShipmentManagement() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ShipmentManagementRemote#getOrderId(long)
	 */
	@Override
	public long getOrderId(final Integer shipmentId) {
		return shipmentDAO.getOrderId(shipmentId);
	}

	/**
	 * @see ShipmentManagementRemote#getShipmentPosition(long)
	 */
	@Override
	public ShipmentPosition getShipmentPosition(final Integer shipmentId) {
		return shipmentDAO.getShipmentPosition(shipmentId);
	}

	/**
	 * @see ShipmentManagementRemote#setShipmentPosition(long, ShipmentPosition)
	 */
	@Override
	public void setShipmentPosition(final Integer shipmentId, final ShipmentPosition position) {
		shipmentDAO.setShipmentPosition(shipmentId, position);
	}

	/**
	 * @see ShipmentManagementRemote#addOrderForShipment(Shipment, long)
	 */
	@Override
	public void addOrderForShipment(final Integer shipmentId, final CustomerOrder order) {
		shipmentDAO.addOrderForShipment(shipmentId, order);
	}

}
