package f2.spw;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;

public class Path extends Sprite{
	public static final int Y_TO_FADE = 1100;
	public static final int Y_TO_DIE = 900;
	
	private int step = 100;
	private boolean alive = true;
	
	public Path(int x, int y) {
		super(x, y, 350, 450); 																		//5,10
		
	}

	@Override
	public void draw(Graphics2D g) {
		if(y < Y_TO_FADE)
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
		else{
			g.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 
					(float)(Y_TO_DIE - y)/(Y_TO_DIE - Y_TO_FADE)));
		}

		g.setColor(Color.green);													//red
	
		g.drawImage(null, null, 0, 0);
		
	}
		

	public void proceed(){
		y += step;
		if(y > Y_TO_DIE){
			alive = false;
		}
	}
	
	public boolean isAlive(){
		return alive;
	}
}