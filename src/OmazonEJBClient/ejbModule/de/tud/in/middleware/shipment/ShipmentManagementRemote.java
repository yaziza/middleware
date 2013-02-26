package de.tud.in.middleware.shipment;

import javax.ejb.Remote;

import de.tud.in.middleware.order.CustomerOrder;

@Remote
public interface ShipmentManagementRemote {

	public void addOrderForShipment(Integer shipmentId, CustomerOrder order);

	public long getOrderId(Integer shipmentId);

	public ShipmentPosition getShipmentPosition(Integer shipmentId);

	public void setShipmentPosition(Integer shipmentId, ShipmentPosition position);
}
