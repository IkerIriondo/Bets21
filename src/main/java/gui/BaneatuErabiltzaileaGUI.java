package gui;


import java.awt.Rectangle;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;

import com.toedter.calendar.JCalendar;

import domain.*;
import javax.swing.JButton;

@SuppressWarnings("serial")
public class BaneatuErabiltzaileaGUI extends JFrame{

	private JFrame frame;
	private User gu;
	private User baneatzekoa;

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
	public BaneatuErabiltzaileaGUI(User gu, User baneatzekoa) {
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
		
		JCalendar jCalendar = new JCalendar();
		jCalendar.setBounds(0, 0, 434, 261);
		frame.getContentPane().add(jCalendar);
		
		JButton atzeraButton = new JButton("New button");
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
		
		
	}

}
