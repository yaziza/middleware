package de.tud.in.middleware.order;

import javax.ejb.Remote;

@Remote
public interface OrderManagementRemote {

	/**
	 * @return Returns ID of added order.
	 */
	public long addOrderForCustomer(CustomerOrder order, long customerId);
	
	public void changeOrderState(long orderId, OrderState newState);
	
	public OrderState getOrderState(long orderId);
}
