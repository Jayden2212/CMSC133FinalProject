package implementation;

public class Carrier implements Ship {
	private String name;
	private int size;
	private boolean isVertical;
	private boolean sunk;
	
	public Carrier(boolean isVertical) {
		name = "Airship Carrier";
		size = 5;
		this.isVertical = isVertical;
		sunk = false;
	}
	
	public Carrier() {
		name = "Airship Carrier";
		size = 5;
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
