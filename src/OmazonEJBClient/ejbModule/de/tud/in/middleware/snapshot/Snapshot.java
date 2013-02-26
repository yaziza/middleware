package de.tud.in.middleware.snapshot;

import static java.util.Collections.unmodifiableMap;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import de.tud.in.middleware.customers.Customer;
import de.tud.in.middleware.order.CustomerOrder;
import de.tud.in.middleware.products.Product;
import de.tud.in.middleware.shipment.Shipment;
import de.tud.in.middleware.shipment.Truck;

/**
 * A snapshot of the database. Provides read-only access to management operations.
 */
public final class Snapshot implements Serializable {
	/**
	 * Generated.
	 */
	private static final long serialVersionUID = 256725257655723524L;
	private static final Random rand = new Random();

	public final long id = rand.nextLong();

	public final SnapshotCustomerLogic customerManagment;
	public final SnapshotOrderLogic orderManagment;
	public final SnapshotProductLogic productManagment;
	public final SnapshotShipmentLogic shipmentManagment;
	public final SnapshotTruckLogic truckManagment;

	/**
	 * The provided database entries must be consistent. Meaning, while querying for the required
	 * parameters the database should not change.
	 * 
	 * @param customers
	 *            All current customers.
	 * @param orders
	 *            All current orders.
	 * @param shipments
	 *            All current shipments.
	 * @param trucks
	 *            All current trucks.
	 */
	public Snapshot(final List<Customer> customers, final List<Product> products, final List<CustomerOrder> orders,
			final List<Shipment> shipments, final List<Truck> trucks) {
		customerManagment = createCustomerLogic(customers);
		productManagment = createProductLogic(products);
		orderManagment = createOrderLogic(orders);
		shipmentManagment = createShipmentLogic(shipments);
		truckManagment = createTruckLogic(trucks);
	}

	private SnapshotCustomerLogic createCustomerLogic(final List<Customer> customers) {
		final Map<Integer, Customer> customerMap = new HashMap<Integer, Customer>(customers.size());
		for (final Customer customer : customers) {
			customerMap.put(customer.getId(), customer);
		}
		return new SnapshotCustomerLogic(unmodifiableMap(customerMap));
	}

	private SnapshotProductLogic createProductLogic(final List<Product> products) {
		final Map<Integer, Product> productMap = new HashMap<Integer, Product>(products.size());
		for (final Product product : products) {
			productMap.put(product.getId(), product);
		}
		return new SnapshotProductLogic(unmodifiableMap(productMap));
	}

	private SnapshotOrderLogic createOrderLogic(final List<CustomerOrder> orders) {
		final Map<Integer, CustomerOrder> orderInternalMap = new HashMap<Integer, CustomerOrder>(orders.size());
		for (final CustomerOrder order : orders) {
			orderInternalMap.put(order.getId(), order);
		}
		return new SnapshotOrderLogic(unmodifiableMap(orderInternalMap));
	}

	private SnapshotShipmentLogic createShipmentLogic(final List<Shipment> shipments) {
		final Map<Integer, Shipment> shipmentInternalMap = new HashMap<Integer, Shipment>(shipments.size());
		for (final Shipment shipment : shipments) {
			shipmentInternalMap.put(shipment.getId(), shipment);
		}
		return new SnapshotShipmentLogic(unmodifiableMap(shipmentInternalMap));
	}

	private SnapshotTruckLogic createTruckLogic(final List<Truck> trucks) {
		final Map<Integer, Truck> truckInternalMap = new HashMap<Integer, Truck>(trucks.size());
		for (final Truck truck : trucks) {
			truckInternalMap.put(truck.getId(), truck);
		}
		return new SnapshotTruckLogic(unmodifiableMap(truckInternalMap));
	}
}
