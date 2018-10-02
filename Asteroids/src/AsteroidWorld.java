import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.awt.TexturePaint;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class AsteroidWorld {
	private final int width, height;
	private ArrayList<Entity> entities, toRemove, toAdd;
	AsteroidPanel panel;
	Player player;
	
	public AsteroidWorld(int width, int height) {
		this.width = width;
		this.height = height;
		this.player = new Player();
		this.entities = new ArrayList<Entity>();
		this.toRemove = new ArrayList<Entity>();
		this.toAdd = new ArrayList<Entity>();
		this.add(player);
		Asteroid test = new Asteroid(30, 1);
		this.add(test);
	}
	
	public void show() {
		JFrame frame = new JFrame();//Making a frame for display
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when hit close
		
//		ComponentListener maintainAspectRatio = new ComponentListener() {
//			public void componentShown(ComponentEvent e){}
//			@Override
//			public void componentResized(ComponentEvent e) {
//				
//				
//				
//			}
//			public void componentMoved(ComponentEvent e){}
//			public void componentHidden(ComponentEvent e){}
//		};
//		
		
		this.panel = new AsteroidPanel();
		this.panel.addKeyListener(player);
//		frame.addComponentListener(maintainAspectRatio);
		frame.add(panel); //adds it to the panel
		
		frame.setResizable(false);
		frame.pack(); //size to fit worlds
		frame.setLocationRelativeTo(null); //center on screen
		frame.setVisible(true); //show panel after fixes
		
	}
	
	public int getWidth() {
		return this.width;
	}
	public int getHeight() {
		return this.height;
	}
	
	public void add(Entity e) {
		e.setParentWorld(this);
		toAdd.add(e);
	}
	public void remove(Entity e) {
		e.setParentWorld(null);
		toRemove.add(e);    
	}
	public void tick() {
		this.entities.addAll(this.toAdd);
		toAdd.clear();
		for(Entity e:entities) {
			e.tick();
		}
		this.entities.removeAll(this.toRemove);
		toRemove.clear();
		this.panel.repaint();
	}


	public void paint(Graphics g) {
		for(Entity e:entities) 
			e.paint(g);
	}
	
	public abstract static class Entity {
		AsteroidWorld world;
		double x,y, rotation, xSpeed, ySpeed, maxDist; 
		
		boolean edgeLoop, fill;
		Color color;
		Shape shape; //object has shape
		
		public void tick() {//has ability to update
			this.x+=this.xSpeed;
			this.y+=this.ySpeed;
			if(edgeLoop) {
				if (this.x < 0) {
					this.x = this.world.getWidth() ;
				}
				if (this.x > this.world.getWidth()) {
					this.x = 0;
				}

				if (this.y < 0) {
					this.y = this.world.getHeight();
				}
				if (this.y > this.world.getHeight() ) {
					this.y = 0;
				}
			}
			else {
				if(this.x < -this.maxDist || this.y < this.maxDist || this.x > this.world.getWidth()+this.maxDist || this.y > this.world.getHeight()+ this.maxDist) {
					this.world.remove(this);
				}
			}
		}
		
		public void paint(Graphics g) {
			
			Graphics2D g2 = (Graphics2D) g; //convert thing to paint to be 2D
			
			AffineTransform at = new AffineTransform(); //wrapper for transformation matrices
			
			at.translate(x, y); //transformations apply in reverse order. translates last
			at.rotate(rotation); //rotate lol
			
			AffineTransform backup = g2.getTransform(); //backup transformations before application
			g2.transform(at); //perform the transformation
			g2.setColor(color);
			g2.setStroke(new BasicStroke(2)); //determine line width
			
			g2.draw(shape);//trace my shape
			
			g2.setTransform(backup); //restore the state of graphic before drawing
		}
		
		public void setParentWorld(AsteroidWorld world) {
			this.world = world;
		}
		
		protected void setShape(Shape shape) {
			this.shape = shape;
			Rectangle2D bounds = shape.getBounds2D();
			this.maxDist = Math.sqrt(Math.pow(bounds.getWidth(), 2) + Math.pow(bounds.getHeight(), 2));
		}
		
		protected Shape getShape() {
			return this.shape;
		}
		

	}
	
	@SuppressWarnings("serial")
	private class AsteroidPanel extends JPanel {
		
		private BufferedImage stars = ImageHandler.getImage("stars.png");
		
		private AsteroidPanel() {
			this.setPreferredSize(new Dimension(640,480)); //set preferred size of the game panel used with pack()
			this.setBackground(Color.BLACK); //set default  background as something that will fit in with the game
			this.setFocusable(true);
		}
		
		@Override
		public Dimension getPreferredSize() {
			return new Dimension(AsteroidWorld.this.width, AsteroidWorld.this.height);
		}
		
		@Override
		public void paintComponent(Graphics g) { //Final step of drawing chain by repaint
			super.paintComponent(g); //default draw, just fills background
			
			TexturePaint starBG = new TexturePaint(stars, new Rectangle(0,0,stars.getWidth(), stars.getHeight()));//lets you paint an image and tile it from an origin      
			Graphics2D g2 = (Graphics2D)g;
			
			g2.setPaint(starBG); //like setting the paint, but is setting the tile to paint repeatedly instead of a single color
			g2.fillRect(0, 0, getWidth(), getHeight()); //fill whole window with stars
			for(Entity e: AsteroidWorld.this.entities) {
				e.paint(g);
			}
		}
	}

	
}
