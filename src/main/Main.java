package main;

import javax.swing.JFrame;
import main.GamePanel;

public class Main {
	public static void main(String args[]) {
		JFrame window = new JFrame();
		window.setSize(400, 650);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setResizable(false);
		
		GamePanel gp = new GamePanel();
		window.add(gp);
		window.pack();
		window.requestFocus();
		window.setVisible(true);
		gp.startGameThread();
	}
}
