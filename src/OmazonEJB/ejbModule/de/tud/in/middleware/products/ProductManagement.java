package de.tud.in.middleware.products;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.tud.in.middleware.dao.ProductDAO;

/**
 * Session Bean implementation class ProductManagement
 */
@WebService
@Stateless
@LocalBean
public class ProductManagement implements ProductManagementRemote,
		ProductManagementLocal {

	@EJB
	ProductDAO productDAO;

	public ProductManagement() {
	}

	@Override
	public long getNumberOfProducts() {
		return productDAO.getNumberOfProducts();
	}

	@Override
	public String getProductDescription(long id) {
		return productDAO.getProductDescription(id);
	}

	@Override
	public long addProduct(String description) {
		return productDAO.addProduct(description);
	}

	@Override
	public List<Product> getProducts() {
		return productDAO.getProducts();
	}

	@Override
	public Product getProduct(long id) {
		return productDAO.getProduct(id);
	}

	@Override
	public void changeProductDescription(long id, String description) {
		productDAO.changeProductDescription(id, description);
	}

	@Override
	public void removeProduct(long id) {
		productDAO.removeProduct(id);
	}
}
