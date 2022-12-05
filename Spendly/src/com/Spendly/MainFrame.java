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


		RegisteredUser.register("ahmed", "ahmed@gmail.com", "123", "123");
		RegisteredUser.register("salah", "salah@gmail.com", "123", "123").setAsAdmin();
		System.out.println(ApplicationState.registered_users.get(1).email);

//		ServiceProvider vodafone = new ServiceProvider("Vodafone");
//		Service mobileReServ = new Service(vodafone, "Mobile Recharge", 300);
//		Discount disc = new OverallDiscount(mobileReServ, 10);
//		mobileReServ.setDiscountBehavior(disc);
//		User.login("ahmed@gmail.com", "123");
//		vodafone.provide(mobileReServ);
//		new Transaction(User.getInstance(), mobileReServ).AddToContext();
//		System.out.println(User.getInstance().getWallet().getBalance());
//		vodafone.provide(mobileReServ);
//		new Transaction(User.getInstance(), mobileReServ).AddToContext();
//		System.out.println(User.getInstance().getWallet().getBalance());
//		var tr = ApplicationState.transactions.get(0);
//		tr.service.provider.payMethod.refund(tr.user.getWallet(), tr.paidAmount);
//		System.out.println(User.getInstance().getWallet().getBalance());
	}

}
