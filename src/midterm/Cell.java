package midterm;

public class Cell {

		private CellState state;
		private Choosable isRevealed;
		private Flagged flag;
		int[] position = {0,0};
		char stateDisplay;
		
		public Cell(int v, int h){
			position[0] = v;
			position[1] = h;
			isRevealed = Choosable.NOT_REVEALED;
			flag = Flagged.NOT_FLAGGED;
		}
		
		public void setAsMine(){
			state = CellState.MINE;
			stateDisplay = '*';
			
		}
		
		public void setAsNumber(int i){
			state = CellState.NUMBER;
			stateDisplay = (char)(i+48);
		}
		
		public void setAsBlank(){
			state = CellState.BLANK;
			stateDisplay = ' ';
		}
		
		public CellState getState(){
			return state;
		}
		
		
		
		public void setFlag(){
			flag = Flagged.FLAGGED;
		}
		public void unsetFlag(){
			flag = Flagged.NOT_FLAGGED;
		}
		
		public boolean isFlagged(){
			if (flag == Flagged.FLAGGED){
				return true;
			}
			return false;
		}
		
		public void setAsRevealed(){
			isRevealed = Choosable.REVEALED;
		}
		
		public void setAsJustRevealed(){
			isRevealed = Choosable.JUST_REVEALED;
		}
		
		public Choosable getChoosableState(){
			return isRevealed;
		}
}
