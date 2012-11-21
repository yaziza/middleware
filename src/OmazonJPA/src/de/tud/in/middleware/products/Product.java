package de.tud.in.middleware.products;

import java.io.Serializable;
import java.lang.Integer;
import java.lang.String;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Product
 * 
 */
@Entity
public class Product implements Serializable {

	@Id
	@GeneratedValue
	private Integer id;
	private String description;
	private static final long serialVersionUID = 1L;

	public Product() {
		super();
	}

	public Product(String description) {
		super();
		this.description = description;
	}
	
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
