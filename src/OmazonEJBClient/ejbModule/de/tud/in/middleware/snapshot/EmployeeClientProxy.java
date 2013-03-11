package de.tud.in.middleware.snapshot;

import java.io.Serializable;
import java.util.Random;

public final class EmployeeClientProxy implements Serializable, MobileClient {
	private static final long serialVersionUID = -6014457740219416126L;

	public final Integer id = new Random().nextInt();

	private final MobileClient listener;

	public EmployeeClientProxy(final MobileClient listener) {
		this.listener = listener;
	}

	@Override
	public boolean prepareToUpdateSnapshot(final Snapshot snap) {
		return listener.prepareToUpdateSnapshot(snap);
	}

	@Override
	public boolean updateToSnapshot(final Integer id) {
		return listener.updateToSnapshot(id);
	}

}
