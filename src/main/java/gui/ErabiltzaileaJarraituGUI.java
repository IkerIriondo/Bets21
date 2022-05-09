package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;

import domain.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

@SuppressWarnings("serial")
public class ErabiltzaileaJarraituGUI extends JFrame{

	private JFrame frame;
	private User user;
	private JTable erabilTable;
	private List<Erabiltzailea> erabiltzaileak;
	
	private String erabiltzaileakColumnNames[] = {ResourceBundle.getBundle("Etiquetas").getString("Zenb"),
			ResourceBundle.getBundle("Etiquetas").getString("Username")};
	private DefaultTableModel erabiltzaileakTableModel;
	private JTextField zenbatDiruIrabazField;
	private JTextField zenbatApostuField;
	private JTextField zenbatApostuIrabaziField;
	private JTextField izenaField;
	private JTextField abizenaField;
	private JTextField zenbatDiruField;
	
	
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErabiltzaileaJarraituGUI window = new ErabiltzaileaJarraituGUI();
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
	public ErabiltzaileaJarraituGUI(User user) {
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
		frame.setBounds(100, 100, 573, 395);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ErregistratuaGUI(user);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 322, 138, 23);
		frame.getContentPane().add(atzeraButton);
		
		
		
		JLabel zenbatDiruIrabazLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("IrabazDiruTotala")); 
		zenbatDiruIrabazLabel.setBounds(242, 90, 127, 14);
		frame.getContentPane().add(zenbatDiruIrabazLabel);
		
		zenbatDiruIrabazField = new JTextField();
		zenbatDiruIrabazField.setEditable(false);
		zenbatDiruIrabazField.setBounds(375, 87, 172, 20);
		frame.getContentPane().add(zenbatDiruIrabazField);
		zenbatDiruIrabazField.setColumns(10);
		
		JLabel zenbatApostuLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ZenbatApos")); 
		zenbatApostuLabel.setBounds(242, 115, 127, 14);
		frame.getContentPane().add(zenbatApostuLabel);
		
		zenbatApostuField = new JTextField();
		zenbatApostuField.setEditable(false);
		zenbatApostuField.setBounds(375, 112, 172, 20);
		frame.getContentPane().add(zenbatApostuField);
		zenbatApostuField.setColumns(10);
		
		JLabel zenbatAposIrabaziLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ZenbAposIrabaz")); 
		zenbatAposIrabaziLabel.setBounds(242, 140, 127, 14);
		frame.getContentPane().add(zenbatAposIrabaziLabel);
		
		zenbatApostuIrabaziField = new JTextField();
		zenbatApostuIrabaziField.setEditable(false);
		zenbatApostuIrabaziField.setBounds(375, 134, 172, 20);
		frame.getContentPane().add(zenbatApostuIrabaziField);
		zenbatApostuIrabaziField.setColumns(10);
		
		JLabel izenaLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Name")); 
		izenaLabel.setBounds(242, 40, 127, 14);
		frame.getContentPane().add(izenaLabel);
		
		izenaField = new JTextField();
		izenaField.setEditable(false);
		izenaField.setBounds(375, 37, 172, 20);
		frame.getContentPane().add(izenaField);
		izenaField.setColumns(10);
		
		JLabel abizenaLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Abizena"));
		abizenaLabel.setBounds(242, 65, 127, 14);
		frame.getContentPane().add(abizenaLabel);
		
		abizenaField = new JTextField();
		abizenaField.setEditable(false);
		abizenaField.setBounds(375, 62, 172, 20);
		frame.getContentPane().add(abizenaField);
		abizenaField.setColumns(10);
		
		JButton jarraituButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Follow")); 
		jarraituButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i = erabilTable.getSelectedRow();
				Erabiltzailea erabil = erabiltzaileak.get(i);
				
				BLFacade facade = MainGUI.getBusinessLogic();
				float dirua = Float.parseFloat(zenbatDiruField.getText());
				user = facade.jarraituErabiltzailea(user,erabil,dirua);
			}
		});
		jarraituButton.setBounds(311, 260, 138, 23);
		frame.getContentPane().add(jarraituButton);
		
		JButton mezuaBidaliButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendMessage"));
		mezuaBidaliButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		mezuaBidaliButton.setBounds(101, 260, 138, 23);
		frame.getContentPane().add(mezuaBidaliButton);
		
		JScrollPane erabilScrollPane = new JScrollPane();
		erabilScrollPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				try {
					int i = erabilTable.getSelectedRow();
					Erabiltzailea erabil = erabiltzaileak.get(i);
					
					izenaField.setText(erabil.getIzena());
					abizenaField.setText(erabil.getAbizena());
					zenbatDiruIrabazField.setText(erabil.getZenbatDiruIrabazi().toString());
					zenbatApostuField.setText(erabil.getZenbatApostu().toString());
					zenbatApostuIrabaziField.setText(erabil.getZenbatAposIrabazi().toString());
					
				}catch(Exception e1) {
					
				}
			}
		});
		erabilScrollPane.setBounds(10, 11, 222, 143);
		frame.getContentPane().add(erabilScrollPane);
		
		erabilTable = new JTable();
		erabilScrollPane.setViewportView(erabilTable);
	
		erabilScrollPane.setViewportView(erabilTable);
		erabiltzaileakTableModel = new DefaultTableModel(null, erabiltzaileakColumnNames);
		erabilTable.setModel(erabiltzaileakTableModel);
		
		JLabel zenbatDiruLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaJarraituGUI.lblNewLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		zenbatDiruLabel.setBounds(111, 191, 105, 14);
		frame.getContentPane().add(zenbatDiruLabel);
		
		zenbatDiruField = new JTextField();
		zenbatDiruField.setText(ResourceBundle.getBundle("Etiquetas").getString("ErabiltzaileaJarraituGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		zenbatDiruField.setBounds(283, 188, 86, 20);
		frame.getContentPane().add(zenbatDiruField);
		zenbatDiruField.setColumns(10);
		
		erabilTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		erabilTable.getColumnModel().getColumn(1).setPreferredWidth(268);
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		erabiltzaileak = facade.lortuErabiltzaileZerrenda();
		int i = 1;
		for (Erabiltzailea e : erabiltzaileak) {
			Vector<Object> row = new Vector<Object>();
			row.add(i);
			row.add(e.getUsername());
			i++;
			erabiltzaileakTableModel.addRow(row);
		}
		
	}
}
