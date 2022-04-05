package gui;

import javax.swing.JFrame;
import javax.swing.JButton;

import domain.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.time.Period;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.BLFacade;

@SuppressWarnings("serial")
public class ApustuaEzabatuGUI extends Frame{

	private JFrame frame;
	private User user;
	private JTextField galderaField;
	private JTextField gertaeraField;
	private JTextField erantzunField;
	private JTextField zenbatField;
	private JComboBox<Integer> comboBox;
	private JLabel infoLabel;
	private Apustua apustu;
	private ErantzunPosiblea er;
	private Question galdera;
	private Event gertaera;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApustuaEzabatuGUI window = new ApustuaEzabatuGUI();
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
	public ApustuaEzabatuGUI(User user) {
		this.user = user;
		initialize();
		frame.setVisible(true);
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
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel aukeratuLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectBet"));
		aukeratuLabel.setBounds(137, 16, 175, 14);
		frame.getContentPane().add(aukeratuLabel);
		
		JLabel galderaLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Query"));
		galderaLabel.setBounds(30, 106, 144, 14);
		frame.getContentPane().add(galderaLabel);
		
		galderaField = new JTextField();
		galderaField.setEditable(false);
		galderaField.setBounds(195, 103, 175, 20);
		frame.getContentPane().add(galderaField);
		galderaField.setColumns(10);
		
		JLabel gertaeraLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event"));
		gertaeraLabel.setBounds(30, 84, 144, 14);
		frame.getContentPane().add(gertaeraLabel);
		
		gertaeraField = new JTextField();
		gertaeraField.setEditable(false);
		gertaeraField.setBounds(194, 81, 176, 20);
		frame.getContentPane().add(gertaeraField);
		gertaeraField.setColumns(10);
		
		JLabel erantzunLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("YourBet"));
		erantzunLabel.setBounds(30, 131, 144, 14);
		frame.getContentPane().add(erantzunLabel);
		
		erantzunField = new JTextField();
		erantzunField.setEditable(false);
		erantzunField.setBounds(195, 128, 175, 20);
		frame.getContentPane().add(erantzunField);
		erantzunField.setColumns(10);
		
		JLabel zenbatLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ZenbatApostatu"));
		zenbatLabel.setBounds(30, 156, 144, 14);
		frame.getContentPane().add(zenbatLabel);
		
		zenbatField = new JTextField();
		zenbatField.setEditable(false);
		zenbatField.setBounds(195, 153, 175, 20);
		frame.getContentPane().add(zenbatField);
		zenbatField.setColumns(10);
		
		infoLabel = new JLabel("");
		infoLabel.setBounds(40, 190, 330, 14);
		frame.getContentPane().add(infoLabel);
		
		comboBox = new JComboBox<Integer>();
		comboBoxHasieratu();
		
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = (int)comboBox.getSelectedItem();
					BLFacade facade = MainGUI.getBusinessLogic();
					List<ApustuContainer> apustuak = facade.apustuakLortu();
					ApustuContainer ac = null;
					for (ApustuContainer a : apustuak) {
						if(apustu==null && a.getApustu().getId()==i) {
							ac = a;
						}
					}
					apustu = ac.getApustu();
					
					er = ac.getErantzun();
					erantzunField.setText(er.getErantzunPosiblea());
					
					galdera = ac.getGaldera();
					galderaField.setText(galdera.getQuestion());
					
					gertaera = ac.getGertaera();
					gertaeraField.setText(gertaera.getDescription());
					
					float zenbat = apustu.getDirua();
					zenbatField.setText(String.valueOf(zenbat));;
					
				}catch(Exception e2) {
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoBets"));
					System.out.println("Ez duzu apusturik");
				}
			}
		});
		comboBox.setBounds(137, 41, 175, 22);
		frame.getContentPane().add(comboBox);
		
		JButton ezabatuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Delete"));
		ezabatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					Date data = gertaera.getEventDate();
					LocalDate g = LocalDate.now();
					Date gaur = new Date(g.getYear()-1900,g.getMonthValue()-1,g.getDayOfMonth());
					if(data.compareTo(gaur)>=0) {
						BLFacade facade = MainGUI.getBusinessLogic();
						user = facade.apustuaEzabatu(apustu);
						comboBoxHasieratu();
					}else {
						infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("HappenedEvent"));
						System.out.println("Gertaera hau dagoeneko gertatu da");
					}
			}
		});
		ezabatuButton.setBounds(164, 215, 89, 23);
		frame.getContentPane().add(ezabatuButton);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ErregistratuaGUI(user);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
	}

	private void comboBoxHasieratu() {
		try{
			comboBox.removeAllItems();
			if(!user.getApustuak().isEmpty()) {
				for (Apustua a : user.getApustuak()) {
					comboBox.addItem(a.getId());
				}
			}else {
				infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoBets"));
				System.out.println("Ez duzu apusturik");
			}
		}catch(Exception e) {
			infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoBets"));
			System.out.println("Ez duzu apusturik");
		}
	}
}//Klasea
