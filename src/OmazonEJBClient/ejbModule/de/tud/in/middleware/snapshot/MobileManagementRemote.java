package de.tud.in.middleware.snapshot;

import javax.ejb.Remote;

@Remote
public interface MobileManagementRemote {
	public static final String PREPARE_MSG_TYPE = "PrepareSnap";
	public static final String UPDATE_MSG_TYPE = "UpdateToSnap";

	/**
	 * Registers calling mobile client. Every mobile client must call this, so a snapshot can be distributed to all
	 * active mobile clients.
	 */
	public void login(Integer clientID);

	/**
	 * Removes calling client as active mobile client.
	 */
	public void logout(Integer clientID);

	/**
	 * Requests a new snapshot to be distributed to all mobile clients.
	 * 
	 * @return False, iff a snapshot has already been requested but not distributed. In this case the clients should try
	 *         again later.
	 */
	public boolean requestSnapshot();

	public void voteCommit(Integer clientID, Integer snapshotID, boolean vote);

	public void ackGlobalCommit(Integer clientID, Integer snapshotID, boolean vote);
}
