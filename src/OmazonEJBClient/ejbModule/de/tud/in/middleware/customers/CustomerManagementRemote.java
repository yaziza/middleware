package de.tud.in.middleware.customers;

import javax.ejb.Remote;

@Remote
public interface CustomerManagementRemote {

	public long getNumberOfCustomers();

	public String getCustomerName(long id);

	public long addCustomer(String name);
}
