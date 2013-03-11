package de.tud.in.middleware.snapshot;

public interface MobileClient {

	/**
	 * Orders the mobile client to prepare for updating its internal snapshot to the given one.
	 * 
	 * @param snap
	 *            The new snapshot.
	 * @return Return true if client will update its snapshot ("vote commit"), false if update will be aborted.
	 */
	public boolean prepareToUpdateSnapshot(Snapshot snap);

	/**
	 * "Global commit". Called after all clients are prepared to update their snapshot.
	 * 
	 * @param id
	 *            Identifier of the snapshot.
	 * @return Return true iff snapshot has been updated ("ACK global commit").
	 */
	public boolean updateToSnapshot(Integer id);
}
