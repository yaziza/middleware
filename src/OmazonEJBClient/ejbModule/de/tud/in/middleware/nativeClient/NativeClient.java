package de.tud.in.middleware.nativeClient;

import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.tud.in.middleware.customers.CustomerManagementRemote;
import de.tud.in.middleware.order.OrderManagementRemote;
import de.tud.in.middleware.products.ProductInstance;

public class NativeClient {
	/**
	 * @param args
	 * @throws NamingException
	 */

	public static void main(final String[] args) throws NamingException {

		final Context ctx = new InitialContext();
		final CustomerManagementRemote cmr = (CustomerManagementRemote) ctx
				.lookup("de.tud.in.middleware.customers.CustomerManagementRemote#de.tud.in.middleware.customers.CustomerManagementRemote");
		final OrderManagementRemote omr = (OrderManagementRemote) ctx
				.lookup("de.tud.in.middleware.order.OrderManagementRemote#de.tud.in.middleware.order.OrderManagementRemote");
		System.out.println("Anzahl Kunden: " + cmr.getNumberOfCustomers());

		final ProductInstance pi = new ProductInstance();
		pi.setAmount(5);
		final List<ProductInstance> productList = new ArrayList<ProductInstance>();
		productList.add(pi);

		System.out.println("F�ge Kunden hinzu... " + cmr.addCustomer("Horst", "alexander@froemmgen.de"));
		System.out.println("Neue Order mit Nummer: " + omr.addOrderForCustomer(productList, 1));
	}
}