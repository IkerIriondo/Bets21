package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import domain.Admin;
import domain.Erabiltzailea;
import domain.User;

import javax.swing.JRadioButton;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends Frame {

	private JFrame frame;
	private JTextField emailField;
	private JPasswordField passField;
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
		
		JLabel lblPassword = new JLabel("Password:");
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
		
		JButton hasiButton = new JButton("Saioa hasi");
		
		hasiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BLFacade facade = MainGUI.getBusinessLogic();
				
				User user = facade.isLogin(emailField.getText(), passField.getText());
				
				if (user==null){
					
					System.out.println("User not found");
					
				}else if (user.getClass()==Erabiltzailea.class) {
					frame.setVisible(false);
					System.out.println("Correctly logged in");
					FindQuestionsGUI f = new FindQuestionsGUI();
					f.setVisible(true);
				}else if (user.getClass()==Admin.class) {
					frame.setVisible(false);
					System.out.println("Logged as admin");
					AdminGUI a = new AdminGUI();
				}
				
			}
		});
		hasiButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		hasiButton.setBounds(253, 130, 111, 34);
		frame.getContentPane().add(hasiButton);
		
		JButton jarraituButton = new JButton("Jarraitu saioa hasi gabe");
		jarraituButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				FindQuestionsGUI f = new FindQuestionsGUI();
				f.setVisible(true);
				
			}
		});
		jarraituButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		jarraituButton.setBounds(10, 214, 169, 23);
		frame.getContentPane().add(jarraituButton);
		
		JButton registerButton = new JButton("ERREGISTRATU");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				RegisterGUI r = new RegisterGUI();
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registerButton.setBounds(62, 130, 158, 34);
		frame.getContentPane().add(registerButton);
	}
}
