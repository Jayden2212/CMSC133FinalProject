package implementation;

public class Board {
	private Cell[][] board;
	private Ship[] ships;
	
	public Board() {
		board = new Cell[10][10];
		ships = new Ship[5];
		
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
	
	public void displayBoard() {
		System.out.println("  1 2 3 4 5 6 7 8 9 10");
		System.out.println("A - - - - - - - - - - ");
		System.out.println("B - - - - - - - - - - ");
		System.out.println("C - - - - - - - - - - ");
		System.out.println("D - - - - - - - - - - ");
		System.out.println("E - - - - - - - - - - ");
		System.out.println("F - - - - - - - - - - ");
		System.out.println("G - - - - - - - - - - ");
		System.out.println("H - - - - - - - - - - ");
		System.out.println("I - - - - - - - - - - ");
		System.out.println("J - - - - - - - - - - ");
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
