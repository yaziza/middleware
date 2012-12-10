/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package de.tud.in.middleware.businessLogic;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import de.tud.in.middleware.customers.CustomerManagementRemote;
import de.tud.in.middleware.products.ProductManagementRemote;

/**
 * 
 * @author yasser
 */
public class NativeClient extends javax.swing.JFrame {

	// Variables declaration - do not modify
	private javax.swing.JButton customerButton;
	private javax.swing.JLabel customerNameLabel;
	private javax.swing.JTextField customerNameTextField;
	private javax.swing.JPanel customerPanel;
	private javax.swing.JTabbedPane jTabbedPane1;
	private javax.swing.JButton productButton;
	private javax.swing.JLabel productDescriptionLabel;
	private javax.swing.JTextField productDescriptionTextField;
	private javax.swing.JPanel productPanel;

	// End of variables declaration

	/**
	 * Creates new form NativeClient
	 */
	public NativeClient() {
		initComponents();
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jTabbedPane1 = new javax.swing.JTabbedPane();
		customerPanel = new javax.swing.JPanel();
		customerNameLabel = new javax.swing.JLabel();
		customerButton = new javax.swing.JButton();
		customerNameTextField = new javax.swing.JTextField();
		productPanel = new javax.swing.JPanel();
		productDescriptionLabel = new javax.swing.JLabel();
		productButton = new javax.swing.JButton();
		productDescriptionTextField = new javax.swing.JTextField();

		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		customerNameLabel.setText("Customer Name :");

		customerButton.setText("add customer");
		customerButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				customerButtonActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout customerPanelLayout = createCustomerPanelLayout();
		customerPanelLayout
				.setVerticalGroup(customerPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(customerPanelLayout
								.createSequentialGroup()
								.add(45, 45, 45)
								.add(customerPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(customerNameLabel)
										.add(customerNameTextField,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED,
										111, Short.MAX_VALUE)
								.add(customerButton).addContainerGap()));

		jTabbedPane1.addTab("Add Customer", customerPanel);

		productDescriptionLabel.setText("Product Description :");

		productButton.setText("add product");
		productButton.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				productButtonActionPerformed(evt);
			}
		});

		org.jdesktop.layout.GroupLayout productPanelLayout = createProductPanelLayout();

		productPanelLayout
				.setVerticalGroup(productPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(productPanelLayout
								.createSequentialGroup()
								.add(45, 45, 45)
								.add(productPanelLayout
										.createParallelGroup(
												org.jdesktop.layout.GroupLayout.BASELINE)
										.add(productDescriptionLabel)
										.add(productDescriptionTextField,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
								.addPreferredGap(
										org.jdesktop.layout.LayoutStyle.RELATED,
										111, Short.MAX_VALUE)
								.add(productButton).addContainerGap()));

		jTabbedPane1.addTab("Add Product", productPanel);

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(
				getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				org.jdesktop.layout.GroupLayout.TRAILING,
				layout.createSequentialGroup().addContainerGap()
						.add(jTabbedPane1).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
				layout.createSequentialGroup().add(29, 29, 29)
						.add(jTabbedPane1).addContainerGap()));

		pack();
	}// </editor-fold>

	private org.jdesktop.layout.GroupLayout createProductPanelLayout() {
		org.jdesktop.layout.GroupLayout productPanelLayout = new org.jdesktop.layout.GroupLayout(
				productPanel);
		productPanel.setLayout(productPanelLayout);
		productPanelLayout
				.setHorizontalGroup(productPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(org.jdesktop.layout.GroupLayout.TRAILING,
								productPanelLayout
										.createSequentialGroup()
										.add(34, 34, 34)
										.add(productDescriptionLabel,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												133,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												41, Short.MAX_VALUE)
										.add(productPanelLayout
												.createParallelGroup(
														org.jdesktop.layout.GroupLayout.TRAILING,
														false)
												.add(productButton,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														136, Short.MAX_VALUE)
												.add(productDescriptionTextField))
										.add(23, 23, 23)));
		return productPanelLayout;
	}

	private org.jdesktop.layout.GroupLayout createCustomerPanelLayout() {
		org.jdesktop.layout.GroupLayout customerPanelLayout = new org.jdesktop.layout.GroupLayout(
				customerPanel);
		customerPanel.setLayout(customerPanelLayout);
		customerPanelLayout
				.setHorizontalGroup(customerPanelLayout
						.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING)
						.add(org.jdesktop.layout.GroupLayout.TRAILING,
								customerPanelLayout
										.createSequentialGroup()
										.add(34, 34, 34)
										.add(customerNameLabel,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
												133,
												org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
										.addPreferredGap(
												org.jdesktop.layout.LayoutStyle.RELATED,
												41, Short.MAX_VALUE)
										.add(customerPanelLayout
												.createParallelGroup(
														org.jdesktop.layout.GroupLayout.TRAILING,
														false)
												.add(customerButton,
														org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
														136, Short.MAX_VALUE)
												.add(customerNameTextField))
										.add(23, 23, 23)));
		return customerPanelLayout;
	}

	private void customerButtonActionPerformed(java.awt.event.ActionEvent evt) {
		CustomerManagementRemote customerManagementRemote;
		Context context;
		try {
			context = new InitialContext();

			context = new InitialContext();
			customerManagementRemote = (CustomerManagementRemote) context
					.lookup("de.tud.in.middleware.customers.CustomerManagementRemote#de.tud.in.middleware.customers.CustomerManagementRemote");

			customerManagementRemote.addCustomer(customerNameTextField.getText());
			customerNameTextField.setText("");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void productButtonActionPerformed(java.awt.event.ActionEvent evt) {
		ProductManagementRemote productManagementRemote;
		Context context;
		try {
			context = new InitialContext();
			productManagementRemote = (ProductManagementRemote) context
					.lookup("de.tud.in.middleware.products.ProductManagementRemote#de.tud.in.middleware.products.ProductManagementRemote");

			productManagementRemote.addProduct(productDescriptionTextField
					.getText());
			productDescriptionTextField.setText("");

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
			java.util.logging.Logger.getLogger(NativeClient.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(NativeClient.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(NativeClient.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(NativeClient.class.getName())
					.log(java.util.logging.Level.SEVERE, null, ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new NativeClient().setVisible(true);
			}
		});
	}
}
