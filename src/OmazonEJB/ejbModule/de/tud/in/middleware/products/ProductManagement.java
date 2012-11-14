package de.tud.in.middleware.products;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.tud.in.middleware.dao.products.ProductDAO;

/**
 * Session Bean implementation class ProductManagement
 */
@WebService
@Stateless
@LocalBean
public class ProductManagement implements ProductManagementRemote, ProductManagementLocal {

	@EJB 
	ProductDAO productDAO;
	
    /**
     * Default constructor. 
     */
    public ProductManagement() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public long getNumberOfProducts() {
		return productDAO.getNumberOfProducts();
	}

	@Override
	public String getProductDescription(long id) {
		return productDAO.getProductDescription(id);
	}

}
