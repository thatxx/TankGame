package entity;

import java.awt.Color;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Levelre extends JPanel implements Runnable{
	private StaticObject[][] map = new StaticObject[34][34];
	private int totalNum = 20;
	private Tankre[] tkarr = new Tankre[totalNum];
	private int aliveNum = 0;
	private int killNum = 0;
	private Tankre player ;
	private int playerlife = 1;
	private Thread th = new Thread(this);
	private boolean gg = true;
	public Levelre(){
		this.setLayout(null);
		this.setBounds(0, 0, 850, 850);
		this.setBackground(Color.black);
		initMap();
		this.setVisible(true);
//		setTank();
		player = new Tankre(Tankre.P1D,"player",30,12,this);
		this.add(player);
		th.start();
	}
	
	public String queryMap(int i,int j){
		return  map[i][j].getType();
	}
	public StaticObject getMap(int i,int j){
		return map[i][j];
	}
	
	private void initMap(){
		int rand1;						//1:wall 2:grass 3:water 4:iron
		int rand2; 						//2:vertical 1:horizontal
		//init border
		for(int i = 0; i < 34; i++){	
			map[0][i] = new StaticObject("border",0,i);
			map[1][i] = new StaticObject("border",1,i);
			map[32][i] = new StaticObject("border",32,i);
			map[33][i] = new StaticObject("border",33,i);
			map[i][32] = new StaticObject("border",i,32);
			map[i][33] = new StaticObject("border",i,33);
			map[i][1] = new StaticObject("border",i,1);
			map[i][0] = new StaticObject("border",i,0);
			this.add(map[0][i]);
			this.add(map[1][i]);
			this.add(map[32][i]);
			this.add(map[33][i]);
			this.add(map[i][32]);
			this.add(map[i][33]);
			this.add(map[i][1]);
			this.add(map[i][0]);
		}
		//init tankplace
		for(int i = 2; i < 4; i++){
			for(int j = 2; j < 4; j++){
				map[i][j] = new StaticObject(i,j);
				map[i][j+28] = new StaticObject(i,j);
			}
		}
		//init playerplace
		for(int i = 30; i < 32; i++){
			for(int j = 12; j < 14; j++){
				map[i][j] = new StaticObject(i,j);
				map[i][j+6] = new StaticObject(i,j);
			}
		}
		//init home
		for(int i = 14;i < 18; i++){
			map[29][i] = new StaticObject(StaticObject.WALL,"wall",29,i);
			this.add(map[29][i]);
		}
		for(int j = 30;j < 32; j++){
			map[j][14] = new StaticObject(StaticObject.WALL,"wall",j,14);
			this.add(map[j][14]);
			map[j][17] = new StaticObject(StaticObject.WALL,"wall",j,17);
			this.add(map[j][17]);
		}
		map[30][15] = new StaticObject(StaticObject.SYMBOL,"symbol",30,15);
		map[30][15].setBounds(15*25, 30*25, 50, 50);
		map[31][15] = new StaticObject("symbol",31,15);
		map[30][16] = new StaticObject("symbol",30,16);
		map[31][16] = new StaticObject("symbol",31,16);
		this.add(map[30][15]);
		this.add(map[31][15]);
		this.add(map[30][16]);
		this.add(map[31][16]);
		//init others
		for(int i = 2; i < 32; i++){	
			for(int j = 2; j < 32; j++){
				rand1 = (int)(Math.random()*50)+1;
				rand2 = (int)(Math.random()*2)+1;
				switch(rand1){
					case 1:
					case 2:
					case 3:
						buildmap(StaticObject.WALL,"wall",rand2,i,j);
						break;
					case 4:
						buildmap(StaticObject.GRASS,"grass",rand2,i,j);
						break;
					case 5:
						buildmap(StaticObject.WATER,"water",rand2,i,j);
						break;
					case 6:
						buildmap(StaticObject.IRON,"iron",rand2,i,j);
						break;
					default:
						if(map[i][j] != null){
							break;
						}
						map[i][j] = new StaticObject(i,j);
						this.add(map[i][j]);
						break;
				}
			}
		}
	}
	private void buildmap(ImageIcon icon,String str,int rand2,int i,int j){
		int k = 4;
		while(true){
			if(map[i][j] != null){
				break;
			}
			map[i][j] = new StaticObject(icon,str,i,j);
			this.add(map[i][j]);
			if(rand2 == 1){
//				if(i+1 < 31 && map[i+1][j] == null){
//					map[i+1][j] = new StaticObject(icon,str,i+1,j);
//					this.add(map[i+1][j]);
//				}
				j++;
			}else{
//				if(j+1 < 31 && map[i][j+1] == null){
//					map[i][j+1] = new StaticObject(icon,str,i,j+1);
//					this.add(map[i][j+1]);
//				}
				i++;
			}
			k--;
			if(k==0||i>30||j>30){
				break;
			}
		}
	}
	
	private void setTank(){
		this.add(new Tankre(Tankre.TANKD,"tank",2,2,this));
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
		while(totalNum>0){
			if(aliveNum<5){
				if(queryMap(2,2).equals("")&&queryMap(2,3).equals("")&&queryMap(3,2).equals("")&&queryMap(3,3).equals("")){
					tkarr[totalNum-1] = new Tankre(Tankre.TANKD,"tank"+(totalNum-1),2,2,this);
					this.add(tkarr[totalNum-1]);
					totalNum--;
					aliveNum++;
					
				}else if(queryMap(2,30).equals("")&&queryMap(2,31).equals("")&&queryMap(31,2).equals("")&&queryMap(30,2).equals("")){
					tkarr[totalNum-1] = new Tankre(Tankre.TANKD,"tank"+(totalNum-1),2,30,this);
					this.add(tkarr[totalNum-1]);
					totalNum--;
					aliveNum++;
				}
			}
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		gg();
	}
	public int getKillNum(){
		return killNum;
	}
	public void tankDie(String str){
		int i = Integer.parseInt(str);
		System.out.printf("tk[%d] : ",i);
		if(tkarr[i].getDie()){
			System.out.println("alive");
			tkarr[i].die();
			killNum++;
			aliveNum--;
		}
//		for(int i = 0; i < totalNum ; i++){
//			if(tkarr[i] == null){
//				continue;
//			}
//			System.out.println(tkarr[i].getType());
//			
//			if(tkarr[i].getType().contains(str)){
//				tkarr[i].die();
//			}
//		}
	}
	public void setTotalNum(int i){
		this.totalNum = i;
	}
	public void playerDie(){
		Point p = player.getLocation();
		player.setMap(p.y/25,p.x/25,true);
		player.setVisible(false);
		this.remove(player);
		playerlife--;
		if(playerlife==0){
			gg();
		}else{
			player = new Tankre(Tankre.P1D,"player",30,12,this);
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			this.add(player);
		}
	}
	public void gg(){
		if(!gg){
			return ;
		}
		gg = false;
		totalNum = 0;
		this.setVisible(false);
		MainUI.jf.remove(this);
		MainUI.jf.add(new GG(killNum));
		MainUI.jf.validate();
		MainUI.jf.repaint();
	}
	public Tankre getPlayer(){
		return player;
	}
}
