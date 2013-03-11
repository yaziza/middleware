package de.tud.in.middleware.snapshot;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Queue;
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

	@Resource(mappedName = "jms/SimpleConnectionFactory")
	// = connection factorie's JNDI name
	private ConnectionFactory connectionFactory;

	@Resource(mappedName = "jms/MobileClient")
	private Queue clientMsgQueue;

	@Override
	public void login(final Integer clientID) {
		mobileClients.add(clientID);
	}

	@Override
	public void logout(final Integer clientID) {
		mobileClients.remove(clientID);
	}

	@Override
	public synchronized boolean requestSnapshot() {
		if (votedCommit != null || acknowledgedCommit != null) {
			return false;
		}

		final Snapshot snap = getSnapshot();
		synchronized (mobileClients) {
			votedCommit = new ClientSessionSet(mobileClients.size(), snap.id);
			sendToAllClients(snap, MobileManagementRemote.PREPARE_MSG_TYPE);
			startVoteTimeout(snap.id);
		}
		return true;
	}

	private void startVoteTimeout(final Integer id) {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						sleep(ClientSessionSet.TIMEOUT_LIMIT_MILIS);
						synchronized (mobileClients) {
							if (votedCommit == null || votedCommit.snapshotID != id) {
								return;
							}
							checkVote();
						}
					} catch (final InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

			private void checkVote() {
				if (votedCommit.timeout < System.currentTimeMillis()) {
					System.out.println("Voting timeout. Abort.");
					abort();
				}
			}
		}.start();
	}

	private void startAckTimeout(final Integer id) {
		new Thread() {
			@Override
			public void run() {
				synchronized (mobileClients) {
					while (acknowledgedCommit != null && acknowledgedCommit.snapshotID == id) {
						try {
							sleep(ClientSessionSet.TIMEOUT_LIMIT_MILIS);
							checkGlobalAcks();
						} catch (final InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

			private void checkGlobalAcks() {
				if (acknowledgedCommit.timeout < System.currentTimeMillis()) {
					System.out.println("Global commit ACKs timeout. Abort.");
					abort();
				}
			}
		}.start();
	}

	private void sendToAllClients(final Serializable msgObj, final String msgType) {
		try {
			final Connection conn = connectionFactory.createConnection();
			final Session session = conn.createSession(false, Session.AUTO_ACKNOWLEDGE);
			final Message msg = session.createObjectMessage(msgObj);
			msg.setJMSType(msgType);

			final MessageProducer producer = session.createProducer(clientMsgQueue);
			producer.send(msg);
		} catch (final JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public synchronized void voteCommit(final Integer clientID, final Integer snapshotID, final boolean vote) {
		if (votedCommit == null || votedCommit.snapshotID != snapshotID) {
			System.out.println("Ignored vote.");
			return;
		}
		if (vote) {
			votedCommit.addClient(clientID);
			if (votedCommit.complete()) {
				System.out.println("Voting successful.");
				acknowledgedCommit = new ClientSessionSet(mobileClients.size(), snapshotID);
				sendToAllClients(snapshotID, MobileManagementRemote.UPDATE_MSG_TYPE);
				startAckTimeout(snapshotID);
			}
		} else {
			System.out.println("Negative vote! Abort.");
			abort();
		}
	}

	private void abort() {
		synchronized (mobileClients) {
			votedCommit = null;
			acknowledgedCommit = null;
		}
	}

	@Override
	public synchronized void ackGlobalCommit(final Integer clientID, final Integer snapshotID, final boolean ack) {
		if (acknowledgedCommit == null || acknowledgedCommit.snapshotID != snapshotID) {
			System.out.println("Ignored acknowledgement.");
			return;
		}
		if (ack) {
			acknowledgedCommit.addClient(clientID);
			if (acknowledgedCommit.complete()) {
				System.out.println("Snapshot update successful.");
				abort();
			}
		} else {
			System.out.println("Negative ack! Abort.");
			abort();
		}
	}
}
