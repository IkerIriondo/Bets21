package gui;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;
import java.util.ResourceBundle;

import javax.swing.JFrame;

import domain.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;
import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;

import java.awt.Rectangle;

@SuppressWarnings("serial")
public class GertaeraBikoiztuGUI extends JFrame{

	private JFrame frame;
	private User user;
	private Event gertaera;
	private JTextField gertZenbField;
	private JTextField gertDeskField;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GertaeraBikoiztuGUI window = new GertaeraBikoiztuGUI();
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
	public GertaeraBikoiztuGUI(User user, Event gertaera) {
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
		this.gertaera = gertaera;
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
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
				f.getGertBikoiztButton().setVisible(true);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		JLabel gertZenbLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventN"));
		gertZenbLabel.setBounds(222, 11, 112, 14);
		frame.getContentPane().add(gertZenbLabel);
		
		gertZenbField = new JTextField();
		gertZenbField.setEditable(false);
		gertZenbField.setText(gertaera.getEventNumber().toString());
		gertZenbField.setBounds(306, 11, 118, 20);
		frame.getContentPane().add(gertZenbField);
		gertZenbField.setColumns(10);
		
		JLabel gertDeskLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Event")); //$NON-NLS-1$ //$NON-NLS-2$
		gertDeskLabel.setBounds(222, 36, 118, 14);
		frame.getContentPane().add(gertDeskLabel);
		
		gertDeskField = new JTextField();
		gertDeskField.setEditable(false);
		gertDeskField.setText(gertaera.getDescription()); //$NON-NLS-1$ //$NON-NLS-2$
		gertDeskField.setBounds(306, 42, 118, 20);
		frame.getContentPane().add(gertDeskField);
		gertDeskField.setColumns(10);
		
		JCalendar jCalendar = new JCalendar();
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		jCalendar.setBounds(26, 11, 184, 120);
		frame.getContentPane().add(jCalendar);
		
		JButton gertBikoiztuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BikoGertaera"));
		gertBikoiztuButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				//Date data = jCalendar1.getDate();
				Date data = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.gertaeraBikoiztu(gertaera,data);
			}
		});
		gertBikoiztuButton.setBounds(157, 184, 135, 23);
		frame.getContentPane().add(gertBikoiztuButton);
		
		
	}
}
