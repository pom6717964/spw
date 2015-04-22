package f2.spw;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.Timer;
import java.awt.Point;
 
public abstract class AttackHP implements KeyListener, GameReporter{
 
	private int attack;
	private int HP;
	private int speed;
	private Point position = new Point();
	
	public AttackHP(int givenAttack, int givenHP){
		attack = givenAttack;
		HP = givenHP;
		speed = 20;
		position.setLocation(0, 0);
	}
	
	public AttackHP(int givenAttack, int givenHP, int xPos, int yPos){
		attack = givenAttack;
		HP = givenHP;
		speed = 20;
		position.setLocation(xPos, yPos);
	}
	
	public void fly(int x, int y) {
		position.setLocation(x,y);
	}
	
	public int getHealth(){
		return HP;
	}
	
	public void removeHealth(int attack){
		HP -= attack;
	}
 
	
	/*public void attack(SpaceShip target) {
		if(target.isDestroyed())
			System.out.println("That Ship is destroyed sir!!");
		else
			target.removeHealth(attack);
	}
	*/

	public boolean isDestroyed(){
		if(HP < 0)
			return true;
		else
			return false;
	}
 

	public abstract String getName();
	
}