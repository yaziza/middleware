/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tud.in.middleware.businessLogic;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.MessageDriven;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.tud.in.middleware.order.OrderManagementRemote;
import de.tud.in.middleware.products.Product;
import de.tud.in.middleware.products.ProductInstance;
import de.tud.in.middleware.products.ProductManagementRemote;
import de.tud.in.middleware.snapshot.MobileManagementRemote;

/**
 * 
 * @author yasser
 */
@MessageDriven(mappedName = "jms/MobileClient")
public class CustomerNativeClient extends javax.swing.JFrame implements
		MessageListener {

	private static final long serialVersionUID = -308256521610779734L;

	/**
	 * Creates new form CustomerNativeClient
	 * 
	 * @throws NamingException
	 */
	public CustomerNativeClient() throws NamingException {
		init();
		initComponents();
	}

	private void init() throws NamingException {
		context = new InitialContext();

		productManagementRemote = (ProductManagementRemote) context
				.lookup(JNDINames.PRODUCT_NAME);

		orderManagementRemote = (OrderManagementRemote) context
				.lookup(JNDINames.ORDER_NAME);

		products = productManagementRemote.getProducts();
	}

	@Override
	public void onMessage(Message message) {
		if (!(message instanceof ObjectMessage)) {
			System.err.println("Unknown message object in MobileClient queue.");
			return;
		}

		try {
			if (message.getJMSType().equals(
					MobileManagementRemote.UPDATE_MSG_TYPE)) {
				products = productManagementRemote.getProducts();
				productChoice.removeAll();
				for (Product item : products)
					productChoice.addItem(item.getDescription());

				initComponents();
				System.out.println("update notification");

			} else {
				System.err
						.println("Unknown message type in MobileClient queue.");
				return;
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		orderPanel = new javax.swing.JPanel();
		customerIDLabel = new javax.swing.JLabel();
		customerIDTextField = new javax.swing.JTextField();
		productIDLabel = new javax.swing.JLabel();
		quantityLabel = new javax.swing.JLabel();
		quantityTextField = new javax.swing.JTextField();
		addproductButton = new javax.swing.JButton();
		orderButton = new javax.swing.JButton();
		productChoice = new java.awt.Choice();

		orderButton.setEnabled(false);

		for (Product item : products)
			productChoice.addItem(item.getDescription());

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		customerIDLabel.setText("customer ID :");

		productIDLabel.setText("product :");

		quantityLabel.setText("quantity :");

		addproductButton.setText("add product");
		addproductButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				addproductButtonActionPerformed(evt);
			}
		});

		orderButton.setText("send order");
		orderButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				orderButtonActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout orderPanelLayout = new org.jdesktop.layout.GroupLayout(
				orderPanel);
		orderPanel.setLayout(orderPanelLayout);
		orderPanelLayout
				.setHorizontalGroup(orderPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(orderPanelLayout
								.createSequentialGroup()
								.add(orderPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(orderPanelLayout
												.createSequentialGroup()
												.add(67, 67, 67)
												.add(orderPanelLayout
														.createParallelGroup(
																org.jdesktop.layout.GroupLayout.LEADING)
														.add(orderPanelLayout
																.createParallelGroup(
																		org.jdesktop.layout.GroupLayout.LEADING,
																		false)
																.add(productIDLabel,
																		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																		Short.MAX_VALUE)
																.add(customerIDLabel,
																		org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
																		90,
																		Short.MAX_VALUE))
														.add(quantityLabel)))
										.add(orderPanelLayout
												.createSequentialGroup()
												.add(47, 47, 47)
												.add(addproductButton)))
								.add(70, 70, 70)
								.add(orderPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(orderButton)
										.add(orderPanelLayout
												.createParallelGroup(
														org.jdesktop.layout.GroupLayout.TRAILING,
														false)
												.add(org.jdesktop.layout.GroupLayout.LEADING,
														productChoice,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														Short.MAX_VALUE)
												.add(org.jdesktop.layout.GroupLayout.LEADING,
														customerIDTextField,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														108, Short.MAX_VALUE)
												.add(org.jdesktop.layout.GroupLayout.LEADING,
														quantityTextField)))
								.addContainerGap(101, Short.MAX_VALUE)));
		orderPanelLayout
				.setVerticalGroup(orderPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(orderPanelLayout
								.createSequentialGroup()
								.add(34, 34, 34)
								.add(orderPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(customerIDLabel)
										.add(customerIDTextField,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(18, 18, 18)
								.add(orderPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.LEADING)
										.add(productIDLabel)
										.add(productChoice,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(18, 18, 18)
								.add(orderPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(quantityLabel)
										.add(quantityTextField,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.add(18, 18, 18)
								.add(orderPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(addproductButton).add(orderButton))
								.addContainerGap(67, Short.MAX_VALUE)));

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(0, 449, Short.MAX_VALUE)
				.add(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(layout
								.createSequentialGroup()
								.add(0, 0, Short.MAX_VALUE)
								.add(orderPanel,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.add(0, 0, Short.MAX_VALUE))));
		layout.setVerticalGroup(layout
				.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
				.add(0, 300, Short.MAX_VALUE)
				.add(layout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(layout
								.createSequentialGroup()
								.add(0, 20, Short.MAX_VALUE)
								.add(orderPanel,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
										org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
										org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.add(0, 20, Short.MAX_VALUE))));

		pack();
	}// </editor-fold>

	private void addproductButtonActionPerformed(
			final java.awt.event.ActionEvent evt) {
		orderButton.setEnabled(true);

		Product product;
		ProductInstance productInstance;

		final Integer productID = getProductIDFromString();
		product = productManagementRemote.getProduct(productID);

		productInstance = new ProductInstance();
		productInstance.setProduct(product);

		final int quantity = Integer.parseInt(quantityTextField.getText());
		productInstance.setAmount(quantity);

		orderedProducts.add(productInstance);
		quantityTextField.setText("");

		orderButton.setEnabled(true);
	}

	private Integer getProductIDFromString() {
		return 1;
	}

	private void orderButtonActionPerformed(final java.awt.event.ActionEvent evt) {
		orderButton.setEnabled(false);

		final Integer customerID = Integer.parseInt(customerIDTextField
				.getText());
		orderManagementRemote.addOrderForCustomer(orderedProducts, customerID);
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
					CustomerNativeClient.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(
					CustomerNativeClient.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(
					CustomerNativeClient.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(
					CustomerNativeClient.class.getName()).log(
					java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					new CustomerNativeClient().setVisible(true);
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
	private OrderManagementRemote orderManagementRemote;
	private List<ProductInstance> orderedProducts = new ArrayList<ProductInstance>();
	private List<Product> products = new ArrayList<Product>();
	//

	// Variables declaration - do not modify
	private javax.swing.JButton addproductButton;
	private javax.swing.JLabel customerIDLabel;
	private javax.swing.JTextField customerIDTextField;
	private javax.swing.JButton orderButton;
	private javax.swing.JPanel orderPanel;
	private java.awt.Choice productChoice;
	private javax.swing.JLabel productIDLabel;
	private javax.swing.JLabel quantityLabel;
	private javax.swing.JTextField quantityTextField;
	// End of variables declaration
}
