package de.tud.in.middleware.snapshot;

public interface MobileClient {

	/**
	 * Orders the mobile client to update its internal snapshot to the given one.
	 * @param snap The new snapshot.
	 * @return Return true, iff internal snapshot has been replaced by given one.
	 */
	public boolean updateSnapshot(Snapshot snap);
}
