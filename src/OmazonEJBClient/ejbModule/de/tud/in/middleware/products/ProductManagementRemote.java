package de.tud.in.middleware.products;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ProductManagementRemote {

	public long getNumberOfProducts();

	public String getProductDescription(Integer id);

	public long addProduct(String description);

	public List<Product> getProducts();

	public Product getProduct(Integer id);

	public void changeProductDescription(Integer id, String description);

	public void removeProduct(Integer id);
}
