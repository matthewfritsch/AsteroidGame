import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageHandler {

	static {
		File imgDir = new File("res" + File.separator + "images");
		if(!imgDir.exists()) {
			imgDir.mkdirs();
		}
	}
	
	public static BufferedImage getImage(String name) {
		File f = new File("res" + File.separator + "images" + File.separator + name);
		try {
			return ImageIO.read(f);
		}
		catch(IOException e) {
			System.err.println("Could not get image " + f.getPath());
			e.printStackTrace();
			return null;
		}
	}

}
