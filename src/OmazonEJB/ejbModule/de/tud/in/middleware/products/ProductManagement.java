package de.tud.in.middleware.products;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

/**
 * Session Bean implementation class ProductManagement
 */
@WebService
@Stateless
@LocalBean
public class ProductManagement implements ProductManagementRemote,
		ProductManagementLocal {

	public ProductManagement() {
	}

	@Override
	public long getNumberOfProducts() {
		return 5;
	}
}
