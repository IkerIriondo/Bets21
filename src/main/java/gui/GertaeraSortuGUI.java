package gui;

import java.awt.EventQueue;
import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.Rectangle;
import javax.swing.JTextField;

public class GertaeraSortuGUI extends Frame{

	private JFrame frame;
	private JTextField gertaeraField;
	private JTextField gertZenbField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	}

	/**
	 * Create the application.
	 */
	public GertaeraSortuGUI() {
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
				AdminGUI a = new AdminGUI();
			}
		});
		atzeraButton.setBounds(307, 11, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		JCalendar jCalendar = new JCalendar();
		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		
		jCalendar.setBounds(0, 0, 184, 111);
		frame.getContentPane().add(jCalendar);
		
		gertaeraField = new JTextField();
		gertaeraField.setBounds(231, 133, 193, 20);
		frame.getContentPane().add(gertaeraField);
		gertaeraField.setColumns(10);
		
		JLabel gertaeraLabel = new JLabel("Gertaera:");
		gertaeraLabel.setBounds(36, 135, 89, 17);
		frame.getContentPane().add(gertaeraLabel);
		
		JLabel gertZenbLabel = new JLabel("Zenbakia(ez da beharrezkoa)");
		gertZenbLabel.setBounds(36, 163, 166, 14);
		frame.getContentPane().add(gertZenbLabel);
		
		gertZenbField = new JTextField();
		gertZenbField.setBounds(231, 163, 193, 20);
		frame.getContentPane().add(gertZenbField);
		gertZenbField.setColumns(10);
		
		JButton gertSortuButton = new JButton("Gertaera sortu");
		gertSortuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Date data = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
				String zenb = gertZenbField.getText();
				String deskribapena = gertaeraField.getText();
				
				String gaur = LocalDate.now().toString();
				System.out.println(gaur);
				String[] gaurkoa = gaur.split("-");
				int urtea = Integer.parseInt(gaurkoa[0]);
				int hil = Integer.parseInt(gaurkoa[1]);
				int eg = Integer.parseInt(gaurkoa[2]);
				Date gauregun = new Date(urtea,hil,eg);
				
				BLFacade facade = MainGUI.getBusinessLogic();
				
				if(!data.before(gauregun)) {
					if(!deskribapena.isBlank()) {
						facade.gertaeraSortu(deskribapena,zenb,data);
					}else {
						System.out.println("Sartu deskribapen zuzen bat");
					}
				}else {
					System.out.println("Hautatutako data iraganekoa da");
				}
				
				
			}
		});
		gertSortuButton.setBounds(141, 227, 154, 23);
		frame.getContentPane().add(gertSortuButton);
	}
}