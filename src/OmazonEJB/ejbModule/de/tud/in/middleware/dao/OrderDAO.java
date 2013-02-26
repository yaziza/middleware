package de.tud.in.middleware.dao;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import de.tud.in.middleware.customers.Customer;
import de.tud.in.middleware.mail.MailHandler;
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

	public OrderDAO() {
		// nothing to do
	}

	/**
	 * @return Returns added order.
	 */
	public CustomerOrder addOrder(final List<ProductInstance> products, final long customerId) {
		final CustomerOrder order = new CustomerOrder();
		order.setState(OrderState.INIT);
		// XXX long int
		final Customer customer = entityManager.find(Customer.class, (int) customerId);
		order.setCustomer(customer);

		MailHandler.sendMail(customer.getEMail(), "Order Confirmation Omazon", "<H2>Dear " + customer.getName()
				+ "</H2>Your order is confirmed.");

		customerDAO.addOrderForCustomer(customerId, order);

		entityManager.persist(order);
		entityManager.flush();
		return order;
	}

	public void changeOrderState(final long orderId, final OrderState newState) {
		// XXX long int
		final CustomerOrder order = getOrder(orderId);
		order.setState(newState);
	}

	private CustomerOrder getOrder(final long orderId) {
		final CustomerOrder order = entityManager.find(CustomerOrder.class, (int) orderId);
		return order;
	}

	public OrderState getOrderState(final long orderId) {
		// XXX How do we want to handle null ?
		return getOrder(orderId).getState();
	}
}
