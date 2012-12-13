package de.tud.in.middleware.shipment;

import java.io.Serializable;

public class ShipmentPosition implements Serializable {
	private static final long serialVersionUID = -6913549327352479194L;
	private static final double DEFAULT_STARTING_LATITUDE = 49.877643;
	private static final double DEFAULT_STARTING_LONGITUDE = 8.655004;

	private double lastLatitude = DEFAULT_STARTING_LATITUDE;
	private double lastLongitude = DEFAULT_STARTING_LONGITUDE;

	public ShipmentPosition() {
		// nothing to do
	}

	public ShipmentPosition(final double latitude, final double longitude) {
		lastLatitude = latitude;
		lastLongitude = longitude;
	}

	public double getLastLatitude() {
		return lastLatitude;
	}

	public double getLastLongitude() {
		return lastLongitude;
	}

	public void setLastLatitude(final double lastLatitude) {
		this.lastLatitude = lastLatitude;
	}

	public void setLastLongitude(final double lastLongitude) {
		this.lastLongitude = lastLongitude;
	}
}
