package midterm;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Grid extends Cell {

	public Grid(int v, int h) {
		super(v, h);
		
	}
	public static void main(String[] args) {
		HiddenMinefield hiddenBoard = new HiddenMinefield(5,5,5);
		
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
			public void actionPerformed (ActionEvent e){
				
				JFrame frame2 = new JFrame("Bombermen Minesweeper Game");
				frame2.setVisible(true);
				frame2.setSize(600,600);
				frame2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
				JPanel panel = new JPanel(new GridBagLayout());
				frame2.getContentPane().add(panel,BorderLayout.NORTH);
				GridBagConstraints c = new GridBagConstraints();
				JLabel label01 = new JLabel("Welcome ");
				c.gridx = 0;
				c.gridy = 1;
				c.insets = new Insets(10,10,10,10);
				panel.add(label01,c);
				JLabel label02 = new JLabel("To the");
				c.gridx = 0;
				c.gridy = 2;
				c.insets = new Insets(10,10,10,10);
				panel.add(label02,c);
				JLabel label03 = new JLabel("Bombermen");
				c.gridx = 0;
				c.gridy = 3;
				c.insets = new Insets(10,10,10,10);
				panel.add(label03,c);
				JLabel label04 = new JLabel("MineSweeper Game!");
				c.gridx = 0;
				c.gridy = 4;
				c.insets = new Insets(10,10,10,10);
				panel.add(label04,c);
				
				
				}
			}

		}
	
