package de.tud.in.middleware.nativeClient;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.tud.in.middleware.customers.CustomerManagementRemote;
import de.tud.in.middleware.order.CustomerOrder;
import de.tud.in.middleware.products.ProductInstance;

public class NativeClient {
	/**
	 * @param args
	 * @throws NamingException
	 */

	public static void main(String[] args) throws NamingException {
		
		
		Context ctx = new InitialContext();
		CustomerManagementRemote cmr = (CustomerManagementRemote) ctx
				.lookup("de.tud.in.middleware.customers.CustomerManagementRemote#de.tud.in.middleware.customers.CustomerManagementRemote");

		System.out.println("Anzahl Kunden: " + cmr.getNumberOfCustomers());

		ProductInstance pi = new ProductInstance();
		pi.setAmount(5);
		
		CustomerOrder customerOrder = new CustomerOrder();
		customerOrder.getProductInstances().add(pi);
		System.out.println("Füge Kunden hinzu... " + cmr.addCustomer("Horst"));
		System.out.println("Neue Order mit Nummer: " + cmr.addOrderForCustomer(customerOrder, 1));
	}
}