import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;

public class AsteroidsGame {

	
	public static void main(String[] args) {
		JFrame frame = new JFrame();//Making a frame for display
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when hit close
		
		AsteroidPanel world = new AsteroidPanel(); //makes a panel for the background
		frame.add(world); //adds it to the panel
		
		
		frame.pack(); //size to fit worlds
		frame.setLocationRelativeTo(null); //center on screen
		frame.setVisible(true); //show panel after fixes
		
		
		
		Timer t = new Timer(1000/60, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				world.tick();
				world.repaint();
			}
		});
		
		t.start();
		
	}

}
