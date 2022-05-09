package gui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;

import domain.*;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class MezuakBidaliGUI extends JFrame{

	private JFrame frame;
	private User bidaltzaile;
	private Elkarrizketa elkarrizketa;
	private Vector<Mezua> mezuak;
	
	private JTable mezuakTable;
	private JScrollPane mezuakScrollPane;
	private DefaultTableModel mezuakTableModel;
	
	private String[] mezuakColumnNames = {"Nork", "Mezua"};
	private JTextField mezuaField;
	private JButton bidaliMezuaButton;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MezuakBidaliGUI window = new MezuakBidaliGUI();
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
	public MezuakBidaliGUI(User bidaltzaile, Elkarrizketa elkar) {
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
		this.bidaltzaile = bidaltzaile;
		elkarrizketa = elkar;
		mezuak = elkarrizketa.getMezuak();
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 499, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ElkarrizketakGUI(bidaltzaile);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		mezuakScrollPane = new JScrollPane();
		
		mezuakScrollPane.setBounds(10, 11, 450, 185);
		frame.getContentPane().add(mezuakScrollPane);
		
		mezuakTable = new JTable();
		mezuakScrollPane.setViewportView(mezuakTable);
	
		mezuakTableModel = new DefaultTableModel(null, mezuakColumnNames);
		mezuakTable.setModel(mezuakTableModel);	
		
		mezuaField = new JTextField();
		mezuaField.setBounds(151, 227, 150, 20);
		frame.getContentPane().add(mezuaField);
		mezuaField.setColumns(10);
		
		bidaliMezuaButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendMessage"));
		bidaliMezuaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		bidaliMezuaButton.setBounds(349, 227, 89, 23);
		frame.getContentPane().add(bidaliMezuaButton);
		
		mezuakTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		mezuakTable.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		for(Mezua m: mezuak) {
			Vector<Object> row = new Vector<Object>();
			row.add(m.getNork());
			row.add(m.getMezua());
			mezuakTableModel.addRow(row);
		}
		
	}

}
