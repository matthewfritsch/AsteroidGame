import java.awt.Polygon;
import java.awt.Shape;

public class Asteroid extends AsteroidWorld.Entity {
	
	final double radius, rotSpeed, travelDir;
	
	public Asteroid(double radius, double direction) {
		this.travelDir = direction;
		double speed = Math.random()*3+1;
		this.xSpeed = Math.cos(direction) * speed;
		this.ySpeed = Math.sin(direction) * speed;
		this.edgeLoop = true;
		this.radius = radius;
		this.rotSpeed = Math.random()/5-0.1;
		
		this.shape = genShape();
		
		
	}
	
	public Asteroid(Asteroid parent, boolean up) {
		this(parent.radius/2, parent.travelDir+(up?0.1:-0.1));
		this.x = parent.x;
		this.y = parent.y;
	}
	
	private Shape genShape() {
		Polygon myShape = new Polygon();
		int numVertices = (int)(Math.random()*5+8);
		for(int i = 0; i < numVertices; i++) {
			double angle = 2*Math.PI/numVertices*i;
			double randRadius = radius*(0.75+Math.random()/2); //±25% random radius
			myShape.addPoint((int)(randRadius*Math.cos(angle)), (int)(randRadius*Math.sin(angle)));
		}
		return myShape;
	}
	
	
	public void tick() {
		super.tick();
		this.rotation +=rotSpeed;
	}


	public void split() {
		world.remove(this);
		if(this.radius > 10) {
			world.add(new Asteroid(this, true));
			world.add(new Asteroid(this, false));
		}
		
	}
	
	
}
