package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import aco.Way;

public class WayDrawer {

	public static BufferedImage draw(Way way, Map map, int width) {
		double scale_factor = (double) width / (double) map.getWidth();
		int w = width;
		int h = (int) (map.getHeight() * scale_factor);

		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);

		// Zeichne Hintergrund
		Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);

		// Zeichne Cities
		g.setColor(Color.BLACK);
		for (City city : map.getCities()) {
			g.fillRect((int) (city.x * scale_factor - 2), (int) (city.y
					* scale_factor - 2), 5, 5);
		}

		// Zeichne Pfade
		g.setColor(Color.DARK_GRAY);
		for (Path path : way.getPaths()) {
			g.drawLine((int) (path.city1.x * scale_factor),
					(int) (path.city1.y * scale_factor),
					(int) (path.city2.x * scale_factor),
					(int) (path.city2.y * scale_factor));
		}

		return image;
	}
}
