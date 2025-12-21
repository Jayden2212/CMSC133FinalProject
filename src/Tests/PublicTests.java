package Tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import implementation.Battleship;
import implementation.Board;
import implementation.Carrier;
import implementation.Cruiser;
import implementation.Destroyer;
import implementation.Submarine;

class PublicTests {
/* These methods tested are all within the Board class as this class implements and utilizes all the other classes methods.
 * The public tests therefore thoroughly test most methods made and used throughout this project.
 */
	
	@Test
	public void testShoot() {
		Board board = new Board();
		int row = 2, col = 2;
		boolean isPlayer = true;
		
		board.placeShip(new Carrier(), row, col, isPlayer);
		board.setHasNuke(false);
		
		boolean shot = board.shoot(row, col, isPlayer);
		
		assertTrue(shot);
		assertTrue(board.getCell(row - 1, col - 1).getShipName().equalsIgnoreCase("Airship Carrier"));
		assertTrue(board.getCell(row - 1, col - 1).getHit() == true);
		assertTrue(board.getCell(row - 1, col - 1).getDisplay().equalsIgnoreCase("O"));
		
		boolean shot2 = board.shoot(11, 10, isPlayer);
		assertFalse(shot2);
		
		row = 6;
		col = 7;
		boolean shot3 = board.shoot(row, col, isPlayer);
		assertTrue(board.getCell(row - 1, col - 1).getDisplay().equalsIgnoreCase("X"));
	}
	
	@Test
	public void testPlaceShip() {
		Board board = new Board();
		int row = 2, col = 2;
		boolean isPlayer = true;
		
		board.placeShip(new Carrier(), row, col, isPlayer);
		assertTrue(board.getCell(row - 1, col - 1).getShipName().equalsIgnoreCase("Airship Carrier"));
		assertTrue(board.getCell(row - 1, col - 1).getDisplay().equalsIgnoreCase("A"));
		
		row = 5;
		col = 6;
		board.placeShip(new Destroyer(), row, col, isPlayer);
		assertTrue(board.getCell(row - 1, col - 1).getShipName().equalsIgnoreCase("Destroyer"));
		assertTrue(board.getCell(row - 1, col - 1).getDisplay().equalsIgnoreCase("D"));
	}
	
	@Test
	public void testNuke() {
		Board board = new Board();
		int row = 2, col = 2;
		boolean isPlayer = true;
		
		board.placeShip(new Carrier(), row, col, isPlayer);
		int numOfNukedCells = board.nuke(row - 1, col - 1, "Airship Carrier", "O");
		
		assertTrue(numOfNukedCells == 5);
		assertTrue(board.getShip("Airship Carrier").getHealth() == 0);
	}
	
	@Test
	public void testDecreaseShipHealth() {
		Board board = new Board();
		int row = 2, col = 2;
		boolean isPlayer = true;
		
		board.placeShip(new Carrier(), row, col, isPlayer);
		board.decreaseShipHealth("Airship Carrier");
		
		assertTrue(board.getShip("Airship Carrier").getHealth() == 4);
		
		board.decreaseShipHealth("Airship Carrier");
		assertTrue(board.getShip("Airship Carrier").getHealth() == 3);
	}
	
	@Test
	public void testIsValidCell() {
		Board board = new Board();
		
		assertTrue(board.isValidCell(2, 2) == true);
		assertTrue(board.isValidCell(5, 1) == true);
		assertTrue(board.isValidCell(10, 9) == false);
		assertTrue(board.isValidCell(-1, 9) == false);
	}
	
	@Test
	public void testIsOccupiedCell() {
		Board board = new Board();
		int row = 2, col = 2;
		boolean isPlayer = true;
		
		board.placeShip(new Carrier(), row, col, isPlayer);
		
		assertTrue(board.isOccupiedCell(row - 1, col - 1) == true);
		assertTrue(board.isOccupiedCell(row + 3, col + 3) == false);
	}
	
	@Test
	public void testAllShipsSunk() {
		Board board = new Board();
		boolean isPlayer = true;
		
		board.placeShip(new Carrier(true), 4, 5, isPlayer);
		board.placeShip(new Battleship(true), 6, 7, isPlayer);
		board.placeShip(new Cruiser(), 10, 1, isPlayer);
		board.placeShip(new Submarine(), 1, 1, isPlayer);
		board.placeShip(new Destroyer(), 8, 3, isPlayer);
		
		board.getShip("Airship Carrier").setSunk(true);
		board.getShip("Battleship").setSunk(true);
		board.getShip("Cruiser").setSunk(true);
		board.getShip("Submarine").setSunk(true);
		board.getShip("Destroyer").setSunk(false);
		
		assertTrue(board.allShipsSunk() == false);
		
		board.getShip("Destroyer").setSunk(true);
		assertTrue(board.allShipsSunk() == true);
	}
}
