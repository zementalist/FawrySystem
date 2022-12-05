package com.Spendly;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class ServiceProviderList extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServiceProviderList frame = new ServiceProviderList();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServiceProviderList() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblSearchProviders = new JLabel("Search Providers");
		lblSearchProviders.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblSearchProviders.setBounds(172, 11, 109, 25);
		contentPane.add(lblSearchProviders);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(38, 47, 243, 20);
		contentPane.add(textField);
		
		JButton nextBtn = new JButton("Next");
		nextBtn.setBounds(320, 46, 89, 23);
		contentPane.add(nextBtn);
		
		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(38, 96, 371, 160);
		contentPane.add(list);
		setVisible(true);
	}
}
