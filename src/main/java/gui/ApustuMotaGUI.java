package gui;


import javax.swing.JFrame;
import javax.swing.JButton;

public class ApustuMotaGUI {

	private JFrame frame;

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
	
	public ApustuMotaGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton apustuEginButton = new JButton("New button");
		apustuEginButton.setBounds(143, 76, 132, 30);
		frame.getContentPane().add(apustuEginButton);
		
		JButton apustuAnitzaEginButton = new JButton("New button");
		apustuAnitzaEginButton.setBounds(143, 135, 132, 30);
		frame.getContentPane().add(apustuAnitzaEginButton);
	}

}
