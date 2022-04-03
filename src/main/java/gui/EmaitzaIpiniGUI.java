package gui;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

import domain.ErantzunPosiblea;
import domain.Question;
import domain.User;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;

import businessLogic.BLFacade;

@SuppressWarnings("serial")
public class EmaitzaIpiniGUI extends Frame{

	private JFrame frame;
	private User user;
	private Question galdera;
	private JTextField kuotaField;
	private JTextField galderaField;
	private ErantzunPosiblea eran;
	
	private JComboBox<String> comboBox;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EmaitzaIpiniGUI window = new EmaitzaIpiniGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the application.
	 * @param galdera 
	 */
	public EmaitzaIpiniGUI(User user, Question galdera) {
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
		this.user = user;
		this.galdera = galdera;
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
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
				f.getEmaitzaIpiniButton().setVisible(true);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		JLabel gladeraLabel = new JLabel("Galdera:");
		gladeraLabel.setBounds(58, 31, 46, 14);
		frame.getContentPane().add(gladeraLabel);
		
		JLabel aukeratuEmaitzaLabel = new JLabel("Aukeratu emaitza:");
		aukeratuEmaitzaLabel.setBounds(107, 64, 89, 14);
		frame.getContentPane().add(aukeratuEmaitzaLabel);
		
		JLabel kuotaLabel = new JLabel("Kuota:");
		kuotaLabel.setBounds(150, 144, 46, 14);
		frame.getContentPane().add(kuotaLabel);
		
		kuotaField = new JTextField();
		kuotaField.setEditable(false);
		kuotaField.setBounds(222, 141, 86, 20);
		frame.getContentPane().add(kuotaField);
		kuotaField.setColumns(10);
		
		JLabel infoLabel = new JLabel("");
		infoLabel.setBounds(42, 116, 351, 14);
		frame.getContentPane().add(infoLabel);
		
		
		JButton emaitzaIpiniButton = new JButton("Emaitza Ipini");
		emaitzaIpiniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.emaitzaIpini(eran);
				infoLabel.setText("Emaitza ondo ipini da");
				System.out.println("Emaitza ondo ipini da");
			}
		});
		emaitzaIpiniButton.setBounds(166, 194, 102, 30);
		frame.getContentPane().add(emaitzaIpiniButton);
		
		galderaField = new JTextField();
		galderaField.setEditable(false);
		galderaField.setBounds(114, 28, 261, 20);
		frame.getContentPane().add(galderaField);
		galderaField.setColumns(10);
		galderaField.setText(galdera.getQuestion());
		
		comboBox = new JComboBox<String>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = comboBox.getSelectedIndex();
				eran = galdera.getErantzunPosibleak().get(i);
				kuotaField.setText(String.valueOf(eran.getKuota()));
			}
		});
		
		comboBox.setBounds(107, 83, 227, 22);
		for (ErantzunPosiblea eran : galdera.getErantzunPosibleak()) {
			comboBox.addItem(eran.getErantzunPosiblea());
		}
		frame.getContentPane().add(comboBox);
		
		
		
	}
}
