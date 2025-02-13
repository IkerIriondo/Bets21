package gui;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

import domain.*;

@SuppressWarnings("serial")
public class ErregistratuaGUI extends Frame{

	private JFrame frame;
	private User user;

	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErregistratuaGUI window = new ErregistratuaGUI();
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
	public ErregistratuaGUI(User user) {
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
		
		JButton galderaKontsButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestion"));
		galderaKontsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
			}
		});
		galderaKontsButton.setBounds(67, 42, 153, 23);
		frame.getContentPane().add(galderaKontsButton);
		
		JButton sartuDiruaButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DiruaSartu"));
		sartuDiruaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new DiruaSartuGUI(user);
			}
		});
		sartuDiruaButton.setBounds(255, 42, 129, 23);
		frame.getContentPane().add(sartuDiruaButton);
		
		JButton saioaItxiButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SaioaItxi"));
		saioaItxiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginGUI();
			}
		});
		saioaItxiButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(saioaItxiButton);
		
		JButton mugimenduakButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MugIkusi"));
		mugimenduakButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new MugimenduakIkusiGUI(user);
			}
		});
		mugimenduakButton.setBounds(67, 76, 153, 23);
		frame.getContentPane().add(mugimenduakButton);
		
		JButton apustuaEginButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Bet"));
		apustuaEginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ApustuMotaGUI(user);
				frame.setVisible(false);
			}
		});
		apustuaEginButton.setBounds(255, 76, 129, 23);
		frame.getContentPane().add(apustuaEginButton);
		
		JButton apustuaEzabatuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteBet"));
		apustuaEzabatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ApustuaEzabatuGUI(user);
				frame.setVisible(false);
			}
		});
		apustuaEzabatuButton.setBounds(67, 110, 153, 23);
		frame.getContentPane().add(apustuaEzabatuButton);
		
		JButton erabilJarraituButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FollowUser"));
		erabilJarraituButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ErabiltzaileaJarraituGUI(user);
				frame.setVisible(false);
			}
		});
		erabilJarraituButton.setBounds(255, 110, 129, 23);
		frame.getContentPane().add(erabilJarraituButton);
		
		JButton mezuaBidaliButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendMessage")); //$NON-NLS-1$ //$NON-NLS-2$
		mezuaBidaliButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ElkarrizketakGUI(user);
				frame.setVisible(false);
			}
		});
		mezuaBidaliButton.setBounds(67, 144, 153, 23);
		frame.getContentPane().add(mezuaBidaliButton);
		
	}
}
