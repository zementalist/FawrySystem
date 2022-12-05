package com.Spendly;

import java.awt.EventQueue;
import java.lang.constant.DynamicCallSiteDesc;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import DataStorage.Transaction;
import Models.CashPaymentMethod;
import Models.RegisteredUser;
import Models.Service;
import Models.ServiceProvider;
import Models.*;
import Singletons.ApplicationState;
import Singletons.User;

import java.awt.BorderLayout;

public class MainFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		LoginFrame loginframe = new LoginFrame();

		loginframe.setVisible(true);
		loginframe.setLocationRelativeTo(null);
		dispose();


		// Register User & login
		RegisteredUser.register("ahmed", "ahmed@gmail.com", "123", "123");
		RegisteredUser.register("salah", "salah@gmail.com", "123", "123").setAsAdmin();
		User.login("ahmed@gmail.com", "123");

		// Init factory (in this case i'm using factory, not the abstract factory class)
		ServiceProviderFactory spf = new ServiceProviderFactory();
		ServiceFactory sf = new ServiceFactory();

		// Create provider & service, using factory method
		ServiceProvider vodafone = spf.makeServiceProvider("Vodafone");
		Service mob_recharge_service = sf.makeService("Mobile recharge services");
		vodafone.setService(mob_recharge_service);
		System.out.println("Service (Mobile Recharge) | Provider (Vodafone)");
		
		// Set service cost (charge 100$)
		int cost = 100;
		System.out.println("Charge amount: " + cost);
		mob_recharge_service.setCost(cost);
		
		// Admin is creating a discount for the first payment for user
		Discount disc = new OverallDiscount(10);
		mob_recharge_service.setDiscountBehavior(disc);
		disc.setService(mob_recharge_service); // update observer state
		
		
		// Print user's wallet balance before service
		System.out.print("Balance before service: ");
		System.out.println(User.getInstance().getWallet().getBalance());
		
		int payment_method_id = 0; // credit card
		vodafone.provide(payment_method_id);
		
		System.out.print("Balance after service: ");
		System.out.println(User.getInstance().getWallet().getBalance());
		
		// Re-request the same service (just testing that discount is first time only)
		vodafone.provide(payment_method_id); // pay method index = 1 = cash method
		
		System.out.print("Balance after re-service: ");
		System.out.println(User.getInstance().getWallet().getBalance());
		
		// User is requesting a refund for a specific transaction
		var tr = ApplicationState.transactions.get(0);
		System.out.println("User requested refund for transaction at index 0");
		
		
		// Admin is accepting the refund request
		tr.payMethod.refund(tr.user.getWallet(), tr.paidAmount);

		
		System.out.print("Balance after refunding: ");
		System.out.println(User.getInstance().getWallet().getBalance());
	}

}
