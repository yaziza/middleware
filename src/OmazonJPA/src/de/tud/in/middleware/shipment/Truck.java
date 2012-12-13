package de.tud.in.middleware.shipment;

import de.tud.in.middleware.shipment.Shipment;
import de.tud.in.middleware.shipment.ShipmentPosition;
import java.io.Serializable;
import java.lang.Integer;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Truck
 *
 */
@Entity

public class Truck implements Serializable {

	   
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private ShipmentPosition position;
	@ManyToOne
	private Shipment shipments;
	private static final long serialVersionUID = 1L;

	public Truck() {
		super();
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
	public Shipment getShipments() {
		return this.shipments;
	}

	public void setShipments(Shipment shipments) {
		this.shipments = shipments;
	}
   
}
