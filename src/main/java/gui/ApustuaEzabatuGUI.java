package gui;

import javax.swing.JFrame;
import javax.swing.JButton;

import domain.*;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		
		JLabel aukeratuLabel = new JLabel("Aukeratu zure apustu bat");
		aukeratuLabel.setBounds(137, 16, 175, 14);
		frame.getContentPane().add(aukeratuLabel);
		
		JLabel galderaLabel = new JLabel("Galdera:");
		galderaLabel.setBounds(30, 106, 144, 14);
		frame.getContentPane().add(galderaLabel);
		
		galderaField = new JTextField();
		galderaField.setEditable(false);
		galderaField.setBounds(195, 103, 175, 20);
		frame.getContentPane().add(galderaField);
		galderaField.setColumns(10);
		
		JLabel gertaeraLabel = new JLabel("Gertaera:");
		gertaeraLabel.setBounds(30, 84, 144, 14);
		frame.getContentPane().add(gertaeraLabel);
		
		gertaeraField = new JTextField();
		gertaeraField.setEditable(false);
		gertaeraField.setBounds(194, 81, 176, 20);
		frame.getContentPane().add(gertaeraField);
		gertaeraField.setColumns(10);
		
		JLabel erantzunLabel = new JLabel("Zure apustua:");
		erantzunLabel.setBounds(30, 131, 144, 14);
		frame.getContentPane().add(erantzunLabel);
		
		erantzunField = new JTextField();
		erantzunField.setEditable(false);
		erantzunField.setBounds(195, 128, 175, 20);
		frame.getContentPane().add(erantzunField);
		erantzunField.setColumns(10);
		
		JLabel zenbatLabel = new JLabel("Zenbat apostatu duzu:");
		zenbatLabel.setBounds(30, 156, 144, 14);
		frame.getContentPane().add(zenbatLabel);
		
		zenbatField = new JTextField();
		zenbatField.setEditable(false);
		zenbatField.setBounds(195, 153, 175, 20);
		frame.getContentPane().add(zenbatField);
		zenbatField.setColumns(10);
		
		JLabel infoLabel = new JLabel("");
		infoLabel.setBounds(137, 190, 199, 14);
		frame.getContentPane().add(infoLabel);
		
		JComboBox<Integer> comboBox = new JComboBox<Integer>();
		for (Apustua a : user.getApustuak()) {
			comboBox.addItem(a.getId());
		}
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = (int)comboBox.getSelectedItem();
				BLFacade facade = MainGUI.getBusinessLogic();
				Apustua apustu = facade.apustuaLortu(i);
				
				ErantzunPosiblea er = apustu.getEmaitzaPosiblea();
				erantzunField.setText(er.getErantzunPosiblea());
				
				Question q = er.getGaldera();
				galderaField.setText(q.getQuestion());
				
				Event gert = q.getEvent();
				gertaeraField.setText(gert.getDescription());
				
				float zenbat = apustu.getDirua();
				zenbatField.setText(String.valueOf(zenbat));;
			}
		});
		comboBox.setBounds(137, 41, 175, 22);
		frame.getContentPane().add(comboBox);
		
		JButton ezabatuButton = new JButton("Ezabatu");
		ezabatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		ezabatuButton.setBounds(164, 215, 89, 23);
		frame.getContentPane().add(ezabatuButton);
		
		JButton atzeraButton = new JButton("Atzera");
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ErregistratuaGUI(user);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
	}
}
