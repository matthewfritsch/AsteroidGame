import java.awt.Color;
import java.awt.geom.Ellipse2D;

public class Projectile extends AsteroidWorld.Entity {
	
	public Projectile(AsteroidWorld.Entity source, double speed, int damage) {
		this.color = Color.yellow;
		this.setShape(new Ellipse2D.Double(-2, -2, 4, 4));
		this.x = source.x;
		this.y = source.y;
		this.rotation = source.rotation;
		this.xSpeed = Math.cos(rotation)*speed + source.xSpeed;
		this.ySpeed = Math.sin(rotation) * speed + source.ySpeed;
	}
}
