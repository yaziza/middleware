package de.tud.in.middleware.customers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.tud.in.middleware.dao.customers.CustomerDAO;
import de.tud.in.middleware.order.CustomerOrder;
import de.tud.in.middleware.products.ProductInstance;

/**
 * Session Bean implementation class CustumerManagement
 */
@WebService
@Stateless
@LocalBean
public class CustomerManagement implements CustomerManagementRemote,
		CustomerManagementLocal {

	@EJB
	CustomerDAO customerDAO;

	public CustomerManagement() {
	}

	@Override
	public long getNumberOfCustomers() {
		return customerDAO.getNumberOfCustomers();
	}

	@Override
	public String getCustomerName(long id) {
		return customerDAO.getCustomerName(id);
	}

	@Override
	public long addCustomer(String name) {
		return customerDAO.addCustomer(name);
	}
	
	
	@Override
	public long addOrderForCustomer(CustomerOrder order, long customerId) {
		return customerDAO.addOrderForCustomer(1, order);
	}

}
