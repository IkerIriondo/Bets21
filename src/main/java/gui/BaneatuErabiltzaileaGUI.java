package gui;


import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

import domain.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class BaneatuErabiltzaileaGUI extends JFrame{

	private JFrame frame;
	private User gu;
	private User baneatzekoa;
	private JTextField mezuaField;
	private JTextField dataField;
	private Mezua m;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BaneatuErabiltzaileaGUI window = new BaneatuErabiltzaileaGUI();
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
	public BaneatuErabiltzaileaGUI(User gu, User baneatzekoa, Mezua m) {
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
		this.gu = gu;
		this.baneatzekoa = baneatzekoa;
		this.m = m;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JCalendar jCalendar = new JCalendar();
		jCalendar.setBounds(0, 0, 215, 108);
		frame.getContentPane().add(jCalendar);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new MezuErreportatuakGUI(gu);
				frame.setVisible(false);
				
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		JLabel usernameLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Username"));
		usernameLabel.setBounds(238, 119, 110, 14);
		frame.getContentPane().add(usernameLabel);
		usernameLabel.setText(baneatzekoa.getUsername());
		
		JLabel repMezuaLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ReportedMessage"));
		repMezuaLabel.setBounds(10, 167, 174, 14);
		frame.getContentPane().add(repMezuaLabel);
		
		mezuaField = new JTextField();
		mezuaField.setEditable(false);
		mezuaField.setBounds(219, 164, 149, 20);
		frame.getContentPane().add(mezuaField);
		mezuaField.setColumns(10);
		
		mezuaField.setText(m.getMezua());
		
		JLabel dataLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("WhenLastBanned"));
		dataLabel.setBounds(10, 142, 174, 14);
		frame.getContentPane().add(dataLabel);
		
		dataField = new JTextField();
		dataField.setEditable(false);
		dataField.setBounds(219, 139, 149, 20);
		frame.getContentPane().add(dataField);
		dataField.setColumns(10);
		
		int year = baneatzekoa.getZenbatDenboraBan().getYear() +1900;
		int hil = baneatzekoa.getZenbatDenboraBan().getMonth() +1;
		int day = baneatzekoa.getZenbatDenboraBan().getDate();
		
		dataField.setText(year + "/" + hil + "/" + day);
		
		JButton baneatuButton = new JButton("New button");
		baneatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		baneatuButton.setBounds(195, 215, 89, 23);
		frame.getContentPane().add(baneatuButton);
		
		
	}
}
