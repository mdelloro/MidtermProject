package midterm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Grid implements ActionListener{
	
	JFrame frame = new JFrame("The Bombermen Minesweeper Game");
	JButton reset = new JButton("Play Again");
	JButton[][] buttons = new JButton[20][20];
	int [][] counts = new int[20][20];
	Container grid = new Container();
	
	public Grid(){
	frame.setSize(450,550);
	frame.setLayout(new BorderLayout());
	frame.add(reset, BorderLayout.NORTH);
	reset.addActionListener(this);
	grid.setLayout(new GridLayout(20,20));
	for(int a = 0; a < buttons.length; a++){
		for(int b = 0; b < buttons.length; b++){
			buttons[a][b] = new JButton();
			buttons[a][b].addActionListener(this); 
			grid.add(buttons[a][b]);
			
		}
	}
	frame.add(grid, BorderLayout.CENTER);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	
	
	}
	public void actionPerformed(){
		
		
//		if (hiddenBoard.board[v][h].getState()==CellState.BLANK){
//			grid[v+1][h+1].setEnabled(false);
//			hiddenBoard.board[v][h].setAsJustRevealed();
//			grid[v+1][h+1].setText(" ");
//			hiddenBoard.checkForNearbyBlanks(v, h);
//		}
//		if (hiddenBoard.board[v][h].getState()==CellState.NUMBER){
//			grid[v+1][h+1].setEnabled(false);
//			grid[v+1][h+1].setText("1");
//			hiddenBoard.board[v][h].setAsRevealed();
//		}

		
		
	}
	
	public static void main(String[] args) {
		HiddenMinefield hidden = new HiddenMinefield(5,5,1);
		new Grid();
		
				

	}
	@Override
	public void actionPerformed(ActionEvent e) {
		JButton button = (JButton) e.getSource();
		button.setText("!");
		button.setEnabled(false);
		
		
	}

}
