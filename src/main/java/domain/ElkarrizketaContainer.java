package domain;

import java.util.Vector;

public class ElkarrizketaContainer {

	private Elkarrizketa elkarrizketa;
	private User user1;
	private User user2;
	private Vector<MezuContainer> mezuak;
	
	public ElkarrizketaContainer(Elkarrizketa elkar) {
		this.elkarrizketa = elkar;
		this.user1 = elkarrizketa.getUser1();
		this.user2 = elkarrizketa.getUser2();
		mezuak = new Vector<MezuContainer>();
		if(elkarrizketa.getMezuak() != null) {
			for (Mezua m : elkarrizketa.getMezuak()) {
				MezuContainer mCont = new MezuContainer(m);
				mezuak.add(mCont);
			}
		}
	}

	public ElkarrizketaContainer() {
		
	}
	
	public Elkarrizketa getElkarrizketa() {
		return elkarrizketa;
	}

	public void setElkarrizketa(Elkarrizketa elkarrizketa) {
		this.elkarrizketa = elkarrizketa;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}

	public Vector<MezuContainer> getMezuak() {
		return mezuak;
	}

	public void setMezuak(Vector<MezuContainer> mezuak) {
		this.mezuak = mezuak;
	}

	public boolean mezurikEz() {
		return ((mezuak==null) || (mezuak.size()==0));
	}
	
}
