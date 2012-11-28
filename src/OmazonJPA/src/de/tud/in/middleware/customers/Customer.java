package de.tud.in.middleware.customers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import de.tud.in.middleware.order.CustomerOrder;

/**
 * Entity implementation class for Entity: Costumer
 * 
 */
@Entity
public class Customer implements Serializable {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String name;
	private List<CustomerOrder> orders;
	
	private static final long serialVersionUID = 1L;

	public Customer() {
		super();
		
		orders = new ArrayList<CustomerOrder>();
	}
	
	public Customer(String name) {
		super();
		this.name = name;
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@OneToMany(cascade=CascadeType.PERSIST)
	public List<CustomerOrder> getOrders() {
		return orders;
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + "]";
	}
}
