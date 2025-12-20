package implementation;

public class Submarine implements Ship {
	private String name;
	private int size;
	private boolean isVertical;
	private boolean sunk;
	
	public Submarine(boolean isVertical) {
		name = "Submarine";
		size = 3;
		this.isVertical = isVertical;
		sunk = false;
	}
	public Submarine() {
		name = "Submarine";
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
