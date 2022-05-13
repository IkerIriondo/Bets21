package gui;


import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;

import businessLogic.*;
import domain.Admin;
import domain.Erabiltzailea;
import domain.User;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

@SuppressWarnings("serial")
public class LoginGUI extends Frame {

	private JFrame frame;
	private JTextField emailField;
	private JPasswordField passField;
	private JLabel infoLabel;
	
	User user;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
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
	}*/

	/**
	 * Create the application.
	 */
	public LoginGUI() {
		super();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});
		
		initialize();
		frame.setVisible(true);
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
		
		JLabel lblPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(62, 74, 97, 21);
		frame.getContentPane().add(lblPassword);
		
		emailField = new JTextField();
		emailField.setBounds(230, 44, 134, 21);
		frame.getContentPane().add(emailField);
		emailField.setColumns(10);
		
		passField = new JPasswordField();
		passField.setColumns(10);
		passField.setBounds(230, 76, 134, 21);
		frame.getContentPane().add(passField);
		
		infoLabel = new JLabel();
		infoLabel.setBounds(47, 175, 346, 14);
		frame.getContentPane().add(infoLabel);
		
		JLabel info2Label = new JLabel("");
		info2Label.setBounds(62, 186, 302, 14);
		frame.getContentPane().add(info2Label);
		
		JButton hasiButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		
		hasiButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				BLFacade facade = MainGUI.getBusinessLogic();
				
				user = facade.isLogin(emailField.getText(), passField.getText());
				
				if (user==null){
					
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoUser"));
					info2Label.setText(ResourceBundle.getBundle("Etiquetas").getString("NoPass"));
					
				}else if (user.getClass()==Erabiltzailea.class) {
					frame.setVisible(false);
					System.out.println("Correctly logged in");
					new ErregistratuaGUI(user);
				}else if (user.getClass()==Admin.class) {
					frame.setVisible(false);
					System.out.println("Logged as admin");
					new AdminGUI(user);
				}
				
			}
		});
		hasiButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		hasiButton.setBounds(253, 130, 111, 34);
		frame.getContentPane().add(hasiButton);
		
		JButton jarraituButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("NoLogin"));
		jarraituButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				FindQuestionsGUI f = new FindQuestionsGUI(null);
				f.setVisible(true);
			}
		});
		jarraituButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jarraituButton.setBounds(10, 214, 198, 23);
		frame.getContentPane().add(jarraituButton);
		
		JButton registerButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new RegisterGUI();
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registerButton.setBounds(62, 130, 158, 34);
		frame.getContentPane().add(registerButton);
		
		JButton hizkuntzaButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Lang"));
		hizkuntzaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				MainGUI m = new MainGUI();
				m.setVisible(true);
			}
		});
		hizkuntzaButton.setBounds(274, 216, 152, 21);
		frame.getContentPane().add(hizkuntzaButton);
		
		
	}
}
