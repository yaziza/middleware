package de.tud.in.middleware.products;

import javax.ejb.Remote;

@Remote
public interface ProductManagementRemote {

	public long getNumberOfProducts();
	
	public String getProductDescription(long id);
}
