package implementation;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.*;

public class Board {
	private Cell[][] board;
	private ArrayList<Ship> ships;
	private boolean hasNuke;
	
	public Board() {
		board = new Cell[10][10];
		ships = new ArrayList<Ship>();
		hasNuke = true;
		
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
	
	public Cell getCell(int row, int col) {
		return board[row][col];
	}
	
	public int getTotalShips() {
		return ships.size();
	}
	
	// returns true if a shot has been successful, false otherwise
	public boolean shoot(int row, int col, boolean isPlayer) {
		row -= 1;
		col -= 1;
		if (isValidCell(row, col) && board[row][col].getHit() == false) {
			System.out.println("\nShot At: " + (row + 1) + "," + (col + 1));
			
			if (board[row][col].getHasShip()) {
				decreaseShipHealth(board[row][col].getShipName());
				board[row][col].setDisplay("O");
				
				if (hasNuke == true && isPlayer) {
					System.out.println("\nWould you like to nuke the ship you just hit (one time ability to insantly destroy a ship) (enter Y or N)? ");
					Scanner scan = new Scanner(System.in);
					String response = scan.next();
					
					if (response.equalsIgnoreCase("y")) {
						nuke(row, col, board[row][col].getShipName(), "O");
						this.hasNuke = false;
					}
				} else if (hasNuke == true && isPlayer == false) {
					nuke(row, col, board[row][col].getShipName(), "O");
					this.hasNuke = false;
					System.out.println("\nComputer has used their nuke!!");
				}
			} else {
				board[row][col].setDisplay("X");
			}
			board[row][col].setHit(true);
			return true;
		}
		System.out.println("\nIncorrect Input, Try Again");
		return false;
	}
	
	// recursively finds all cells starting from (row, column) that matches the target ship and hits all cells
	public int nuke(int row, int col, String target, String replacement) {
		if (!isValidCell(row, col)) {
			return 0;
		}
		if (!board[row][col].getShipName().equalsIgnoreCase(target)) {
			return 0;
		}
		if (board[row][col].getHit() == true) {
			return 0;
		}
		
		board[row][col].setDisplay(replacement);
		decreaseShipHealth(board[row][col].getShipName());
		board[row][col].setHit(true);;
		int changed = 0;
		changed++;
		
		changed += nuke(row - 1, col, target, replacement);
		changed += nuke(row, col - 1, target, replacement);
		changed += nuke(row, col + 1, target, replacement);
		changed += nuke(row + 1, col, target, replacement);
		
		return changed;
	}
	
	// returns true if a ship has successfully been placed at a valid position, false otherwise
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
				
				if (isPlayer) {
					board[currentRow][currentCol].setDisplay(ship.getName().substring(0, 1));
				}
			}
			System.out.println("\nSuccesfully placed ship");
			ships.add(ship);
			return true;
		}
		return false;
	}
	
	// reads each text file line by line, updating cells with ships on it as necessary
	public void createBoardUsingFile(String fileName, boolean isPlayer) {
		resetBoard();
		try {
			Scanner scan = new Scanner(new File(fileName));
			int row = 0;
			
			while (scan.hasNextLine()) {
				String currentLine = scan.nextLine();
				String[] displayCells = currentLine.split(" ");
				
				for (int i = 0; i < displayCells.length; i++) {
					if (!displayCells[i].equalsIgnoreCase("-")) {
						board[row][i].setHasShip(true);
						
						if (displayCells[i].equalsIgnoreCase("A")) {
							board[row][i].setShipName("Airship Carrier");
						} else if (displayCells[i].equalsIgnoreCase("B")) {
							board[row][i].setShipName("Battleship");
						} else if (displayCells[i].equalsIgnoreCase("C")) {
							board[row][i].setShipName("Cruiser");
						} else if (displayCells[i].equalsIgnoreCase("S")) {
							board[row][i].setShipName("Submarine");
						} else if (displayCells[i].equalsIgnoreCase("D")) {
							board[row][i].setShipName("Destroyer");
						}
						
						if (isPlayer) {
							board[row][i].setDisplay(displayCells[i]);
						}
					}
				}
				row += 1;
			}
			ships.add(new Carrier());
			ships.add(new Battleship());
			ships.add(new Cruiser());
			ships.add(new Submarine());
			ships.add(new Destroyer());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// computer shoots at random cells while in huntMode until it hits a boat
	// the adjacent cells from that shot are added to an array list and huntMode is turned off
	// when not in huntMode, it shoots at adjacent cells from the list until they are gone from the list
	// switching back to huntMode and restarting the process
	public void computerShoot(Computer comp, boolean huntMode) {
		int row;
		int col;
		if (huntMode == true) {
			do {
				row = (int)(Math.random() * 10) + 1;
				col = (int)(Math.random() * 10) + 1;
			} while (shoot(row, col, false) == false);
			
			row -= 1;
			col -= 1;
			if (board[row][col].getHasShip()) {
				if (isValidCell(row - 1, col) && board[row - 1][col].getHit() == false) {
					comp.addTargetCell((row - 1) + "," + col);
				}
				if (isValidCell(row, col - 1) && board[row][col - 1].getHit() == false) {
					comp.addTargetCell(row + "," + (col - 1));
				}
				if (isValidCell(row, col + 1) && board[row][col + 1].getHit() == false) {
					comp.addTargetCell(row + "," + (col + 1));
				}
				if (isValidCell(row + 1, col) && board[row + 1][col].getHit() == false) {
					comp.addTargetCell((row + 1) + "," + col);
				}
				comp.setHuntMode(false);
			}
		} else {
			int size = comp.getTargetCellsList().size();
			
			if (!comp.getTargetCellsList().isEmpty()) {
				int random;
				do {
					random = (int)(Math.random() * size);
					row = Integer.parseInt(comp.getCell(random).substring(0,1));
					col = Integer.parseInt(comp.getCell(random).substring(2));
					comp.removeTargetCell(random);
				} while (shoot(row + 1, col +  1, false) == false);
				
				if (board[row][col].getHasShip()) {
					if (isValidCell(row - 1, col) && board[row - 1][col].getHit() == false) {
						comp.addTargetCell((row - 1) + "," + col);
					}
					if (isValidCell(row, col - 1) && board[row][col - 1].getHit() == false) {
						comp.addTargetCell(row + "," + (col - 1));
					}
					if (isValidCell(row, col + 1) && board[row][col + 1].getHit() == false) {
						comp.addTargetCell(row + "," + (col + 1));
					}
					if (isValidCell(row + 1, col) && board[row + 1][col].getHit() == false) {
						comp.addTargetCell((row + 1) + "," + col);
					}
				}
				
				if (checkShipSunkStatus(board[row][col].getShipName()) == true) {
					comp.clearTargetCells();
				}
			}
			if (comp.getTargetCellsList().size() <= 0) {
				comp.setHuntMode(true);
			}
		}
	}
	
	// places ships at random positions and orientations until it's valid
	public void computerPlaceAllShips() {
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
			} while (placeShip(ships[i], startRow, startCol, false) == false);
		}
	}
	
	// returns true if the row and column passed are valid positions on the board, false otherwise
	public boolean isValidCell(int row, int col) {
		if (row >= 0 && row < board.length && col >= 0 && col < board[0].length) {
			return true;
		}
		return false;
	}
	
	// returns true if the cell has a ship on it, false otherwise
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
	
	// returns true if the ship specified has sunk, false otherwise
	public boolean checkShipSunkStatus(String name) {
		for (int i = 0; i < ships.size(); i++) {
			if (ships.get(i).getName().equals(name)) {
				if (ships.get(i).getSunk() == true) {
					return true;
				}
			}
		}
		return false;
	}
	
	// decreases the specified ship's health by one, if health has reached zero, the ships sunk status is set true
	public void decreaseShipHealth(String name) {
		for (int i = 0; i < ships.size(); i++) {
			if (ships.get(i).getName().equalsIgnoreCase(name)) {
				ships.get(i).decreaseHealth();
				if (ships.get(i).getHealth() == 0) {
					ships.get(i).setSunk(true);
					System.out.println("\n" + ships.get(i).getName() + " HAS BEEN SUNK!");
				}
			}
		}
	}
	
	// displays String representation of the Board
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
	
	// checks if all the ships on the board have sunk
	public boolean allShipsSunk() {
		for (int i = 0; i < ships.size(); i++) {
			if (ships.get(i).getSunk() == false) {
				return false;
			}
		}
		return true;
	}
	
	public void resetBoard() {
		board = new Cell[10][10];
		ships = new ArrayList<Ship>();
		hasNuke = true;
		
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
	
	public void setHasNuke(boolean hasNuke) {
		this.hasNuke = hasNuke;
	}
	
	public Ship getShip(String name) {
		for (int i = 0; i < ships.size(); i++) {
			if (ships.get(i).getName().equals(name)) {
				return ships.get(i);
			}
		}
		return null;
	}
}
