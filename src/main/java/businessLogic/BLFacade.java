package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

import domain.*;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
		
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();

	@WebMethod public User isLogin(String email, String password);

	@WebMethod public User register(String izena, String abizena, String jaioData, String email, String username, String password);

	@WebMethod public Event gertaeraSortu(String deskribapena, String zenb, Date data);

	@WebMethod public ErantzunPosiblea kuotaIpini(int galdZenb, float kuota, String kuoMota);

	@WebMethod public User diruaSartu(User user, float dirua);

	@WebMethod public Question bilatuGaldera(int galdZenb);

	@WebMethod public User apustuaEgin(float apostu, User user, ErantzunPosiblea erantzun);

	@WebMethod public Apustua apustuaLortu(int i);

	@WebMethod public User apustuaEzabatu(Apustua apustu);

	@WebMethod public void gertaeraEzabatu(Event ev);

	@WebMethod public void emaitzaIpini(ErantzunPosiblea eran);

	@WebMethod public List<ApustuContainer> apustuakLortu();

	@WebMethod public Event gertaeraBikoiztu(Event gertaera, Date data);

	@WebMethod public List<Erabiltzailea> lortuErabiltzaileZerrenda();

	@WebMethod public User jarraituErabiltzailea(User user, Erabiltzailea erabil, float dirua);

}
