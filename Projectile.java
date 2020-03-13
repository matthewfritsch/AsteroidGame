import java.awt.Color;
import java.awt.geom.Ellipse2D;
public class Projectile extends AsteroidWorld.Entity {
	long deathTime;
	public Projectile(AsteroidWorld.Entity source, double speed, int damage, int lifeSpanMillis) {
		this.color = Color.yellow;
		this.shape = new Ellipse2D.Double(-2, -2, 4, 4);
		this.x = source.x;
		this.y = source.y;
		this.rotation = source.rotation;
		this.xSpeed = Math.cos(rotation)*speed + source.xSpeed;
		this.ySpeed = Math.sin(rotation) * speed + source.ySpeed;
		this.deathTime = System.currentTimeMillis() + lifeSpanMillis;
	}
	public void tick() {
		super.tick();
		if(System.currentTimeMillis() > deathTime) {
			world.remove(this);
		}
		Asteroid as = this.collidesWithType(Asteroid.class);
		if(as != null) {
			as.split();
			world.remove(this);
		}
	}
}
