package midterm;

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

		System.out.println("What level of skill would you like to play?\n(Beginner)\n(Intermediate)\n(Expert)");
		String response = scanner.nextLine().toLowerCase();
		while (skillquestion == true) {
			switch (response) {
			case "beginner":
				skill = SkillLevel.BEGINNER;
				skillquestion = false;
				hiddenBoard = new HiddenMinefield(8, 8, 15);
				hiddenBoard.placeMines(8, 8);
				displayBoard = new Minefield(9, 9);
				displayBoard.displayBoard();

				break;

			case "intermediate":

				skill = SkillLevel.INTERMEDIATE;
				skillquestion = false;
				hiddenBoard = new HiddenMinefield(15, 15, 50);
				hiddenBoard.placeMines(15, 15);
				displayBoard = new Minefield(16, 16);
				displayBoard.displayBoard();

				break;

			case "expert":
				skill = SkillLevel.EXPERT;
				skillquestion = false;
				hiddenBoard = new HiddenMinefield(16, 30, 99);
				displayBoard = new Minefield(17, 31);
				displayBoard.displayBoard();

				break;
			default:
				System.out.println("Invalid choice please choose again from the options listed. ");
				scanner.nextLine();
			}
		}
		v = verticalCoordinate(scanner, hiddenBoard);
		h = horizontalCoordinate(scanner, hiddenBoard);
		hiddenBoard.placeMines(v, h);
		hiddenBoard.setNumbers();
		displayBoard.playerMove(v, h, hiddenBoard);
		displayBoard.displayBoard();

		while (true) {
			if (hiddenBoard.checkCell(v, h)) {
				hiddenBoard.displayBoard();
				break;
			}
			v = verticalCoordinate(scanner, hiddenBoard);
			
			h = horizontalCoordinate(scanner, hiddenBoard);

			displayBoard.playerMove(v, h, hiddenBoard);
			
			hiddenBoard.checkForJustRevealed();
			displayBoard.revealedCells(hiddenBoard);
			displayBoard.displayBoard();

		}

		System.out.println("You lose!");
		System.out.println("Would you like to play again(y/n)");
		userprompt = scanner.nextLine();
		while (userprompt.equalsIgnoreCase("y"))
			;
		System.out.println("\nGoodbye.");
		scanner.close();
	}

	public static int verticalCoordinate(Scanner scanner, HiddenMinefield hiddenBoard) {
		char vchar = 0;
		int v = 0;
		System.out.println("Please enter your vertical coordinate");
		vchar = scanner.nextLine().charAt(0);

		if (vchar < 0 && vchar >= (char) hiddenBoard.board.length) {
			System.out.println(" Does not exist in board dumb dumb!");
			verticalCoordinate(scanner, hiddenBoard);
		} else if (vchar >= 65 && vchar <= 90) {
			v = (int) (vchar - 65);
			return v;
		} else if (vchar >= 97 && vchar <= 122) {
			v = (int) (vchar - 97);
			return v;
		}

		else {
			System.out.println(" Try again silly!");
			verticalCoordinate(scanner, hiddenBoard);
		}

		return v;
	}

	public static int horizontalCoordinate(Scanner scanner, HiddenMinefield hiddenBoard) {
		char hchar = 0;
		int h = 0;
		System.out.println("Please enter your horizontal coordinate");
		hchar = scanner.nextLine().charAt(0);
		if (hchar < 'A' && hchar >= (char) hiddenBoard.board[0].length + 65) {
			System.out.println(" Does not exist in board dumb dumb!");
			horizontalCoordinate(scanner, hiddenBoard);
		} else if (hchar >= 'A' && hchar <= 'Z') {
			h = (int) (hchar - 65);
			return h;
		} else if (hchar >= 'a' && hchar <= 'z') {
			h = (int) (hchar - 97);
			return h;
		}

		else {
			System.out.println(" Try again silly!");
			horizontalCoordinate(scanner, hiddenBoard);
		}

		return h;
	}
}
