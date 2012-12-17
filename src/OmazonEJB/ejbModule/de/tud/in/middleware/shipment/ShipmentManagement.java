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
    public long getOrderId(long shipmentId) {
    	return shipmentDAO.getOrderId(shipmentId);
    }

	/**
     * @see ShipmentManagementRemote#getShipmentPosition(long)
     */
    public ShipmentPosition getShipmentPosition(long shipmentId) {
		return shipmentDAO.getShipmentPosition(shipmentId);
    }

	/**
     * @see ShipmentManagementRemote#setShipmentPosition(long, ShipmentPosition)
     */
    public void setShipmentPosition(long shipmentId, ShipmentPosition position) {
    	shipmentDAO.setShipmentPosition(shipmentId, position);
    }

	/**
     * @see ShipmentManagementRemote#addOrderForShipment(Shipment, long)
     */
    public void addOrderForShipment(long shipmentId, CustomerOrder order) {
		shipmentDAO.addOrderForShipment(shipmentId, order);
    }

}
