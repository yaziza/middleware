package de.tud.in.middleware.snapshot;

import java.util.List;
import java.util.Map;

import de.tud.in.middleware.customers.Customer;
import de.tud.in.middleware.customers.CustomerManagementRemote;

public final class SnapshotCustomerLogic implements CustomerManagementRemote {

	private final Map<Integer, Customer> customerMap;

	public SnapshotCustomerLogic(final Map<Integer, Customer> customerMap) {
		this.customerMap = customerMap;
	}

	@Override
	public long getNumberOfCustomers() {
		return customerMap.size();
	}

	@Override
	public String getCustomerName(final Integer id) {
		return customerMap.get(id).getName();
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public long addCustomer(final String name, final String eMail) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public void changeCustomerName(final Integer id, final String name) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public void removeCustomer(final Integer id) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

	@Override
	public List<Customer> getCustomers() {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

}
