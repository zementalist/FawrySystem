package com.Spendly;

import java.util.prefs.Preferences;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Singletons.ApplicationState;
import Singletons.User;

import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;
	private JTextField emailField;
	Preferences prefs = Preferences.userNodeForPackage(ApplicationState.class);

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		setTitle("Login");
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		JFrame currentFrame = this;
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String email = emailField.getText();
				String password = String.valueOf(passwordField.getPassword());
				boolean is_logged = User.login(email, password);
				if(is_logged) {
					ServiceListFrame frame = new ServiceListFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				}
				else {
					System.out.println("Invalid Credentials");
					JOptionPane.showMessageDialog(currentFrame,
						    "Invalid email or password.",
						    "Invalid Credentials",
						    JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.setBounds(274, 188, 89, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_1 = new JLabel("I don't have an account");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFrame signupFrame = new SignUpFrame();
				signupFrame.setVisible(true);
				signupFrame.setLocationRelativeTo(null);
				dispose();
				setVisible(false);
			}
		});
		lblNewLabel_1.setForeground(SystemColor.textHighlight);
		lblNewLabel_1.setBounds(75, 194, 167, 14);
		contentPane.add(lblNewLabel_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(75, 152, 167, 24);
		contentPane.add(passwordField);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblPassword.setBounds(75, 117, 105, 24);
		contentPane.add(lblPassword);
		
		emailField = new JTextField();
		emailField.setToolTipText("example@example.com");
		emailField.setColumns(10);
		emailField.setBounds(75, 72, 167, 24);
		contentPane.add(emailField);
		
		JLabel lblNewLabel = new JLabel("Email");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(75, 37, 92, 24);
		contentPane.add(lblNewLabel);
		
		JLabel autoLoginUser = new JLabel("Auto Login (User)");
		autoLoginUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean is_logged = User.login("ahmed@gmail.com", "123");
				if(is_logged) {
					ServiceListFrame frame = new ServiceListFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		autoLoginUser.setForeground(SystemColor.textHighlight);
		autoLoginUser.setBounds(319, 11, 105, 14);
		contentPane.add(autoLoginUser);
		
		JLabel autoLoginAdmin = new JLabel("Auto Login (Admin)");
		autoLoginAdmin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				boolean is_logged = User.login("salah@gmail.com", "123");
				if(is_logged) {
					ServiceListFrame frame = new ServiceListFrame();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
					dispose();
				}
			}
		});
		autoLoginAdmin.setForeground(SystemColor.textHighlight);
		autoLoginAdmin.setBounds(319, 48, 105, 14);
		contentPane.add(autoLoginAdmin);
		this.setVisible(true);
		
//		User.setInstance("ahmed","ahmed@gmail.com", "123");
//		System.out.println(User.user.email);
//		ApplicationState.registered_users.stream().filter(c -> "ahmed@gmail.com".equals(c.email)).findFirst().get()
		
		
	}
}