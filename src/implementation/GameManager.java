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
		int row;
		int col;
		
		System.out.println("Welcome to the game of Batleship!! The goal of the game is to take down "
				+ "\nall five of the enemies ships while keeping your ships alive through turn based action. Good Luck!");
		
		System.out.println("Enter Player name: ");
		String name = scan.next();
		player.setName(name);
		
		System.out.println("\nWould you like to use a pre-made board (enter Y or N)? ");
		String choice = scan.next();
		
		if (choice.equalsIgnoreCase("Y")) {
			do {
				int numChoice;
				do {
					System.out.println("Which board would you like to preview (1, 2, or 3)? ");
					numChoice = scan.nextInt();
				} while (numChoice != 1 && numChoice != 2 && numChoice != 3);
				
				if (numChoice == 1) {
					playerBoard.createBoardUsingFile("BattleshipBoard1.txt", true);
					playerBoard.displayBoard();
				} else if (numChoice == 2) {
					playerBoard.createBoardUsingFile("BattleshipBoard2.txt", true);
					playerBoard.displayBoard();
				} else {
					playerBoard.createBoardUsingFile("BattleshipBoard3.txt", true);
					playerBoard.displayBoard();
				}
				
				System.out.println("Is this your choice (enter Y or N)? ");
				choice = scan.next();
			} while (!choice.equalsIgnoreCase("Y"));
		} else {
			playerBoard.displayBoard();
			
			boolean isVertical;
			Ship[] ships = {new Carrier(), new Battleship(), new Cruiser(), new Submarine(), new Destroyer()};
			
			for (int i = 0; i < ships.length; i++) {
				do {
					System.out.print("What ROW would you like to place your " + ships[i].getName() + "(Size = " + ships[i].getSize() + ")? (ex. A = 1) ");
					row = scan.nextInt();
					
					System.out.print("What COLUMN would you like to place your " + ships[i].getName() + "(Size = " + ships[i].getSize() + ")? ");
					col = scan.nextInt();
					
					System.out.print("In what orientation? (enter v or h, (vertical = left to right, horizontal = top to bottom)) ");
					String direction = scan.next();
					if (direction.equalsIgnoreCase("v")) {
						isVertical = true;
					} else {
						isVertical = false;
					}
					ships[i].setIsVertical(isVertical);
				} while (playerBoard.placeShip(ships[i], row, col, true) == false);
				
				playerBoard.displayBoard();
				System.out.println("Total # of Ships On The Board: " + playerBoard.getTotalShips());
			}
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
					System.out.println("Enter the ROW you would like to shoot at (ex. A = 1)? ");
					row = scan.nextInt();
					System.out.println("Enter the COLUMN you would like to shoot at? ");
					col = scan.nextInt();
				} while (computerBoard.shoot(row, col, true) == false);
				
				System.out.println("\nComputer's Board");
				computerBoard.displayBoard();
				
				player.setWinStatus(computerBoard.allShipsSunk());
				turn = 1;
			} 
			else {
				System.out.println("\nComputer's turn");
				
				System.out.println("Computer's shooting...");
				playerBoard.computerShoot(computer, computer.getHuntMode());
				
				System.out.println("\n" + player.getName() + "'s Board");
				playerBoard.displayBoard();
				
				System.out.println("Type any key to continue");
				cont = scan.next();
				
				computer.setWinStatus(playerBoard.allShipsSunk());
				turn = 0;
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
