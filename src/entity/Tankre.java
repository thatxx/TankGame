package entity;

import java.awt.Image;
import java.awt.Point;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Tankre extends JLabel implements Runnable{
	public static final ImageIcon TANKD = new ImageIcon(new ImageIcon("src/img/enemy1D.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon TANKU = new ImageIcon(new ImageIcon("src/img/enemy1U.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon TANKL = new ImageIcon(new ImageIcon("src/img/enemy1L.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon TANKR = new ImageIcon(new ImageIcon("src/img/enemy1R.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon P1D = new ImageIcon(new ImageIcon("src/img/p1tankD.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon P1U = new ImageIcon(new ImageIcon("src/img/p1tankU.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon P1L = new ImageIcon(new ImageIcon("src/img/p1tankL.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon P1R = new ImageIcon(new ImageIcon("src/img/p1tankR.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));	
	public static final ImageIcon P2D = new ImageIcon(new ImageIcon("src/img/p2tankD.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon P2U = new ImageIcon(new ImageIcon("src/img/p2tankU.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon P2L = new ImageIcon(new ImageIcon("src/img/p2tankL.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon P2R = new ImageIcon(new ImageIcon("src/img/p2tankR.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon BORN1 = new ImageIcon(new ImageIcon("src/img/born1.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon BORN2 = new ImageIcon(new ImageIcon("src/img/born2.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon BORN3 = new ImageIcon(new ImageIcon("src/img/born3.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon BORN4 = new ImageIcon(new ImageIcon("src/img/born4.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	public static final ImageIcon BLAST1 = new ImageIcon(new ImageIcon("src/img/blast1.gif").getImage().getScaledInstance(10, 10,Image.SCALE_DEFAULT));
	public static final ImageIcon BLAST2 = new ImageIcon(new ImageIcon("src/img/blast3.gif").getImage().getScaledInstance(20, 20,Image.SCALE_DEFAULT));
	public static final ImageIcon BLAST3 = new ImageIcon(new ImageIcon("src/img/blast5.gif").getImage().getScaledInstance(30, 30,Image.SCALE_DEFAULT));
	public static final ImageIcon BLAST4 = new ImageIcon(new ImageIcon("src/img/blast7.gif").getImage().getScaledInstance(40, 40,Image.SCALE_DEFAULT));
	
	private ImageIcon typeD;
	private Levelre level;
	private String type;
	private boolean alive;
	public int way;
	private Thread th = new Thread(this);
	public void die(){
		alive = false;
	}
	public boolean getDie(){
		return alive;
	}
	public String getType(){
		return this.type;
	}
	public Tankre() {
	}
	public Tankre(int i, int j) {
		this.setBounds(j*25, i*25, 50, 50);	
		this.setVisible(true);
		this.type = "";
	}
	public Tankre(ImageIcon icon, String str, int i, int j, Levelre level) {
		this(i,j);
		this.setIcon(icon);
		this.level = level;
		this.alive = true;
		this.type = str;
		if(type.contains("player")){
			final Point p = this.getLocation();
			final Levelre l = this.level;
			final Tankre tk = this;
			MainUI.jf.addKeyListener(new KeyAdapter(){
				public void keyPressed(KeyEvent e){
					switch(e.getKeyCode()){
						case KeyEvent.VK_DOWN:	
							tk.way = 1;
							move(1,1,p);
							break;
						case KeyEvent.VK_UP:
							tk.way = 2;
							move(2,1,p);
							break;
						case KeyEvent.VK_LEFT:
							tk.way = 3;
							move(3,1,p);
							break;
						case KeyEvent.VK_RIGHT:
							tk.way = 4;
							move(4,1,p);
							break;
						case KeyEvent.VK_A:
							new Shotre(tk.way,p,l,type);
							break;
					}
				}
			});
		}
			th.start();
		
	}
		
		
	@Override
	public void run() {
		// TODO Auto-generated method stub
		born();
		while(true){
			if(type.contains("tank")){
				Point p = this.getLocation();
				int rand = (int)(Math.random()*4+1);
				int len = (int)(Math.random()*27+3);
				if(!move(rand,len,p)){
					return ;
				}	
			}
		}
	}
	
	public boolean move(int rand, int len, Point p) {
		int px = 0;
		int py = 0;
		
		switch(rand){
			case 1:
				px = 0;
				py = 2;
				this.setIcon((type.contains("tank"))?TANKD:P1D);
				break;
			case 2:
				px = 0;
				py = -1;
				this.setIcon((type.contains("tank"))?TANKU:P1U);
				break;
			case 3:
				px = -1;
				py = 0;
				this.setIcon((type.contains("tank"))?TANKL:P1L);
				break;
			case 4:
				px = 2;
				py = 0;
				this.setIcon((type.contains("tank"))?TANKR:P1R);
				break;
		}
		while(len-->0){
//			System.out.printf("p.x = %d , p.y = %d ,way:%d\n",p.y,p.x,rand);
			int flag;
			flag = (int)(Math.random()*10)+1;
			if(flag==2 && type.contains("tank")){
				new Shotre(rand,p,level,type);
			}
			if(!queryMap(p.y/25+py, p.x/25+px)||!queryMap(p.y/25+((py==0)?1:py), p.x/25+((px==0)?1:px))){
				break;
			}
			flag = (int)(Math.random()*10)+1;
			if(flag==2 && type.contains("tank")){
				new Shotre(rand,p,level,type);
			}
			if(!alive){
				System.out.println(type+"die");
				setMap(p.y/25,p.x/25,true);
				destory();
				this.setVisible(false);
				level.remove(this);
				return false;
			}
			setMap(p.y/25,p.x/25,true);
			setMap(p.y/25+((py==0)?0:(py>0)?1:-1),p.x/25+((px==0)?0:(px>0)?1:-1),false);
			for(int i = 0;i < 5; i++){
				p.x += (px==0)?0:(px>0)?5:-5;
				p.y += (py==0)?0:(py>0)?5:-5;
				this.setLocation(p);
				if(type.contains("tank")){
					try {
						Thread.sleep(50);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return true;
	}
	public void setMap(int i,int j,boolean b){
		StaticObject so = level.getMap(i,j);
		so.setType(b?"":type);
		so = level.getMap(i+1,j);
		so.setType(b?"":type);
		so = level.getMap(i,j+1);
		so.setType(b?"":type);
		so = level.getMap(i+1,j+1);
		so.setType(b?"":type);
	}
	private void born(){
		Icon icon = this.getIcon();
		String str = type;
		type = "creating";
		Point p = this.getLocation();
		setMap(p.y/25,p.x/25,false);
		try {
			for(int i=0;i<3;i++){
				this.setIcon(BORN1);
				Thread.sleep(100);
				this.setIcon(BORN2);
				Thread.sleep(100);
				this.setIcon(BORN3);
				Thread.sleep(100);
				this.setIcon(BORN4);
				Thread.sleep(100);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.setIcon(icon);
		setMap(p.y/25,p.x/25,true);
		type = str;
	}
	private boolean queryMap(int i,int j){
		String str = level.queryMap(i, j);
//		System.out.printf("map[%d][%d] = %s\n",i,j,str);
		if(str.equals("")||str.equals("grass")){
			return true;
		}else{
			return false;
		}
	}
	private void destory(){
		Point p = this.getLocation();
		try {
			this.setIcon(BLAST2);
			this.setBounds(p.x, p.y, 30, 30);
			Thread.sleep(30);
			this.setIcon(BLAST4);
			this.setBounds(p.x, p.y, 50, 50);
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
