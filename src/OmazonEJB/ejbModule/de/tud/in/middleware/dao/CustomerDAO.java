package de.tud.in.middleware.dao;

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
		Query query = entityManager
				.createQuery("select count(id) from Customer id");
		result = (Long) query.getSingleResult();
		return result;
	}

	public String getCustomerName(long id) {
		Customer customer = (Customer) entityManager.find(Customer.class,
				(int) id);
		if (customer == null)
			return "Customer dont exist!";

		return customer.getName();
	}

	public int addOrderForCustomer(long id, CustomerOrder order) {
		Customer customer = (Customer) entityManager.find(Customer.class,
				(int) id);

		customer.getOrders().add(order);

		for (ProductInstance pi : order.getProductInstances()) {
			entityManager.persist(pi);
		}
		entityManager.persist(order);
		entityManager.persist(customer);

		entityManager.flush();

		return order.getId();
	}

	public long addCustomer(String name) {
		Customer customer = new Customer(name);
		entityManager.persist(customer);
		entityManager.flush();

		return customer.getId();
	}

}
