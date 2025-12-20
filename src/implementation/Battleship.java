package implementation;

public class Battleship implements Ship {
	private String name;
	private int size;
	private boolean isVertical;
	private boolean sunk;
	
	public Battleship(boolean isVertical) {
		name = "Battleship";
		size = 4;
		this.isVertical = isVertical;
		sunk = false;
	}
	
	public Battleship() {
		name = "Battleship";
		size = 4;
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
