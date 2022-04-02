package gui;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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
		
		JButton galderaKontsButton = new JButton("Galderak Kontsultatu");
		galderaKontsButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
			}
		});
		galderaKontsButton.setBounds(67, 86, 153, 23);
		frame.getContentPane().add(galderaKontsButton);
		
		JButton sartuDiruaButton = new JButton("Dirua Sartu");
		sartuDiruaButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new DiruaSartuGUI(user);
			}
		});
		sartuDiruaButton.setBounds(268, 86, 129, 23);
		frame.getContentPane().add(sartuDiruaButton);
		
		JButton saioaItxiButton = new JButton("Saioa Itxi");
		saioaItxiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginGUI();
			}
		});
		saioaItxiButton.setBounds(10, 227, 89, 23);
		frame.getContentPane().add(saioaItxiButton);
		
		JButton mugimenduakButton = new JButton("Mugimenduak Ikusi");
		mugimenduakButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new MugimenduakIkusiGUI(user);
			}
		});
		mugimenduakButton.setBounds(67, 131, 153, 23);
		frame.getContentPane().add(mugimenduakButton);
		
		JButton apustuaEginButton = new JButton("Apustua egin");
		apustuaEginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
				f.getApustuEginButton().setVisible(true);
			}
		});
		apustuaEginButton.setBounds(268, 131, 129, 23);
		frame.getContentPane().add(apustuaEginButton);
		
		JButton apustuaEzabatuButton = new JButton("Apustua Ezabatu");
		apustuaEzabatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ApustuaEzabatuGUI(user);
				frame.setVisible(false);
			}
		});
		apustuaEzabatuButton.setBounds(67, 165, 153, 23);
		frame.getContentPane().add(apustuaEzabatuButton);
	}
}
