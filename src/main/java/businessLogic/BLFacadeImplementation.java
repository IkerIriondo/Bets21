package businessLogic;

import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		    dbManager.initializeDB();
		    } else
		     dbManager=new DataAccess();
		dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(event.getEventDate())>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   };
	
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@Override
	public User isLogin(String email, String password) {
		dbManager.open(false);
		User ema = dbManager.isLogin(email, password);
		dbManager.close();
		return ema;
	}

	@Override
	public User register(String izena, String abizena, String jaioDat, String email, String username, String password) {
		dbManager.open(false);
		User user = dbManager.register(izena,abizena,jaioDat,email,username,password);
		dbManager.close();
		return user;
	}

	@Override
	public Event gertaeraSortu(String deskribapena, String zenb, Date data) {
		dbManager.open(false);
		Event e = dbManager.gertaeraSortu(deskribapena,zenb,data);
		dbManager.close();
		return e;
	}

	@Override
	public ErantzunPosiblea kuotaIpini(int galdZenb, float kuota, String kuoMota) {
		dbManager.open(false);
		ErantzunPosiblea k = dbManager.kuotaIpini(galdZenb,kuota,kuoMota);
		dbManager.close();
		return k;
		
	}

	@Override
	public User diruaSartu(User user, float dirua) {
		dbManager.open(false);
		User u = dbManager.diruaSartu(user,dirua);
		dbManager.close();
		return u;
		
	}

	@Override
	public Question bilatuGaldera(int galdZenb) {
		dbManager.open(false);
		Question gald = dbManager.bilatuGaldera(galdZenb);
		dbManager.close();
		return gald;
	}

	@Override
	public User apustuaEgin(float apostu, User user, ErantzunPosiblea erantzun) {
		dbManager.open(false);
		User u = dbManager.apustuaEgin(apostu,user,erantzun);
		dbManager.close();
		return u;
	}

	@Override
	public Apustua apustuaLortu(int i) {
		dbManager.open(false);
		Apustua apustu = dbManager.apustuaLortu(i);
		dbManager.close();
		return apustu;
	}

	@Override
	public User apustuaEzabatu(Apustua apustu) {
		dbManager.open(false);
		User user = dbManager.apustuaEzabatu(apustu);
		dbManager.close();
		return user;
	}

	@Override
	public void gertaeraEzabatu(Event ev) {
		dbManager.open(false);
		dbManager.gertaeraEzabatu(ev);
		dbManager.close();
			
	}

	@Override
	public void emaitzaIpini(ErantzunPosiblea eran) {
		dbManager.open(false);
		dbManager.emaitzaIpini(eran);
		dbManager.close();
	}

	@Override
	public List<ApustuContainer> apustuakLortu() {
		dbManager.open(false);
		List<ApustuContainer> a = dbManager.apustuakLortu();
		dbManager.close();
		return a;
	}

	@Override
	public Event gertaeraBikoiztu(Event gertaera, Date data) {
		dbManager.open(false);
		Event e = dbManager.gertaeraBikoiztu(gertaera,data);
		dbManager.close();
		return e;
	}

	@Override
	public List<Erabiltzailea> lortuErabiltzaileZerrenda() {
		dbManager.open(false);
		List<Erabiltzailea> erabiltzaileak = dbManager.lortuErabiltzaileak();
		dbManager.close();
		return erabiltzaileak;
	}

	@Override
	public User jarraituErabiltzailea(User user, Erabiltzailea erabil, float dirua) {
		dbManager.open(false);
		User u = dbManager.jarraituErabiltzailea(user,erabil,dirua);
		dbManager.close();
		return u;
	}

	@Override
	public Mezua bidaliMezua(User user, Elkarrizketa elkarrizketa, String testua) {
		dbManager.open(false);
		Mezua m = dbManager.bidaliMezua(user,elkarrizketa,testua);
		dbManager.close();
		return m;
	}

	@Override
	public List<Erabiltzailea> bilatuErabiltzaileak(String bilatzeko) {
		dbManager.open(false);
		List<Erabiltzailea> ema = dbManager.bilatuErabiltzaileak(bilatzeko);
		dbManager.close();
		return ema;
	}

}

