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
}
