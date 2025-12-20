package implementation;

import java.util.ArrayList;
import java.util.Random;

public class Board {
	private Cell[][] board;
	private ArrayList<Ship> ships;
	
	public Board() {
		board = new Cell[10][10];
		ships = new ArrayList<Ship>();
		
		for (int i = 0; i < board.length; i++) {
			String letter = "";
			for (int j = 0; j < board[i].length; j++) {
				if (i == 0) {letter = "A";}
				else if (i == 1) {letter = "B";}
				else if (i == 2) {letter = "C";}
				else if (i == 3) {letter = "D";}
				else if (i == 4) {letter = "E";}
				else if (i == 5) {letter = "F";}
				else if (i == 6) {letter = "G";}
				else if (i == 7) {letter = "H";}
				else if (i == 8) {letter = "I";}
				else if (i == 9) {letter = "J";}
				
				board[i][j] = new Cell(letter + (j + 1));
			}
		}
	}
	
	public Cell getCell(String name) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				if (board[i][j].getName().equalsIgnoreCase(name)) {
					return board[i][j];
				}
			}
		}
		return null;
	}
	
	public int getTotalShips() {
		return ships.size();
	}
	
	public boolean shoot(int row, int col) {
		row -= 1;
		col -= 1;
		if (isValidCell(row, col) && board[row][col].getHit() == false) {
			if (board[row][col].getHasShip()) {
				decreaseShipHealth(board[row][col].getShipName());
				board[row][col].setDisplay("O");
			} else {
				board[row][col].setDisplay("X");
			}
			board[row][col].setHit(true);
			System.out.println("\nSuccesfully shot target");
			return true;
		}
		System.out.println("\nIncorrect Input, Try Again");
		return false;
	}
	
	public boolean placeShip(Ship ship, int startRow, int startCol, boolean isPlayer) {
		// checks if all the spaces for the ship are valid and free
		if (ships != null || !checkShip(ship.getName())) {
			startRow -= 1;
			startCol -= 1;
			for (int i = 0; i < ship.getSize(); i++) {
				int currentRow = ship.getIsVertical() ? startRow + i : startRow;
				int currentCol = ship.getIsVertical() ? startCol : startCol + i;
				
				if (!isValidCell(currentRow, currentCol) || isOccupiedCell(currentRow, currentCol)) {
					System.out.println("\nIncorrect Input, Try Again");
					return false;
				}
			}
			
			// places the ship on the appropriate cells after the initial check
			for (int i = 0; i < ship.getSize(); i++) {
				int currentRow = ship.getIsVertical() ? startRow + i : startRow;
				int currentCol = ship.getIsVertical() ? startCol : startCol + i;
				board[currentRow][currentCol].setHasShip(true);
				board[currentRow][currentCol].setShipName(ship.getName());
				
				if (!isPlayer) {
					board[currentRow][currentCol].setDisplay(ship.getName().substring(0, 1));
				}
			}
			System.out.println("\nSuccesfully placed ship");
			ships.add(ship);
			return true;
		}
		return false;
	}
	
	public void computerPlaceAllShips(Board board) {
		Ship[] ships = {new Carrier(), new Battleship(), new Cruiser(), new Submarine(), new Destroyer()};
		int startRow;
		int startCol;
		boolean isVertical = false;
		Random rand = new Random();
		
		for (int i = 0; i < 5; i++) {
			do {
				startRow = (int)(Math.random() * 10) + 1;
				startCol = (int)(Math.random() * 10) + 1;
				isVertical = rand.nextBoolean();
				ships[i].setIsVertical(isVertical);
			} while (board.placeShip(ships[i], startRow, startCol, false) == false);
		}
	}
	
	
	public boolean isValidCell(int row, int col) {
		if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
			return true;
		}
		return false;
	}
	
	public boolean isOccupiedCell(int row, int col) {
		if (board[row][col].getHasShip()) {
			return true;
		}
		return false;
	}
	
	// checks whether the ship has been properly made within the array or not
	public boolean checkShip(String name) {
		for (int i = 0; i < ships.size(); i++) {
			if (ships.get(i).getName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public void decreaseShipHealth(String name) {
		for (int i = 0; i < ships.size(); i++) {
			if (ships.get(i).getName().equalsIgnoreCase(name)) {
				ships.get(i).decreaseHealth();
				if (ships.get(i).getHealth() == 0) {
					ships.get(i).setSunk(true);
					System.out.println(ships.get(i).getName() + " has been sunk!");
				}
			}
		}
	}
	
	public void displayBoard() {
		System.out.println("  1 2 3 4 5 6 7 8 9 10");
		for (int i = 0; i < board.length; i++) {
			System.out.print(board[i][0].getName().substring(0,1));
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(" " + board[i][j].getDisplay());
			}
			System.out.println();
		}
	}
	
	public void displayNames() {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].length; j++) {
				System.out.print(board[i][j].getName() + " ");
			}
			System.out.println();
		}
	}
}
