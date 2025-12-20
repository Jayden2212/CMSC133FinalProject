package implementation;

public class Destroyer implements Ship{
	private String name;
	private int size;
	private boolean isVertical;
	private boolean sunk;
	private int health;
	
	public Destroyer(boolean isVertical) {
		name = "Destroyer";
		size = 2;
		this.isVertical = isVertical;
		sunk = false;
		health = 2;
	}
	
	public Destroyer() {
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
