package midterm;

import java.util.Random;

public class HiddenMinefield {
	int hiddenBoardSizeX = 0;
	int hiddenBoardSizeY = 0;
	char[][] board;
	private int noOfMines = 0;
	Random rdm = new Random();

	public HiddenMinefield(int x, int y, int noOfMines) {
		board = new char[y][x];
		hiddenBoardSizeX = x;
		hiddenBoardSizeY = y;
		this.noOfMines = noOfMines;
	}

	public void placeMines() {
		int mineAmount = noOfMines;
		int x = 0;
		int y = 0;
		while (mineAmount != 0) {
			x = rdm.nextInt(hiddenBoardSizeX);
			y = rdm.nextInt(hiddenBoardSizeY);
			if (checkCell(y, x) == false) {
				board[y][x] = '*';
				mineAmount--;
			} else {
				continue;
			}

		}
		int mines = 0;
		for (int yy = 0; yy < board.length; yy++) {
			for (int xx = 0; xx < board[yy].length; xx++) {
				if (board[yy][xx] == '*') {
					mines++;
				}
			}
		}
		System.out.println(mines);
	}

	public void generateInitialBoard() {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				board[y][x] = '=';
			}
		}

	}

	public void setNumbers() {
		for (int yy = 0; yy < board.length; yy++) {
			for (int xx = 0; xx < board[yy].length; xx++) {
				if (board[yy][xx] != '*') {
					board[yy][xx] = (char) (checkSurrounding(xx, yy) + 48);
				}
			}
		}

	}

	public void displayBoard() {
		for (int y = 0; y < board.length; y++) {
			for (int x = 0; x < board[y].length; x++) {
				System.out.print(board[y][x]);
				System.out.print(' ');
			}
			System.out.println();
		}
	}

	public int checkSurrounding(int x, int y) {
		int number = 0;
		if (y - 1 >= 0 && y+1<=board.length) {
			for (int yy = y - 1; yy <= y + 1; yy++) {
				if (x - 1 >= 0 && x + 1 < board[y].length) {
					for (int xx = x - 1; xx <= x + 1; xx++) {
						if (board[y][x] == '*') {
							number++;
						}
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
