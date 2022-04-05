package gui;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JFrame;

import domain.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import businessLogic.*;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class DiruaSartuGUI extends Frame{

	private JFrame frame;
	User user;
	private JTextField diruField;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DiruaSartuGUI window = new DiruaSartuGUI();
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
	public DiruaSartuGUI() {
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

	public DiruaSartuGUI(User user) {
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
		this.user = user;
	}
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ErregistratuaGUI(user);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		JLabel diruLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("HowMuchMoney"));
		diruLabel.setBounds(67, 72, 167, 14);
		frame.getContentPane().add(diruLabel);
		
		diruField = new JTextField();
		diruField.setBounds(267, 69, 86, 20);
		frame.getContentPane().add(diruField);
		diruField.setColumns(10);
		
		JLabel infoLabel = new JLabel("");
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(67, 118, 286, 14);
		frame.getContentPane().add(infoLabel);
		
		JButton sartuDiruaButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartu"));
		sartuDiruaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String diru = diruField.getText();
					if(diruZuzena(diru)) {
						float dirua = Float.parseFloat(diru);
						if(dirua<=0) {
							infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Zero"));
							System.out.println("Ezin duzu 0 edo zenbaki txikiagorik sartu");
						}else {
							BLFacade facade = MainGUI.getBusinessLogic();
							user = facade.diruaSartu(user,dirua);
							System.out.println("Zure kontuko dirua eguneratu da");
							infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Refresh"));
						}
					}else {
						System.out.println("Sartu baliozko zenbaki bat");
						infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ValidNumber"));
					}
				}catch(NumberFormatException e1) {
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("ValidNumber"));
					System.out.println("Sartu zenbakizko balio bat");
				}
			}

			
		});
		sartuDiruaButton.setBounds(178, 143, 135, 23);
		frame.getContentPane().add(sartuDiruaButton);
		
	}
	
	private boolean diruZuzena(String diru) {
		Pattern pat = Pattern.compile("^[0-9]+([.][0-9]{1,2}+)?$");
		Matcher mat = pat.matcher(diru);
		if(mat.find()) {
			return true;
		}else{
			return false;
	    }
	}
}
