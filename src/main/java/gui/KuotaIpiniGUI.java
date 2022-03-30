package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class KuotaIpiniGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private JButton atzeraAdminButton = new JButton(/*ResourceBundle.getBundle("Etiquetas").getString("Atzera")*/);

	// Code for JCalendar
	private JCalendar jCalendar1 = new JCalendar();
	private Calendar calendarAnt = null;
	private Calendar calendarAct = null;
	private JScrollPane scrollPaneEvents = new JScrollPane();
	private JScrollPane scrollPaneQueries = new JScrollPane();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

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
	private JTextField erantzunField;
	private JTextField kuotaField;
	private JTextField galdZenbField;
	private final JLabel infoLabel = new JLabel(/*ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.lblNewLabel.text")*/); //$NON-NLS-1$ //$NON-NLS-2$

	private User user;
	
	public KuotaIpiniGUI(User user){
		try{
			jbInit();
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
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		this.user = user;
	}

	
	private void jbInit() throws Exception
	{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(40, 211, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);


		jCalendar1.setBounds(new Rectangle(40, 50, 225, 150));

		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar1.getDate());
		CreateQuestionGUI.paintDaysWithEvents(jCalendar1,datesWithEventsCurrentMonth);

		// Code for JCalendar
		this.jCalendar1.addPropertyChangeListener(new PropertyChangeListener()
		{
			public void propertyChange(PropertyChangeEvent propertychangeevent)
			{

				if (propertychangeevent.getPropertyName().equals("locale"))
				{
					jCalendar1.setLocale((Locale) propertychangeevent.getNewValue());
				}
				else if (propertychangeevent.getPropertyName().equals("calendar"))
				{
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

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(40, 236, 259, 116));

		tableEvents.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int i=tableEvents.getSelectedRow();
				domain.Event ev=(domain.Event)tableModelEvents.getValueAt(i,2); // obtain ev object
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

		scrollPaneEvents.setViewportView(tableEvents);
		tableModelEvents = new DefaultTableModel(null, columnNamesEvents);

		tableEvents.setModel(tableModelEvents);
		tableEvents.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableEvents.getColumnModel().getColumn(1).setPreferredWidth(268);
		tableQueries.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
					int i=tableQueries.getSelectedRow();
					Integer q = (Integer)tableModelQueries.getValueAt(i, 0);
					galdZenbField.setText(q.toString());
			}
		});


		scrollPaneQueries.setViewportView(tableQueries);
		tableModelQueries = new DefaultTableModel(null, columnNamesQueries);

		tableQueries.setModel(tableModelQueries);
		tableQueries.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQueries.getColumnModel().getColumn(1).setPreferredWidth(268);

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		atzeraAdminButton.setText(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.atzeraAdminButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		
		//atzeraAdminButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FindQuestionsGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		
		atzeraAdminButton.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				atzeraAdminButtonActionPerformed(e,user);
			}
		});
		atzeraAdminButton.setBounds(40, 416, 104, 30);
		getContentPane().add(atzeraAdminButton);
		
		erantzunField = new JTextField();
		//erantzunField.setText(ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		erantzunField.setBounds(498, 280, 140, 20);
		getContentPane().add(erantzunField);
		erantzunField.setColumns(10);
		
		JLabel erantzunLabel = new JLabel(/*ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.lblNewLabel.text")*/); //$NON-NLS-1$ //$NON-NLS-2$
		erantzunLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.erantzunLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		erantzunLabel.setBounds(358, 283, 130, 14);
		getContentPane().add(erantzunLabel);
		
		JLabel kuotaLabel = new JLabel(/*ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.lblNewLabel_1.text")*/); //$NON-NLS-1$ //$NON-NLS-2$
		kuotaLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.kuotaLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		kuotaLabel.setBounds(358, 308, 129, 14);
		getContentPane().add(kuotaLabel);
		
		kuotaField = new JTextField();
		//kuotaField.setText(ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.textField_1.text")); //$NON-NLS-1$ //$NON-NLS-2$
		kuotaField.setBounds(498, 305, 140, 20);
		getContentPane().add(kuotaField);
		kuotaField.setColumns(10);
		
		JButton kuotaSortuButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.btnNewButton.text")); //$NON-NLS-1$ //$NON-NLS-2$
		kuotaSortuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int galdZenb = Integer.parseInt(galdZenbField.getText());
					String kuoMota = erantzunField.getText();
					float kuota = Integer.parseInt(kuotaField.getText());
					if(!kuoMota.isBlank()){
						BLFacade facade = MainGUI.getBusinessLogic();
						facade.kuotaIpini(galdZenb,kuota,kuoMota);
						infoLabel.setText("Kuota ondo sortu da");
					}else {
						System.out.println("Sartu erantzun egoki bat");
						infoLabel.setText("Sartu erantzun egoki bat");
					}
					
				}catch(NumberFormatException e2){
					System.out.println("Sartu kuota egoki bat");
					infoLabel.setText("Sartu kuota egoki bat");
				}
				
			}
		});
		kuotaSortuButton.setBounds(294, 416, 104, 30);
		getContentPane().add(kuotaSortuButton);
		
		JLabel galdZenbLabel = new JLabel(/*ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.lblNewLabel.text")*/); //$NON-NLS-1$ //$NON-NLS-2$
		galdZenbLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.galdZenbLabel.text")); //$NON-NLS-1$ //$NON-NLS-2$
		galdZenbLabel.setBounds(358, 258, 130, 14);
		getContentPane().add(galdZenbLabel);
		
		galdZenbField = new JTextField();
		galdZenbField.setEditable(false);
		//galdZenbField.setText(ResourceBundle.getBundle("Etiquetas").getString("KuotaIpiniGUI.textField.text")); //$NON-NLS-1$ //$NON-NLS-2$
		galdZenbField.setBounds(498, 255, 140, 20);
		getContentPane().add(galdZenbField);
		galdZenbField.setColumns(10);
		
		infoLabel.setBounds(179, 377, 375, 14);
		getContentPane().add(infoLabel);

	}


	private void atzeraAdminButtonActionPerformed(ActionEvent e, User user2) {
		this.setVisible(false);
		new AdminGUI(user);
	}
	
}
