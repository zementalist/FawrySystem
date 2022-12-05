package com.Spendly;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.Service;
import Models.ServiceProvider;
import Singletons.ApplicationState;
import Singletons.User;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.AbstractListModel;

public class ServiceFormFrame extends JFrame {

	private JPanel contentPane;
	private JTextField phone_number_input;
	private JTextField amount_input;
	private JList list;
	private JLabel lblPaymentMethod;
	
	private List<String> availablePayMethods = ApplicationState.selected_service_provider.availablePayMethods.stream()
			.map(c -> c.toString()).toList();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceFormFrame frame = new ServiceFormFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServiceFormFrame() {
		var current_frame = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		phone_number_input = new JTextField();
		phone_number_input.setBounds(180, 76, 86, 20);
		contentPane.add(phone_number_input);
		phone_number_input.setColumns(10);
		
		amount_input = new JTextField();
		amount_input.setBounds(180, 142, 86, 20);
		amount_input.setColumns(10);
		contentPane.add(amount_input);
		
		JLabel lblNewLabel = new JLabel("Phone number");
		lblNewLabel.setBounds(71, 79, 69, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setBounds(71, 145, 69, 14);
		contentPane.add(lblAmount);
		
		JButton actionBtn = new JButton("Execute");
		actionBtn.setBounds(335, 50, 89, 23);
		actionBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String phone_number = phone_number_input.getText();
				int amount = Integer.parseInt(amount_input.getText());
				Service service = ApplicationState.selected_service;
				service.setCost(amount);
				ServiceProvider provider = ApplicationState.selected_service_provider;
				provider.setService(service);
				int paymentMethodIndex = list.getSelectedIndex() != -1 ? list.getSelectedIndex() : 0;
				System.out.println("Wallet before: " + User.getInstance().getWallet().getBalance());
				String message = provider.provide(paymentMethodIndex);
				System.out.println("Wallet after: " + User.getInstance().getWallet().getBalance());
				JOptionPane.showMessageDialog(current_frame,
					    message,
					    "Payment Response",
					    JOptionPane.DEFAULT_OPTION);
				
			}
		});
		contentPane.add(actionBtn);
		
		list = new JList();
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return availablePayMethods.size();
			}
			public Object getElementAt(int index) {
				return availablePayMethods.get(index);
			}
		});
		list.setBounds(150, 190, 144, 38);
		contentPane.add(list);
		
		lblPaymentMethod = new JLabel("Payment Method");
		lblPaymentMethod.setBounds(51, 191, 89, 14);
		contentPane.add(lblPaymentMethod);
	}
}
