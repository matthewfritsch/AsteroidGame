import java.awt.Color;
import java.awt.Polygon;

public class Player extends Entity {

	public Player() {
		this.initShape();
		this.x = 100;
		this.y = 100;
		this.rotation = 0;
		this.color = Color.CYAN;
	}
	
	private void initShape() {
		Polygon myShape = new Polygon(); //defined by adding points
		myShape.addPoint(10, 0); //points for chevron, 10 pix high
		myShape.addPoint(-5, 7);
		myShape.addPoint(-3, 0);
		myShape.addPoint(-5, -7);
		
		myShape.translate(-3, 0); //point it 
		this.shape = myShape; //polygon is type of shape, woo
		
	}

	@Override
	public void tick() {
		
		
	}

}
