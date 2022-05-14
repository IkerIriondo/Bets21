package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;

import domain.*;
import javax.swing.JButton;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class MezuErreportatuakGUI extends JFrame{

	private JFrame frame;
	private User user;
	private JTable errepMezuakTable;

	private List<Mezua> errepMezuak;
	
	private DefaultTableModel errepMezuakTableModel;
	
	private String[] zutabeak = {"Username", "Mezua"};
	private JButton baneatuButton;
	private JLabel infoLabel;
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MezuErreportatuakGUI window = new MezuErreportatuakGUI();
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
	public MezuErreportatuakGUI(User user) {
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
		frame.setBounds(100, 100, 643, 428);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AdminGUI(user);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 355, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		JScrollPane errepMezuakScrollPane = new JScrollPane();
		errepMezuakScrollPane.setBounds(41, 28, 525, 242);
		frame.getContentPane().add(errepMezuakScrollPane);
		
		errepMezuakTable = new JTable();
		errepMezuakScrollPane.setViewportView(errepMezuakTable);
		
		errepMezuakTableModel = new DefaultTableModel(null, zutabeak);
		errepMezuakTable.setModel(errepMezuakTableModel);
		
		errepMezuakTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		errepMezuakTable.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		errepMezuak = facade.lortuErreportatutakoMezuak();
		
		for (Mezua m : errepMezuak) {
			Vector<Object> row = new Vector<Object>();
			row.add(m.getNork().getUsername());
			row.add(m.getMezua());
			errepMezuakTableModel.addRow(row);
		}
		
		infoLabel = new JLabel();
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(95, 292, 416, 14);
		frame.getContentPane().add(infoLabel);
		
		baneatuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ban")); //$NON-NLS-1$ //$NON-NLS-2$
		baneatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = errepMezuakTable.getSelectedRow();
					Mezua m = errepMezuak.get(i);
					User baneatzekoa = m.getNork();
					new BaneatuErabiltzaileaGUI(user, baneatzekoa, m);
					frame.setVisible(false);
				} catch (Exception e2) {
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoSelectedMessege"));
				}
				
			}
		});
		baneatuButton.setBounds(280, 329, 89, 23);
		frame.getContentPane().add(baneatuButton);
	}
}
