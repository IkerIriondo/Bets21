package gui;

/**
 * @author Software Engineering teachers
 */


import javax.swing.*;

import businessLogic.BLFacade;

import java.awt.Color;
import java.awt.Font;
import java.util.Locale;
import java.util.ResourceBundle;

import java.awt.event.*;


public class MainGUI extends JFrame {
	
	private static final long serialVersionUID = 1L;

	private JPanel jContentPane = null;

    private static BLFacade appFacadeInterface;
	
	public static BLFacade getBusinessLogic(){
		return appFacadeInterface;
	}
	
	public static void setBussinessLogic (BLFacade afi){
		appFacadeInterface=afi;
	}
	protected JLabel jLabelSelectOption;
	private JButton rdbtnNewRadioButton;
	private JButton rdbtnNewRadioButton_1;
	private JButton rdbtnNewRadioButton_2;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JButton atzeraButton;
	/**
	 * This is the default constructor
	 */
	public MainGUI() {
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
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		// this.setSize(271, 295);
		this.setSize(495, 290);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}

	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getRdbtnNewRadioButton_1());
			jContentPane.add(getRdbtnNewRadioButton_2());
			jContentPane.add(getRdbtnNewRadioButton());
			
			atzeraButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Close"));
			atzeraButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					new LoginGUI();
					itxi();
				}

			});
			atzeraButton.setBounds(10, 222, 85, 21);
			jContentPane.add(atzeraButton);
		}
		return jContentPane;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(0, 0, 481, 63);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	private JButton getRdbtnNewRadioButton() {
		if (rdbtnNewRadioButton == null) {
			rdbtnNewRadioButton = new JButton("English");
			rdbtnNewRadioButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("en"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			rdbtnNewRadioButton.setBounds(175, 182, 144, 32);
			buttonGroup.add(rdbtnNewRadioButton);
			redibujar();
		}
		return rdbtnNewRadioButton;
	}
	private JButton getRdbtnNewRadioButton_1() {
		if (rdbtnNewRadioButton_1 == null) {
			rdbtnNewRadioButton_1 = new JButton("Euskara");
			rdbtnNewRadioButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("eus"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			
			rdbtnNewRadioButton_1.setBounds(175, 126, 144, 32);
			buttonGroup.add(rdbtnNewRadioButton_1);
			redibujar();
		}
		return rdbtnNewRadioButton_1;
	}
	private JButton getRdbtnNewRadioButton_2() {
		if (rdbtnNewRadioButton_2 == null) {
			rdbtnNewRadioButton_2 = new JButton("Castellano");
			rdbtnNewRadioButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Locale.setDefault(new Locale("es"));
					System.out.println("Locale: "+Locale.getDefault());
					redibujar();
				}
			});
			rdbtnNewRadioButton_2.setBounds(175, 73, 144, 32);
			buttonGroup.add(rdbtnNewRadioButton_2);
			redibujar();
		}
		return rdbtnNewRadioButton_2;
	}
	
	private void redibujar() {
		jLabelSelectOption.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("MainTitle"));
	}
	
	private void itxi() {
		this.setVisible(false);
	}
} // @jve:decl-index=0:visual-constraint="0,0"

