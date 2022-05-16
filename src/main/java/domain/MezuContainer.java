package domain;

public class MezuContainer {

	private Mezua mezua;
	private User nork;
	private String testua;
	private boolean reported;
	private Elkarrizketa elkar;
	
	public MezuContainer(Mezua m) {
		this.mezua = m;
		this.nork = mezua.getNork();
		this.testua = mezua.getMezua();
		this.reported = mezua.isReported();
		this.elkar = mezua.getElkarrizketa();
	}

	public MezuContainer() {
		
	}
	
	public Mezua getMezua() {
		return mezua;
	}

	public void setMezua(Mezua mezua) {
		this.mezua = mezua;
	}

	public User getNork() {
		return nork;
	}

	public void setNork(User nork) {
		this.nork = nork;
	}

	public String getTestua() {
		return testua;
	}

	public void setTestua(String testua) {
		this.testua = testua;
	}

	public boolean isReported() {
		return reported;
	}

	public void setReported(boolean reported) {
		this.reported = reported;
	}

	public Elkarrizketa getElkar() {
		return elkar;
	}

	public void setElkar(Elkarrizketa elkar) {
		this.elkar = elkar;
	}

	
	
}
