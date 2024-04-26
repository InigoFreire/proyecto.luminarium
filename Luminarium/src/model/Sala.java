package model;

public class Sala {
	private String id;
	private int aforo;
	
	public Sala() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	@Override
	public String toString() {
		return "Sala [id=" + id + ", aforo=" + aforo + "]";
	}
	
}
