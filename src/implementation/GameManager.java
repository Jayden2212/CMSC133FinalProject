package implementation;
import java.util.*;

public class GameManager {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Board playerBoard = new Board();
		Board computerBoard = new Board();;
		Player player = new Player();
		Computer computer = new Computer();
		int turn = 0;
		
		System.out.println("Welcome to the game of Batleship!! The goal of the game is to take down "
				+ "\nall five of the enemies ships while keeping your ships alive through turn based action. Good Luck!");
		
		System.out.println("Enter Player name: ");
		String name = scan.next();
		player.setName(name);
		
		playerBoard.displayBoard();
		
		int startRow;
		int startCol;
		boolean isVertical;
		Ship[] ships = {new Carrier(), new Battleship(), new Cruiser(), new Submarine(), new Destroyer()};
		
		for (int i = 0; i < ships.length; i++) {
			do {
				System.out.print("At what row would you like to place your " + ships[i].getName() + " (ex. A = 1)? ");
				startRow = scan.nextInt();
				
				System.out.print("At what column would you like to place your " + ships[i].getName() + "? ");
				startCol = scan.nextInt();
				
				System.out.print("In what orientation (vertical or horizontal)? ");
				String direction = scan.next();
				if (direction.equalsIgnoreCase("vertical")) {
					isVertical = true;
				} else {
					isVertical = false;
				}
				ships[i].setIsVertical(isVertical);
			} while (playerBoard.placeShip(ships[i], startRow, startCol, true) == false);
			
			playerBoard.displayBoard();
			System.out.println("Total # of Ships On The Board: " + playerBoard.getTotalShips());
		}
		
		System.out.println("\nComputer's placing their ships...");
		computerBoard.computerPlaceAllShips();
		
		System.out.println("Type any key to continue");
		String cont = scan.next();
		
		while (player.getWinStatus() == false && computer.getWinStatus() == false) {
			if (turn == 0) {
				System.out.println("\nPlayer " + player.getName() + "'s turn");
				
				System.out.println("\nComputer's Board");
				computerBoard.displayBoard();
				
				do {
					System.out.println("Enter the row you would like to shoot at (ex. A = 1)? ");
					startRow = scan.nextInt();
					System.out.println("Enter the column you would like to shoot at? ");
					startCol = scan.nextInt();
				} while (computerBoard.shoot(startRow, startCol) == false);
				
				System.out.println("\nComputer's Board");
				computerBoard.displayBoard();
				
				player.setWinStatus(computerBoard.allShipsSunk());
				turn = 1;
			} 
			else {
				System.out.println("\nComputer's turn");
				
				System.out.println("\n" + player.getName() + "'s Board");
				playerBoard.displayBoard();
				
				System.out.println("Computer's shooting...");
				playerBoard.computerShoot(player);
				System.out.println("Type any key to continue");
				cont = scan.next();
				
				System.out.println("\n" + player.getName() + "'s Board");
				playerBoard.displayBoard();
				
				computer.setWinStatus(playerBoard.allShipsSunk());
				//turn = 0;
			}
		}
		
		if (player.getWinStatus()) {
			System.out.println("\nPlayer WINS!!!");
		} else if (computer.getWinStatus()) {
			System.out.println("\nPlayer loses.....");
		}
		scan.close();
	}
}
