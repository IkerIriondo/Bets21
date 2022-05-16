package gui;


import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;

import domain.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ElkarrizketakGUI extends JFrame{

	private JFrame frame;
	private User user;
	private Vector<ElkarrizketaContainer> elkarrizketak;
	
	private JTable elkarrizketakTable;
	
	private DefaultTableModel elkarrizketakTableModel;
	private String[] elkarrizketakColumnNames = {ResourceBundle.getBundle("Etiquetas").getString("Zenb"), 
			ResourceBundle.getBundle("Etiquetas").getString("Chats")};
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ElkarrizketakGUI window = new ElkarrizketakGUI();
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
	public ElkarrizketakGUI(User user) {
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
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 486, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		elkarrizketak = facade.lortuElkarContainer(user);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getClass() == Admin.class) {
					new AdminGUI(user);
				}else {
					new ErregistratuaGUI(user);
				}
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		JScrollPane elkarrizketakScrollPane = new JScrollPane();
		
		elkarrizketakScrollPane.setBounds(10, 11, 450, 185);
		frame.getContentPane().add(elkarrizketakScrollPane);
		
		elkarrizketakTable = new JTable();
		elkarrizketakScrollPane.setViewportView(elkarrizketakTable);
	
		elkarrizketakTableModel = new DefaultTableModel(null, elkarrizketakColumnNames);
		elkarrizketakTable.setModel(elkarrizketakTableModel);	
		
		elkarrizketakTable.getColumnModel().getColumn(0).setPreferredWidth(10);
		elkarrizketakTable.getColumnModel().getColumn(1).setPreferredWidth(300);
		
		JLabel infoLabel = new JLabel(); 
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(57, 202, 353, 14);
		frame.getContentPane().add(infoLabel);
		
		JButton mezuaBidaliButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendMessage")); 
		mezuaBidaliButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = elkarrizketakTable.getSelectedRow();
					ElkarrizketaContainer elkarrizketa = elkarrizketak.get(i);
				
					new MezuakBidaliGUI(user,elkarrizketa.getElkarrizketa());
					frame.setVisible(false);
				} catch (Exception e2) {
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoSelectedChat"));
				}
			}
		});
		mezuaBidaliButton.setBounds(162, 227, 150, 23);
		frame.getContentPane().add(mezuaBidaliButton);
		
		JButton elkarrizketaBerriaButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("NewChat"));
		elkarrizketaBerriaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ElkarrizketaBerriaGUI(user);
				frame.setVisible(false);
			}
		});
		elkarrizketaBerriaButton.setBounds(371, 227, 89, 23);
		frame.getContentPane().add(elkarrizketaBerriaButton);
		
		
		int i = 1;
		for (ElkarrizketaContainer elkCont : elkarrizketak) {
			Vector<Object> row = new Vector<Object>();
			row.add(i);
			if(elkCont.getUser1().getUsername().contentEquals(user.getUsername())) {
				row.add(elkCont.getUser2().getUsername());
			}else {
				row.add(elkCont.getUser1().getUsername());
			}
			i++;
			elkarrizketakTableModel.addRow(row);
		}
		
	}
}
