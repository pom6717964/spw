package f2.spw;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.Color;

public class SpaceShip extends Sprite{

	int step = 10;
	
	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, width, height);
		
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(Color.GREEN);
		g.fillOval(x, y, width, height);
		
	}

	public void move(int direction){
		
        x += (step * direction);
        //y += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 480 - width)
			x = 480 - width;
		/*if(y = -4)
			y = 0;
		if(y > 1000 - height)
			y = 1000 - height;*/
	}

}
