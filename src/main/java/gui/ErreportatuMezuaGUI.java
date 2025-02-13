package gui;

import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import businessLogic.BLFacade;
import domain.Mezua;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class ErreportatuMezuaGUI extends JFrame{

	private JFrame frame;
	private JTextField mezuaField;
	private Mezua mezua;
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ErreportatuMezuaGUI window = new ErreportatuMezuaGUI();
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
	public ErreportatuMezuaGUI(Mezua m) {
		super();
		mezua = m;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 287, 182);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel mezuErrepLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ReportMessage"));
		mezuErrepLabel.setBounds(20, 66, 230, 14);
		frame.getContentPane().add(mezuErrepLabel);
		
		JButton baiErrepButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Yes"));
		baiErrepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				facade.erreportatuMezua(mezua);
				frame.setVisible(false);
			}
		});
		baiErrepButton.setBounds(26, 104, 89, 23);
		frame.getContentPane().add(baiErrepButton);
		
		JButton ezErrepButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("No"));
		ezErrepButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setVisible(false);
			}
		});
		ezErrepButton.setBounds(161, 104, 89, 23);
		frame.getContentPane().add(ezErrepButton);
		
		mezuaField = new JTextField();
		mezuaField.setEditable(false);
		mezuaField.setText(mezua.getMezua());
		mezuaField.setBounds(20, 25, 230, 20);
		frame.getContentPane().add(mezuaField);
		mezuaField.setColumns(10);
		
		
	}
}
