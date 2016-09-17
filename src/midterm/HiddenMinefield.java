package midterm;

import java.util.Random;

public class HiddenMinefield {
	int hiddenBoardSizeV = 0;
	int hiddenBoardSizeH = 0;
	char[][] board;
	private int noOfMines = 0;
	Random rdm = new Random();

	public HiddenMinefield(int v, int h, int noOfMines) {
		board = new char[v][h];
		hiddenBoardSizeH = h;
		hiddenBoardSizeV = v;
		this.noOfMines = noOfMines;
	}
	
	public boolean checkCell(int v, int h) {
		if (board[v][h] == '*') {
			return true;
		}
		return false;
	}

	public void placeMines() {
		int mineAmount = noOfMines;
		int h = 0;
		int v = 0;
		while (mineAmount != 0) {
			h = rdm.nextInt(hiddenBoardSizeH);
			v = rdm.nextInt(hiddenBoardSizeV);
			if (checkCell(v, h) == false) {
				board[v][h] = '*';
				mineAmount--;
			} else {
				continue;
			}

		}
		int mines = 0;
		for (int vv = 0; vv < board.length; vv++) {
			for (int hh = 0; hh < board[vv].length; hh++) {
				if (board[vv][hh] == '*') {
					mines++;
				}
			}
		}
		System.out.println(mines);
	}

	public void generateInitialBoard() {
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[v].length; h++) {
				board[v][h] = '=';
			}
		}

	}

	public void setNumbers() {
		for (int vv = 0; vv < board.length; vv++) {
			for (int hh = 0; hh < board[vv].length; hh++) {
				if (board[vv][hh] != '*') {
					board[vv][hh] = (char) (checkSurrounding(vv, hh) + 48);
					if (board[vv][hh] == '0') {
						board[vv][hh] = ' ';
					}
				}
			}
		}

	}

	public void displayBoard() {
		for (int v = 0; v < board.length; v++) {
			for (int h = 0; h < board[v].length; h++) {
				System.out.print(board[v][h]);
				System.out.print(' ');
			}
			System.out.println();
		}
	}

	public int checkSurrounding(int v, int h) {
		int number = 0;
		// No borders
		if (v-1 >= 0 && (v + 1) < board.length && h-1 >= 0 && (h+1) < board[v].length) {
			for (int y = v-1; y <= (v + 1); y++) {
				for (int x = h-1; x <= (h + 1); x++) {
					if (board[y][x] == '*') {
						number++;
					}
				}

			}

		}
		// Top left corner
		if (v == 0 && h == 0) {
			number = 3;
			if (board[v][h + 1] != '*') {
				number--;
			}
			if (board[v + 1][h] != '*') {
				number--;
			}
			if (board[v + 1][h + 1] != '*') {
				number--;
			}
		}
		// Top right corner
		if (v == 0 && h == board[v].length-1) {
			number = 3;
			if (board[v][h - 1] != '*') {
				number--;
			}
			if (board[v + 1][h] != '*') {
				number--;
			}
			if (board[v + 1][h - 1] != '*') {
				number--;
			}
		}
		// Bottom right corner
		if (v == board.length-1 && h == board[v].length-1) {
			number = 3;
			if (board[v][h - 1] != '*') {
				number--;
			}
			if (board[v - 1][h] != '*') {
				number--;
			}
			if (board[v - 1][h - 1] != '*') {
				number--;
			}
		}
		// Bottom left corner
		if (v == board.length-1 && h == 0) {
			number = 3;
			if (board[v][h + 1] != '*') {
				number--;
			}
			if (board[v - 1][h] != '*') {
				number--;
			}
			if (board[v - 1][h + 1] != '*') {
				number--;
			}
		}
		// Top row
		if (v == 0 && h > 0 && h < board[v].length-1) {
			number = 0;
			for (int y = 0; y <= 1; y++) {
				for (int x = (h - 1); x <= (h + 1); x++) {
					if (board[y][x] == '*') {
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
					if (board[y][x] == '*') {
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
					if (board[y][x] != '*') {
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
					if (board[y][x] == '*') {
						number++;
					}
				}

			}

		}
		return number;
	}

	

}
