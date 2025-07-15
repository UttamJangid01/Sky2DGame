package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandling implements KeyListener{

	GamePanel gp;
	public boolean upPressed, leftPressed, rightPressed;
	
	public KeyHandling(GamePanel gp) {
		this.gp = gp;
		upPressed = false;
		leftPressed = false;
		rightPressed = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP && gp.jump) {
			upPressed = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			gp.player.index = 0;
			leftPressed = true;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			gp.player.index = 1;
			rightPressed = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_LEFT) {
			leftPressed = false;
		}
		else if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
			rightPressed = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e) {}
}
