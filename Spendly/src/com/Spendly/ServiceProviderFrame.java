package com.Spendly;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Models.Service;
import Models.ServiceFactory;
import Models.ServiceProvider;
import Models.ServiceProviderFactory;
import Singletons.ApplicationState;

import javax.swing.JLabel;
import java.awt.Font;
import java.util.List;

import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.JButton;
import javax.swing.AbstractListModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ServiceProviderFrame extends JFrame {

	private JPanel contentPane;
	private String[] providers;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					ServiceProviderFrame frame = new ServiceProviderFrame(ApplicationState.mobile_service_providers);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ServiceProviderFrame(String[] providers) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel title = new JLabel(ApplicationState.selected_service.name);
		title.setFont(new Font("Tahoma", Font.PLAIN, 14));
		title.setBounds(146, 11, 155, 23);
		contentPane.add(title);
		
		JList list = new JList();
		list.setModel(new AbstractListModel() {
			public int getSize() {
				return providers.length;
			}
			public Object getElementAt(int index) {
				return providers[index];
			}
		});
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setBounds(33, 90, 371, 160);
		contentPane.add(list);
		
		JButton nextBtn = new JButton("Next");
		nextBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Object choice = list.getSelectedValue();
				if(choice != null) {
					ServiceProviderFactory provider_factory = new ServiceProviderFactory();
					String selected_provider = choice.toString();
					ServiceProvider provider = provider_factory.makeServiceProvider(selected_provider);
					ApplicationState.selected_service_provider = provider;
					System.out.println(selected_provider);
					JFrame frame = new ServiceFormFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		nextBtn.setBounds(315, 56, 89, 23);
		contentPane.add(nextBtn);
		setVisible(true);
	}
}
