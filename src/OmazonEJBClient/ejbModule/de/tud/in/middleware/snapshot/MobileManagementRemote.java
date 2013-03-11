package de.tud.in.middleware.snapshot;

import javax.ejb.Remote;

@Remote
public interface MobileManagementRemote {

	/**
	 * Registers calling mobile client. Every mobile client must call this, so a snapshot can be
	 * distributed to all active mobile clients.
	 */
	public void login(Integer clientID);

	/**
	 * Removes calling client as active mobile client.
	 */
	public void logout(Integer clientID);

	/**
	 * Requests a new snapshot to be distributed to all mobile clients.
	 */
	public void requestSnapshot();
	
	public void voteCommit(Integer clientID, Integer snapshotID, boolean vote);
	
	public void ackGlobalCommit(Integer clientID, Integer snapshotID, boolean vote);
}
