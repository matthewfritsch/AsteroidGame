import java.awt.Polygon;

public class Asteroid extends AsteroidWorld.Entity {
	
	final double radius, rotSpeed;
	
	public Asteroid(double radius, double rotation) {
		double speed = Math.random()*3+1;
		this.xSpeed = Math.cos(rotation) * speed;
		this.ySpeed = Math.sin(rotation) * speed;
		this.edgeLoop = true;
		this.radius = radius;
		this.rotSpeed = Math.random()/5-0.1;
		Polygon myShape = new Polygon();
		int numVertices = (int)(Math.random()*5+8);
		for(int i = 0; i < numVertices; i++) {
			double angle = 2*Math.PI/numVertices*i;
			double randRadius = radius*(0.75+Math.random()/2); //±25% random radius
			myShape.addPoint((int)(randRadius*Math.cos(angle)), (int)(randRadius*Math.sin(angle)));
		}
		this.shape = myShape;
		
		
		
	}
	
	
	public void tick() {
		super.tick();
		this.rotation +=rotSpeed;
	}
	
	
}
