package de.tud.in.middleware.products;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ProductManagementRemote {

	public long getNumberOfProducts();
	
	public String getProductDescription(long id);
	
	long addProduct(String description);
	
	public List<Product> getProducts();
}
