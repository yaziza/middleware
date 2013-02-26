package de.tud.in.middleware.shipment;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * Entity implementation class for Entity: Truck
 * 
 */
@Entity
public class Truck implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private ShipmentPosition position;
	private List<Shipment> shipments;
	private static final long serialVersionUID = 1L;

	public Truck() {
		super();
		position = new ShipmentPosition();
	}

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public ShipmentPosition getPosition() {
		return position;
	}

	public void setPosition(final ShipmentPosition position) {
		this.position = position;
	}

	@OneToMany(cascade = CascadeType.PERSIST)
	public List<Shipment> getShipments() {
		return shipments;
	}

	@OneToMany(cascade = CascadeType.PERSIST)
	public void setShipments(final List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public void addShipmentToTruck(final Shipment shipment) {
		shipment.setPosition(position);
		shipments.add(shipment);
	}

	public void removeShipmentFromTruck(final Shipment shipment) {
		shipments.remove(shipment);
	}
}
