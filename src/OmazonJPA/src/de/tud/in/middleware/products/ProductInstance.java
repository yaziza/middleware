package de.tud.in.middleware.products;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public final class ProductInstance implements Serializable {
	@Id
	@GeneratedValue
	private Integer id;
	private Product product;
	private int amount;

	private static final long serialVersionUID = 1L;

	public Integer getId() {
		return id;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(final Product product) {
		this.product = product;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(final int amount) {
		this.amount = amount;
	}
}
