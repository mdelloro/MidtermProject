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
		
		System.out.println("****************************************");
		
		System.out.println("****************************************");
		
		System.out.println("Welcome to the Bombermen Minesweeper Game\nLet's Play a Game!!!");
		
		System.out.println("****************************************");
		
		
		System.out.println("Are you ready to sweep some mines with the Bombermen!?!");
		
		System.out.println("****************************************");
		
		System.out.println("What level of skill would you like to play?\n(Beginner)\n(Intermediate)\n(Expert)");
			String response = scanner.nextLine().toLowerCase();
			while(skillquestion == true){
			switch(response){
			case "beginner":
				skill=SkillLevel.BEGINNER;
				skillquestion=false;
				HiddenMinefield hiddenBoard = new HiddenMinefield(x,y,mines);
				break;

			case "intermediate":
				skill=SkillLevel.INTERMEDIATE;
				skillquestion=false;
				break;

			case "expert":
				skill=SkillLevel.EXPERT;
				skillquestion=false;
				break;
			default:
				System.out.println("Invalid choice please choose again from the options listed. ");
				scanner.nextLine();			}
	}
			
		System.out.println("Would you like to play again(y/n)");
				scanner.nextLine();	
		//System.out.println("How many mines would you like to play?\n(10-30)");
		
		//System.out.println("How many rows would you like to play?\n(8-30)");

		//System.out.println("How many columns would you like to play?\n(8-30)");

	}

}