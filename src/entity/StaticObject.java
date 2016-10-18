package entity;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class StaticObject extends JLabel{
	public static final ImageIcon WALL = new ImageIcon(new ImageIcon("src/img/wall.gif").getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
	public static final ImageIcon GRASS = new ImageIcon(new ImageIcon("src/img/grass.png").getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
	public static final ImageIcon WATER = new ImageIcon(new ImageIcon("src/img/water.gif").getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
	public static final ImageIcon IRON = new ImageIcon(new ImageIcon("src/img/iron.gif").getImage().getScaledInstance(25, 25,Image.SCALE_DEFAULT));
	public static final ImageIcon SYMBOL = new ImageIcon(new ImageIcon("src/img/symbol.gif").getImage().getScaledInstance(50, 50,Image.SCALE_DEFAULT));
	private String type ;
	public  String shot ;
	public StaticObject(){
		
	}
	public StaticObject(int i,int j){
		this.setBounds(j*25, i*25, 25, 25);	
		this.setVisible(true);
		this.type = "";
	}
	public StaticObject(String str,int i,int j){
		this(i,j);
		this.type = str;
	}
	public StaticObject(ImageIcon icon,String str,int i,int j){
		this(str,i,j);
		this.setIcon(icon);
	}
	public void setType(String str){
		this.type = str;
	}
	public String getType(){
		return type;
	}
}
