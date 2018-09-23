import java.awt.Color;
import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Player extends Entity implements KeyListener{

	boolean left, right, up;
	
	public Player() {
		this.initShape();
		this.x = 100;
		this.y = 100;
		this.rotation = 0;
		this.color = Color.CYAN;
		left = right = up = false;
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
		super.tick();
		if(left) {
			rotation -=Math.PI/50;
		}
		if(right) {
			rotation +=Math.PI/50;	
		}
		if(up) {
			xSpeed += Math.cos(rotation)/3;
			ySpeed += Math.sin(rotation)/3;
		}
		xSpeed*=0.96;
		ySpeed*=0.96;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			left = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			right = true;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			up = true;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if(e.getKeyCode() == KeyEvent.VK_A) {
			left = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			right = false;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			up = false;
		}
	}

}
