package midterm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class HiddenMinefield implements ActionListener {
	int hiddenBoardSizeV = 0;
	int hiddenBoardSizeH = 0;
	Cell[][] board;
	private int noOfMines = 0;
	Random rdm = new Random();
	
	int v = 0;
	int h = 0;

	JFrame frame = new JFrame("The Bombermen Minesweeper Game");
	JButton reset = new JButton("Button");
	JButton[][] buttons;
	int[][] counts = new int[20][20];
	Container grid = new Container();

	// Constructor
	public HiddenMinefield(int v, int h, int noOfMines) {
		board = new Cell[v][h];
		buttons = new JButton[v][h];
		hiddenBoardSizeH = h;
		hiddenBoardSizeV = v;
		this.v=v;
		this.h=h;
		this.noOfMines = noOfMines;
		generateInitialBoard();

		frame.setSize(450, 550);
		frame.setLayout(new BorderLayout());
		frame.add(reset, BorderLayout.NORTH);
		reset.addActionListener(this);
		grid.setLayout(new GridLayout(v, h));
		for (int a = 0; a < buttons.length; a++) {
			for (int b = 0; b < buttons[a].length; b++) {
				buttons[a][b] = new JButton();
				buttons[a][b].putClientProperty("row", a);
				buttons[a][b].putClientProperty("column", b);
				
				buttons[a][b].addActionListener(this);
				grid.add(buttons[a][b]);

			}
		}
		frame.add(grid, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		//System.out.println("clicked column " + button.getClientProperty("column")
        //+ ", row " + button.getClientProperty("row"));
		playerMove((Integer)button.getClientProperty("row"), (Integer)button.getClientProperty("column"));
		button.setEnabled(false);
		if (checkCell((Integer)button.getClientProperty("row"),(Integer)button.getClientProperty("column"))){
			revealBoard();
			for (int v = 0; v < board.length; v++) {
				for (int h = 0; h < board[v].length; h++) {
					if (board[v][h].getState() == CellState.MINE){
					buttons[v][h].setBackground(Color.RED);
					}
				}
			}	
			JOptionPane.showMessageDialog(frame, "Uh oh, you lost!");
		}
		
		checkForJustRevealed();
		revealedCells();
		if(checkForWin()){
			JOptionPane.showMessageDialog(frame, "Congratulations, you won!");
			for (int v = 0; v < board.length; v++) {
				for (int h = 0; h < board[v].length; h++) {
					if (board[v][h].getState() == CellState.MINE){
					buttons[v][h].setBackground(Color.GREEN);
					}
				}
			}	
		}
	}
	
	public void revealBoard(){
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[v].length; h++) {
				buttons[v][h].setText(board[v][h].getStateDisplay());
				buttons[v][h].setEnabled(false);
			}
		}	
	}
	
	public void revealedCells(){
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[v].length; h++) {
				if(board[v][h].getChoosableState() == Choosable.REVEALED){
					buttons[v][h].setText(board[v][h].getStateDisplay());
					buttons[v][h].setEnabled(false);
				}
			}
		}	
	}

	public void playerMove(int v, int h) {

		if (board[v][h].isFlagged()) {
			System.out.println("---------------------------------------------------");
			System.out.println("-------Cell is flagged and cannot be chosen.-------");
			System.out.println("---------------------------------------------------");
			return;
		}

		if (board[v][h].getState() == CellState.BLANK) {
			buttons[v][h].setText(board[v][h].getStateDisplay());
			buttons[v][h].setEnabled(false);
			board[v][h].setAsJustRevealed();
			checkForNearbyBlanks(v, h);
		}
		if (board[v][h].getState() == CellState.NUMBER) {
			buttons[v][h].setText(board[v][h].getStateDisplay());
			buttons[v][h].setEnabled(false);
			board[v][h].setAsRevealed();
		}
		if (board[v][h].getState() == CellState.MINE) {
			buttons[v][h].setText(board[v][h].getStateDisplay());
			buttons[v][h].setEnabled(false);
			board[v][h].setAsRevealed();
		}

	}

	public void changeToFlag(int v, int h) {
		buttons[v][h].setText("!");
	}

	// Checks cell for a mine.
	public boolean checkCell(int v, int h) {
		if (board[v][h].getState() == CellState.MINE) {
			return true;
		}
		return false;
	}

	// Method to generate mines, excluding the first player choice.
	public void placeMines() {
		int mineAmount = noOfMines;
		int h = 0;
		int v = 0;
		while (mineAmount != 0) {
			h = rdm.nextInt(hiddenBoardSizeH);
			v = rdm.nextInt(hiddenBoardSizeV);
//			if (v == y && h == x) {
//				continue; 
			if (checkCell(v, h) == false) {
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
	}

	// Creates board with provided size and fills each with a new Cell object.
	public void generateInitialBoard() {
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[v].length; h++) {
				board[v][h] = new Cell(v, h);
			}
		}

	}

	public boolean checkForWin() {
		boolean didWin;
		int counter = 0;
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[v].length; h++) {
				if (board[v][h].getChoosableState() == Choosable.NOT_REVEALED
						&& board[v][h].getState() != CellState.MINE) {
					counter++;
				}
			}
		}

		if (counter > 0) {
			didWin = false;
		} else {
			didWin = true;
		}

		return didWin;
	}

	// For cells that aren't a mine, this method sets the corresponding value to
	// the cell
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

	// Displays hidden board
	public void displayBoard() {
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[0].length; h++) {
				if (board[v][h].getState() == CellState.BLANK) {
					System.out.print(' ');
				}
				if (board[v][h].getState() == CellState.NUMBER) {
					System.out.print(board[v][h].stateDisplay);
				}
				if (board[v][h].getState() == CellState.MINE) {
					System.out.print('*');
				}
				System.out.print(' ');
			}
			System.out.println();
		}
	}

	// Checks the cells around the given one for bombs. Used to determine number
	// value of cell
	public int checkSurrounding(int v, int h) {
		int number = 0;
		// No borders
		if (v - 1 >= 0 && (v + 1) < board.length && h - 1 >= 0 && (h + 1) < board[v].length) {
			for (int y = v - 1; y <= (v + 1); y++) {
				for (int x = h - 1; x <= (h + 1); x++) {
					if (board[y][x].stateDisplay == '*') {
						number++;
					}
					// if(board[y][x].getState() == CellState.BLANK){
					// board[y][x].setAsJustRevealed();
					// }

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
		if (v == 0 && h == board[v].length - 1) {
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
		if (v == board.length - 1 && h == board[v].length - 1) {
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
		if (v == board.length - 1 && h == 0) {
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
		if (v == 0 && h > 0 && h < board[v].length - 1) {
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
		if (v == board.length - 1 && h > 0 && h < board[v].length - 1) {
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
		if (v < board.length - 1 && v > 0 && h == board[v].length - 1) {
			number = 0;
			for (int y = v - 1; y <= v + 1; y++) {
				for (int x = board[v].length - 2; x < board[v].length; x++) {
					if (board[y][x].stateDisplay == '*') {
						number++;
					}
				}

			}

		}
		// board[v][h].setAsRevealed();
		return number;
	}

	public void checkForJustRevealed() {
		int objectCount = 1;
		while (objectCount != 0) {
			for (int v = 0; v < board.length; v++) {
				for (int h = 0; h < board[0].length; h++) {
					if (board[v][h].getChoosableState() == Choosable.JUST_REVEALED) {
						checkForNearbyBlanks(v, h);
						checkForNearbyNumbers(v, h);
					}

				}

			}
			objectCount = 0;

			for (int v = 0; v < board.length; v++) {
				for (int h = 0; h < board[0].length; h++) {
					if (board[v][h].getChoosableState() == Choosable.JUST_REVEALED) {
						objectCount++;
					}

				}
			}

		}

	}

	public void checkForNearbyBlanks(int v, int h) {
		// No borders
		if (v - 1 >= 0 && (v + 1) < board.length && h - 1 >= 0 && (h + 1) < board[v].length) {
			for (int y = v - 1; y <= (v + 1); y++) {
				for (int x = h - 1; x <= (h + 1); x++) {
					if (board[y][x].getState() == CellState.BLANK
							&& board[y][x].getChoosableState() != Choosable.REVEALED) {
						board[y][x].setAsJustRevealed();
					}

				}

			}

		}
		// Top left corner
		if (v == 0 && h == 0) {

			if (board[v][h + 1].getState() == CellState.BLANK
					&& board[v][h + 1].getChoosableState() != Choosable.REVEALED) {
				board[v][h + 1].setAsJustRevealed();
			}
			if (board[v + 1][h].getState() == CellState.BLANK
					&& board[v + 1][h].getChoosableState() != Choosable.REVEALED) {
				board[v + 1][h].setAsJustRevealed();
			}
			if (board[v + 1][h + 1].getState() == CellState.BLANK
					&& board[v + 1][h + 1].getChoosableState() != Choosable.REVEALED) {
				board[v + 1][h + 1].setAsJustRevealed();
			}

		}
		// Top right corner
		if (v == 0 && h == board[v].length - 1) {

			if (board[v][h - 1].getState() == CellState.BLANK
					&& board[v][h - 1].getChoosableState() != Choosable.REVEALED) {
				board[v][h - 1].setAsJustRevealed();
			}
			if (board[v + 1][h].getState() == CellState.BLANK
					&& board[v + 1][h].getChoosableState() != Choosable.REVEALED) {
				board[v + 1][h].setAsJustRevealed();
			}
			if (board[v + 1][h - 1].getState() == CellState.BLANK
					&& board[v + 1][h - 1].getChoosableState() != Choosable.REVEALED) {
				board[v + 1][h - 1].setAsJustRevealed();
			}
		}
		// Bottom right corner
		if (v == board.length - 1 && h == board[v].length - 1) {

			if (board[v][h - 1].getState() == CellState.BLANK
					&& board[v][h - 1].getChoosableState() != Choosable.REVEALED) {
				board[v][h - 1].setAsJustRevealed();
			}
			if (board[v - 1][h].getState() == CellState.BLANK
					&& board[v - 1][h].getChoosableState() != Choosable.REVEALED) {
				board[v - 1][h].setAsJustRevealed();
			}
			if (board[v - 1][h - 1].getState() == CellState.BLANK
					&& board[v - 1][h - 1].getChoosableState() != Choosable.REVEALED) {
				board[v - 1][h - 1].setAsJustRevealed();
			}
		}
		// Bottom left corner
		if (v == board.length - 1 && h == 0) {

			if (board[v][h + 1].getState() == CellState.BLANK
					&& board[v][h + 1].getChoosableState() != Choosable.REVEALED) {
				board[v][h + 1].setAsJustRevealed();
			}
			if (board[v - 1][h].getState() == CellState.BLANK
					&& board[v - 1][h].getChoosableState() != Choosable.REVEALED) {
				board[v - 1][h].setAsJustRevealed();
			}
			if (board[v - 1][h + 1].getState() == CellState.BLANK
					&& board[v - 1][h + 1].getChoosableState() != Choosable.REVEALED) {
				board[v - 1][h + 1].setAsJustRevealed();
			}
		}
		// Top row
		if (v == 0 && h > 0 && h < board[v].length - 1) {
			for (int y = 0; y <= 1; y++) {
				for (int x = (h - 1); x <= (h + 1); x++) {
					if (board[y][x].getState() == CellState.BLANK
							&& board[y][x].getChoosableState() != Choosable.REVEALED) {
						board[y][x].setAsJustRevealed();
					}
				}

			}

		}
		// Bottom row
		if (v == board.length - 1 && h > 0 && h < board[v].length - 1) {
			for (int y = board.length - 2; y < board.length; y++) {
				for (int x = (h - 1); x <= (h + 1); x++) {
					if (board[y][x].getState() == CellState.BLANK
							&& board[y][x].getChoosableState() != Choosable.REVEALED) {
						board[y][x].setAsJustRevealed();
					}
				}

			}

		}
		// Leftmost row
		if (v < board.length - 1 && v > 0 && h == 0) {
			for (int y = v - 1; y <= v + 1; y++) {
				for (int x = 0; x <= 1; x++) {
					if (board[y][x].getState() == CellState.BLANK
							&& board[y][x].getChoosableState() != Choosable.REVEALED) {
						board[y][x].setAsJustRevealed();
					}
				}

			}

		}
		// Rightmost row
		if (v < board.length - 1 && v > 0 && h == board[v].length - 1) {
			for (int y = v - 1; y <= v + 1; y++) {
				for (int x = board[v].length - 2; x < board[v].length; x++) {
					if (board[y][x].getState() == CellState.BLANK
							&& board[y][x].getChoosableState() != Choosable.REVEALED) {
						board[y][x].setAsJustRevealed();
					}
				}

			}

		}
		board[v][h].setAsRevealed();
	}

	public void checkForNearbyNumbers(int v, int h) {
		// No borders
		if (v - 1 >= 0 && (v + 1) < board.length && h - 1 >= 0 && (h + 1) < board[v].length) {
			for (int y = v - 1; y <= (v + 1); y++) {
				for (int x = h - 1; x <= (h + 1); x++) {
					if (board[y][x].getState() == CellState.NUMBER
							&& board[y][x].getChoosableState() != Choosable.REVEALED) {
						board[y][x].setAsRevealed();
					}

				}

			}

		}
		// Top left corner
		if (v == 0 && h == 0) {

			if (board[v][h + 1].getState() == CellState.NUMBER
					&& board[v][h + 1].getChoosableState() != Choosable.REVEALED) {
				board[v][h + 1].setAsRevealed();
			}
			if (board[v + 1][h].getState() == CellState.NUMBER
					&& board[v + 1][h].getChoosableState() != Choosable.REVEALED) {
				board[v + 1][h].setAsRevealed();
			}
			if (board[v + 1][h + 1].getState() == CellState.NUMBER
					&& board[v + 1][h + 1].getChoosableState() != Choosable.REVEALED) {
				board[v + 1][h + 1].setAsRevealed();
			}

		}
		// Top right corner
		if (v == 0 && h == board[v].length - 1) {

			if (board[v][h - 1].getState() == CellState.NUMBER
					&& board[v][h - 1].getChoosableState() != Choosable.REVEALED) {
				board[v][h - 1].setAsRevealed();
			}
			if (board[v + 1][h].getState() == CellState.NUMBER
					&& board[v + 1][h].getChoosableState() != Choosable.REVEALED) {
				board[v + 1][h].setAsRevealed();
			}
			if (board[v + 1][h - 1].getState() == CellState.NUMBER
					&& board[v + 1][h - 1].getChoosableState() != Choosable.REVEALED) {
				board[v + 1][h - 1].setAsRevealed();
			}
		}
		// Bottom right corner
		if (v == board.length - 1 && h == board[v].length - 1) {

			if (board[v][h - 1].getState() == CellState.NUMBER
					&& board[v][h - 1].getChoosableState() != Choosable.REVEALED) {
				board[v][h - 1].setAsRevealed();
			}
			if (board[v - 1][h].getState() == CellState.NUMBER
					&& board[v - 1][h].getChoosableState() != Choosable.REVEALED) {
				board[v - 1][h].setAsRevealed();
			}
			if (board[v - 1][h - 1].getState() == CellState.NUMBER
					&& board[v - 1][h - 1].getChoosableState() != Choosable.REVEALED) {
				board[v - 1][h - 1].setAsRevealed();
			}
		}
		// Bottom left corner
		if (v == board.length - 1 && h == 0) {

			if (board[v][h + 1].getState() == CellState.NUMBER
					&& board[v][h + 1].getChoosableState() != Choosable.REVEALED) {
				board[v][h + 1].setAsRevealed();
			}
			if (board[v - 1][h].getState() == CellState.NUMBER
					&& board[v - 1][h].getChoosableState() != Choosable.REVEALED) {
				board[v - 1][h].setAsRevealed();
			}
			if (board[v - 1][h + 1].getState() == CellState.NUMBER
					&& board[v - 1][h + 1].getChoosableState() != Choosable.REVEALED) {
				board[v - 1][h + 1].setAsRevealed();
			}
		}
		// Top row
		if (v == 0 && h > 0 && h < board[v].length - 1) {
			for (int y = 0; y <= 1; y++) {
				for (int x = (h - 1); x <= (h + 1); x++) {
					if (board[y][x].getState() == CellState.NUMBER
							&& board[y][x].getChoosableState() != Choosable.REVEALED) {
						board[y][x].setAsRevealed();
					}
				}

			}

		}
		// Bottom row
		if (v == board.length - 1 && h > 0 && h < board[v].length - 1) {
			for (int y = board.length - 2; y < board.length; y++) {
				for (int x = (h - 1); x <= (h + 1); x++) {
					if (board[y][x].getState() == CellState.NUMBER
							&& board[y][x].getChoosableState() != Choosable.REVEALED) {
						board[y][x].setAsRevealed();
					}
				}

			}

		}
		// Leftmost row
		if (v < board.length - 1 && v > 0 && h == 0) {
			for (int y = v - 1; y <= v + 1; y++) {
				for (int x = 0; x <= 1; x++) {
					if (board[y][x].getState() == CellState.NUMBER
							&& board[y][x].getChoosableState() != Choosable.REVEALED) {
						board[y][x].setAsRevealed();
					}
				}

			}

		}
		// Rightmost row
		if (v < board.length - 1 && v > 0 && h == board[v].length - 1) {
			for (int y = v - 1; y <= v + 1; y++) {
				for (int x = board[v].length - 2; x < board[v].length; x++) {
					if (board[y][x].getState() == CellState.NUMBER
							&& board[y][x].getChoosableState() != Choosable.REVEALED) {
						board[y][x].setAsRevealed();
					}
				}

			}

		}
		board[v][h].setAsRevealed();
	}

}
