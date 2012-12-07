package de.tud.in.middleware.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.tud.in.middleware.customers.Customer;
import de.tud.in.middleware.order.CustomerOrder;
import de.tud.in.middleware.order.OrderState;
import de.tud.in.middleware.products.ProductInstance;

@Stateless
@LocalBean
public class OrderDAO {

	@PersistenceContext
	private EntityManager entityManager;
	@EJB
	private CustomerDAO customerDAO;
	
	public OrderDAO(){
		// nothing to do
	}

	/**
	 * @return Returns added order.
	 */
	public CustomerOrder addOrder(List<ProductInstance> products, long customerId) {
		CustomerOrder order = new CustomerOrder();
		// XXX long int
		Customer customer = (Customer) entityManager.find(Customer.class, (int) customerId);
		order.setCustomer(customer);
		
		customerDAO.addOrderForCustomer(customerId, order);
		
		entityManager.persist(order);
		entityManager.flush();
		return order;
	}

	public void changeOrderState(long orderId, OrderState newState) {
		// XXX long int
		CustomerOrder order = getOrder(orderId);
		order.setState(newState);
	}

	private CustomerOrder getOrder(long orderId) {
		CustomerOrder order = entityManager.find(CustomerOrder.class, (int) orderId);
		return order;
	}

	public OrderState getOrderState(long orderId) {
		// XXX How do we want to handle null ?
		return getOrder(orderId).getState();
	}
}
