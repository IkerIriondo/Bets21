package gui;


import javax.swing.JFrame;

import domain.User;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ApustuMotaGUI extends JFrame{

	private JFrame frame;
	private User user;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApustuMotaGUI window = new ApustuMotaGUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	 * Create the application.
	 */
	
	public ApustuMotaGUI(User user) {
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
		
		JButton apustuEginButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SingleBet"));
		apustuEginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
				f.getApustuEginButton().setVisible(true);
			}
		});
		apustuEginButton.setBounds(143, 76, 132, 30);
		frame.getContentPane().add(apustuEginButton);
		
		JButton apustuAnitzaEginButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MultipleBet"));
		apustuAnitzaEginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ApustuAnitzakGUI(user);
				frame.setVisible(false);
			}
		});
		apustuAnitzaEginButton.setBounds(143, 135, 132, 30);
		frame.getContentPane().add(apustuAnitzaEginButton);
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ErregistratuaGUI(user);
				frame.setVisible(false);
			}
		});
		atzeraButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(atzeraButton);
	}
}
