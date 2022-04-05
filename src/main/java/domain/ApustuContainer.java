package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ApustuContainer {

	private Apustua apustu;
	private ErantzunPosiblea erantzun;
	private Question galdera;
	private Event gertaera;
	
	public ApustuContainer(Apustua a) {
		apustu = a;
		erantzun = a.getEmaitzaPosiblea();
		galdera = erantzun.getGaldera();
		gertaera = galdera.getEvent();
	}
	


	public ApustuContainer() {
		apustu=null;
		erantzun=null;
		galdera=null;
		gertaera=null;
	}
	
	public Apustua getApustu() {
		return apustu;
	}

	public ErantzunPosiblea getErantzun() {
		return erantzun;
	}
	
	public String toString() {
		return apustu + "/" + erantzun;
	}
	
	public Question getGaldera() {
		return galdera;
	}

	public Event getGertaera() {
		return gertaera;
	}
	
}
