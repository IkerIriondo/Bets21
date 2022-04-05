package domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class ApustuContainer {

	private Apustua apustu;
	private ErantzunPosiblea erantzun;
	
	public ApustuContainer(Apustua a) {
		apustu = a;
		erantzun = a.getEmaitzaPosiblea();
	}
	
	public ApustuContainer() {
		apustu=null;
		erantzun=null;
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
	
}
