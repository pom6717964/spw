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
	private ArrayList<Path> paths = new ArrayList<Path>();
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();	

	private SpaceShip v;	
	
	private Timer timer;
	//private HpSpace hpspace;
	//private long score = 0;
	//private boolean stop = false;
	//private int time = 0;
	private double difficulty = 0.2;
	private long score = 1;
	private int movePath = 0;
	public GameEngine(GamePanel gp, SpaceShip v) {
		this.gp = gp;
		this.v = v;		
		
		gp.sprites.add(v);
		

		timer = new Timer(45, new ActionListener() {
			
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
		Enemy e = new Enemy((int)(Math.random()*1800), 50);
		gp.sprites.add(e);
		enemies.add(e);
	}
	
	private void process(){
		generatePath();
		if(Math.random() < difficulty){
			generateEnemy();
		}
		
		Iterator<Enemy> e_iter = enemies.iterator();
		Iterator<Path> p_iter = paths.iterator();

		while(e_iter.hasNext()){
			Enemy e = e_iter.next();
			e.proceed();
			
			if(!e.isAlive()){
				e_iter.remove();
				gp.sprites.remove(e);
			}
		}
		
		while(p_iter.hasNext()){
			Path p = p_iter.next();
			p.proceed();
			if(!p.isAlive()){
				p_iter.remove();
				gp.sprites.remove(p);
			}
		}


		gp.updateGameUI(this);
		
		Rectangle2D.Double vr = v.getRectangle();
		Rectangle2D.Double er;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				gp.sprites.remove(e);
				score +=10;
			}
		}

		Rectangle2D.Double pr;
		for(Path p : paths){
			pr = p.getRectangle();
			if(pr.intersects(vr)){
				score -=1;											
			}
		}
	}
	
	public void die(){
		timer.stop();
	}

	private void generatePath(){
		Path u = new Path(movePath(),2); 
		gp.sprites.add(u);
		paths.add(u);			
		
		Path d = new Path(movePath()+300,20); 
		gp.sprites.add(d);
		paths.add(d);			
	}
	
	private int movePath(){
		int n = (int)(Math.random()*2);
		if(n == 0 && movePath < 800)
			movePath += 2;
		if(n == 1 && movePath > 0)
			movePath -= 2;
		if(movePath > 400)
			movePath -= 2;
		if(movePath < 0)
			movePath += 2;
		return movePath;
	}



	void controlVehicle(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			v.LeftRight(-1);						
			break;
		case KeyEvent.VK_RIGHT:
			v.LeftRight(1);												
			break;
		case KeyEvent.VK_D:
			difficulty += 0.1;
			break;
		 case KeyEvent.VK_UP:
		 	v.UpDown(-1);
		 	break;						
		 case KeyEvent.VK_DOWN:
		 	 v.UpDown(1);						
		 	break;
		case KeyEvent.VK_Z:
		 	die();	
		 	break;					
		 case KeyEvent.VK_X:
		 	start();	
		 	break;						
		 case KeyEvent.VK_R:
		 	score = 0;					
		 	break; 
		}
	}

	public long getScore(){
		return score;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		controlVehicle(e);

	}

	@Override
	public void keyReleased(KeyEvent e) {
		/*if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("Right key Released");
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            System.out.println("Left key Released");
        }*/
	}

	@Override
	public void keyTyped(KeyEvent e) {
		//do nothing		
	}
}
