package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class ApustuAnitzakGUI {

	private JFrame frame;
	private JTextField KuotaField;
	private JTextField DiruaField;
	private JTable elkarrizketakTable;
	private DefaultTableModel elkarrizketakTableModel;
	private String[] elkarrizketakColumnNames = {"Galdera","Erantzun Posiblea"};

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
	public ApustuAnitzakGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 558, 367);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel KuotaLabel = new JLabel("New label");
		KuotaLabel.setBounds(292, 57, 46, 14);
		frame.getContentPane().add(KuotaLabel);
		
		JLabel DiruaLabel = new JLabel("New label");
		DiruaLabel.setBounds(292, 102, 46, 14);
		frame.getContentPane().add(DiruaLabel);
		
		KuotaField = new JTextField();
		KuotaField.setBounds(348, 54, 170, 20);
		frame.getContentPane().add(KuotaField);
		KuotaField.setColumns(10);
		
		DiruaField = new JTextField();
		DiruaField.setBounds(348, 99, 170, 20);
		frame.getContentPane().add(DiruaField);
		DiruaField.setColumns(10);
		
		JButton apustuAnitzaEginButton = new JButton("New button");
		apustuAnitzaEginButton.setBounds(323, 254, 170, 40);
		frame.getContentPane().add(apustuAnitzaEginButton);
		
		JButton apustuaGehituButton = new JButton("New button");
		apustuaGehituButton.setBounds(351, 190, 111, 33);
		frame.getContentPane().add(apustuaGehituButton);
		
		JScrollPane elkarrizketakScrollPane = new JScrollPane();
		
		elkarrizketakScrollPane.setBounds(10, 11, 272, 306);
		frame.getContentPane().add(elkarrizketakScrollPane);
		
		elkarrizketakTable = new JTable();
		elkarrizketakScrollPane.setViewportView(elkarrizketakTable);
	
		elkarrizketakTableModel = new DefaultTableModel(null, elkarrizketakColumnNames);
		elkarrizketakTable.setModel(elkarrizketakTableModel);	
		
		elkarrizketakTable.getColumnModel().getColumn(0).setPreferredWidth(30);
		elkarrizketakTable.getColumnModel().getColumn(1).setPreferredWidth(200);
	}
}
