package gui;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import businessLogic.BLFacade;
import domain.*;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JFormattedTextField;

@SuppressWarnings("serial")
public class RegisterGUI extends Frame {

	private JFrame frame;
	private JTextField izenaField;
	private JTextField abizenaField;
	private JFormattedTextField jaioDatField;
	private JTextField emailField;
	private JTextField usernameField;
	private JPasswordField PasswordField;
	private JPasswordField confirmPasswordField;
	private JButton atzeraButton;
	private JLabel infoLabel;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
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
	}*/

	/**
	 * Create the application.
	 */
	public RegisterGUI() {
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
		
		JLabel lblIzena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name"));
		lblIzena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblIzena.setBounds(57, 10, 142, 17);
		frame.getContentPane().add(lblIzena);
		
		JLabel lblAbizena = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Surname"));
		lblAbizena.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAbizena.setBounds(57, 35, 142, 17);
		frame.getContentPane().add(lblAbizena);
		
		JLabel lblJaiotzedata = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BDate"));
		lblJaiotzedata.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblJaiotzedata.setBounds(57, 60, 142, 17);
		frame.getContentPane().add(lblJaiotzedata);
		
		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblEmail.setBounds(57, 85, 123, 17);
		frame.getContentPane().add(lblEmail);
		
		JLabel lblUsername = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("User"));
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblUsername.setBounds(57, 110, 142, 17);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblPassword.setBounds(57, 135, 142, 17);
		frame.getContentPane().add(lblPassword);
		
		infoLabel = new JLabel("");
		infoLabel.setBounds(57, 188, 315, 14);
		frame.getContentPane().add(infoLabel);
		
		JLabel lblConfirmPassword = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CPass"));
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblConfirmPassword.setBounds(57, 162, 142, 17);
		frame.getContentPane().add(lblConfirmPassword);
		
		JButton registerButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		registerButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				User ema;
				String izena = izenaField.getText();
				String abizena = abizenaField.getText();
				String jaioData = jaioDatField.getText();
				String email = emailField.getText();
				String username = usernameField.getText();
				String password = PasswordField.getText();
				String passConfirm = confirmPasswordField.getText();
				
				if(!password.contentEquals(passConfirm)) {
					System.out.println("Pasahitzak ezberdinak dira");
					infoLabel.setText("Pasahitzak ezberdinak dira");
				}else if(!izena.isBlank() && !abizena.isBlank() && !jaioData.isBlank() && !email.isBlank()&& !username.isBlank() && !password.isBlank()){
					String[] osagaiak = jaioData.split("/");
					String urtea = osagaiak[0];
					String hilabete = osagaiak[1];
					String egun = osagaiak[2];
					try {
						int u = Integer.parseInt(urtea);
						int h = Integer.parseInt(hilabete);
						int eg = Integer.parseInt(egun);
					
						if(dataZuzena(jaioData)) {
							Period adina = Period.between(LocalDate.of(u, h, eg), LocalDate.now());
							if(adina.getYears() >= 18) {
								if (emailaBaliozkoa(email)) {
									ema = facade.register(izena,abizena,jaioData,email,username,password);
									if (ema != null) {
										System.out.println("Kontua ondo sortu da");
										frame.setVisible(false);
										new ErregistratuaGUI(ema);
									}else {
										infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ThereIs"));
										System.out.println("Dagoeneko badago erabiltzaile bat email hori duena");
									}
								}else {
									System.out.println(ResourceBundle.getBundle("Etiquetas").getString("NotEmail"));
									infoLabel.setText("Emaila ez da egokia");
								}
							}else {
								infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Adingabea"));
								System.out.println("Adingabekoa zara");
							}
						}else {
							infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoData"));
							System.out.println("Sartutako data ez da zuzena");
						}
					} catch (Exception e2) {
						System.out.println(ResourceBundle.getBundle("Etiquetas").getString("NoData"));
						infoLabel.setText("Sartutako data ez da zuzena");
					}
				}else {
					infoLabel.setText("Sartu ondo datuak");
					System.out.println("Sartu ondo datuak");
				}
				
			}
		});
		registerButton.setFont(new Font("Tahoma", Font.PLAIN, 13));
		registerButton.setBounds(175, 216, 158, 34);
		frame.getContentPane().add(registerButton);
		
		izenaField = new JTextField();
		izenaField.setColumns(10);
		izenaField.setBounds(235, 10, 134, 21);
		frame.getContentPane().add(izenaField);
		
		abizenaField = new JTextField();
		abizenaField.setColumns(10);
		abizenaField.setBounds(235, 35, 134, 21);
		frame.getContentPane().add(abizenaField);
		
		jaioDatField = new JFormattedTextField();
		MaskFormatter mask;
		try {
			mask = new MaskFormatter("####/##/##");
			mask.install(jaioDatField);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		jaioDatField.setName("");
		jaioDatField.setToolTipText("uuuu/hh/ee");
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
		
		PasswordField = new JPasswordField();
		PasswordField.setColumns(10);
		PasswordField.setBounds(235, 135, 134, 21);
		frame.getContentPane().add(PasswordField);
		
		confirmPasswordField = new JPasswordField();
		confirmPasswordField.setColumns(10);
		confirmPasswordField.setBounds(235, 160, 134, 21);
		frame.getContentPane().add(confirmPasswordField);
		
		atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginGUI();
			}
		});
		atzeraButton.setBounds(28, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
	}
	
	private boolean dataZuzena(String data) {
		try {
			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy/MM/dd");
            formatoFecha.setLenient(false);
            formatoFecha.parse(data);
		}catch(ParseException e1) {
			return false;
		}
		return true;
	}
	
	private boolean emailaBaliozkoa(String email) {
		Pattern pat = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");   
		Matcher mat = pat.matcher(email);
		if(mat.find()) {
			return true;
		}else{
			return false;
	    }
	}
}
