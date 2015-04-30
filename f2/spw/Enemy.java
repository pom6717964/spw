package f2.spw;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 700;  //start fade
	public static final int Y_TO_DIE = 850;   // die
	protected int step = 6;
	private boolean alive = true;
	protected long hp = 10;


	BufferedImage pig;	

	public Enemy(int x, int y){
		super(x, y, 25, 30); 																		//5,10
		try{
			pig = ImageIO.read(new File("f2/pict/pig.png"));
		}
		catch(IOException d){

		}
	}

	public boolean isAlive(){
		return alive;
	}

	public void reduceHP(int damage){
		this.hp=this.hp-damage;
	}

	public long getHP(){
		return this.hp;
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}

		g.drawImage(pig,x,y,width,height,null);
	}
		

	public void proceed(){
		//int a = (int)(Math.random()*4);
		y += step;

		if(y > Y_TO_DIE){
			alive = false;
		}
	}
}