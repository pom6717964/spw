package f2.spw;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Bullet extends Sprite{
	public static final int Y_TO_FADE = 1300;  //start fade
	public static final int Y_TO_DIE = 850;   // die
	protected int step = 11;
	private boolean alive = true;
	protected long hp = 10;



	public Bullet(int x, int y){
		super(x+19, y, 7, 30); 																		//5,10
		
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
		g.setColor(new Color(25,25,112));
		g.fillRect(x, y, width-2, height-4);

	}
		

	public void proceed(){
		
		y -= step;
		
		if(y > Y_TO_DIE){
			alive = false;
		}

		else if(x > Y_TO_FADE){
			alive = false;
		}
	
	}
}