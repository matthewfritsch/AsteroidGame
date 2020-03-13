import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
public class Player extends AsteroidWorld.Entity implements KeyListener {
	boolean left, right, up, fire;
	int fireDelay;
	long lastFire;
	public Player() {
		this.initShape();
		this.x = 100;
		this.y = 100;
		this.rotation = 0;
		this.color = Color.CYAN;
		left = right = up = false;
		fireDelay = 333;
		this.edgeLoop = true;
	}
	private void initShape() {
		Polygon myShape = new Polygon(); // defined by adding points
		myShape.addPoint(10, 0); // points for chevron, 10 pix high
		myShape.addPoint(-5, 7);
		myShape.addPoint(-3, 0);
		myShape.addPoint(-5, -7);
		myShape.translate(-3, 0); // point it
		this.shape = myShape; // polygon is type of shape, woo
	}
	@Override
	public void tick() {
		super.tick();
		if (left) {
			rotation -= Math.PI / 50;
		}
		if (right) {
			rotation += Math.PI / 50;
		}
		if (up) {
			xSpeed += Math.cos(rotation) / 3;
			ySpeed += Math.sin(rotation) / 3;
		}
		xSpeed *= 0.96;
		ySpeed *= 0.96;
		if (fire && System.currentTimeMillis() - lastFire > fireDelay) {
			this.world.add(new Projectile(this, 10, 1, 750));
			this.lastFire = Math.max(System.currentTimeMillis(), lastFire+fireDelay);
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			left = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			right = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_W) {
			up = true;
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			fire = true;
		}
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) 
			left = false;
		if (e.getKeyCode() == KeyEvent.VK_D) 
			right = false;
		if (e.getKeyCode() == KeyEvent.VK_W) 
			up = false;
		if(e.getKeyCode() == KeyEvent.VK_SPACE) 
			fire = false;
	}
}
