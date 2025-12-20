package implementation;

public interface Ship {
	public String getName();
	public int getSize();
	public boolean getIsVertical();
	public void setIsVertical(boolean isVertical);
	public int getHealth();
	public void decreaseHealth();
	public boolean getSunk();
	public void setSunk(boolean sunk);
}
