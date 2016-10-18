package entity;

import java.awt.Image;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Shotre extends JLabel implements Runnable{
	public static final ImageIcon SHOT = new ImageIcon(new ImageIcon("src/img/tankmissile.gif").getImage().getScaledInstance(10, 10,Image.SCALE_DEFAULT));
	public static final ImageIcon BLAST1 = new ImageIcon(new ImageIcon("src/img/blast1.gif").getImage().getScaledInstance(10, 10,Image.SCALE_DEFAULT));
	public static final ImageIcon BLAST2 = new ImageIcon(new ImageIcon("src/img/blast3.gif").getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT));
	public static final ImageIcon BLAST3 = new ImageIcon(new ImageIcon("src/img/blast5.gif").getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT));
	public static final ImageIcon BLAST4 = new ImageIcon(new ImageIcon("src/img/blast7.gif").getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT));
	private int rand = 0;
	private Levelre level = null;
	private String type;
	
	public Shotre(int rand,Point p,Levelre level,String type){
		this.type = type+"shot";
		this.rand = rand;
		this.level = level;
		this.setVisible(true);
		this.setIcon(SHOT);
		switch(rand){  //((type.contains("tank"))?50:49)
		case 1:
			this.setBounds(p.x+20, p.y+49, 10, 10);
			break;
		case 2:
			this.setBounds(p.x+20, p.y, 10, 10);
			break;
		case 3:
			this.setBounds(p.x, p.y+20, 10, 10);
			break;
		case 4:
			this.setBounds(p.x+49, p.y+20, 10, 10);
			break;
		}
		level.add(this);
		new Thread(this).start();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Point p = this.getLocation();
		int py = 0;
		int px = 0;
		switch(rand){
		case 1:
			px = 0;
			py = 1;
			break;
		case 2:
			px = 0;
			py = -1;
			break;
		case 3:
			px = -1;
			py = 0;
			break;
		case 4:
			px = 1;
			py = 0;
			break;
		}
		while(true){
			try{
				if(!queryMap(p.y/25+py,p.x/25+px)||!queryMap(p.y/25+((py==0)?1:py),p.x/25+((px==0)?1:px))){
					return ;
				}
//				setMap(p.y/25,p.x/25,true);
//				setMap(p.y/25+((py==0)?0:(py>0)?1:-1),p.x/25+((px==0)?0:(px>0)?1:-1),false);
				for(int i = 0;i < 5; i++){
					p.x += (px==0)?0:(px>0)?5:-5;
					p.y += (py==0)?0:(py>0)?5:-5;
					this.setLocation(p);
					Thread.sleep(20);
				}
			} catch (InterruptedException e){
				e.printStackTrace();
			}
		}
		
	}
	public void setMap(int i,int j,boolean b){
		StaticObject so = level.getMap(i,j);
		so.shot = b?"":type;
	}
	private boolean queryMap(int i,int j){
		String str = level.queryMap(i, j);
		StaticObject so = level.getMap(i,j);
		Point p = this.getLocation();
		if(str.equals("")||str.equals("grass")||str.equals("water")||str.equals(type)){
			return true;
		}
		if(str.equals("symbol")){
			level.gg();
			return false;
		}
		if(str.equals("wall")){
			destory();
			try {
				so.setIcon(BLAST1);
				Thread.sleep(30);
				so.setIcon(BLAST3);
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			so.setIcon(null);
			so.setType("");
		}
		if(str.equals("iron")||str.equals("border")){
			destory();
		}
		if(str.contains("tank")&&type.contains("player")){
			destory();
			level.tankDie(str.substring(4));
		}
		if(str.contains("player")&&type.contains("tank")){
			destory();
			level.playerDie();
		}
		if(str.contains("shot")){
			setMap(p.y/25,p.x/25,true);
		}
//		Thread.sleep(10);
		this.setVisible(false);
		this.level.remove(this);
		return false;
	}
	private void destory(){
		Point p = this.getLocation();
		try {
			this.setIcon(BLAST1);
			this.setBounds(p.x, p.y, 10, 10);
			Thread.sleep(30);
			this.setIcon(BLAST3);
			this.setBounds(p.x, p.y, 30, 30);
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
