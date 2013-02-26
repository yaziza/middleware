package de.tud.in.middleware.dao;

import java.util.List;

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
		final Query query = entityManager.createQuery("select count(id) from Product id");
		result = (Long) query.getSingleResult();
		return result;
	}

	public String getProductDescription(final long id) {
		final Product product = entityManager.find(Product.class, (int) id);
		if (product == null) {
			return "Product dont exist!";
		}

		return product.getDescription();
	}

	public Product getProduct(final long id) {
		final Product product = entityManager.find(Product.class, (int) id);
		return product;
	}

	public long addProduct(final String description) {
		final Product product = new Product(description);
		entityManager.persist(product);
		entityManager.flush();

		return product.getId();
	}

	@SuppressWarnings("unchecked")
	public List<Product> getProducts() {
		final Query query = entityManager.createQuery("SELECT e FROM Product e");
		return query.getResultList();
	}

	public void changeProductDescription(final long id, final String description) {
		final Product product = entityManager.find(Product.class, (int) id);
		product.setDescription(description);

		entityManager.persist(product);
		entityManager.flush();
	}

	public void removeProduct(final long id) {
		final Product product = entityManager.find(Product.class, (int) id);

		entityManager.remove(product);
		entityManager.flush();
	}
}
