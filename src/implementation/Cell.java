package implementation;

public class Cell {
	private boolean hit;
	private String name;
	private boolean hasShip;
	private String shipName;
	private String display;
	
	public Cell(String name) {
		this.name = name;
		hit = false;
		hasShip = false;
		shipName = "";
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
	
	public String getShipName() {
		return shipName;
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
	
	public void setShipName(String shipName) {
		this.shipName = shipName;
	}
}
