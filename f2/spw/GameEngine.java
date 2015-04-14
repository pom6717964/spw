package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;


public class GameEngine implements KeyListener, GameReporter{
	GamePanel gp;
	
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	
	private SpaceShip v;	
	
	private Timer timer;
	
	private long score = 0;
	private double difficulty = 0.4;
	//private int x=100;
	//private int y=100;
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		
		timer = new Timer(60, new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				process();
			}
		});
		timer.setRepeats(true);
		
	}
	
	public void start(){
		timer.start();
	}
	
	private void generateEnemy(){
		Enemy e = new Enemy((int)(Math.random()*550), 15);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
				score += 1000;
			}
		}
		
		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				die();
				return;
			}
		}
	}
	
	public void die(){
		timer.stop();
	}
	
	void controlVehicle(KeyEvent e) {
		 
		switch (e.getKeyCode()) {
			/*case KeyEvent.VK_DOWN: y += 50; break;
            case KeyEvent.VK_UP: y -= 50; break;
            case KeyEvent.VK_LEFT: x -= 50; break;
            case KeyEvent.VK_RIGHT: x += 50; break;
         }*/
		case KeyEvent.VK_LEFT:
			v.move(-2);
			break;
		case KeyEvent.VK_RIGHT:
			v.move(2);
			break;
		/*case KeyEvent.VK_UP:
			v.move(-4);
			break;
		case KeyEvent.VK_DOWN:
			v.move(2);
			break;*/
		case KeyEvent.VK_D:
			difficulty += 0.2;
			break;
		}
	}

	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);

		 /*if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key pressed");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key pressed");
        }
        /*switch(e.getKeyCode()){
        case (KeyEvent.VK_UP):{
            if(direction!=Direction.SOUTH){
                direction = Direction.NORTH;
                wc.move(direction);
            }
            break;
        }
        case (KeyEvent.VK_DOWN):{
            if(direction!=Direction.NORTH){
                direction = Direction.SOUTH;
                wc.move(direction);
            }
            break;
        }
        case (KeyEvent.VK_RIGHT):{
            if(direction!=Direction.WEST){
                direction = Direction.EAST;
                wc.move(direction);
            }
            break;
        }
        case (KeyEvent.VK_LEFT):{
            if(direction!=Direction.EAST){
                direction = Direction.WEST;
                wc.move(direction);
            }
            break;
        }
       }*/

		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key Released");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key Released");
        }
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
