import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

public class AsteroidWorld {
	private final int width, height;
	private ArrayList<Entity> entities;
	
	public AsteroidWorld(int width, int height) {
		this.width = width;
		this.height = height;
	}
	

	public Dimension getSize() {
		return new Dimension(width, height);
	}
	
	public void tick() {
		for(Entity e:entities) {
			e.tick();
		}
	}


	public void paint(Graphics g) {
		for(Entity e:entities) {
			e.paint(g);
		}
	}
	
	
	
}
