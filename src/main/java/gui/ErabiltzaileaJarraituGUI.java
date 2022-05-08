package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import domain.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class ErabiltzaileaJarraituGUI extends JFrame{

	private JFrame frame;
	private User user;
	private JTable erabilTable;
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
		frame.setBounds(100, 100, 518, 334);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ErregistratuaGUI(user);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 261, 138, 23);
		frame.getContentPane().add(atzeraButton);
		
		JScrollPane erabilScrollPane = new JScrollPane();
		erabilScrollPane.setBounds(41, 32, 171, 108);
		frame.getContentPane().add(erabilScrollPane);
		
		erabilTable = new JTable();
		erabilScrollPane.setViewportView(erabilTable);
	
		erabilScrollPane.setViewportView(erabilTable);
		erabilTableModel = new DefaultTableModel(null, erabilColumnNames);
		
		erabilTable.setModel(erabilTableModel);
		
	}
}
