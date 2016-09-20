package midterm;

import org.junit.Test;

import junit.framework.Assert;

public class MinesweeperTest {
	
	@Test
	public void testForABlank(){
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
	public void testForAMine(){
		HiddenMinefield test = new HiddenMinefield(10, 10, 99);
		Minefield test2 = new Minefield (11, 11);
		test.placeMines(4,4);
		test2.playerMove(5,5, test);
		Assert.assertEquals(CellState.MINE, test.board[5][5].getState());
	}
	@Test
	public void testForANumber(){
		HiddenMinefield test = new HiddenMinefield(10, 10, 99);
			test.board[2][2].setAsMine();
			test.board[2][3].setAsMine();
			test.board[2][4].setAsMine();
			test.setNumbers();
			Assert.assertEquals('2',test.board[1][2].stateDisplay);
			
		}
	@Test
	public void testForFlag(){
		HiddenMinefield test = new HiddenMinefield(10, 10, 20);
		Minefield test2 = new Minefield (11, 11);
		test.board[4][4].setFlag();
		Assert.assertEquals(true, test.board[4][4].isFlagged());
	}
	@Test
	public void testForUnflag(){
		HiddenMinefield test = new HiddenMinefield(10, 10, 20);
		Minefield test2 = new Minefield (11, 11);
		test.board[4][4].setFlag();
		test.board[4][4].unsetFlag();
		Assert.assertEquals(false, test.board[4][4].isFlagged());
		
	}
	@Test
	public void testForABigFieldMine(){
		HiddenMinefield test = new HiddenMinefield(100, 100, 9999);
		Minefield test2 = new Minefield (11, 11);
		test.placeMines(4,4);
		test2.playerMove(5,5, test);
		Assert.assertEquals(CellState.MINE, test.board[5][5].getState());
	}
}
	


