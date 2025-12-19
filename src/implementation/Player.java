package implementation;

public class Player {
	private String name;
	private boolean winStatus;
	
	public Player(String name) {
		this.name = name;
		winStatus = false;
	}
	
	public Player() {
		this("");
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getWinStatus() {
		return winStatus;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setWinStatus(boolean winStatus) {
		this.winStatus = winStatus;
	}
}
