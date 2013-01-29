package de.tud.in.middleware.shipment;

import de.tud.in.middleware.shipment.Shipment;
import de.tud.in.middleware.shipment.ShipmentPosition;
import java.io.Serializable;
import java.lang.Integer;
import java.util.List;

import javax.persistence.*;

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
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShipmentPosition getPosition() {
		return this.position;
	}

	public void setPosition(ShipmentPosition position) {
		this.position = position;
	}

	@OneToMany(cascade = CascadeType.PERSIST)
	public List<Shipment> getShipments() {
		return this.shipments;
	}

	@OneToMany(cascade = CascadeType.PERSIST)
	public void setShipments(List<Shipment> shipments) {
		this.shipments = shipments;
	}

	public void addShipmentToTruck(Shipment shipment) {
		shipment.setPosition(this.position);
		shipments.add(shipment);
	}

	public void removeShipmentFromTruck(Shipment shipment) {
		shipments.remove(shipment);
	}
}
