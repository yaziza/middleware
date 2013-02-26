package de.tud.in.middleware.customers;

import javax.ejb.Remote;

@Remote
public interface CustomerManagementRemote {

	public long getNumberOfCustomers();

	public String getCustomerName(Integer id);

	public long addCustomer(String name, String eMail);

	public void changeCustomerName(Integer id, String name);

	public void removeCustomer(Integer id);

}
