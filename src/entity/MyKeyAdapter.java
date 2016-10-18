package entity;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class MyKeyAdapter extends KeyAdapter{
	private int rand = -1;
	public int getrand(){
		return rand;
	}
	public void keyPressed(KeyEvent e){
		switch(e.getKeyCode()){
			case KeyEvent.VK_DOWN:
				rand = 1;
				break;
			case KeyEvent.VK_UP:
				rand = 2;
				break;
			case KeyEvent.VK_LEFT:
				rand = 3;
				break;
			case KeyEvent.VK_RIGHT:
				rand = 4;
				break;
		}
	}
}
