package de.tud.in.middleware.shipment;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Shipment implements Serializable{
	private static final long serialVersionUID = 1589893603759874751L;
	
	@Id
	@GeneratedValue
	public int id;
	public ShipmentPosition position = new ShipmentPosition();

	public Shipment() {
		// nothing to do
	}

	public int getId() {
		return id;
	}

	public ShipmentPosition getPosition() {
		return position;
	}

	public void setId(final int id) {
		this.id = id;
	}

	public void setPosition(final ShipmentPosition position) {
		this.position = position;
	}
}
