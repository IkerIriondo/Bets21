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
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class ElkarrizketakGUI extends JFrame{

	private JFrame frame;
	private User user;
	private Vector<Elkarrizketa> elkarrizketak;
	
	private JTable elkarrizketakTable;
	
	private DefaultTableModel elkarrizketakTableModel;
	private String[] elkarrizketakColumnNames = {"Zenb","Elkarrizketak"};
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
		elkarrizketak = user.getElkarrizketak();
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
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(user.getClass() == Admin.class) {
					new AdminGUI(user);
				}else {
					new ErregistratuaGUI(user);
				}
				frame.setVisible(rootPaneCheckingEnabled);
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
		
		JButton mezuaBidaliButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendMessage")); //$NON-NLS-1$ //$NON-NLS-2$
		mezuaBidaliButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = elkarrizketakTable.getSelectedRow();
				Elkarrizketa elkarrizketa = elkarrizketak.get(i);
				
				new MezuakBidaliGUI(user,elkarrizketa);
				frame.setVisible(false);
			}
		});
		mezuaBidaliButton.setBounds(208, 207, 89, 23);
		frame.getContentPane().add(mezuaBidaliButton);
		
		int i = 1;
		for (Elkarrizketa elk : elkarrizketak) {
			Vector<Object> row = new Vector<Object>();
			if(elk.getUser1().equals(user)) {
				row.add(elk.getUser2());
			}else {
				row.add(elk.getUser1());
			}
			row.add(i);
			i++;
			elkarrizketakTableModel.addRow(row);
		}
		
	}
}
