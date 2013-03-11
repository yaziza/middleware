package de.tud.in.middleware.snapshot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jws.WebService;

import de.tud.in.middleware.customers.Customer;
import de.tud.in.middleware.dao.CustomerDAO;
import de.tud.in.middleware.dao.OrderDAO;
import de.tud.in.middleware.dao.ProductDAO;
import de.tud.in.middleware.dao.ShipmentDAO;
import de.tud.in.middleware.dao.TruckDAO;
import de.tud.in.middleware.order.CustomerOrder;
import de.tud.in.middleware.products.Product;
import de.tud.in.middleware.shipment.Shipment;
import de.tud.in.middleware.shipment.Truck;

/**
 * Session Bean implementation class MobileManagment
 */
@Stateless
@LocalBean
@WebService
public class MobileManagment implements MobileManagementRemote,
		MobileManagementLocal {

	private final Set<Integer> mobileClients = new HashSet<Integer>();

	@EJB
	CustomerDAO customerDAO;
	@EJB
	OrderDAO orderDAO;
	@EJB
	ProductDAO productDAO;
	@EJB
	ShipmentDAO shipmentDAO;
	@EJB
	TruckDAO truckDAO;

	@Override
	public void login(Integer clientID) {
		mobileClients.add(clientID);
	}

	@Override
	public void logout(Integer clientID) {
		mobileClients.remove(clientID);
	}

	// XXX I assumed we will use timeout at client side, so no explicit aborts
	// are done here.
	@Override
	public void requestSnapshot() {
		// final Snapshot snap = getSnapshot();
		// synchronized (mobileClients) {
		// for (final Integer client : mobileClients) {
		// if (!voteCommit) {
		// // logger.warning("Distributing snapshot failed in first phase.");
		// return;
		// }
		// }
		// for (final MobileClient client : mobileClients) {
		// final boolean ack = client.updateToSnapshot(snap.id);
		// if (!ack) {
		// // logger.warning("Distributing snapshot failed in second phase.");
		// return;
		// }
		// }
		// }
	}

	private Snapshot getSnapshot() {
		// TODO Ensure that DB will not be modified between the following reads.
		final List<Customer> customers = customerDAO.getCustomers();
		final List<Product> products = productDAO.getProducts();
		final List<CustomerOrder> orders = orderDAO.getOrders();
		final List<Shipment> shipments = shipmentDAO.getShipments();
		final List<Truck> trucks = truckDAO.getTrucks();

		return new Snapshot(customers, products, orders, shipments, trucks);
	}

	@Override
	public void voteCommit(Integer clientID, Integer snapshotID, boolean vote) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ackGlobalCommit(Integer clientID, Integer snapshotID,
			boolean vote) {
		// TODO Auto-generated method stub

	}

}
