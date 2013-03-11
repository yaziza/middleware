package de.tud.in.middleware.snapshot;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

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
 * Session Bean implementation class MobileManagement
 */
@Stateless
@LocalBean
public class MobileManagment implements MobileManagementRemote {

	private final Set<Integer> mobileClients = new HashSet<Integer>();
	private ClientSessionSet votedCommit;
	private ClientSessionSet acknowledgedCommit;

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
	public void login(final Integer clientID) {
		mobileClients.add(clientID);
	}

	@Override
	public void logout(final Integer clientID) {
		mobileClients.remove(clientID);
	}

	@Override
	public boolean requestSnapshot() {
		if (votedCommit != null || acknowledgedCommit != null) {
			return false;
		}

		final Snapshot snap = getSnapshot();
		synchronized (mobileClients) {
			votedCommit = new ClientSessionSet(mobileClients.size(), snap.id);
			acknowledgedCommit = new ClientSessionSet(mobileClients.size(), snap.id);
			try {
				sendPrepareSnapshotToAll(snap);
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return true;
	}

	private void sendPrepareSnapshotToAll(final Snapshot snapshot) throws JMSException {
		Session session = null;
		Message msg = session.createObjectMessage(snapshot);
		// TODO
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
	public void voteCommit(final Integer clientID, final Integer snapshotID, final boolean vote) {
		if (votedCommit == null || votedCommit.snapshotID != snapshotID) {
			System.out.println("Ignored vote.");
			return;
		}
		if (vote) {
			votedCommit.addClient(clientID);
			if(votedCommit.complete()){
				System.out.println("Voting successful.");
				sendGlobalCommitToAll(snapshotID);
			}
		} else {
			abort();
			System.out.println("Negative vote!");
		}
	}

	private void sendGlobalCommitToAll(Integer snapshotID) {
		// TODO Auto-generated method stub
		
	}

	private void abort() {
		votedCommit = null;
		acknowledgedCommit = null;
	}

	@Override
	public void ackGlobalCommit(final Integer clientID, final Integer snapshotID, final boolean ack) {
		if (acknowledgedCommit == null || acknowledgedCommit.snapshotID != snapshotID) {
			System.out.println("Ignored acknowledgement.");
			return;
		}
		if (ack) {
			acknowledgedCommit.addClient(clientID);
			if(acknowledgedCommit.complete()){
				System.out.println("Snapshot update successful.");
				abort();
			}
		} else {
			abort();
			System.out.println("Negative ack!");
		}
	}
}
