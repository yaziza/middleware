package de.tud.in.middleware.customers;

import javax.ejb.Remote;

import de.tud.in.middleware.order.CustomerOrder;

@Remote
public interface CustomerManagementRemote {

	public long getNumberOfCustomers();

	public String getCustomerName(long id);

	public long addCustomer(String name);
	
	public long addOrderForCustomer(CustomerOrder order, long customerId);
}
