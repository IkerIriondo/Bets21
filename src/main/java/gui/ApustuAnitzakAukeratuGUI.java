package gui;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import businessLogic.BLFacade;
import configuration.UtilDate;
import domain.*;

@SuppressWarnings("serial")
public class ApustuAnitzakAukeratuGUI extends JFrame{

	private JFrame frame;
	
	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	
	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();
	private JComboBox<String> erantzunPosibleComboBox = new JComboBox<String>();
	private ApustuAnitzakGUI apustuAnitzak;
	private Vector<Integer> galdZbn = new Vector<Integer>();

	private JTable tableEvents= new JTable();
	private JTable tableQueries = new JTable();

	private DefaultTableModel tableModelEvents;
	private DefaultTableModel tableModelQueries;

	
	private String[] columnNamesEvents = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("EventN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Event"), 

	};
	private String[] columnNamesQueries = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QueryN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Query")

	};
	private final JButton apustuaGehituButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("addBet"));
	private final JLabel infoLabel=new JLabel();
	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ApustuAnitzakAukeratuGUI window = new ApustuAnitzakAukeratuGUI();
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
	public ApustuAnitzakAukeratuGUI(ApustuAnitzakGUI a) {
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
		this.apustuAnitzak=a;
		initialize();
		frame.setVisible(true);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setLayout(null);
		frame.setSize(new Dimension(700, 500));
		frame.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 215, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);
		getContentPane().setLayout(null);

		frame.getContentPane().add(jLabelEventDate);
		frame.getContentPane().add(jLabelQueries);
		frame.getContentPane().add(jLabelEvents);

		JLabel erantzunPosibleakLabel = new JLabel(); 
		erantzunPosibleakLabel.setBounds(456, 215, 203, 14);
		frame.getContentPane().add(erantzunPosibleakLabel);
		infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
		infoLabel.setBounds(40, 391, 406, 14);
		
		
		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener(){
			public void propertyChange(PropertyChangeEvent propertychangeevent){

				if (propertychangeevent.getPropertyName().equals("locale")){
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar")){
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar1.getLocale());
//					jCalendar1.setCalendar(calendarAct);
					Date firstDay=UtilDate.trim(new Date(jCalendar1.getCalendar().getTime().getTime()));

					 
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) {
							// Si en JCalendar está 30 de enero y se avanza al mes siguiente, devolvería 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este código se dejará como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}						
						
						jCalendar1.setCalendar(calendarAct);

						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
					}



					CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);
													
					

					try {
						tableModelEvents.setDataVector(null, columnNamesEvents);
						tableModelEvents.setColumnCount(3); // another column added to allocate ev objects

						BLFacade facade=MainGUI.getBusinessLogic();

						Vector<domain.Event> events=facade.getEvents(firstDay);

						if (events.isEmpty() ) jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")+ ": "+dateformat1.format(calendarAct.getTime()));
						else jLabelEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events")+ ": "+dateformat1.format(calendarAct.getTime()));
						for (domain.Event ev:events){
							Vector<Object> row = new Vector<Object>();

							System.out.println("Events "+ev);

							row.add(ev.getEventNumber());
							row.add(ev.getDescription());
							row.add(ev); // ev object added in order to obtain it with tableModelEvents.getValueAt(i,2)
							tableModelEvents.addRow(row);		
						}
						tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
						tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
						tableEvents.getColumnModel().removeColumn(tableEvents.getColumnModel().getColumn(2)); // not shown in JTable
					} catch (Exception e1) {

						jLabelQueries.setText(e1.getMessage());
					}

				}
			} 
		});

		frame.getContentPane().add(jCalendar1);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 367, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 240, 406, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				Event ev=(Event)tableModelEvents.getValueAt(i,2); // obtain ev object
				Vector<Question> queries=ev.getQuestions();

				tableModelQueries.setDataVector(null, columnNamesQueries);

				if (queries.isEmpty())
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQueries")+": "+ev.getDescription());
				else 
					jLabelQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedEvent")+" "+ev.getDescription());

				for (domain.Question q:queries){
					Vector<Object> row = new Vector<Object>();

					row.add(q.getQuestionNumber());
					row.add(q.getQuestion());
					tableModelQueries.addRow(row);	
				}
				tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);
			}
		});

		
		erantzunPosibleComboBox.setBounds(456, 243, 203, 22);
		
		
		frame.getContentPane().add(erantzunPosibleComboBox);
		
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				erantzunPosibleComboBox.removeAllItems();
				int i = tableQueries.getSelectedRow();
				int galdZenb = (int)tableModelQueries.getValueAt(i, 0);
				BLFacade facade = MainGUI.getBusinessLogic();
				Question galdera = facade.bilatuGaldera(galdZenb);
				for (ErantzunPosiblea eran : galdera.getErantzunPosibleak()) {
					erantzunPosibleComboBox.addItem(eran.getErantzunPosiblea());
				}
			}
		});
		
		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		frame.getContentPane().add(scrollPaneEvents);
		frame.getContentPane().add(scrollPaneQueries);
		apustuaGehituButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				infoLabel.setText("");
				
				int indize = erantzunPosibleComboBox.getSelectedIndex();
				int i = tableQueries.getSelectedRow();
				int galdZenb = (int)tableModelQueries.getValueAt(i, 0);
				BLFacade facade = MainGUI.getBusinessLogic();
				Question galdera = facade.bilatuGaldera(galdZenb);
				ErantzunPosiblea er = galdera.getErantzunPosibleak().get(indize);
				
				Date gaur = new Date();
				gaur.setHours(0);
				gaur.setMinutes(0);
				gaur.setSeconds(0);
				
				Date aukeratua = jCalendar1.getDate();
				
				aukeratua.setHours(0);
				aukeratua.setMinutes(0);
				aukeratua.setSeconds(0);
				
				if(gaur.compareTo(aukeratua)<0) {
	
					if(!galdZbn.contains(galdZenb)) {
						galdZbn.add(galdZenb);
					
						Vector<Object> row = new Vector<Object>();
						row.add(galdera.getQuestionNumber());
						row.add(er.getErantzunPosiblea());
						apustuAnitzak.getErantzunakTableModel().addRow(row);
					
						float kuota = Float.parseFloat(apustuAnitzak.getKuotaField().getText())*er.getKuota();
						apustuAnitzak.getKuotaField().setText(String.valueOf(kuota));
				
						float apustuMin = Float.parseFloat(apustuAnitzak.getApustuMinField().getText())+galdera.getBetMinimum();
						apustuAnitzak.getApustuMinField().setText(String.valueOf(apustuMin));
					}
				}else {
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("PastDate"));
				}
				
			}
		});
		apustuaGehituButton.setBounds(456, 391, 203, 47);
		
		frame.getContentPane().add(apustuaGehituButton);
		frame.getContentPane().add(infoLabel);
	}

	public void close() {
		frame.setVisible(false);
	}
}
