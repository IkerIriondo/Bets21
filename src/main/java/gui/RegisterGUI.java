package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JTextField;

public class RegisterGUI {

	private JFrame frame;
	private JTextField izenaField;
	private JTextField abizenaField;
	private JTextField jaioDatField;
	private JTextField emailField;
	private JTextField usernameField;
	private JTextField PasswordField;
	private JTextField confirmPasswordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI window = new RegisterGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RegisterGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblIzena = new JLabel("Izena:");
		lblIzena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIzena.setBounds(57, 10, 123, 17);
		frame.getContentPane().add(lblIzena);
		
		JLabel lblAbizena = new JLabel("Abizena:");
		lblAbizena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAbizena.setBounds(57, 35, 123, 17);
		frame.getContentPane().add(lblAbizena);
		
		JLabel lblJaiotzedata = new JLabel("Jaiotze-Data:");
		lblJaiotzedata.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblJaiotzedata.setBounds(57, 60, 123, 17);
		frame.getContentPane().add(lblJaiotzedata);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(57, 85, 123, 17);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(57, 110, 123, 17);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(57, 135, 123, 17);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblConfirmPassword = new JLabel("Confirm Password:");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(57, 160, 123, 17);
		frame.getContentPane().add(lblConfirmPassword);
		
		JButton registerButton = new JButton("ERREGISTRATU");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registerButton.setBounds(133, 200, 158, 34);
		frame.getContentPane().add(registerButton);
		
		izenaField = new JTextField();
		izenaField.setColumns(10);
		izenaField.setBounds(235, 10, 134, 21);
		frame.getContentPane().add(izenaField);
		
		abizenaField = new JTextField();
		abizenaField.setColumns(10);
		abizenaField.setBounds(235, 35, 134, 21);
		frame.getContentPane().add(abizenaField);
		
		jaioDatField = new JTextField();
		jaioDatField.setColumns(10);
		jaioDatField.setBounds(235, 60, 134, 21);
		frame.getContentPane().add(jaioDatField);
		
		emailField = new JTextField();
		emailField.setColumns(10);
		emailField.setBounds(235, 85, 134, 21);
		frame.getContentPane().add(emailField);
		
		usernameField = new JTextField();
		usernameField.setColumns(10);
		usernameField.setBounds(235, 110, 134, 21);
		frame.getContentPane().add(usernameField);
		
		PasswordField = new JTextField();
		PasswordField.setColumns(10);
		PasswordField.setBounds(235, 135, 134, 21);
		frame.getContentPane().add(PasswordField);
		
		confirmPasswordField = new JTextField();
		confirmPasswordField.setColumns(10);
		confirmPasswordField.setBounds(235, 160, 134, 21);
		frame.getContentPane().add(confirmPasswordField);
	}
}