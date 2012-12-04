package de.tud.in.middleware.dao.products;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.tud.in.middleware.products.Product;

/**
 * Session Bean implementation class ProductDAO
 */
@Stateless
@LocalBean
public class ProductDAO {

	@PersistenceContext
	EntityManager entityManager;

	public ProductDAO() {
	}

	public long getNumberOfProducts() {
		long result;
		Query query = entityManager
				.createQuery("select count(id) from Product id");
		result = (Long) query.getSingleResult();
		return result;
	}

	public String getProductDescription(long id) {
		Product product = (Product) entityManager.find(Product.class, (int) id);
		if (product == null)
			return "Product dont exist!";

		return product.getDescription();
	}

	public long addProduct(String description) {
		Product product = new Product(description);
		entityManager.persist(product);
		entityManager.flush();
		
		return product.getId();
	}

}
