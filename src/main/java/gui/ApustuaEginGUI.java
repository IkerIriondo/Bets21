package gui;

import javax.swing.JFrame;

import domain.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.BLFacade;

import javax.swing.JComboBox;

public class ApustuaEginGUI {

	private JFrame frame;
	private User user;
	private Question galdera;
	private JTextField diruField;
	private JTextField apustuMinField;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApustuaEginGUI window = new ApustuaEginGUI();
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
	public ApustuaEginGUI(User u, Question gald) {
		this.user = u;
		this.galdera = gald;
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
		
		JButton atzeraButton = new JButton("Atzera");
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
				f.getApustuEginButton().setVisible(true);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		JLabel infoLabel = new JLabel("");
		infoLabel.setBounds(90, 176, 301, 14);
		frame.getContentPane().add(infoLabel);
		
		JLabel aukeratuLabel = new JLabel("Aukeratu erantzun posible bat:");
		aukeratuLabel.setBounds(123, 37, 186, 14);
		frame.getContentPane().add(aukeratuLabel);
		
		JLabel apustuMinLabel = new JLabel("Apostu minimoa:");
		apustuMinLabel.setBounds(66, 126, 115, 14);
		frame.getContentPane().add(apustuMinLabel);
		
		apustuMinField = new JTextField();
		apustuMinField.setEnabled(false);
		apustuMinField.setBounds(242, 117, 86, 20);
		String min = String.valueOf(galdera.getBetMinimum());
		apustuMinField.setText(min);
		frame.getContentPane().add(apustuMinField);
		apustuMinField.setColumns(10);
		
		JComboBox<String> erantzunPosibleComboBox = new JComboBox<String>();
		erantzunPosibleComboBox.setBounds(125, 65, 203, 22);
		
		for (ErantzunPosiblea eran : galdera.getKuotak()) {
			erantzunPosibleComboBox.addItem(eran.getErantzunPosiblea());
		}
		frame.getContentPane().add(erantzunPosibleComboBox);
		
		JButton apostatuButton = new JButton("Apustua egin");
		apostatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//try {
					float apostu = Float.parseFloat(diruField.getText());
					if(apostu >= Float.parseFloat(min)) {
						if(apostu <= user.getDirua()) {
							BLFacade facade = MainGUI.getBusinessLogic();
							int indize = erantzunPosibleComboBox.getSelectedIndex();
							user = facade.apustuaEgin(apostu,user,galdera.getKuotak().get(indize));
							infoLabel.setText("Ondo sortu da zure apustua");
							System.out.println("Ondo sortu da zure apustua");
						}else {
							infoLabel.setText("Ez daukazu diru nahikoa zure kontuan");
							System.out.println("Ez daukazu diru nahikoa zure kontuan");
						}
					}else {
						infoLabel.setText("Ez da apustu minimora iristen");
						System.out.println("Ez da apustu minimora iristen");
					}
				/*}catch(Exception e1) {
					infoLabel.setText("Sartu baliozko zenbaki bat");
					System.out.println("Sartu baliozko zenbaki bat");
				}*/
				
			}
		});
		apostatuButton.setBounds(148, 201, 161, 23);
		frame.getContentPane().add(apostatuButton);
		
		
		
		JLabel zenbatApustuLabel = new JLabel("Zenbat apostatu nahi duzu?");
		zenbatApustuLabel.setBounds(66, 151, 146, 14);
		frame.getContentPane().add(zenbatApustuLabel);
		
		diruField = new JTextField();
		diruField.setBounds(242, 148, 86, 20);
		frame.getContentPane().add(diruField);
		diruField.setColumns(10);
		
		
		
		
		
		
	}
}
