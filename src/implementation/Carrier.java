package implementation;

public class Carrier implements Ship {
	private String name;
	private int size;
	private boolean isVertical;
	private boolean sunk;
	private int health;
	
	public Carrier(boolean isVertical) {
		name = "Airship Carrier";
		size = 5;
		this.isVertical = isVertical;
		sunk = false;
		health = 5;
	}
	
	public Carrier() {
		this(false);
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean getIsVertical() {
		return isVertical;
	}
	
	public boolean getSunk() {
		return sunk;
	}
	
	public void setIsVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
	
	public void decreaseHealth() {
		health -= 1;
	}
	
	public void setSunk(boolean sunk) {
		this.sunk = sunk;
	}
}
