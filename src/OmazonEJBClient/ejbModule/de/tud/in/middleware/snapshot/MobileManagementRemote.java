package de.tud.in.middleware.snapshot;

import javax.ejb.Remote;

@Remote
public interface MobileManagementRemote {

	/**
	 * Registers calling mobile client. Every mobile client must call this, so a snapshot can be distributed to all active mobile clients.
	 */
	public void registerMyself();
	
	/**
	 * Removes calling client as active mobile client.
	 */
	public void unregisterMyself();
	
	/**
	 * Requests a new snapshot to be distributed to all mobile clients.
	 */
	public void requestSnapshot();
}
