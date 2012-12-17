package de.tud.in.middleware.shipment;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Shipment
 * 
 */
@Entity
public class Shipment implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer orderId;
	private ShipmentPosition position;

	private static final long serialVersionUID = 1L;

	public Shipment() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShipmentPosition getPosition() {
		return position;
	}

	public void setPosition(ShipmentPosition position) {
		this.position = position;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
}
