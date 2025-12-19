package implementation;

public class Cell {
	private boolean hit;
	private String name;
	private boolean hasShip;
	
	public Cell(String name) {
		this.name = name;
		hit = false;
		hasShip = false;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean getHit() {
		return hit;
	}
	
	public boolean getHasShip() {
		return hasShip;
	}
}
