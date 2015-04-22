package f2.spw;
import java.awt.Color;
import java.awt.Graphics2D;
public class HP extends Sprite{
    public int hp; 
    public HP(int x,int y, int hp){
        super(x,y,hp,2);
        this.hp = hp;
    }
    @Override
    public void draw(Graphics2D g){
        g.setColor(new Color(139,0,139));
        //g.fillOval(x,y,this.hp,50)
        //g.fillOval(18,50,180,70);
    }
    public void procreed(){
        this.hp -= 1;
    }
}
