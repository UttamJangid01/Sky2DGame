package Designing;

import java.awt.Font;
import java.awt.Graphics2D;

import main.GamePanel;

public class Score {
	
	GamePanel gp;
	
	public Score(GamePanel gp) {
		this.gp = gp;
	}
	
	public void draw(Graphics2D g2) {
		g2.setFont(new Font("Arile", Font.BOLD, 20));
		g2.drawString("Score : "+gp.count, 10, 20);
	}
}
