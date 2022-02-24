package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginGUI {

	private JFrame frame;
	private JTextField emailField;
	private JTextField passField;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	private static BLFacade appFacadeInterface;
		
		public static BLFacade getBusinessLogic(){
			return appFacadeInterface;
		}
		 
		public static void setBussinessLogic (BLFacade afi){
			appFacadeInterface=afi;
		}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI window = new LoginGUI();
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
	public LoginGUI() {
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
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setBounds(62, 42, 97, 21);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(62, 74, 97, 21);
		frame.getContentPane().add(lblPassword);
		
		emailField = new JTextField();
		emailField.setBounds(230, 44, 134, 21);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		passField = new JTextField();
		passField.setColumns(10);
		passField.setBounds(230, 76, 134, 21);
		frame.getContentPane().add(passField);
		
		JButton hasiButton = new JButton("Saioa hasi");
		
		hasiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(erabilSelect.isSelected()) {
					
					System.out.println(appFacadeInterface.isLogin(emailField.getText(), passField.getText()));
				}
				
			}
		});
		hasiButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		hasiButton.setBounds(253, 130, 111, 34);
		frame.getContentPane().add(hasiButton);
		
		JButton jarraituButton = new JButton("Jarraitu saioa hasi gabe");
		jarraituButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jarraituButton.setBounds(10, 214, 169, 23);
		frame.getContentPane().add(jarraituButton);
		
		JButton registerButton = new JButton("ERREGISTRATU");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registerButton.setBounds(62, 130, 158, 34);
		frame.getContentPane().add(registerButton);
	}
}
