package de.tud.in.middleware.customers;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.tud.in.middleware.dao.CustomerDAO;

/**
 * Session Bean implementation class CustumerManagement
 */
@WebService
@Stateless
@LocalBean
public class CustomerManagement implements CustomerManagementRemote, CustomerManagementLocal {

	@EJB
	CustomerDAO customerDAO;

	public CustomerManagement() {
	}

	@Override
	public long getNumberOfCustomers() {
		return customerDAO.getNumberOfCustomers();
	}

	@Override
	public String getCustomerName(final Integer id) {
		return customerDAO.getCustomerName(id);
	}

	@Override
	public long addCustomer(final String name, final String eMail) {
		return customerDAO.addCustomer(name, eMail);
	}

	@Override
	public void changeCustomerName(final Integer id, final String name) {
		customerDAO.changeCustomerName(id, name);
	}

	@Override
	public void removeCustomer(final Integer id) {
		customerDAO.removeCustomer(id);
	}
}
