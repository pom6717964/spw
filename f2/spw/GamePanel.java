package f2.spw;

import java.awt.Color;
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
		bi = new BufferedImage(450, 650, BufferedImage.TYPE_INT_ARGB);
		big = (Graphics2D) bi.getGraphics();
			big.setBackground(Color.BLACK);

	}

	public void updateGameUI(GameReporter reporter){
		big.clearRect(0, 0, 450, 650);
		if(reporter.getScore() < 100000)
			big.setColor(Color.BLACK);
		else if(reporter.getScore() > 100000 && reporter.getScore() < 500000)
			big.setColor(Color.GREEN);
		else
			big.setColor(Color.RED);
		big.drawString(String.format("%09d", reporter.getScore()), 350, 35);
		for(Sprite s : sprites){
			s.draw(big);
			//system.out.print("LEVEL UP");
		}
		
		repaint();
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(bi, null, 0, 0);
	}

}
