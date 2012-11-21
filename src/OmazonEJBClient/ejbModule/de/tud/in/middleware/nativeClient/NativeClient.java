package de.tud.in.middleware.nativeClient;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.tud.in.middleware.customers.CustomerManagementRemote;

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

	}
}