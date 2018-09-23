import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

public class AsteroidPanel extends JPanel {
	private static final long serialVersionUID = 1L; //be rid of stupid warning
	
	private static BufferedImage stars = ImageHandler.getImage("stars.png");
	
	private ArrayList<Entity> entities = new ArrayList<Entity>();
	
	public AsteroidPanel() {
		this.setPreferredSize(new Dimension(640,480)); //set preferred size of the game panel used with pack()
		this.setBackground(Color.BLACK); //set default  background as something that will fit in with the game
		Player p = new Player();
		entities.add(p);
		this.addKeyListener(p);
		this.setFocusable(true);
	}
	
	public void tick() {
		for(Entity e:entities) {
			e.tick();
		}
	}
	
	@Override
	public void paintComponent(Graphics g) { //Final step of drawing chain by repaint
		super.paintComponent(g); //default draw, just fills background
		
		TexturePaint starBG = new TexturePaint(stars, new Rectangle(0,0,stars.getWidth(), stars.getHeight()));//lets you paint an image and tile it from an origin      
		Graphics2D g2 = (Graphics2D)g;
		
		g2.setPaint(starBG); //like setting the paint, but is setting the tile to paint repeatedly instead of a single color
		g2.fillRect(0, 0, getWidth(), getHeight()); //fill whole window with stars
		for(Entity e:entities) { //repaint all entities!
			e.paint(g);
		}
	}
	
	
	
}
