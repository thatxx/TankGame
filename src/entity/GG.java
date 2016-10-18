package entity;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
class GGText extends JLabel implements Runnable{
	public GGText(int kill){
		this.setVisible(true);
		this.setForeground(Color.ORANGE);
		this.setFont(new Font("Dialog", 1, 30));
		this.setText("Game Over Score : "+kill);
		new Thread(this).start(); 
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(int i = 0; i < 50; i++){
			this.setBounds(250, 800-i*10, 400, 200);
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		while(true){
			
		}
	}
	
}
public class GG extends JPanel{
	
	private JLabel gameover = new JLabel();
	private int kill = 0;
	public GG(int kill){
		this.kill = kill;
		this.setLayout(null);
		initText();
		this.add(new GGText(kill));
		this.setBounds(0, 0, 850, 850);
		this.setBackground(Color.black);
		this.setVisible(true);
		
		
	}
	private void initText(){
		gameover.setBounds(250, 300, 400, 200);
		gameover.setVisible(true);
		gameover.setForeground(Color.ORANGE);
		gameover.setFont(new Font("Dialog", 1, 30));
		gameover.setText("Game Over Score : "+kill);
		
	}
}
