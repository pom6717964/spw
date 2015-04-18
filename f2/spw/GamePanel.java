package f2.spw;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(1100, 900, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(new Color(127,255,212));

	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 1100, 900);
		//big.setFont(new Font("default",Font.PLAIN,13));
		big.setColor(Color.BLACK);
		big.setFont(new Font("default",Font.BOLD,24));
		big.drawString(String.format("%08d",reporter.getScore()), 510, 40);
		/*if(reporter.getScore() < 100000)
			big.setColor(Color.BLACK);
		else if(reporter.getScore() > 100000 && reporter.getScore() < 500000)
			big.setColor(Color.GREEN);
		else
			big.setColor(Color.RED);
		big.setFont(new Font("default",Font.PLAIN,25));*/
		//big.setColor(Color.MAGENTA);
		
		for(Sprite s : sprites){
			s.draw(big);
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
