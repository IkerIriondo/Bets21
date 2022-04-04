package gui;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.LocalDate;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class GertaeraSortuGUI extends Frame{

	private JFrame frame;
	private JTextField gertaeraField;
	private JTextField gertZenbField;

	private User user;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GertaeraSortuGUI window = new GertaeraSortuGUI();
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
	public GertaeraSortuGUI(User user) {
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
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new AdminGUI(user);
			}
		});
		atzeraButton.setBounds(307, 11, 100, 23);
		frame.getContentPane().add(atzeraButton);
		
		JCalendar jCalendar = new JCalendar();
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		
		jCalendar.setBounds(0, 0, 184, 111);
		frame.getContentPane().add(jCalendar);
		
		gertaeraField = new JTextField();
		gertaeraField.setBounds(231, 133, 193, 20);
		frame.getContentPane().add(gertaeraField);
		gertaeraField.setColumns(10);
		
		JLabel gertaeraLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event")+ ":");
		gertaeraLabel.setBounds(36, 135, 89, 17);
		frame.getContentPane().add(gertaeraLabel);
		
		JLabel gertZenbLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("GertZenb")+":");
		gertZenbLabel.setBounds(36, 163, 166, 14);
		frame.getContentPane().add(gertZenbLabel);
		
		gertZenbField = new JTextField();
		gertZenbField.setBounds(231, 163, 193, 20);
		frame.getContentPane().add(gertZenbField);
		gertZenbField.setColumns(10);
		
		JLabel infoLabel = new JLabel("");
		infoLabel.setBounds(67, 202, 312, 14);
		frame.getContentPane().add(infoLabel);
		
		JButton gertSortuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		gertSortuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date data = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
				String zenb = gertZenbField.getText();
				String deskribapena = gertaeraField.getText();
				
				String gaur = LocalDate.now().toString();
				String[] gaurkoa = gaur.split("-");
				int urtea = Integer.parseInt(gaurkoa[0]);
				int hil = Integer.parseInt(gaurkoa[1]) -1;
				int eg = Integer.parseInt(gaurkoa[2]);
				Date gauregun = UtilDate.newDate(urtea,hil,eg);
				
				BLFacade facade = MainGUI.getBusinessLogic();
				
				if(!data.before(gauregun)) {
					if(!deskribapena.isBlank()) {
						try {
							facade.gertaeraSortu(deskribapena,zenb,data);
						infoLabel.setText("Gertaera ondo sortu da");
						} catch (Exception e2) {
							infoLabel.setText("Dagoeneko badago gertaera bat zenbaki horrekin");
						}
						
					}else {
						System.out.println("Sartu deskribapen zuzen bat");
						infoLabel.setText("Sartu deskribapen zuzen bat");
					}
				}else {
					System.out.println("Hautatutako data iraganekoa da");
					infoLabel.setText("Hautatutako data iraganekoa da");
				}
				
				
			}
		});
		gertSortuButton.setBounds(141, 227, 154, 23);
		frame.getContentPane().add(gertSortuButton);
		
		
	}
}