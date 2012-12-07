package de.tud.in.middleware.shipment;

import javax.ejb.Remote;

@Remote
public interface ShipmentManagementRemote {
	/**
	 * @return Returns the ID of the added shipment.
	 */
	public long addShipmentForOrder(Shipment shipment, long orderId);

	public long getOrderId(long shipmentId);

	public ShipmentPosition getShipmentPosition(long shipmentId);

	public void setShipmentPosition(long shipmentId, ShipmentPosition position);
}
