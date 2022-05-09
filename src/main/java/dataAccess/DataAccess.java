package dataAccess;


import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.*;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));
			
			
			
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion("¿Quién ganará el partido?",1);
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion("¿Quién ganará el partido?",1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion("¿Quién ganará el partido?",1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			q1.setEvent(ev1);
			q2.setEvent(ev1);
			q3.setEvent(ev11);
			q4.setEvent(ev11);
			q5.setEvent(ev17);
			q6.setEvent(ev17);
			
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 
	
			ErantzunPosiblea eran = new ErantzunPosiblea(q1,1,"Athletic");
			q1.addErantzunPosibleak(eran);
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			//ADMINISTRATZAILEAK
			Admin admin = new Admin("Ibai", "Heras", "2002/12/10", "admin@gmail.com", "admin1", "1234");
			db.persist(admin);
			
			//ERABILTZAILEAK
			Erabiltzailea user = new Erabiltzailea("Iker","Pagola","2002/01/05","proba@gmail.com", "User1","1234");
			
			Erabiltzailea user1 = new Erabiltzailea("Paco","Fiestas","1999/06/25","pacof@gmail.com","paco","1234");
			user1.setZenbatAposIrabazi(5);
			user1.setZenbatDiruIrabazi(500);
			user1.setZenbatApostu(6);
			
			Jarraipena jarrai = new Jarraipena(user,user1,50);
			
			user1.setDirua(500);
			user1.gehituJarraitzailea(jarrai);
			user.gehituJarraitua(jarrai);
			
			db.persist(user1);
			db.persist(jarrai);
			
			//GUREAK
			Event e33 = new Event(33, "Erreala Vs Eibar",UtilDate.newDate(2022, 2-1, 13));
			Question q33 = e33.addQuestion("Zein izango da irabazlea?", 33);
			q33.setEvent(e33);
			ErantzunPosiblea ema = new ErantzunPosiblea(q33,2,"Erreala");
			ErantzunPosiblea ema1 = new ErantzunPosiblea(q33,4,"Eibar");
			q33.addErantzunPosibleak(ema);
			q33.addErantzunPosibleak(ema1);
			
			Apustua a1 = new Apustua(5, user, ema);
			
			ema.getApustuak().add(a1);	
			user.diruaGehitu(100);		
			user.apustuaGehitu(a1);
			
			
			db.persist(user);
			db.persist(e33);
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
		db.getTransaction().commit();
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			q.setEvent(ev);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
	}
	

public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
public boolean existQuestion(Event event, String question) {
	System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
	Event ev = db.find(Event.class, event.getEventNumber());
	return ev.DoesQuestionExists(question);
	
}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	

	public User isLogin(String email, String password) {
		User user = db.find(User.class, email);
		if (user==null || !user.isCorrectPassword(password)) return null;
		else return user;
	}

	public User register(String izena, String abizena, String jaioDat, String email, String username, String password) {
		if (db.find(User.class, email)==null) {
			db.getTransaction().begin();
			Erabiltzailea user = new Erabiltzailea(izena, abizena, jaioDat, email, username, password);
			db.persist(user);
			db.getTransaction().commit();
			System.out.println("Erabiltzailea sortuta");
			return user;
		}else {
			//System.out.println("Dagoeneko badago erabiltzaile bat email horrekin");
			return null;
		}
	}

	public Event gertaeraSortu(String deskribapena, String zenb, Date data) {
		
		if(!zenb.isBlank()) {
			int zenbakia = Integer.parseInt(zenb);
			if(db.find(Event.class, zenb) == null) {
				db.getTransaction().begin();
				Event event = new Event(zenbakia,deskribapena,data);
				db.persist(event);
				db.getTransaction().commit();
				System.out.println("Gertaera ondo sortu da");
				return event;
			}else {
				System.out.println("Dagoeneko badago gertaera bat zenbaki horrekin");
				return null;
			}
		}else {
			db.getTransaction().begin();
			Event event = new Event(deskribapena,data);
			db.persist(event);
			db.getTransaction().commit();
			System.out.println("Gertaera ondo sortu da");
			return event;
		}
		
		
	}

	public ErantzunPosiblea kuotaIpini(int galdZenb, float kuota, String kuoMota) {
		db.getTransaction().begin();
		Question q = db.find(Question.class, galdZenb);
		ErantzunPosiblea k = new ErantzunPosiblea(q,kuota,kuoMota);
		q.addErantzunPosibleak(k);
		db.getTransaction().commit();
		System.out.println("Kuota ondo sortu da");
		return k;
	}

	public User diruaSartu(User user, float dirua) {
		db.getTransaction().begin();
		User u = db.find(User.class, user.getEmail());
		u.diruaGehitu(dirua);
		db.getTransaction().commit();
		return u;
	}

	public Question bilatuGaldera(int galdZenb) {
		db.getTransaction().begin();
		Question q = db.find(Question.class, galdZenb);
		System.out.println("DataAccess: " + q.getQuestion());
		db.getTransaction().commit();
		return q;
	}

	public User apustuaEgin(float apostu, User user, ErantzunPosiblea erantzun) {
		db.getTransaction().begin();
		Apustua ap;
		User u = db.find(User.class, user.getEmail());
		ErantzunPosiblea e = db.find(ErantzunPosiblea.class, erantzun.getId());
		List<Jarraipena> ezabatzekoak = new LinkedList<Jarraipena>();
		boolean aurkitua = false; 
		int i = 0;
		while(!aurkitua && i<u.getApustuak().size()) {
			if(u.getApustuak().get(i).getEmaitzaPosiblea().equals(e)) {
				aurkitua = true;
			}else {
				i++;
			}
		}
		if(aurkitua) {
			Apustua a = db.find(Apustua.class, u.getApustuak().get(i).getId());
			a.setDirua(a.getDirua()+apostu);
			u.apustuaEguneratu(apostu);
		}else {
			Apustua apustu = new Apustua(apostu,user,erantzun);
			u.apustuaGehitu(apustu);
			e.getApustuak().add(apustu);
		}
		for (Jarraipena j : user.getJarraitzaileak()) {
			User us = db.find(Erabiltzailea.class, j.getZeinek());
			Jarraipena ja = db.find(Jarraipena.class, j);
			float diru = ja.getZenbatDiru();
			if(diru <= apostu) {
				ap = new Apustua(diru,us,erantzun);
				u.ezabatuJarraitzailea(ja);
				us.ezabatuJarraituak(ja);
				us.apustuaGehitu(ap);
				ezabatzekoak.add(ja);
			}else {
				ap = new Apustua(apostu,us,erantzun);
				us.apustuaGehitu(ap);
				ja.setZenbatDiru(diru - apostu);
			}
			e.getApustuak().add(ap);
		}
		for (Jarraipena j : ezabatzekoak) {
			db.remove(j);
		}
		db.getTransaction().commit();
		
		return u;
	}

	public Apustua apustuaLortu(int i) {
		db.getTransaction().begin();
		Apustua apustu = db.find(Apustua.class, i);
		db.getTransaction().commit();
		return apustu;
	}

	public User apustuaEzabatu(Apustua apustu) {
		db.getTransaction().begin();
		Apustua a = db.find(Apustua.class, apustu.getId());
		User u = db.find(User.class, a.getErabiltzailea().getEmail());
		ErantzunPosiblea er = db.find(ErantzunPosiblea.class, a.getEmaitzaPosiblea().getId());
		er.getApustuak().remove(a);
		u.diruaItzuli(a);
		db.remove(a);
		db.getTransaction().commit();
		return u;
	}

	public void gertaeraEzabatu(Event ev) {
		db.getTransaction().begin();
		Event e = db.find(Event.class, ev.getEventNumber());
		for (Question q : e.getQuestions()) {
			for (ErantzunPosiblea eran : q.getErantzunPosibleak()) {
				for (Apustua a : eran.getApustuak()) {	
					User u = db.find(User.class, a.getErabiltzailea().getEmail());
					u.diruaItzuli(a);
				}
			}
		}
		db.remove(e);
		db.getTransaction().commit();
	}

	public void emaitzaIpini(ErantzunPosiblea eran) {
		db.getTransaction().begin();
		ErantzunPosiblea e = db.find(ErantzunPosiblea.class, eran.getId());
		for (Apustua a : e.getApustuak()) {
			User u = db.find(User.class, a.getErabiltzailea().getEmail());
			u.apustuaIrabazi(a);
		}
		Question q = db.find(Question.class, e.getGaldera().getQuestionNumber());
		for (ErantzunPosiblea ema : q.getErantzunPosibleak()) {
			for (Apustua ap : ema.getApustuak()) {
				db.remove(ap);
			}
			ema.setApustuak(new Vector<Apustua>());
		}
		q.setResult(e);
		db.getTransaction().commit();
	}

	public List<ApustuContainer> apustuakLortu() {
		db.getTransaction().begin();
		List<ApustuContainer> ema = new LinkedList<ApustuContainer>();
		TypedQuery<Apustua> query = db.createQuery("SELECT ap FROM Apustua ap",Apustua.class);   
		List<Apustua> apustuak = query.getResultList();
		for (Apustua a : apustuak) {
			ema.add(new ApustuContainer(a));
		}
		db.getTransaction().commit();
		return ema;
	}

	public Event gertaeraBikoiztu(Event g, Date data) {
		db.getTransaction().begin();
		Event e = new Event(g.getDescription(),data);
		for (Question q : g.getQuestions()) {
			for (ErantzunPosiblea ep : q.getErantzunPosibleak()) {
				ep.setApustuak(new Vector<Apustua>());
			}
			e.addQuestion(q.getQuestion(),q.getBetMinimum());
		}
		for (Question q : e.getQuestions()) {
			q.setEvent(e);
		}
		db.persist(e);
		db.getTransaction().commit();
		return e;
	}

	public List<Erabiltzailea> lortuErabiltzaileak() {
		db.getTransaction().begin();
		TypedQuery<Erabiltzailea> query = db.createQuery("SELECT e FROM Erabiltzailea e ORDER BY e.zenbatDiruIrabazi DESC",Erabiltzailea.class);
		List<Erabiltzailea> erabiltzaileak = query.getResultList();
		db.getTransaction().commit();
		return erabiltzaileak;
	}

	public User jarraituErabiltzailea(User user, Erabiltzailea erabil, float dirua) {
		db.getTransaction().begin();
		
		User gu = db.find(Erabiltzailea.class, user);
		User jarraitua = db.find(Erabiltzailea.class, erabil);
		
		Jarraipena jarrai = new Jarraipena(gu, jarraitua, dirua);
		gu.gehituJarraitua(jarrai);
		jarraitua.gehituJarraitzailea(jarrai);
		
		db.persist(jarrai);
		
		db.getTransaction().commit();
		return null;
	}
	
}
