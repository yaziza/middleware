package de.tud.in.middleware.order;

import javax.ejb.Remote;

@Remote
public interface OrderManagementRemote {

	public long addOrderForCustomer(CustomerOrder order, long customerId);
	
	public long changeOrderState(long orderId, OrderState newState);
	
	public OrderState getOrderState(long orderId);
}
