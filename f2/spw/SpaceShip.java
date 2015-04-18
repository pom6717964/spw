package f2.spw;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Iterator;
import java.awt.Color;
import javax.swing.Timer;

public class SpaceShip extends Sprite{

	private int step = 20;
	//private int hp = 100;
	//private boolean die = false;
	BufferedImage rocket1;

	public SpaceShip(int x, int y, int width, int height) {
		super(x, y, 50, 70);
		try{
				rocket1 = ImageIO.read(new File("f2/pict/rocket1.png"));
			}
			catch(IOException d){

			}
	}

	@Override
	public void draw(Graphics2D g) {
		g.drawImage(rocket1,x,y,width,height,null);
		/*g.setColor(Color.GREEN);
		g.fillOval(x, y, width, height);*/
		
	}
	
	/*public int getHp(){
		return this.hp;
	}*/

	public void LeftRight(int direction){
        x += (step * direction);
		if(x < 0)
			x = 0;
		if(x > 1100 - width)
			x = 1100 - width;
	}

	public void UpDown(int direction){
		y += (step*direction);
		if(y < 0)
			y = 0;
		if(y > 900 - height)
			y = 900 - height;
	}

	/*public void die(){
        this.die = true;
    }

    public boolean getDie(){
        return this.die;
    }*/


}
