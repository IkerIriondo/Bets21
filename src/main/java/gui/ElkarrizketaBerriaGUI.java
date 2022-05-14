package gui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import domain.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ElkarrizketaBerriaGUI extends JFrame{

	private JFrame frame;
	private User user;
	private JTextField bilatuField;
	private List<Erabiltzailea> erabil;
	private List<String> emailak;
	
	private String[] erabiltzaileakColumnNames = {"Zenb", "Erabiltzailea"};
	
	private JTable elkarrizketakTable;
	private DefaultTableModel elkarrizketakTableModel;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElkarrizketaBerriaGUI window = new ElkarrizketaBerriaGUI(null);
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
	public ElkarrizketaBerriaGUI(User user) {
		super();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				try {
					//if (ConfigXML.getInstance().isBusinessLogicLocal()) facade.close();
				} catch (Exception e1) {
					System.out.println("Error: "+e1.toString()+" , probably problems with Business Logic or Database");
				}
				System.exit(1);
			}
		});
		emailak = new LinkedList<String>();
		this.user = user;
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
		
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ElkarrizketakGUI(user);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		for (Elkarrizketa e : user.getElkarrizketak()) {
			
			if (e.getUser1()==user) {
				emailak.add(e.getUser2().getEmail());
			}else {
				emailak.add(e.getUser1().getEmail());
			}
			
		}
		
		bilatuField = new JTextField();
		bilatuField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				String bilatzeko = bilatuField.getText();
				
				BLFacade facade = MainGUI.getBusinessLogic();
				erabil = facade.bilatuErabiltzaileak(bilatzeko);
				
				elkarrizketakTableModel.setRowCount(0);
				
				int i = 1;
				for (Erabiltzailea era : erabil) {
					if(!user.getEmail().contentEquals(era.getEmail())) {
						if(!emailak.contains(era.getEmail())) {
							Vector<Object> row = new Vector<Object>();
							row.add(i);
							row.add(era.getUsername());
							i++;
							elkarrizketakTableModel.addRow(row);	
						}						
					}		
				}
				
			}
		});
		
		bilatuField.setBounds(10, 11, 414, 20);
		frame.getContentPane().add(bilatuField);
		bilatuField.setColumns(10);
		
		JScrollPane elkarrizketakScrollPane = new JScrollPane();
		
		elkarrizketakScrollPane.setBounds(10, 40, 414, 165);
		frame.getContentPane().add(elkarrizketakScrollPane);
		
		elkarrizketakTable = new JTable();
		elkarrizketakScrollPane.setViewportView(elkarrizketakTable);
	
		elkarrizketakTableModel = new DefaultTableModel(null, erabiltzaileakColumnNames);
		elkarrizketakTable.setModel(elkarrizketakTableModel);	
		
		elkarrizketakTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		elkarrizketakTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		
		JLabel infoLabel = new JLabel(); 
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(56, 211, 323, 14);
		frame.getContentPane().add(infoLabel);
		
		JButton elkarrizketaBerriaButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("NewChat")); //$NON-NLS-1$ //$NON-NLS-2$
		elkarrizketaBerriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					BLFacade facade = MainGUI.getBusinessLogic();
					int i = elkarrizketakTable.getSelectedRow();
					User zeinekin = erabil.get(i+1);
					user = facade.elkarrizketaBerria(user, zeinekin);
					new MezuakBidaliGUI(user, user.getElkarrizketak().lastElement()/*user.getElkarrizketak().get(user.getElkarrizketak().size())*/);
					frame.setVisible(false);
				} catch (Exception e2) {
					
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoSelectedChat"));
					
				}
				
			}
		});
		elkarrizketaBerriaButton.setBounds(154, 227, 124, 23);
		frame.getContentPane().add(elkarrizketaBerriaButton);
		
	}
}
