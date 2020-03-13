import javax.swing.Timer;
public class AsteroidsGame {
	public static void main(String[] args) {
		AsteroidWorld world = new AsteroidWorld(640, 480);
		Timer t = new Timer(1000/60, e->world.tick());
		world.show();
		t.start();
	}
}
