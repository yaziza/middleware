package de.tud.in.middleware.shipment;

import javax.ejb.Remote;

import de.tud.in.middleware.order.CustomerOrder;

@Remote
public interface ShipmentManagementRemote {

	public void addOrderForShipment(long shipmentId, CustomerOrder order);

	public long getOrderId(long shipmentId);

	public ShipmentPosition getShipmentPosition(long shipmentId);

	public void setShipmentPosition(long shipmentId, ShipmentPosition position);
}
