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

import businessLogic.BLFacade;

import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class MezuakBidaliGUI extends JFrame{

	private JFrame frame;
	private User bidaltzaile;
	private Elkarrizketa elkar;
	private ElkarrizketaContainer elkarrizketa;
	private Vector<MezuContainer> mezuak;
	
	private JTable mezuakTable;
	private JScrollPane mezuakScrollPane;
	private DefaultTableModel mezuakTableModel;
	
	private String[] mezuakColumnNames = {ResourceBundle.getBundle("Etiquetas").getString("Who"), 
			ResourceBundle.getBundle("Etiquetas").getString("Message")};
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
		this.elkar = elkar;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 565, 359);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		elkarrizketa = facade.sortuElkarContainer(elkar);
		
		mezuak = facade.lortuMezuak(elkarrizketa.getMezuak());
		mezuak.addAll(new Vector<MezuContainer>());
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(elkarrizketa.mezurikEz()) {
					BLFacade facade = MainGUI.getBusinessLogic();
					bidaltzaile = facade.elkarrizketaEzabatu(elkarrizketa.getElkarrizketa(), bidaltzaile);
				}
				
				new ElkarrizketakGUI(bidaltzaile);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 286, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		mezuakScrollPane = new JScrollPane();
		
		mezuakScrollPane.setBounds(10, 11, 529, 240);
		frame.getContentPane().add(mezuakScrollPane);
		
		mezuakTable = new JTable();
		mezuakTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				int i = mezuakTable.getSelectedRow();
				BLFacade facade = MainGUI.getBusinessLogic();
				MezuContainer m = facade.mezuaBilatu(mezuak.get(i).getMezua());
				if(!m.isReported() && !m.getNork().getUsername().equals(bidaltzaile.getUsername())) {
					new ErreportatuMezuaGUI(m.getMezua());
				}
			}
		});
		mezuakScrollPane.setViewportView(mezuakTable);
	
		mezuakTableModel = new DefaultTableModel(null, mezuakColumnNames);
		mezuakTable.setModel(mezuakTableModel);	
		
		mezuaField = new JTextField();
		mezuaField.setBounds(173, 287, 233, 20);
		frame.getContentPane().add(mezuaField);
		mezuaField.setColumns(10);
		
		bidaliMezuaButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendMessage"));
		bidaliMezuaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String testua = mezuaField.getText();
				if(!testua.isBlank()) {
					BLFacade facade = MainGUI.getBusinessLogic();
					elkarrizketa = facade.bidaliMezua(bidaltzaile,elkarrizketa.getElkarrizketa(),testua);
					mezuak = elkarrizketa.getMezuak();
					if(elkarrizketa.getUser1().getEmail().contentEquals(bidaltzaile.getEmail())) {
						bidaltzaile = elkarrizketa.getUser1();
					}else {
						bidaltzaile = elkarrizketa.getUser2();
					}
					mezuak = elkarrizketa.getMezuak();
					eguneratuMezuakTable(testua);
					mezuaField.setText("");
				}
			}
		});
		bidaliMezuaButton.setBounds(450, 286, 89, 23);
		frame.getContentPane().add(bidaliMezuaButton);
		
		mezuakTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		mezuakTable.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		try {
			for(MezuContainer m: mezuak) {
				Vector<Object> row = new Vector<Object>();
				row.add(m.getNork().getUsername());
				row.add(m.getTestua());
				mezuakTableModel.addRow(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void eguneratuMezuakTable(String m) {
		Vector<Object> row = new Vector<Object>();
		row.add(bidaltzaile.getUsername());
		row.add(m);
		mezuakTableModel.addRow(row);
	}
}
