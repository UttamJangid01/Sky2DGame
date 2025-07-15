package Designing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;

public class Player {
	
	GamePanel gp;
	BufferedImage image[];
	public int index;
	public int x, y;
	public final int width = 60;
	public final int height = 70;
	
	public Player(GamePanel gp) {
		this.gp = gp;
		x = 110;
		y = 526;
//		y = 470;
		index = 1;
		image = new BufferedImage[2];
		getLoadImage();
	}
	
	public void getLoadImage() {
		try {
			image[0] = ImageIO.read(getClass().getResourceAsStream("/Images/among-us-left.png"));
			image[1] = ImageIO.read(getClass().getResourceAsStream("/Images/among-us-right.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update() {
		if(gp.keyH.upPressed && gp.jump) {
			gp.velocityY = -6;
			move();
		}
		else if(gp.keyH.leftPressed) {
			x -= 2;
		}
		else if(gp.keyH.rightPressed) {
			x += 2;
		}
	}
	
	public void move() {
		gp.velocityY += gp.gravity;
		y += gp.velocityY;
	}
	
	public void draw(Graphics2D g2) {
		g2.drawImage(image[index], x, y, width, height, null);
	}
}
