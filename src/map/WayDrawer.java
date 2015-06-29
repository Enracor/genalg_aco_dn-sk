package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;

import aco.Way;

public class WayDrawer {

	public static BufferedImage draw(Way way, Map map, int width) {
		double scale_factor = (double) width / (double) map.getWidth();
		int w = width;
		int h = (int) (map.getHeight() * scale_factor);

		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D g = image.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
				RenderingHints.VALUE_INTERPOLATION_BICUBIC);

		// Zeichne Hintergrund
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);

		// Zeichne Cities
		g.setColor(Color.BLACK);
		for (City city : map.getCities()) {
			g.fillRect((int) (city.x * scale_factor - 2), (int) (city.y
					* scale_factor - 2), 5, 5);
		}

		// Zeichne Pfade
		int path_count = 0;
		for (Path path : way.getPaths()) {
			g.setColor(getPathColor((float) (path_count++ / (float) way
					.getPaths().length)));
			g.drawLine((int) (path.city1.x * scale_factor),
					(int) (path.city1.y * scale_factor),
					(int) (path.city2.x * scale_factor),
					(int) (path.city2.y * scale_factor));

		}

		return image;
	}

	private static Color getPathColor(float value) {
		float R = 0;
		float G = 0;
		float B = 0;

		if (0 <= value && value <= 1 / 8F) {
			R = 0;
			G = 0;
			B = (float) (4 * value + .5); // .5 - 1 // b = 1/2
		} else if (1 / 8 < value && value <= 3 / 8F) {
			R = 0;
			G = (float) (4 * value - .5); // 0 - 1 // b = - 1/2
			B = 1; // small fix
		} else if (3 / 8 < value && value <= 5 / 8F) {
			R = (float) (4 * value - 1.5); // 0 - 1 // b = - 3/2
			G = 1;
			B = (float) (-4 * value + 2.5); // 1 - 0 // b = 5/2
		} else if (5 / 8 < value && value <= 7 / 8F) {
			R = 1;
			G = (float) (-4 * value + 3.5); // 1 - 0 // b = 7/2
			B = 0;
		} else if (7 / 8 < value && value <= 1F) {
			R = (float) (-4 * value + 4.5); // 1 - .5 // b = 9/2
			G = 0;
			B = 0;
		} else { // should never happen - value > 1
			R = (float) .5;
			G = 0;
			B = 0;
		}

		return new Color(R, G, B);
	}
}
