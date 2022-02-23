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
		lblEmail.setBounds(62, 25, 97, 21);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(62, 57, 97, 21);
		frame.getContentPane().add(lblPassword);
		
		emailField = new JTextField();
		emailField.setBounds(230, 27, 134, 21);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		passField = new JTextField();
		passField.setColumns(10);
		passField.setBounds(230, 59, 134, 21);
		frame.getContentPane().add(passField);
		
		JRadioButton erabilSelect = new JRadioButton("Erabiltzailea");
		buttonGroup.add(erabilSelect);
		erabilSelect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		erabilSelect.setBounds(91, 100, 109, 23);
		frame.getContentPane().add(erabilSelect);
		
		JRadioButton adminSelect = new JRadioButton("Admin");
		buttonGroup.add(adminSelect);
		adminSelect.setFont(new Font("Tahoma", Font.PLAIN, 13));
		adminSelect.setBounds(230, 100, 109, 23);
		frame.getContentPane().add(adminSelect);
		
		JButton hasiButton = new JButton("Saioa hasi");
		
		hasiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(erabilSelect.isSelected()) {
					
					System.out.println(appFacadeInterface.isLogin(emailField.getText(), passField.getText()));
				}
				
			}
		});
		hasiButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		hasiButton.setBounds(72, 146, 97, 23);
		frame.getContentPane().add(hasiButton);
		
		JButton jarraituButton = new JButton("Jarraitu saioa hasi gabe");
		jarraituButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jarraituButton.setBounds(195, 146, 169, 23);
		frame.getContentPane().add(jarraituButton);
		
		JButton registerButton = new JButton("ERREGISTRATU");
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registerButton.setBounds(136, 195, 158, 34);
		frame.getContentPane().add(registerButton);
	}
}
