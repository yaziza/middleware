package de.tud.in.middleware.order;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

/**
 * Session Bean implementation class OrderManagement
 */
@Stateless
@LocalBean
public class OrderManagement implements OrderManagementRemote, OrderManagementLocal {

    /**
     * Default constructor. 
     */
    public OrderManagement() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public long addOrderForCustomer(CustomerOrder order, long customerId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void changeOrderState(long orderId, OrderState newState) {
		// TODO Auto-generated method stub
	}

	@Override
	public OrderState getOrderState(long orderId) {
		// TODO Auto-generated method stub
		return null;
	}

}
