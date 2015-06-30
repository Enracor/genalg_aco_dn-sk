package map;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class PheromoneDrawer {
	private static Comparator<Path> pheromoneComp = new Comparator<Path>() {
		public int compare(Path p1, Path p2) {
			if (p1.pheromones < p2.pheromones)
				return -1;
			if (p1.pheromones > p2.pheromones)
				return 1;
			return 0;
		}
	};

	public static BufferedImage draw(Map map, int width) {
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
		g.setStroke(new BasicStroke(2));

		// Zeichne Hintergrund
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);

		// Zeichne Pfade
		List<Path> paths = map.getPaths();
		List<Path> sortedPaths = new ArrayList<Path>(paths.size());
		for (Path path : paths) {
			sortedPaths.add(path);
		}
		Collections.sort(sortedPaths, pheromoneComp);
		Path highest = getPathWithMostPheromones(map);
		for (Path path : sortedPaths) {
			g.setColor(getPathColor((float) (path.pheromones / highest.pheromones)));
			g.drawLine((int) (path.city1.x * scale_factor),
					(int) (path.city1.y * scale_factor),
					(int) (path.city2.x * scale_factor),
					(int) (path.city2.y * scale_factor));

		}

		// Zeichne Cities
		g.setColor(Color.BLACK);
		for (City city : map.getCities()) {
			g.fillRect((int) (city.x * scale_factor - 2), (int) (city.y
					* scale_factor - 1), 3, 3);
		}

		return image;
	}

	private static Path getPathWithMostPheromones(Map map) {
		Path highest = null;
		for (Path path : map.getPaths()) {
			if (highest == null || path.pheromones > highest.pheromones)
				highest = path;
		}
		return highest;
	}

	private static Color getPathColor(float value) {
		float R = 0;
		float G = 0;
		float B = 0;

		R = 1F;
		G = 1F - value;
		B = 1F - value;

		return new Color(R, G, B);
	}
}
