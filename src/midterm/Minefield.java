package midterm;

import java.util.Random;

public class Minefield {
	int boardSizeV = 0;
	int boardSizeH = 0;
	char[][] board;
	private int noOfMines = 0;
	Random rdm = new Random();
	
	public Minefield(int v, int h) {
		board = new char[v][h];
		boardSizeH = h;
		boardSizeV = v;
		this.noOfMines = noOfMines;
		generateInitialBoard();
	}
	//Checks the move entered by the player and sets the visible board to that value.
	public void playerMove(int v, int h, HiddenMinefield hiddenBoard){
		if (hiddenBoard.board[v][h].getState()==CellState.BLANK){
			board[v+1][h+1] = hiddenBoard.board[v][h].stateDisplay;
			hiddenBoard.board[v][h].setAsJustRevealed();
			hiddenBoard.checkSurrounding(v, h);
		}
		if (hiddenBoard.board[v][h].getState()==CellState.NUMBER){
			board[v+1][h+1] = hiddenBoard.board[v][h].stateDisplay;
			hiddenBoard.board[v][h].setAsRevealed();
		}

		
		
	}
	//Creates visible board and fills each index of the array with an equals sign, as well as the coordinates on the edges.
	public void generateInitialBoard() {
		//Generates horizontal coordinate reference
		for (int i = 1; i<board[0].length; i++){
			if ((i+64) > 90 && (i+64) < 97){
				board[0][i] = (char)(i+70);
			}else{
			board[0][i] = (char)(i+64);
			}
		}
		//Generates vertical coordinate reference
		for (int i = 1; i<board.length; i++){
			if ((i+64) > 90 && (i+64) < 97){
				board[i][0] = (char)(i+70);
			}else{
			board[i][0] = (char)(i+64);
			}
		}
		for (int v = 1; v < board.length; v++) {
			for (int h = 1; h < board[v].length; h++) {
				board[v][h] = '=';
			}
		}

	}
	//Displays visible board.
	public void displayBoard() {
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[v].length; h++) {
				System.out.print(board[v][h]);
				System.out.print(' ');
			}
			System.out.println();
		}
	}
	
	public void revealedCells(HiddenMinefield hiddenBoard){
		for (int v = 0; v < hiddenBoard.board.length; v++) {
			for (int h = 0; h < hiddenBoard.board[v].length; h++) {
				if(hiddenBoard.board[v][h].getChoosableState() == Choosable.REVEALED){
					board[v+1][h+1]=hiddenBoard.board[v][h].stateDisplay;
				}
			}
		}
				
				
		
		
		
		
	}

	
}
