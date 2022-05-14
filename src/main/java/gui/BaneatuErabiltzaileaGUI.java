package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import domain.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

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
		
		JLabel infoLabel = new JLabel();
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(122, 199, 226, 14);
		frame.getContentPane().add(infoLabel);
		
		int year = baneatzekoa.getZenbatDenboraBan().getYear() +1900;
		int hil = baneatzekoa.getZenbatDenboraBan().getMonth() +1;
		int day = baneatzekoa.getZenbatDenboraBan().getDate();
		
		dataField.setText(year + "/" + hil + "/" + day);
		
		JButton baneatuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Ban"));
		baneatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Date noizArte = jCalendar.getDate();
				Date gaur = new Date();
				gaur.setHours(0);
				gaur.setMinutes(0);
				gaur.setSeconds(0);
				
				noizArte.setHours(0);
				noizArte.setMinutes(0);
				noizArte.setSeconds(0);
				
				if(gaur.compareTo(noizArte)<0 && !gaur.toString().contentEquals(noizArte.toString())) {
					BLFacade facade = MainGUI.getBusinessLogic();
					facade.baneatuErabiltzailea(baneatzekoa,noizArte);
					new MezuErreportatuakGUI(gu);
					frame.setVisible(false);
				}else {
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("PastDate"));
				}
			}
		});
		baneatuButton.setBounds(198, 227, 89, 23);
		frame.getContentPane().add(baneatuButton);
		
	}
}
