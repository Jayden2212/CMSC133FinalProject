package implementation;

public class Cruiser implements Ship {
	private String name;
	private int size;
	private boolean isVertical;
	private boolean sunk;
	
	public Cruiser(boolean isVertical) {
		name = "Cruiser";
		size = 3;
		this.isVertical = isVertical;
		sunk = false;
	}
	
	public Cruiser() {
		name = "Cruiser";
		size = 3;
		isVertical = false;
		sunk = false;
	}
	
	public String getName() {
		return name;
	}
	
	public int getSize() {
		return size;
	}
	
	public boolean getIsVertical() {
		return isVertical;
	}
	
	public void setIsVertical(boolean isVertical) {
		this.isVertical = isVertical;
	}
}
