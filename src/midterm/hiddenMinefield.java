package midterm;

public class hiddenMinefield {
	private int[] hiddenBoardSize = {0,0};
	private int noOfMines = 0;
	
	public void setBoardSize(int x, int y){
		hiddenBoardSize[0] = x;
		hiddenBoardSize[1] = y;
	}
	
	public void placeMines(int mineAmount){
		
		
	}
	
	public int checkSurrounding(int x, int y){
		
		return 0;
	}
	
	public boolean checkCell(int x, int y){
		
		return true;
	}

}
