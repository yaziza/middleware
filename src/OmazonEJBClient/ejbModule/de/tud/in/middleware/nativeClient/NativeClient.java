package de.tud.in.middleware.nativeClient;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.tud.in.middleware.costumers.CustomerManagementRemote;
import de.tud.in.middleware.products.ProductManagementRemote;

public class NativeClient {

	/**
	 * @param args
	 * @throws NamingException
	 */
	public static void main(String[] args) throws NamingException {
		Context ctx = new InitialContext();

		ProductManagementRemote pmr = (ProductManagementRemote) ctx
				.lookup("de.tud.in.middleware.products.ProductManagementRemote#de.tud.in.middleware.products.ProductManagementRemote");

		CustomerManagementRemote cmr = (CustomerManagementRemote) ctx
				.lookup("de.tud.in.middleware.costumers.CustomerManagementRemote#de.tud.in.middleware.costumers.CustomerManagementRemote");
		
		System.out.println("Anzahl Kunden: " + cmr.getNumberOfCustomers());
	}

}
