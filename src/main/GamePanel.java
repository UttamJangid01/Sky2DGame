package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.reflect.Array;

import javax.swing.JPanel;

import Designing.Blocks;
import Designing.Blocks.Brick;
import Designing.Player;
import Designing.Score;

public class GamePanel extends JPanel implements Runnable{
	
	public final int bordWidth = 400;
	public final int bordHeight = 650;
	final int FPS = 120;
	public int count = 0;
	public double gravity;
	public double velocityY;
	public boolean jump = false;

	
	Thread gameThread;
	
	// Objects
	public Player player = new Player(this);
	Blocks block = new Blocks(this);
	public KeyHandling keyH = new KeyHandling(this);
	Score score = new Score(this);
	
	GamePanel(){
		this.setPreferredSize(new Dimension(bordWidth, bordHeight));
//		this.setBackground(Color.black);
		this.addKeyListener(keyH);
		this.setFocusable(true);
		gravity = 0.19;
		velocityY = 0;
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void run() {
		double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        while(gameThread != null){
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1) {
            	block.screen();            		
            	Gravity();
            	update();
            	gameOver();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS : "+drawCount);
                drawCount = 0;
                timer = 0;
            }
        }
	}
	
	public void Gravity() {
		int space = 26;
		int temp=0;
		int f=0;
		for(int i=0; i<block.array.size(); i++) {
			Brick brick = block.array.get(i);
			
			if((((player.x >= brick.x+temp && player.x <= (brick.x+brick.width)-temp) || (player.x+player.width >= brick.x+temp && player.x+player.width <= (brick.x+brick.width)-temp))
			&& ((player.y+player.height >= brick.y+space && player.y+player.height <= brick.y+brick.height)))) {
				f=1;
				jump = true;	// we cannot jump in the sky
				player.y = (brick.y+space)-player.height;
				if(brick.score) {
					count++;
					brick.score = false;
				}
				break;
			}
			if((player.x+player.width >= brick.x && player.x+player.width <= brick.x+temp) && ((player.y >= brick.y+space && player.y <= brick.y+brick.height) 
			|| (player.y+player.height >= brick.y+space && player.y+player.height <= brick.y+brick.height))) {
				player.x = brick.x-player.width;
			}
			if((player.x >= (brick.x+brick.width)-temp && player.x <= brick.x+brick.width) && ((player.y >= brick.y+space && player.y <= brick.y+brick.height) 
			|| (player.y+player.height >= brick.y+space && player.y+player.height <= brick.y+brick.height))) {
				player.x = brick.x+brick.width;
			}
			if(player.y <= bordHeight/2 && !jump && (((player.x >= brick.x && player.x <= brick.x+brick.width) 
			|| (player.x+player.width >= brick.x && player.x+player.width <= brick.x+brick.width)) && ((player.y >= brick.y+space && player.y <= brick.y+brick.height)))) {
				player.y = brick.y+brick.height;
				velocityY = 1.9;
			}
			else if((((player.x >= brick.x && player.x <= brick.x+brick.width) || (player.x+player.width >= brick.x && player.x+player.width <= brick.x+brick.width))
			&& ((player.y >= brick.y+space && player.y <= brick.y+brick.height)))) {
				player.y = brick.y+brick.height;
				velocityY = 1;// 0.85
			}
		}
		if(f == 0) {
			velocityY += gravity;
			player.y += velocityY;
			jump = false;
		}else {
			velocityY = 0;	// because the player goes down side the velocityY = 0, other wise player moves the down side normal than fast;
		}
	}
	
	public void update() {
		player.update();
	}
	
	public void gameOver() {
		if(player.y > bordHeight) {
			gameThread = null;
		}
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		player.draw(g2);
		block.draw(g2);
		score.draw(g2);
		if(gameThread == null) {
			g2.setFont(new Font("Arile", Font.BOLD, 40));
			g2.setColor(Color.red);
			g2.drawString("Game Over", (bordWidth/2)-110, (bordHeight/2));
		}
//		g2.setColor(Color.red);
//		g2.drawLine(0, bordHeight/2, bordWidth, bordHeight/2);
	}
}
