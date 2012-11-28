package de.tud.in.middleware.order;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import de.tud.in.middleware.products.ProductInstance;

@Entity
public final class CustomerOrder implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	
	private final List<ProductInstance> productInstances;
	private static final long serialVersionUID = 1L;

	public CustomerOrder() {
		super();
		productInstances = new ArrayList<ProductInstance>();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@OneToMany(cascade=CascadeType.PERSIST)
	public List<ProductInstance> getProductInstances() {
		return productInstances;
	}
	
	
}
