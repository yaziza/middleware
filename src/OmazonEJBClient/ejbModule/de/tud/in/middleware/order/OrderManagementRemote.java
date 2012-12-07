package de.tud.in.middleware.order;

import java.util.List;

import javax.ejb.Remote;

import de.tud.in.middleware.products.ProductInstance;

@Remote
public interface OrderManagementRemote {

	/**
	 * @return Returns ID of added order.
	 */
	public long addOrderForCustomer(List<ProductInstance> products, long customerId);
	
	public void changeOrderState(long orderId, OrderState newState);
	
	public OrderState getOrderState(long orderId);
}
