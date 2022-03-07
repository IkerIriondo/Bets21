package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;

import domain.Event;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class AdminGUI extends JFrame{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdminGUI window = new AdminGUI();
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
	public AdminGUI() {
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
		
		JButton gerSortuButton = new JButton("Gertaera Sortu");
		gerSortuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				GertaeraSortuGUI ger = new GertaeraSortuGUI();
			}
		});
		gerSortuButton.setBounds(76, 78, 126, 21);
		frame.getContentPane().add(gerSortuButton);
		
		JButton galSortuButton = new JButton("Galdera Sortu");
		galSortuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				CreateQuestionGUI c = new CreateQuestionGUI(new Vector<Event>());
				c.setVisible(true);
			}
		});
		galSortuButton.setBounds(239, 78, 126, 21);
		frame.getContentPane().add(galSortuButton);
		
		JButton kuotaIpiniButton = new JButton("Kuota Ipini");
		kuotaIpiniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				KuotaIpiniGUI k = new KuotaIpiniGUI();
			}
		});
		kuotaIpiniButton.setBounds(76, 172, 126, 21);
		frame.getContentPane().add(kuotaIpiniButton);
		
		JButton galKonButton = new JButton("Galderak Kontsultatu");
		galKonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FindQuestionsGUI f = new FindQuestionsGUI();
				f.setVisible(true);
			}
		});
		galKonButton.setBounds(239, 172, 126, 21);
		frame.getContentPane().add(galKonButton);
		
		JButton itxiButton = new JButton("Saioa Itxi");
		itxiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				LoginGUI l = new LoginGUI();
			}
		});
		itxiButton.setBounds(341, 10, 85, 21);
		frame.getContentPane().add(itxiButton);
	}

}
