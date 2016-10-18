package entity;

import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class MainUI implements Runnable{
	public static JFrame jf = new JFrame();
	public JPanel jp = null;
	public MainUI(){
		jf.setLayout(null);
		jf.setBounds(50, 100, 850, 850);
		jf.setVisible(true);
	}
	public MainUI(Levelre level){
		this();
		this.jp = level;
		jf.add(level);
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		final Levelre level = new Levelre();
		MainUI mainui = new MainUI(level);
		
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			
		}
	}

}
