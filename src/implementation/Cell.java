package implementation;

public class Cell {
	private boolean hit;
	private String name;
	private boolean hasShip;
	private String display;
	
	public Cell(String name) {
		this.name = name;
		hit = false;
		hasShip = false;
		display = "-";
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
	
	public String getDisplay() {
		return display;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}
	
	public void setHasShip(boolean hasShip) {
		this.hasShip = hasShip;
	}
	
	public void setDisplay(String display) {
		this.display = display;
	}
}
