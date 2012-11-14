package de.tud.in.middleware.customers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.tud.in.middleware.costumers.CustomerManagementLocal;
import de.tud.in.middleware.costumers.CustomerManagementRemote;
import de.tud.in.middleware.dao.customers.CustomerDAO;

/**
 * Session Bean implementation class CustomerManagement
 */
@WebService
@Stateless
@LocalBean
public class CustomerManagement implements CustomerManagementRemote, CustomerManagementLocal {

	@EJB 
	CustomerDAO customerDAO;
    /**
     * Default constructor. 
     */
    public CustomerManagement(){
        // TODO Auto-generated constructor stub
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

}
