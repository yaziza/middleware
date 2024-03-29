package de.tud.in.middleware.snapshot;

import java.io.Serializable;
import java.util.Map;

import de.tud.in.middleware.order.CustomerOrder;
import de.tud.in.middleware.shipment.Shipment;
import de.tud.in.middleware.shipment.ShipmentManagementRemote;
import de.tud.in.middleware.shipment.ShipmentPosition;

public final class SnapshotShipmentLogic implements ShipmentManagementRemote, Serializable {

	private static final long serialVersionUID = 5237569615680796350L;
	private final Map<Integer, Shipment> shipmentMap;

	public SnapshotShipmentLogic(final Map<Integer, Shipment> shipmentMap) {
		this.shipmentMap = shipmentMap;
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public void addOrderForShipment(final Integer shipmentId, final CustomerOrder order) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

	@Override
	public long getOrderId(final Integer shipmentId) {
		return shipmentMap.get(shipmentId).getOrderId();
	}

	@Override
	public ShipmentPosition getShipmentPosition(final Integer shipmentId) {
		return shipmentMap.get(shipmentId).getPosition();
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public void setShipmentPosition(final Integer shipmentId, final ShipmentPosition position) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

}
