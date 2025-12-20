package implementation;

import java.util.ArrayList;

public class Computer extends Player{
	private boolean huntMode;
	private ArrayList<String> targetCells;
	
	public Computer() {
		super("");
		huntMode = true;
		targetCells = new ArrayList<String>();
	}
	
	public boolean getHuntMode() {
		return huntMode;
	}
	
	public String getCell(int index) {
		return targetCells.get(index);
	}
	
	public ArrayList<String> getTargetCellsList() {
		return targetCells;
	}
	
	public void setHuntMode(boolean huntMode) {
		this.huntMode = huntMode;
	}
	
	public void addTargetCell(String cellPos) {
		targetCells.add(cellPos);
	}
	
	public void removeTargetCell(int index) {
		targetCells.remove(index);
	}
	
	public String displayAllTargetCells() {
		String str = "";
		for (int i = 0; i < targetCells.size(); i++) {
			str += targetCells.get(i) + " ";
		}
		return str;
	}
}
