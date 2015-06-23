package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Map {

	private class City {
		public int x, y;

		public City(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			return ((City) obj).x == x && ((City) obj).y == y;
		}
	}

	private class Path {
		public int x1, y1, x2, y2;
		public double pheromones;

		public Path(int x1, int y1, int x2, int y2) {
			this.x1 = x1;
			this.y1 = y1;
			this.x2 = x2;
			this.y2 = y2;
		}
	}

	private List<City> cities;
	private List<Path> paths;
	private int width;
	private int height;

	public static Map fromFile(String path) {
		try {
			for (String line : Files.readAllLines(Paths.get(path))) {
				if(line.contains("="))
					continue;
				for(String field : line.split(" ")){
					//TODO
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public Map(int width, int height) {
		cities = new LinkedList<>();
		paths = new LinkedList<>();
		this.width = width;
		this.height = height;
	}

	public void addCity(int x, int y) {
		cities.add(new City(x, y));
	}

	public void addPath(int x1, int y1, int x2, int y2) {
		paths.add(new Path(x1, y1, x2, y2));
	}

	public boolean isCity(int x, int y) {
		return cities.contains(new City(x, y));
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public BufferedImage getImage(int width) {
		double scale_factor = (double) width / (double) this.width;
		int w = width;
		int h = (int) (this.height * scale_factor);

		BufferedImage image = new BufferedImage(w, h,
				BufferedImage.TYPE_INT_RGB);

		// Zeichne Hintergrund
		Graphics2D g = image.createGraphics();
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, w, h);

		// Zeichne Cities
		g.setColor(Color.BLACK);
		for (City city : cities) {
			g.fillRect((int) (city.x * scale_factor - 2), (int) (city.y
					* scale_factor - 2), 5, 5);
		}

		// Zeichne Pfade
		g.setColor(Color.DARK_GRAY);
		for (Path path : paths) {
			g.drawLine((int) (path.x1 * scale_factor),
					(int) (path.y1 * scale_factor),
					(int) (path.x2 * scale_factor),
					(int) (path.y2 * scale_factor));
		}

		return image;
	}

}
