package midterm;

public class Cell {

		private CellState state;
		private Choosable isRevealed;
		private Flagged flag;
		int[] position = {0,0};
		
		public Cell(int v, int h){
			position[0] = v;
			position[1] = h;
			isRevealed = Choosable.NOT_REVEALED;
			flag = Flagged.NOT_FLAGGED;
		}
		
		public void setAsMine(int v, int h){
			state = CellState.MINE;
			
		}
		
		public void setAsNumber(int v, int h){
			state = CellState.NUMBER;
		}
		
		public void setAsBlank(int v, int h){
			state = CellState.BLANK;
		}
		
		public CellState getState(int v, int h){
			return state;
		}
		
		
		
		public void setFlag(){
			flag = Flagged.FLAGGED;
		}
		
		public Flagged getFlagState(int v, int h){
			return flag;
		}
		
		public void setAsRevealed(){
			isRevealed = Choosable.REVEALED;
		}
		
		public void setAsJustRevealed(){
			isRevealed = Choosable.JUST_REVEALED;
		}
		
		public Choosable getChoosableState(int v, int h){
			return isRevealed;
		}
}
