package gui;


import javax.swing.JFrame;

import domain.*;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.Vector;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class AdminGUI extends JFrame{

	private JFrame frame;
	private User user;
	/**
	 * Launch the application.
	 */
/*	public static void main(String[] args) {
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
	}*/

	/**
	 * Create the application.
	 */
	public AdminGUI(User user) {
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
		
		JButton gerSortuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent"));
		gerSortuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new GertaeraSortuGUI(user);
			}
		});
		gerSortuButton.setBounds(76, 78, 126, 21);
		frame.getContentPane().add(gerSortuButton);
		
		JButton galSortuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("CreateQuery"));
		galSortuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				CreateQuestionGUI c = new CreateQuestionGUI(new Vector<Event>(), user);
				c.setVisible(true);
			}
		});
		galSortuButton.setBounds(254, 78, 147, 21);
		frame.getContentPane().add(galSortuButton);
		
		JButton kuotaIpiniButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("KuotaIpini"));
		kuotaIpiniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				KuotaIpiniGUI k = new KuotaIpiniGUI(user);
				k.setVisible(true);
			}
		});
		kuotaIpiniButton.setBounds(76, 142, 126, 21);
		frame.getContentPane().add(kuotaIpiniButton);
		
		JButton galKonButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestion"));
		galKonButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
			}
		});
		galKonButton.setBounds(254, 110, 147, 21);
		frame.getContentPane().add(galKonButton);
		
		JButton itxiButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SaioaItxi"));
		itxiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
				new LoginGUI();
			}
		});
		itxiButton.setBounds(329, 10, 97, 28);
		frame.getContentPane().add(itxiButton);
		
		JButton gertaeraEzabatuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DeleteEvent"));
		gertaeraEzabatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
				f.getGertaeraEzabatuButton().setVisible(true);
				frame.setVisible(false);
				
			}
		});
		gertaeraEzabatuButton.setBounds(76, 110, 126, 21);
		frame.getContentPane().add(gertaeraEzabatuButton);
		
		JButton emaitzaIpiniButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SelectAResult"));
		emaitzaIpiniButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
				f.getEmaitzaIpiniButton().setVisible(true);
				frame.setVisible(false);
				
			}
		});
		emaitzaIpiniButton.setBounds(254, 142, 147, 21);
		frame.getContentPane().add(emaitzaIpiniButton);
		
		JButton gertaeraBikoiztuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BikoGertaera")); 
		gertaeraBikoiztuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FindQuestionsGUI f = new FindQuestionsGUI(user);
				f.setVisible(true);
				f.getGertBikoiztButton().setVisible(true);
				frame.setVisible(false);
			}
		});
		gertaeraBikoiztuButton.setBounds(76, 174, 126, 23);
		frame.getContentPane().add(gertaeraBikoiztuButton);
		
		JButton erreporteakIkusiButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SeeReports")); //$NON-NLS-1$ //$NON-NLS-2$
		erreporteakIkusiButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new MezuErreportatuakGUI(user);
				frame.setVisible(false);
			}
		});
		erreporteakIkusiButton.setBounds(254, 174, 147, 23);
		frame.getContentPane().add(erreporteakIkusiButton);
		
		JButton mezuaBidaliButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendMessage")); //$NON-NLS-1$ //$NON-NLS-2$
		mezuaBidaliButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ElkarrizketakGUI(user);
				frame.setVisible(false);
			}
		});
		mezuaBidaliButton.setBounds(254, 212, 147, 23);
		frame.getContentPane().add(mezuaBidaliButton);
	}
}
