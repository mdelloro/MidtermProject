package midterm;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Grid {

	public static void main(String[] args) {
		HiddenMinefield hiddenBoard = new HiddenMinefield(5,5,5);
		
		JFrame frame = new JFrame("Bombermen Minesweeper Game");
		frame.setVisible(true);
		frame.setSize(600,600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Welcome to the Bombermen Minesweeper Game!");
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.add(label);
		
		JButton button = new JButton("Play Minesweeper ");
		panel.add(button);
		panel.add(button);
	}

}
