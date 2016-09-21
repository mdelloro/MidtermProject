package midterm;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MinesweeperApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		SkillLevel skill = SkillLevel.BEGINNER;
		boolean skillquestion = true;
		String userprompt = "y";
		int x = 0;
		int y = 0;
		int mines = 0;
		int h = 0;
		int v = 0;
		char hchar = 0;
		char vchar = 0;

		HiddenMinefield hiddenBoard = new HiddenMinefield(5, 5, 5);
		Minefield displayBoard = new Minefield(5, 5);

		System.out.println("********************************************************************************");
		System.out.println("||||||||||||||||||||||||||||||||||||||||****************************************");
		System.out.println("********************************************************************************");
		System.out.println(
				"Welcome to the Bombermen Minesweeper Game\nLet's Play a Game!!!************************************************************");

		System.out.println("********************************************************************************");

		System.out.println("----------------------------------------****************************************");

		System.out.println("********************************************************************************");

		System.out.println("Are you ready to sweep some mines with the Bombermen!?!*************************");

		System.out.println("********************************************************************************");
		System.out.println("********************************************************************************");

		while (userprompt.equalsIgnoreCase("y")) {

			while (skillquestion == true) {
				System.out.println(
						"What level of skill would you like to play?\n(Beginner)\n(Intermediate)\n(Expert)\n(Custom)");
				String response = scanner.next().toLowerCase();
				scanner.nextLine();
				switch (response) {
				case "beginner":
					skill = SkillLevel.BEGINNER;
					skillquestion = false;
					hiddenBoard = new HiddenMinefield(8, 8, 12);
					displayBoard = new Minefield(9, 9);
					displayBoard.displayBoard();
					// scanner.nextLine();
					break;

				case "intermediate":

					skill = SkillLevel.INTERMEDIATE;
					skillquestion = false;
					hiddenBoard = new HiddenMinefield(15, 15, 45);
					displayBoard = new Minefield(16, 16);
					displayBoard.displayBoard();
					// scanner.nextLine();
					break;

				case "expert":
					skill = SkillLevel.EXPERT;
					skillquestion = false;
					hiddenBoard = new HiddenMinefield(16, 30, 99);
					displayBoard = new Minefield(17, 31);
					displayBoard.displayBoard();
					// scanner.nextLine();
					break;
				case "custom":
					skill = SkillLevel.CUSTOM;
					skillquestion = false;
					while (true) {
						try{
						System.out.println("How wide do you want the field to be?");
						h = scanner.nextInt();
						if (h>30) {
							System.out.println("Please choose a number 30 or lower.");
							continue;
						}
						System.out.println("How tall do you want the field to be?");
						v = scanner.nextInt();
						if (v>30) {
							System.out.println("Please choose a number 30 or lower.");
							continue;
						}
						System.out.println("How many mines do you want in the field?");
						mines = scanner.nextInt();
						
						if ((double) mines > ((double) h * (double) v * .75)) {
							System.out.println("Please choose a lower number of mines.");
							continue;
						}
						if (v <0 || h < 0){
							System.out.println("Please enter positive numbers for the board dimensions.");
							continue;
						}
						break;
						}catch (InputMismatchException ex){
							System.out.println("Please enter numbers for the size of the board.");
							scanner.nextLine();
							continue;
						}
					}
					// scanner.nextLine();
					hiddenBoard = new HiddenMinefield(v, h, mines);
					displayBoard = new Minefield(v + 1, h + 1);
					displayBoard.displayBoard();
					break;

				default:
					System.out.println("Invalid choice please choose again from the options listed.");
				}
			}
			v = verticalCoordinate(scanner, hiddenBoard, displayBoard);
			h = horizontalCoordinate(scanner, hiddenBoard);
			hiddenBoard.placeMines(v, h);
			hiddenBoard.setNumbers();
			displayBoard.playerMove(v, h, hiddenBoard);
			hiddenBoard.checkForJustRevealed();
			displayBoard.revealedCells(hiddenBoard);
			displayBoard.displayBoard();

			while (true) {
				System.out.println("Enter a ! to flag a mine and ~ to unflag it.");
				if (hiddenBoard.checkCell(v, h) && !hiddenBoard.board[v][h].isFlagged()) {
					displayBoard.revealBoard(hiddenBoard);
					displayBoard.displayBoard();
					System.out.println("You lose!");
					break;
				}
				if (hiddenBoard.checkForWin() == true) {
					System.out.println("You won!");
					break;
				}
				v = verticalCoordinate(scanner, hiddenBoard, displayBoard);
				h = horizontalCoordinate(scanner, hiddenBoard);
				displayBoard.playerMove(v, h, hiddenBoard);
				hiddenBoard.checkForJustRevealed();
				displayBoard.revealedCells(hiddenBoard);
				displayBoard.displayBoard();

			}
			scanner.nextLine();
			skillquestion = true;
			System.out.println("Would you like to play again(y/n)");
			userprompt = scanner.next();
			scanner.nextLine();

		}
		System.out.println("\nGoodbye.");
		scanner.close();
	}

	public static int verticalCoordinate(Scanner scanner, HiddenMinefield hiddenBoard, Minefield displayBoard) {
		char vchar = 0;
		int v = 0;
		int h = 0;
		while (true) {

			System.out.println("Please enter your vertical coordinate");
			vchar = scanner.next().charAt(0);
			if (vchar == '!') {
				System.out.println("Flag a mine");
				v = verticalCoordinate(scanner, hiddenBoard, displayBoard);
				h = horizontalCoordinate(scanner, hiddenBoard);
				hiddenBoard.board[v][h].setFlag();
				displayBoard.changeToFlag(v, h);
				displayBoard.displayBoard();
				continue;
			}
			if (vchar == '~') {
				System.out.println("Flag a mine");
				v = verticalCoordinate(scanner, hiddenBoard, displayBoard);
				h = horizontalCoordinate(scanner, hiddenBoard);
				hiddenBoard.board[v][h].unsetFlag();
				displayBoard.board[v + 1][h + 1] = '=';
				displayBoard.displayBoard();

			}

			if (vchar >= 'A' && vchar <= 'Z') {
				if (vchar >= hiddenBoard.board.length + 65) {
					System.out.println("Does not exist in board!");
				} else {
					v = (int) (vchar - 65);
					break;
				}
			} else if (vchar >= 'a' && vchar <= 'z') {
				if (vchar >= hiddenBoard.board.length + 70) {
					System.out.println("Does not exist in board!");
				} else {
					v = (int) (vchar - 70);
					break;
				}
			}

			else {
				System.out.println(" Try again silly!");
			}
		}

		return v;
	}

	public static int horizontalCoordinate(Scanner scanner, HiddenMinefield hiddenBoard) {
		char hchar = 0;
		int h = 0;

		while (true) {
			System.out.println("Please enter your horizontal coordinate");
			hchar = scanner.next().charAt(0);
			if (hchar >= 'A' && hchar <= 'Z') {
				if (hchar >= hiddenBoard.board.length + 65) {
					System.out.println("Does not exist in board!");
				} else {
					h = (int) (hchar - 65);
					break;
				}
			} else if (hchar >= 'a' && hchar <= 'z') {
				if (hchar >= hiddenBoard.board.length + 70) {
					System.out.println("Does not exist in board!");
				} else {
					h = (int) (hchar - 70);
					break;
				}
			}

			else {
				System.out.println(" Try again silly!");
			}
		}

		return h;
	}
}
