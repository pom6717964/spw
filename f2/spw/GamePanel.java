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
import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class GamePanel extends JPanel {
	private BufferedImage bi;	
	Graphics2D big;
	ArrayList<Sprite> sprites = new ArrayList<Sprite>();

	public GamePanel() {
		bi = new BufferedImage(1100, 900, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
		big.setBackground(new Color(216,191,216));
	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 1100, 900);
		//big.setFont(new Font("default",Font.PLAIN,13));
		big.setColor(Color.BLACK);
		big.setFont(new Font("default",Font.BOLD,24));
		big.drawString(String.format("%08d",reporter.getScore()), 510, 40);
		
		big.setColor(new Color(0,0,205));
		
		big.setFont(new Font("default",Font.BOLD,30));
		
		big.drawString(String.format("HP %03d", reporter.showHP()),45,50);
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
