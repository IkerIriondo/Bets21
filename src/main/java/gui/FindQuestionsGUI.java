package gui;

import businessLogic.BLFacade;
import configuration.UtilDate;

import com.toedter.calendar.JCalendar;
import domain.*;
import domain.Event;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.beans.*;
import java.text.DateFormat;
import java.time.LocalDate;
import java.util.*;

import javax.swing.table.DefaultTableModel;


public class FindQuestionsGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private final JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private final JLabel jLabelQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Queries")); 
	private final JLabel jLabelEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Events")); 
	private JButton atzeraButton = new JButton();
	
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

	private User user;
	private Question galdera;
	private final JButton apustuEginButton = new JButton();
	private final JLabel infoLabel = new JLabel();
	private final JButton gertaeraEzabatuButton = new JButton();
	private final JButton emaitzaIpiniButton = new JButton();
	private final JButton gertBikoiztButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("BikoGertaera")); //$NON-NLS-1$ //$NON-NLS-2$

	
	public JButton getGertBikoiztButton() {
		return gertBikoiztButton;
	}


	public FindQuestionsGUI(User user)
	{
		//getContentPane().setVisible(false);
		try
		{
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
	
	
	public JButton getApustuEginButton() {
		return apustuEginButton;
	}
	
	public JButton getGertaeraEzabatuButton() {
		return gertaeraEzabatuButton;
	}

	public JButton getEmaitzaIpiniButton() {
		return emaitzaIpiniButton;
	}

	private void jbInit() throws Exception{

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(700, 500));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelQueries.setBounds(138, 248, 406, 14);
		jLabelEvents.setBounds(295, 19, 259, 16);

		this.getContentPane().add(jLabelEventDate, null);
		this.getContentPane().add(jLabelQueries);
		this.getContentPane().add(jLabelEvents);


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

		this.getContentPane().add(jCalendar1, null);
		
		scrollPaneEvents.setBounds(new Rectangle(292, 50, 346, 150));
		scrollPaneQueries.setBounds(new Rectangle(148, 273, 406, 116));

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

		this.getContentPane().add(scrollPaneEvents, null);
		this.getContentPane().add(scrollPaneQueries, null);
		atzeraButton.setText(ResourceBundle.getBundle("Etiquetas").getString("Close"));
		
		atzeraButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atzeraButtonActionPerformed(e, user);
			}
		});
		atzeraButton.setBounds(40, 419, 98, 30);
		getContentPane().add(atzeraButton);
		
		apustuEginButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				
				LocalDate g = LocalDate.now();
				Date gaur = new Date(g.getYear()-1900,g.getMonthValue()-1,g.getDayOfMonth());
				
				Date data = jCalendar1.getDate();
				if(data.compareTo(gaur)>=0) {
					try {
						int i = tableQueries.getSelectedRow();
						int galdZenb = (int)tableModelQueries.getValueAt(i, 0);
						BLFacade facade = MainGUI.getBusinessLogic();
						galdera = facade.bilatuGaldera(galdZenb);
						apostatuButtonActionPerformed(user,galdera);		
					}catch(Exception e2) {
						infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectQ"));
						System.out.println("Aukeratu galdera bat");
					}
				}else {
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("PastDate"));
					System.out.println("Data hau iraganekoa da");
				}
			}
		});
		apustuEginButton.setVisible(false);
		apustuEginButton.setText(ResourceBundle.getBundle("Etiquetas").getString("MakeABet"));
		apustuEginButton.setBounds(251, 423, 150, 23);
		
		getContentPane().add(apustuEginButton);
		infoLabel.setBounds(187, 401, 287, 14);
		
		getContentPane().add(infoLabel);
		gertaeraEzabatuButton.setVisible(false);
		gertaeraEzabatuButton.setText(ResourceBundle.getBundle("Etiquetas").getString("DelEvent"));
		gertaeraEzabatuButton.setBounds(488, 419, 150, 30);
		getContentPane().add(gertaeraEzabatuButton);
		emaitzaIpiniButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
			try {	
				LocalDate g = LocalDate.now();
				Date gaur = new Date(g.getYear()-1900,g.getMonthValue()-1,g.getDayOfMonth());
				Date data = jCalendar1.getDate();

				
				if(data.compareTo(gaur)<=0) {
					int i = tableQueries.getSelectedRow();
					int galdZenb = (int)tableModelQueries.getValueAt(i, 0);
					BLFacade facade = MainGUI.getBusinessLogic();
					galdera = facade.bilatuGaldera(galdZenb);
					if(galdera.getResult()==null) {
						emaitzaIpiniActionPerformed(user, galdera);
					}else {
						infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("HasAnswer"));
						System.out.println("Galdera honek dagoeneko badu emaitza bat");
					}
				}else {
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("NoAnswer"));
					System.out.println("Ez dakigu gertaera honen emaitza oraindik");
				}
			}catch (Exception e1) {}
			}
		});
		emaitzaIpiniButton.setVisible(false);
		emaitzaIpiniButton.setText(ResourceBundle.getBundle("Etiquetas").getString("EmaitzaIpini"));
		emaitzaIpiniButton.setBounds(251, 419, 150, 30);
		
		getContentPane().add(emaitzaIpiniButton);
		gertBikoiztButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = tableEvents.getSelectedRow();
					Event gert = (Event)tableModelEvents.getValueAt(i, 2);
					gertaeraBikoiztuActionPerformed(user,gert);
				}catch(Exception e1) {
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectEvent"));
				}
			}
		});
		gertBikoiztButton.setVisible(false);
		gertBikoiztButton.setBounds(272, 423, 164, 23);
		
		getContentPane().add(gertBikoiztButton);
		gertaeraEzabatuButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					int i = tableEvents.getSelectedRow();
					Event ev=(Event)tableModelEvents.getValueAt(i,2);
					BLFacade facade = MainGUI.getBusinessLogic();
					facade.gertaeraEzabatu(ev);
				} catch (Exception e2) {
					infoLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectEvent"));
				}
				
			}
		});

	}
	
		private void atzeraButtonActionPerformed(ActionEvent e, User user) {
			this.setVisible(false);
			if(user == null) {
				new LoginGUI();
			}else if(user.getClass() == Erabiltzailea.class) {
				new ErregistratuaGUI(user);
			}else {
				new AdminGUI(user);
			}
		}
		
		private void apostatuButtonActionPerformed(User user,Question galdera) {
			new ApustuaEginGUI(user,galdera);
			this.setVisible(false);
		}
		
		private void emaitzaIpiniActionPerformed(User u, Question galdera) {
				 new EmaitzaIpiniGUI(user, galdera);
				 this.setVisible(false);		 
		}
		
		private void gertaeraBikoiztuActionPerformed(User u, Event e) {
			new GertaeraBikoiztuGUI(u, e);
			this.setVisible(false);
		}
		
}
