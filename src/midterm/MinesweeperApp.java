package midterm;

import java.util.Scanner;

public class MinesweeperApp {

	public static void main(String[] args) {
		Scanner scanner = new Scanner (System.in);
		SkillLevel skill = SkillLevel.BEGINNER;
		boolean skillquestion = true;
		String userprompt = "y";
		int x = 0;
		int y = 0;
		int mines = 0;
		HiddenMinefield hiddenBoard = new HiddenMinefield(5,5,5);
		
		System.out.println("****************************************");
		
		System.out.println("||||||||||||||||||||||||||||||||||||||||");
		
		
		System.out.println("****************************************");
		
		System.out.println("Welcome to the Bombermen Minesweeper Game\nLet's Play a Game!!!");
		
		System.out.println("****************************************");
		
		System.out.println("****************************************");
		
		
		System.out.println("Are you ready to sweep some mines with the Bombermen!?!");
		
		System.out.println("****************************************");
		System.out.println("****************************************");
		
		
		System.out.println("What level of skill would you like to play?\n(Beginner)\n(Intermediate)\n(Expert)");
			String response = scanner.nextLine().toLowerCase();
			while(skillquestion == true){
			switch(response){
			case "beginner":
				skill=SkillLevel.BEGINNER;
				skillquestion=false;
				hiddenBoard = new HiddenMinefield(8,8,15);
				break;

			case "intermediate":
				
				skill=SkillLevel.INTERMEDIATE;
				skillquestion=false;
				hiddenBoard = new HiddenMinefield(15,15,50);
				break;

			case "expert":
				skill=SkillLevel.EXPERT;
				skillquestion=false;
				hiddenBoard = new HiddenMinefield(30,30,99);
				break;
			default:
				System.out.println("Invalid choice please choose again from the options listed. ");
				scanner.nextLine();			}
	}
		hiddenBoard.placeMines();
		hiddenBoard.displayBoard();
		System.out.println("Would you like to play again(y/n)");
				scanner.nextLine();	
		
	}

}