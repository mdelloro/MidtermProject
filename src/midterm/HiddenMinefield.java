package midterm;

import java.lang.reflect.Array;
import java.util.Random;

public class HiddenMinefield {
	int hiddenBoardSizeV = 0;
	int hiddenBoardSizeH = 0;
	Cell[][] board;
	private int noOfMines = 0;
	Random rdm = new Random();
	//Constructor
	public HiddenMinefield(int v, int h, int noOfMines) {
		board = new Cell[v][h];
		hiddenBoardSizeH = h;
		hiddenBoardSizeV = v;
		this.noOfMines = noOfMines;
		generateInitialBoard();
	}
	//Checks cell for a mine.
	public boolean checkCell(int v, int h) {
		if (board[v][h].getState()==CellState.MINE) {
			return true;
		}
		return false;
	}
	//Method to generate mines, excluding the first player choice.
	public void placeMines(int y, int x) {
		int mineAmount = noOfMines;
		int h = 0;
		int v = 0;
		while (mineAmount != 0) {
			h = rdm.nextInt(hiddenBoardSizeH);
			v = rdm.nextInt(hiddenBoardSizeV);
			if (v == y && h == x){
				continue;
			}else if (checkCell(v, h) == false) {
				board[v][h].setAsMine();
				mineAmount--;
			} else {
				continue;
			}

		}
		int mines = 0;
		for (int vv = 0; vv < board.length; vv++) {
			for (int hh = 0; hh < board[vv].length; hh++) {
				if (board[vv][hh].getState() == CellState.MINE) {
					mines++;
				}
			}
		}
		System.out.println(mines);
	}
	//Creates board with provided size and fills each with a new Cell object.
	public void generateInitialBoard() {
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[v].length; h++) {
				board[v][h] = new Cell(v,h);
			}
		}

	}
	//For cells that aren't a mine, this method sets the corresponding value to the cell
	public void setNumbers() {
		for (int vv = 0; vv < board.length; vv++) {
			for (int hh = 0; hh < board[vv].length; hh++) {
				if (board[vv][hh].getState() != CellState.MINE) {
					
					
					board[vv][hh].setAsNumber(checkSurrounding(vv, hh));
					if (board[vv][hh].stateDisplay == '0') {
						board[vv][hh].stateDisplay = ' ';
						board[vv][hh].setAsBlank();
					}
					
				}
			}
		}

	}
	//Displays hidden board
	public void displayBoard() {
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[0].length; h++) {
				if(board[v][h].getState()==CellState.BLANK){
					System.out.print(' ');
				}
				if(board[v][h].getState()==CellState.NUMBER){
					System.out.print(board[v][h].stateDisplay);
				}
				if(board[v][h].getState()==CellState.MINE){
					System.out.print('*');
				}
				System.out.print(' ');
			}
			System.out.println();
		}
	}
	//Checks the cells around the given one for bombs.  Used to determine number value of cell
	public int checkSurrounding(int v, int h) {
		int number = 0;
		// No borders
		if (v-1 >= 0 && (v + 1) < board.length && h-1 >= 0 && (h+1) < board[v].length) {
			for (int y = v-1; y <= (v + 1); y++) {
				for (int x = h-1; x <= (h + 1); x++) {
					if (board[y][x].stateDisplay == '*') {
						number++;
					}
//					if(board[y][x].getState() == CellState.BLANK){
//						board[y][x].setAsJustRevealed();
//					}
					
				}
			
			}
			
		}
		// Top left corner
		if (v == 0 && h == 0) {
			number = 3;
			if (board[v][h + 1].stateDisplay != '*') {
				number--;
			}
			if (board[v + 1][h].stateDisplay != '*') {
				number--;
			}
			if (board[v + 1][h + 1].stateDisplay != '*') {
				number--;
			}

		}
		// Top right corner
		if (v == 0 && h == board[v].length-1) {
			number = 3;
			if (board[v][h - 1].stateDisplay != '*') {
				number--;
			}
			if (board[v + 1][h].stateDisplay != '*') {
				number--;
			}
			if (board[v + 1][h - 1].stateDisplay != '*') {
				number--;
			}
		}
		// Bottom right corner
		if (v == board.length-1 && h == board[v].length-1) {
			number = 3;
			if (board[v][h - 1].stateDisplay != '*') {
				number--;
			}
			if (board[v - 1][h].stateDisplay != '*') {
				number--;
			}
			if (board[v - 1][h - 1].stateDisplay != '*') {
				number--;
			}
		}
		// Bottom left corner
		if (v == board.length-1 && h == 0) {
			number = 3;
			if (board[v][h + 1].stateDisplay != '*') {
				number--;
			}
			if (board[v - 1][h].stateDisplay != '*') {
				number--;
			}
			if (board[v - 1][h + 1].stateDisplay != '*') {
				number--;
			}
		}
		// Top row
		if (v == 0 && h > 0 && h < board[v].length-1) {
			number = 0;
			for (int y = 0; y <= 1; y++) {
				for (int x = (h - 1); x <= (h + 1); x++) {
					if (board[y][x].stateDisplay == '*') {
						number++;
					}
				}

			}

		}
		// Bottom row
		if (v == board.length-1 && h > 0 && h < board[v].length-1) {
			number = 0;
			for (int y = board.length - 2; y < board.length; y++) {
				for (int x = (h - 1); x <= (h + 1); x++) {
					if (board[y][x].stateDisplay == '*') {
						number++;
					}
				}

			}

		}
		// Leftmost row
		if (v < board.length - 1 && v > 0 && h == 0) {
			number = 6;
			for (int y = v - 1; y <= v + 1; y++) {
				for (int x = 0; x <= 1; x++) {
					if (board[y][x].stateDisplay != '*') {
						number--;
					}
				}

			}

		}
		// Rightmost row
		if (v < board.length - 1 && v > 0 && h == board[v].length-1) {
			number = 0;
			for (int y = v - 1; y <= v + 1; y++) {
				for (int x = board[v].length-2; x < board[v].length; x++) {
					if (board[y][x].stateDisplay == '*') {
						number++;
					}
				}

			}

		}
//		board[v][h].setAsRevealed();
		return number;
	}
	public void checkForJustRevealed(){
		int objectCount = 1;
		while(objectCount != 0){
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[0].length; h++) {
				if(board[v][h].getChoosableState()==Choosable.JUST_REVEALED ){
					checkSurrounding(v,h);
				}
					
			}
			
		}	objectCount = 0;
		
			for (int v = 0; v < board.length; v++) {
				for (int h = 0; h < board[0].length; h++) {
					if(board[v][h].getChoosableState()==Choosable . JUST_REVEALED ){
						objectCount++;
					}
						
				}
			}
	
		}
	}

	

}
