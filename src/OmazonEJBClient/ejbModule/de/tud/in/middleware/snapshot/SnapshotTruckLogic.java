package de.tud.in.middleware.snapshot;

import java.io.Serializable;
import java.util.Map;

import de.tud.in.middleware.shipment.Shipment;
import de.tud.in.middleware.shipment.ShipmentPosition;
import de.tud.in.middleware.shipment.Truck;
import de.tud.in.middleware.shipment.TruckManagementRemote;

public final class SnapshotTruckLogic implements TruckManagementRemote, Serializable {

	private static final long serialVersionUID = -5873162471980344140L;
	private final Map<Integer, Truck> truckMap;

	public SnapshotTruckLogic(final Map<Integer, Truck> truckMap) {
		this.truckMap = truckMap;
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public long createTruck() {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public void addShipmentToTruck(final Integer truckId, final Shipment shipment) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public void removeShipmentFromTrack(final Integer truckId, final Integer shipmentId) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public void changeTruckPosition(final Integer truckId, final ShipmentPosition position) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

	@Override
	public double getTruckLatitude(final Integer truckId) {
		return truckMap.get(truckId).getPosition().getLastLatitude();
	}

	@Override
	public double getTruckLongitude(final Integer truckId) {
		return truckMap.get(truckId).getPosition().getLastLongitude();
	}

}
