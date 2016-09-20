package midterm;

import org.junit.Test;

import junit.framework.Assert;

public class MinesweeperTest {
	
	@Test
	public void testBlank(){
		HiddenMinefield test = new HiddenMinefield(10, 10, 15);
		test.setNumbers();
		Assert.assertEquals(CellState.BLANK, test.board[5][5].getState());
				
		}
	@Test
	public void testNotReaveled(){
		HiddenMinefield test = new HiddenMinefield(10, 10, 15);
		test.setNumbers();
		Assert.assertEquals(Choosable.NOT_REVEALED, test.board[5][5].getChoosableState());
	}
	@Test
	public void marksReaveled(){
		HiddenMinefield test = new HiddenMinefield(10, 10, 15);
		Minefield test2 = new Minefield (11, 11);
		test.setNumbers();
		test2.playerMove(5,5, test);
		Assert.assertEquals(Choosable.REVEALED, test.board[5][5].getChoosableState());
	}
	@Test
	public void checkMines(){
		HiddenMinefield test = new HiddenMinefield(10, 10, 99);
		Minefield test2 = new Minefield (11, 11);
		test.placeMines(4,4);
		test2.playerMove(5,5, test);
		Assert.assertEquals(CellState.MINE, test.board[5][5].getState());
	}
	}


