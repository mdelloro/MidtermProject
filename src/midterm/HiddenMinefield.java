package midterm;

import java.util.Random;

public class HiddenMinefield {
	int hiddenBoardSizeV = 0;
	int hiddenBoardSizeH = 0;
	char[][] board;
	private int noOfMines = 0;
	Random rdm = new Random();

	public HiddenMinefield(int h, int v, int noOfMines) {
		board = new char[h][v];
		hiddenBoardSizeH = h;
		hiddenBoardSizeV = v;
		this.noOfMines = noOfMines;
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
					board[vv][hh] = (char) (checkSurrounding(hh, vv) + 48);
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

	public int checkSurrounding(int h, int v) {
		int number = 9;
		int hh = h - 1;
		int vv = v - 1;
		// No borders
		if (vv >= 0 && (vv + 2) < board[v].length && hh >= 0 && (hh + 2) < board.length) {
			for (int y = vv; y <= (v + 1); y++) {
				for (int x = hh; x <= (h + 1); x++) {
					if (board[y][x] != '*') {
						number--;
					}
				}

			}

		}
		// Top left corner
		if (v == 0 && h == 0) {
			number = 0;
			if (board[v][h + 1] == '*') {
				number++;
			}
			if (board[v + 1][h] == '*') {
				number++;
			}
			if (board[v + 1][h + 1] == '*') {
				number++;
			}
		}
		// Top right corner
		if (v == 0 && h == board.length) {
			number = 3;
			if (board[v][h - 1] == '*') {
				number--;
			}
			if (board[v - 1][h] == '*') {
				number--;
			}
			if (board[v - 1][h - 1] == '*') {
				number--;
			}
		}
		// Bottom right corner
		if (v == board[v].length && h == board.length) {
			number = 3;
			if (board[v][h - 1] == '*') {
				number--;
			}
			if (board[v - 1][h] == '*') {
				number--;
			}
			if (board[v - 1][h - 1] == '*') {
				number--;
			}
		}
		// Bottom left corner
		if (v == board[v].length-1 && h == 0) {
			number = 0;
			if (board[v][h + 1] == '*') {
				number++;
			}
			if (board[v - 1][h] == '*') {
				number++;
			}
			if (board[v - 1][h + 1] == '*') {
				number++;
			}
		}
		// Top row
		if (v == 0 && h > 0 && h < board.length) {
			number = 4;
			for (int y = 0; y <= 1; y++) {
				for (int x = (h - 1); x < (h + 1); x++) {
					if (board[y][x] != '*') {
						number--;
					}
				}

			}

		}
		// Bottom row
		if (v == board[v].length - 1 && h > 0 && h < board.length) {
			number = 4;
			for (int y = board[v].length - 2; y < board[v].length; y++) {
				for (int x = (h - 1); x < (h + 1); x++) {
					if (board[y][x] != '*') {
						number--;
					}
				}

			}

		}
		// Leftmost row
		if (v < board[v].length - 1 && h == 0 && v > 0) {
			number = 6;
			for (int y = v - 1; y <= v + 1; y++) {
				for (int x = 0; x <= 1; x++) {
					if (board[y][x] != '*') {
						number--;
					}
				}

			}

		}
		// Leftmost row
		if (v < board[v].length - 1 && h == board.length-1 && v > 0) {
			number = 6;
			for (int y = v - 1; y <= v + 1; y++) {
				for (int x = board.length-2; x < board.length; x++) {
					if (board[y][x] != '*') {
						number--;
					}
				}

			}

		}
		return number;
	}

	public boolean checkCell(int y, int x) {
		if (board[y][x] == '*') {
			return true;
		}
		return false;
	}

}
