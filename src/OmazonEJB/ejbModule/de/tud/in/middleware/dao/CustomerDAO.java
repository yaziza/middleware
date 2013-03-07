package de.tud.in.middleware.dao;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import de.tud.in.middleware.customers.Customer;
import de.tud.in.middleware.order.CustomerOrder;
import de.tud.in.middleware.products.ProductInstance;

/**
 * Session Bean implementation class CustomerDAO
 */
@Stateless
@LocalBean
public class CustomerDAO {

	@PersistenceContext
	EntityManager entityManager;

	public CustomerDAO() {
		// TODO Auto-generated constructor stub
	}

	public long getNumberOfCustomers() {
		long result;
		final Query query = entityManager.createQuery("select count(id) from Customer id");
		result = (Long) query.getSingleResult();
		return result;
	}

	public String getCustomerName(final long id) {
		final Customer customer = entityManager.find(Customer.class, (int) id);
		if (customer == null) {
			return "Customer dont exist!";
		}

		return customer.getName();
	}

	public int addOrderForCustomer(final long id, final CustomerOrder order) {
		final Customer customer = entityManager.find(Customer.class, (int) id);

		customer.getOrders().add(order);

		for (final ProductInstance pi : order.getProductInstances()) {
			entityManager.persist(pi);
		}
		entityManager.persist(order);
		entityManager.persist(customer);

		entityManager.flush();

		return order.getId();
	}

	public long addCustomer(final String name, final String eMail) {
		final Customer customer = new Customer(name);
		customer.setEMail(eMail);
		entityManager.persist(customer);
		entityManager.flush();

		return customer.getId();
	}

	public void changeCustomerName(final long id, final String name) {
		final Customer customer = entityManager.find(Customer.class, (int) id);

		if (customer == null) {
			return;
		}

		customer.setName(name);

		entityManager.persist(customer);
		entityManager.flush();
	}

	public void removeCustomer(final long id) {
		final Customer customer = entityManager.find(Customer.class, (int) id);

		if (customer == null) {
			return;
		}

		entityManager.remove(customer);
		entityManager.flush();
	}

	@SuppressWarnings("unchecked")
	public List<Customer> getCustomers() {
		final Query query = entityManager.createQuery("SELECT e FROM Customer e");
		return query.getResultList();
	}
}
