package gui;


import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import domain.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class ApustuAnitzakGUI extends JFrame{

	private JFrame frame;
	private JTextField KuotaField;
	private JTextField DiruaField;
	private JTable erantzunakTable;
	private DefaultTableModel erantzunakTableModel;
	
	private String[] elkarrizketakColumnNames = {ResourceBundle.getBundle("Etiquetas").getString("Question"), 
			ResourceBundle.getBundle("Etiquetas").getString("PossibleAnswer")};
	
	private User user;
	private ApustuAnitzakAukeratuGUI apustuakAukeratu = new ApustuAnitzakAukeratuGUI(user, this);
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApustuAnitzakGUI window = new ApustuAnitzakGUI();
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
	public ApustuAnitzakGUI(User user) {
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
		frame.setBounds(100, 100, 668, 375);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel KuotaLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Kuota"));
		KuotaLabel.setBounds(292, 93, 183, 14);
		frame.getContentPane().add(KuotaLabel);
		
		JLabel DiruaLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("HowMuchBet"));
		DiruaLabel.setBounds(292, 141, 183, 14);
		frame.getContentPane().add(DiruaLabel);
		
		KuotaField = new JTextField();
		KuotaField.setText(ResourceBundle.getBundle("Etiquetas").getString("ApustuAnitzakGUI.KuotaField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		KuotaField.setEditable(false);
		KuotaField.setBounds(485, 90, 131, 20);
		frame.getContentPane().add(KuotaField);
		KuotaField.setColumns(10);
		
		DiruaField = new JTextField();
		DiruaField.setBounds(485, 138, 131, 20);
		frame.getContentPane().add(DiruaField);
		DiruaField.setColumns(10);
		
		JButton apustuAnitzaEginButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("MakeABet"));
		apustuAnitzaEginButton.setBounds(394, 195, 170, 40);
		frame.getContentPane().add(apustuAnitzaEginButton);
		
		JScrollPane erantzunakScrollPane = new JScrollPane();
		
		erantzunakScrollPane.setBounds(10, 11, 272, 306);
		frame.getContentPane().add(erantzunakScrollPane);
		
		erantzunakTable = new JTable();
		erantzunakScrollPane.setViewportView(erantzunakTable);
	
		erantzunakTableModel = new DefaultTableModel(null, elkarrizketakColumnNames);
		erantzunakTable.setModel(erantzunakTableModel);	
		
		JButton atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ErregistratuaGUI(user);
				frame.setVisible(false);
				apustuakAukeratu.close();
				
			}
		});
		atzeraButton.setBounds(505, 284, 111, 33);
		frame.getContentPane().add(atzeraButton);
		
		erantzunakTable.getColumnModel().getColumn(0).setPreferredWidth(50);
		erantzunakTable.getColumnModel().getColumn(1).setPreferredWidth(180);
		
	}

	public DefaultTableModel getErantzunakTableModel() {
		return erantzunakTableModel;		
	}

	public JTextField getKuotaField() {
		return KuotaField;		
	}

}
