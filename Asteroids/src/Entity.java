import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;

public abstract class Entity {

	double x,y, rotation; 
	Color color;
	
	Shape shape; //object has shape
	
	public abstract void tick(); //has ability to update
	
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

}
