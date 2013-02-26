package de.tud.in.middleware.snapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.tud.in.middleware.products.Product;
import de.tud.in.middleware.products.ProductManagementRemote;

public final class SnapshotProductLogic implements ProductManagementRemote {

	private final Map<Integer, Product> productMap;

	public SnapshotProductLogic(final Map<Integer, Product> productMap) {
		this.productMap = productMap;
	}

	@Override
	public long getNumberOfProducts() {
		return productMap.size();
	}

	@Override
	public String getProductDescription(final long id) {
		return getProduct(id).getDescription();
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public long addProduct(final String description) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

	@Override
	public List<Product> getProducts() {
		return new ArrayList<Product>(productMap.values());
	}

	@Override
	public Product getProduct(final long id) {
		return productMap.get(id);
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public void changeProductDescription(final long id, final String description) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

	/**
	 * Unsupported by snapshot.
	 */
	@Override
	public void removeProduct(final long id) {
		throw new UnsupportedOperationException("Snapshot is read only.");
	}

}
