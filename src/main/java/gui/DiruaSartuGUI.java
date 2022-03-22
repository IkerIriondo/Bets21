package gui;

import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

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
		
		JButton atzeraButton = new JButton("Atzera");
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new ErregistratuaGUI(user);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		JLabel diruLabel = new JLabel("Zenbat diru sartu nahi duzu?");
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
		
		JButton sartuDiruaButton = new JButton("Sartu Dirua");
		sartuDiruaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String diru = diruField.getText();
					float dirua = Float.parseFloat(diru);
					if(dirua<=0) {
						infoLabel.setText("Ezin duzu 0 edo zenbaki txikiagorik sartu");
						System.out.println("Ezin duzu 0 edo zenbaki txikiagorik sartu");
					}else {
						BLFacade facade = MainGUI.getBusinessLogic();
						
						facade.diruaSartu(user,dirua);
						System.out.println("Zure kontuko dirua eguneratu da");
						infoLabel.setText("Zure kontuko dirua eguneratu da");
					}
				}catch(NumberFormatException e1) {
					infoLabel.setText("Sartu zenbakizko balio bat");
				}
			}
		});
		sartuDiruaButton.setBounds(178, 143, 135, 23);
		frame.getContentPane().add(sartuDiruaButton);
		
	}
}
