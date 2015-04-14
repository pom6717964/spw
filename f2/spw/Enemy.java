package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy extends Sprite{
	public static final int Y_TO_FADE = 500; 
	public static final int Y_TO_DIE = 650;
	
	private int step = 12;   // num 
	private boolean alive = true;
	
	public Enemy(int x, int y) {  // shoot
		super(x, y, 3, 23);
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}
		g.setColor(Color.YELLOW);
		g.fillRect(x, y, width, height);
		
	}

	public void proceed(){
		y += step;
		/*x += step * Math.cos(-1*direction);
        y += step * Math.sin(-1*direction);*/
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}