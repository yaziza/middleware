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
	
    /**
     * Default constructor. 
     */
    public ProductDAO() {
        // TODO Auto-generated constructor stub
    }
    
    public long getNumberOfProducts() {
    	long result;
    	Query query = entityManager.createQuery("select count(id) from Product id");
    	result = (Long) query.getSingleResult();
    	return result;
    }

	public String getProductDescription(long id) {
    	String format = String.format("select description from Product description where description.id = %d", id);
    	Query query = entityManager.createQuery(format);
    	Product product  =  (Product) query.getSingleResult();
    	
    	return product.getDescription();
	}

}
