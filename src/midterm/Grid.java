package midterm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Grid {

	public static void main(String[] args) {
		HiddenMinefield hiddenBoard = new HiddenMinefield(5,5,5);
		Scanner scanner = new Scanner(System.in);
		SkillLevel skill = SkillLevel.BEGINNER;
		boolean skillquestion = true;
		String userprompt = "y";
		int x = 0;
		int y = 0;
		int mines = 0;
		int h = 0;
		int v = 0;
		char hchar = 0;
		char vchar = 0;

		JFrame frame = new JFrame("Bombermen Minesweeper Game");
		frame.setVisible(true);
		frame.setSize(350,150);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JLabel label = new JLabel("Welcome to the Bombermen Minesweeper Game!");
		JPanel panel = new JPanel();
		frame.add(panel);
		panel.add(label);
		JButton button = new JButton("Play Minesweeper ");
		panel.add(button);
		button.addActionListener(new Action());
}
		static class Action implements ActionListener{
			public void actionPerformed (ActionEvent e,Scanner scanner, HiddenMinefield hiddenBoard) {
				{
				
				JFrame frame2 = new JFrame("Selected Play");
				frame2.setVisible(true);
				frame2.setSize(600,600);
				JLabel label = new JLabel("Play");
				JPanel panel = new JPanel();
				frame2.add(panel);
				panel.add(horizontalCoordinate(scanner, hiddenBoard);
				);
				
				}
			}

		}
	
