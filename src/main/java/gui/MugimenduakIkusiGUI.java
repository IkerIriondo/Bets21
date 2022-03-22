package gui;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import domain.Mugimendua;
import domain.User;

import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MugimenduakIkusiGUI extends Frame{

	private JFrame frame;
	private JTable mugimenduakTable;
	private DefaultTableModel mugimenduakTableModel;
	
	String mugimenduakColumnNames[] = {"Zenb", "Mugimendua"};
	
	User user;
	
	
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MugimenduakIkusiGUI window = new MugimenduakIkusiGUI();
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
	public MugimenduakIkusiGUI(User user) {
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

	public MugimenduakIkusiGUI() {
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
				new ErregistratuaGUI(user);			}
		});
		atzeraButton.setBounds(165, 212, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		mugimenduakTable = new JTable();
		
		JScrollPane mugimenduakScrollPane = new JScrollPane();
		mugimenduakScrollPane.setBounds(94, 58, 254, 113);
		frame.getContentPane().add(mugimenduakScrollPane);
		
		mugimenduakScrollPane.setViewportView(mugimenduakTable);
		mugimenduakTableModel = new DefaultTableModel(null, mugimenduakColumnNames);
		
		mugimenduakTable.setModel(mugimenduakTableModel);
		
		JLabel info1Label = new JLabel("Dirua guztira:");
		info1Label.setBounds(94, 182, 115, 14);
		frame.getContentPane().add(info1Label);
		
		JLabel zenbatDiruLabel = new JLabel("");
		zenbatDiruLabel.setBounds(302, 182, 46, 14);
		zenbatDiruLabel.setText(user.getDirua()+" €");
		frame.getContentPane().add(zenbatDiruLabel);
		
		JLabel infoLabel = new JLabel("");
		infoLabel.setBounds(82, 33, 285, 14);
		frame.getContentPane().add(infoLabel);
		mugimenduakTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		mugimenduakTable.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		if(!user.getMugimenduak().isEmpty()) {
			Vector<domain.Mugimendua> mugimenduak = user.getMugimenduak();
			
			for (Mugimendua mug : mugimenduak) {
				Vector<Object> row = new Vector<Object>();
				row.add(mug.getMugZenb());
				row.add(mug.getDeskribapena());
				mugimenduakTableModel.addRow(row);
			}
		}else {
			infoLabel.setText("Kontu honetan ez da mugimendurik egon");
		}
		
	}
}
