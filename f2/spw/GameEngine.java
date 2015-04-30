package f2.spw;

import java.io.*;
import javax.sound.sampled.*;

import java.io.File;
import javax.sound.sampled.*;
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
	private ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	private SpaceShip v;	
	private HP hp;
	private Timer timer;
	private double difficulty = 0.2;
	private long score = 0;
	private int movePath = 0;
	private boolean stop = false;
	private int t = 0;

	public GameEngine(GamePanel gp, SpaceShip v) {
		this.hp = new HP(10,30,v.getHp());
		this.gp = gp;
		this.v = v;		
		gp.sprites.add(v);
		gp.sprites.add(this.hp);

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

	private void generateBullet(){

		Bullet b = new Bullet(v.moveX(),v.moveY()); 			
		gp.sprites.add(b);
		bullets.add(b);

	}
	
	private void process(){
		generatePath();
		if(Math.random() < difficulty){
			generateEnemy();
		}
		Iterator<Bullet> b_iter = bullets.iterator();
		Iterator<Enemy> e_iter = enemies.iterator();
		Iterator<Path> p_iter = paths.iterator();

		while(b_iter.hasNext()){
			Bullet b = b_iter.next();
			b.proceed();
			
			if(!b.isAlive()){
				b_iter.remove();
				gp.sprites.remove(b);
				
			}
		}

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
		Rectangle2D.Double br;
		for(Enemy e : enemies){
			er = e.getRectangle();
			if(er.intersects(vr)){
				v.reduceHP(1);
				hp.procreed();
				
				gp.sprites.remove(e);
				if(v.getHp()<= 0){
					v.die();
					die();

				}
				
			}
			for(Bullet b : bullets){
				br = b.getRectangle();
				if(br.intersects(er)){
					gp.sprites.remove(e);
					score += 10;
				}
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

	// public void effectSound(){
	// 	try {
	// 		File yourFile = new File("f2/sound/gunshot.wav");
	// 		AudioInputStream stream;
	// 		AudioFormat format;
	// 		DataLine.Info info;
	// 		Clip clip;

	// 		stream = AudioSystem.getAudioInputStream(yourFile);
	// 		format = stream.getFormat();
	// 		info = new DataLine.Info(Clip.class, format);
	// 		clip = (Clip) AudioSystem.getLine(info);
	// 		clip.open(stream);
	// 		clip.start();
	// 	}
	// 	catch (Exception e) {
 //    //whatevers
	// 	}
	// }

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
				case KeyEvent.VK_Q:
				generateBullet();	
				break;
			}
		}

		public void toggle(){
			this.t++;
			System.out.println(this.t);
			if(this.t % 2 ==1)
				this.stop = true;
			else 
				this.stop = false;   
		}

		public long getScore(){
			return score;
		}

		public int showHP(){
			return v.getHp();
		}

		@Override
		public void keyPressed(KeyEvent e) {
			controlVehicle(e);

		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {
		//do nothing		
		}
	}
