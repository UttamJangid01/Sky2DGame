package Designing;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import Designing.Blocks.Brick;
import main.GamePanel;

public class Blocks {
	
	GamePanel gp;
	BufferedImage image;
	Random r = new Random();
	final int maxJump;
	int prevNumber;
	
	public class Brick{
		public int x;
		public int y;
		public int width = 120;
		public int height = 45;
		public boolean score = true;
		Brick(int x, int y){
			this.x = x;
			this.y = y;
		}
	}
	
	public ArrayList<Brick> array;
	
	public Blocks(GamePanel gp){
		this.gp = gp;
		array = new ArrayList<Brick>();
		array.add(new Brick(90, 570));
		array.add(new Brick(250, 470));
		array.add(new Brick(40, 370));
		array.add(new Brick(150, 270));
		array.add(new Brick(15, 170));
		array.add(new Brick(265, 70));
		prevNumber = 265;
		maxJump = 130;
		getLoadImage();
	}
	
	public void getLoadImage() {
		try {
			image = ImageIO.read(getClass().getResourceAsStream("/Images/grass.png"));
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public void screen() {
		if(gp.player.y <= gp.bordHeight/2) {
			for(int i=0; i<array.size(); i++) {
				Brick brick = array.get(i);
				brick.y += 1;
				if(brick.y > gp.bordHeight) {
					array.remove(i);
				}
			}
			Brick brick = array.get(array.size()-1);
			if(brick.y-0 >= 100) {
				addBricks();
			}
		}
	}
	
	public void addBricks() {
		int x = r.nextInt(270);
		int y = 0;
		int temp = Math.abs(prevNumber - x);
		if(temp > maxJump || temp < 40) {
			addBricks();
		}else {
			array.add(new Brick(x, y));
			prevNumber = x;
		}
	}
	
	public void draw(Graphics2D g2) {
		for(int i=0; i<array.size(); i++) {
			Brick brick = array.get(i);
			g2.drawImage(image, brick.x, brick.y, brick.width, brick.height, null);
		}
	}
}
