import javax.swing.JFrame;

public class AsteroidsGame {

	public static void main(String[] args) {
		JFrame frame = new JFrame();//Making a frame for display
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //close when hit close
		
		AsteroidPanel content = new AsteroidPanel(); //makes a panel for the background
		frame.add(content); //adds it to the panel
		
		
		
		
		
		
		frame.pack(); //size to fit contents
		frame.setLocationRelativeTo(null); //center on screen
		frame.setVisible(true); //show panel after fixes
		
	}

}
