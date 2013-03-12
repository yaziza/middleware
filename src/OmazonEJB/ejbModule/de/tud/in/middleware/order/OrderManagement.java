package de.tud.in.middleware.order;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.tud.in.middleware.dao.OrderDAO;
import de.tud.in.middleware.products.ProductInstance;

/**
 * Session Bean implementation class OrderManagement
 */
@WebService
@Stateless
@LocalBean
public class OrderManagement implements OrderManagementRemote, OrderManagementLocal {

	@EJB
	private OrderDAO orderDAO;

	public OrderManagement() {
		// nothing to do
	}

	@Override
	public Integer addOrderForCustomer(final List<ProductInstance> products, final Integer customerId) {
		final CustomerOrder order = orderDAO.addOrder(products, customerId);

		return order.getId();
	}

	@Override
	public void changeOrderState(final Integer orderId, final OrderState newState) {
		orderDAO.changeOrderState(orderId, newState);
	}
	
	@Override
	public void changeOrderStateString(Integer orderId, String newState) {
		orderDAO.changeOrderState(orderId, OrderState.getStateFromDescription(newState));
	}

	@Override
	public OrderState getOrderState(final Integer orderId) {
		return orderDAO.getOrderState(orderId);
	}

	@Override
	public String getOrderStateAsString(final Integer orderId) {
		final OrderState state = getOrderState(orderId);
		return state.toString();
	}

	@Override
	public List<Integer> getOrders() {
		List<Integer> orders = new ArrayList<Integer>();
		for (CustomerOrder order : orderDAO.getOrders())
			orders.add(order.getId());
		
		return orders;
	}

}
