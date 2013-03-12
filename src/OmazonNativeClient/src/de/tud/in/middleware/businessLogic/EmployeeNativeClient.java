/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tud.in.middleware.businessLogic;

import java.util.List;
import java.util.Random;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.tud.in.middleware.customers.Customer;
import de.tud.in.middleware.order.CustomerOrder;
import de.tud.in.middleware.order.OrderState;
import de.tud.in.middleware.order.OrderManagementRemote;
import de.tud.in.middleware.customers.CustomerManagementRemote;
import de.tud.in.middleware.products.Product;
import de.tud.in.middleware.products.ProductManagementRemote;
import de.tud.in.middleware.snapshot.MobileManagementRemote;
import de.tud.in.middleware.snapshot.Snapshot;

/**
 * 
 * @author yasser
 */
@SuppressWarnings("unused")
@MessageDriven(mappedName = "jms/MobileClient")
public class EmployeeNativeClient extends javax.swing.JFrame implements
		MessageListener {

	private static final long serialVersionUID = 711552355024413019L;

	private MobileManagementRemote mobileManagment;
	private final SnapshotRingBuffer preparedSnapshots = new SnapshotRingBuffer(
			3);
	// TODO Use this snapshot to support read-only operations locally.
	private Snapshot currentSnapshot;

	private final Integer clientID = new Random().nextInt();

	/**
	 * Creates new form EmployeNativeClient
	 * 
	 * @throws NamingException
	 */
	public EmployeeNativeClient() throws NamingException {
		init();
		initComponents();
	}

	@Override
	public void onMessage(Message message) {
		System.out.println("got noew snapshot");
		ObjectMessage objMsg;
		if (message instanceof ObjectMessage) {
			objMsg = (ObjectMessage) message;
		} else {
			System.err.println("Unknown message object in MobileClient queue.");
			return;
		}

		try {
			if (objMsg.getJMSType().equals(
					MobileManagementRemote.PREPARE_MSG_TYPE)) {
				handlePrepareSnapshot((Snapshot) objMsg.getObject());
			} else if (message.getJMSType().equals(
					MobileManagementRemote.UPDATE_MSG_TYPE)) {
				handleUpdateToSnapshot((Integer) objMsg.getObject());
			} else {
				System.err
						.println("Unknown message type in MobileClient queue.");
				return;
			}
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void handlePrepareSnapshot(Snapshot snap) {
		preparedSnapshots.add(snap);
		mobileManagment.voteCommit(clientID, snap.id, true);
	}

	private void handleUpdateToSnapshot(Integer snapId) {
		Snapshot snap = preparedSnapshots.getSnapshot(snapId);
		if (snap != null) {
			currentSnapshot = snap;
			mobileManagment.ackGlobalCommit(clientID, snapId, true);
		} else {
			mobileManagment.ackGlobalCommit(clientID, snapId, false);
		}
	}

	private void init() throws NamingException {
		context = new InitialContext();
		productManagementRemote = (ProductManagementRemote) context
				.lookup(JNDINames.PRODUCT_NAME);

		customerManagementRemote = (CustomerManagementRemote) context
				.lookup(JNDINames.CUSTOMER_NAME);

		orderManagementRemote = (OrderManagementRemote) context
				.lookup(JNDINames.ORDER_NAME);

		mobileManagment = (MobileManagementRemote) context
				.lookup(JNDINames.SNAPSHOT_NAME);

		mobileManagment.login(clientID);
		mobileManagment.requestSnapshot();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		nativeClientTabbedPane = new javax.swing.JTabbedPane();
		customersPanel = new javax.swing.JPanel();
		customerNameLabel = new javax.swing.JLabel();
		addCustomerButton = new javax.swing.JButton();
		customerNameTextField = new javax.swing.JTextField();
		customerEmailLabel = new javax.swing.JLabel();
		customerEmailTextField = new javax.swing.JTextField();
		jLabel2 = new javax.swing.JLabel();
		customerIDchoice = new java.awt.Choice();
		removeCustomerButton = new javax.swing.JButton();
		productsPanel = new javax.swing.JPanel();
		productDescriptionLabel = new javax.swing.JLabel();
		addProductButton = new javax.swing.JButton();
		productDescriptionTextField = new javax.swing.JTextField();
		productIDLabel = new javax.swing.JLabel();
		productIDchoice = new java.awt.Choice();
		removeProductButton = new javax.swing.JButton();
		ordersPanel = new javax.swing.JPanel();
		orderIDLabel = new javax.swing.JLabel();
		orderStateButton = new javax.swing.JButton();
		orderStateLabel = new javax.swing.JLabel();
		orderStateLabel1 = new javax.swing.JLabel();
		orderStateChoice = new java.awt.Choice();
		orderIDchoice = new java.awt.Choice();
		
		for (OrderState order : OrderState.values()) 
			orderStateChoice.addItem(order.toString());
		
		refreshContent();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		customerNameLabel.setText("customer Name :");

		addCustomerButton.setText("add customer");
		addCustomerButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						addCustomerButtonActionPerformed(evt);
					}
				});

		customerEmailLabel.setText("customer eMail :");

		jLabel2.setText("customer ID :");

		removeCustomerButton.setText("remove customer");
		removeCustomerButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						removeCustomerButtonActionPerformed(evt);
					}
				});

		org.jdesktop.layout.GroupLayout customersPanelLayout = new org.jdesktop.layout.GroupLayout(
				customersPanel);
		customersPanel.setLayout(customersPanelLayout);
		customersPanelLayout
				.setHorizontalGroup(customersPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(customersPanelLayout
								.createSequentialGroup()
								.add(38, 38, 38)
								.add(customersPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.TRAILING)
										.add(customersPanelLayout
												.createSequentialGroup()
												.add(customersPanelLayout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(customerNameLabel,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																110,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
														.add(addCustomerButton)
														.add(jLabel2,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																110,
																org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
												.add(customersPanelLayout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(customersPanelLayout
																.createSequentialGroup()
																.addPreferredGap(
																		org.jdesktop.layout.LayoutStyle.RELATED,
																		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.add(customersPanelLayout
																		.createParallelGroup(
																				org.jdesktop.layout.GroupLayout.LEADING)
																		.add(org.jdesktop.layout.GroupLayout.TRAILING,
																				customerNameTextField,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				163,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
																		.add(org.jdesktop.layout.GroupLayout.TRAILING,
																				customerIDchoice,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
																				163,
																				org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
														.add(customersPanelLayout
																.createSequentialGroup()
																.add(86, 86, 86)
																.add(removeCustomerButton,
																		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																		163,
																		Short.MAX_VALUE))))
										.add(customersPanelLayout
												.createSequentialGroup()
												.add(customerEmailLabel,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														110,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
												.addPreferredGap(
														org.jdesktop.layout.LayoutStyle.RELATED,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.add(customerEmailTextField,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
														163,
														org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)))
								.add(55, 55, 55)));
		customersPanelLayout
				.setVerticalGroup(customersPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(customersPanelLayout
								.createSequentialGroup()
								.addContainerGap(43, Short.MAX_VALUE)
								.add(customersPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(org.jdesktop.layout.GroupLayout.TRAILING,
												jLabel2)
										.add(org.jdesktop.layout.GroupLayout.TRAILING,
												customerIDchoice,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(29, 29, 29)
								.add(customersPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(customerNameTextField,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(customerNameLabel))
								.add(18, 18, 18)
								.add(customersPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(customerEmailLabel)
										.add(customerEmailTextField,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(45, 45, 45)
								.add(customersPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(addCustomerButton)
										.add(removeCustomerButton))
								.add(28, 28, 28)));

		nativeClientTabbedPane.addTab("Customers", customersPanel);

		productDescriptionLabel.setText("product description :");

		addProductButton.setText("add product");
		addProductButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addProductButtonActionPerformed(evt);
			}
		});

		productIDLabel.setText("product ID :");

		removeProductButton.setText("remove product");
		removeProductButton
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent evt) {
						removeProductButtonActionPerformed(evt);
					}
				});

		org.jdesktop.layout.GroupLayout productsPanelLayout = new org.jdesktop.layout.GroupLayout(
				productsPanel);
		productsPanel.setLayout(productsPanelLayout);
		productsPanelLayout
				.setHorizontalGroup(productsPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(productsPanelLayout
								.createSequentialGroup()
								.add(38, 38, 38)
								.add(productsPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(productsPanelLayout
												.createParallelGroup(
														org.jdesktop.layout.GroupLayout.TRAILING,
														false)
												.add(org.jdesktop.layout.GroupLayout.LEADING,
														productIDLabel,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.add(org.jdesktop.layout.GroupLayout.LEADING,
														productDescriptionLabel,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														136, Short.MAX_VALUE))
										.add(addProductButton))
								.add(66, 66, 66)
								.add(productsPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING,
												false)
										.add(productDescriptionTextField)
										.add(productIDchoice,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE)
										.add(removeProductButton,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												Short.MAX_VALUE))
								.addContainerGap(89, Short.MAX_VALUE)));
		productsPanelLayout
				.setVerticalGroup(productsPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(productsPanelLayout
								.createSequentialGroup()
								.add(75, 75, 75)
								.add(productsPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.TRAILING)
										.add(productIDLabel)
										.add(productIDchoice,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(29, 29, 29)
								.add(productsPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(productDescriptionLabel)
										.add(productDescriptionTextField,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED,
										62, Short.MAX_VALUE)
								.add(productsPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(addProductButton)
										.add(removeProductButton))
								.add(25, 25, 25)));

		nativeClientTabbedPane.addTab("Products", productsPanel);

		orderIDLabel.setText("order ID :");

		orderStateButton.setText("change state");
		orderStateButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				orderStateButtonActionPerformed(evt);
			}
		});

		orderStateLabel1.setText("order State :");

		org.jdesktop.layout.GroupLayout ordersPanelLayout = new org.jdesktop.layout.GroupLayout(
				ordersPanel);
		ordersPanel.setLayout(ordersPanelLayout);
		ordersPanelLayout
				.setHorizontalGroup(ordersPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(ordersPanelLayout
								.createSequentialGroup()
								.add(51, 51, 51)
								.add(ordersPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(orderIDLabel,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												76,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(orderStateButton)
										.add(orderStateLabel1,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												76,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(23, 23, 23)
								.add(ordersPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(orderStateLabel,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												174,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.add(ordersPanelLayout
												.createParallelGroup(
														org.jdesktop.layout.GroupLayout.TRAILING,
														false)
												.add(org.jdesktop.layout.GroupLayout.LEADING,
														orderIDchoice,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.add(org.jdesktop.layout.GroupLayout.LEADING,
														orderStateChoice,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														104, Short.MAX_VALUE)))
								.addContainerGap(100, Short.MAX_VALUE)));
		ordersPanelLayout
				.setVerticalGroup(ordersPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(ordersPanelLayout
								.createSequentialGroup()
								.add(57, 57, 57)
								.add(ordersPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(orderIDLabel)
										.add(orderIDchoice,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(29, 29, 29)
								.add(ordersPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.TRAILING)
										.add(orderStateLabel1)
										.add(orderStateChoice,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED,
										77, Short.MAX_VALUE)
								.add(ordersPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(orderStateButton)
										.add(orderStateLabel,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												29,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(36, 36, 36)));

		nativeClientTabbedPane.addTab("Orders", ordersPanel);

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().add(nativeClientTabbedPane)
						.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				org.jdesktop.layout.GroupLayout.TRAILING,
				layout.createSequentialGroup()
						.addContainerGap(22, Short.MAX_VALUE)
						.add(nativeClientTabbedPane,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								314,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
						.addContainerGap()));

		pack();
	}// </editor-fold>

	private void refreshContent() {
		products = productManagementRemote.getProducts();
		for (Product product : products)
			productIDchoice.addItem(product.getDescription());

		customers = customerManagementRemote.getCustomers();
		for (Customer customer : customers)
			customerIDchoice.addItem(customer.getName());

		orders = orderManagementRemote.getOrders();
		 for (Integer order : orders)
			 orderIDchoice.addItem(order.toString());
	}

	private void orderStateButtonActionPerformed(java.awt.event.ActionEvent evt) {
		Integer orderID = Integer.parseInt(orderIDchoice.getSelectedItem());
		String state = orderStateChoice.getSelectedItem();
		orderManagementRemote.changeOrderStateString(orderID, state);
	}

	private void addProductButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String description = productDescriptionTextField.getText();
		productManagementRemote.addProduct(description);
		productDescriptionTextField.setText("");
		productIDchoice.addItem(description);
	}

	private void addCustomerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		String name = customerNameTextField.getText();
		customerNameTextField.setText("");
		String eMail = customerEmailTextField.getText();
		customerEmailTextField.setText("");
		customerManagementRemote.addCustomer(name, eMail);

		customerIDchoice.addItem(name);
	}

	private void removeProductButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		String selectedItem = productIDchoice.getSelectedItem();
		Integer productID = getProductIDFromString(selectedItem);
		productManagementRemote.removeProduct(productID);
		productIDchoice.remove(selectedItem);
	}

	private Integer getProductIDFromString(String selectedItem) {
		for (Product product : products) {
			if (product.getDescription().equals(selectedItem))
				return product.getId();
		}
		return -1;
	}

	private void removeCustomerButtonActionPerformed(
			java.awt.event.ActionEvent evt) {
		String selectedItem = customerIDchoice.getSelectedItem();
		Integer customerID = getCustomerIDFromString(selectedItem);
		customerManagementRemote.removeCustomer(customerID);
		customerIDchoice.remove(selectedItem);
	}

	private Integer getCustomerIDFromString(String selectedItem) {
		for (Customer customer : customers) {
			if (customer.getName().equals(selectedItem))
				return customer.getId();
		}
		return -1;
	}

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		/* Set the Nimbus look and feel */
		// <editor-fold defaultstate="collapsed"
		// desc=" Look and feel setting code (optional) ">
		/*
		 * If Nimbus (introduced in Java SE 6) is not available, stay with the
		 * default look and feel. For details see
		 * http://download.oracle.com/javase
		 * /tutorial/uiswing/lookandfeel/plaf.html
		 */
		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager
					.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(
					EmployeeNativeClient.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					EmployeeNativeClient.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					EmployeeNativeClient.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					EmployeeNativeClient.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new EmployeeNativeClient().setVisible(true);
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	//
	private Context context;
	private ProductManagementRemote productManagementRemote;
	private CustomerManagementRemote customerManagementRemote;
	private OrderManagementRemote orderManagementRemote;

	List<Product> products;
	List<Customer> customers;
	List<Integer> orders;
	//

	// Variables declaration - do not modify
	private javax.swing.JButton addCustomerButton;
	private javax.swing.JButton addProductButton;
	private javax.swing.JLabel customerEmailLabel;
	private javax.swing.JTextField customerEmailTextField;
	private java.awt.Choice customerIDchoice;
	private javax.swing.JLabel customerNameLabel;
	private javax.swing.JTextField customerNameTextField;
	private javax.swing.JPanel customersPanel;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JTabbedPane nativeClientTabbedPane;
	private javax.swing.JLabel orderIDLabel;
	private java.awt.Choice orderIDchoice;
	private javax.swing.JButton orderStateButton;
	private java.awt.Choice orderStateChoice;
	private javax.swing.JLabel orderStateLabel;
	private javax.swing.JLabel orderStateLabel1;
	private javax.swing.JPanel ordersPanel;
	private javax.swing.JLabel productDescriptionLabel;
	private javax.swing.JTextField productDescriptionTextField;
	private javax.swing.JLabel productIDLabel;
	private java.awt.Choice productIDchoice;
	private javax.swing.JPanel productsPanel;
	private javax.swing.JButton removeCustomerButton;
	private javax.swing.JButton removeProductButton;
	// End of variables declaration
}
