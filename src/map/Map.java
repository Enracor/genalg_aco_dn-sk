package map;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

public class Map {

	private class City {
		public int x;
		public int y;

		public City(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object obj) {
			return ((City) obj).x == x && ((City) obj).y == y;
		}
	}

	private List<City> map;
	private int width;
	private int height;

	public static Map fromFile(String path) {
		return null;
	}

	public Map(int width, int height) {
		map = new LinkedList<>();
		this.width = width;
		this.height = height;
	}

	public void addCity(int x, int y) {
		map.add(new City(x, y));
	}

	public boolean isCity(int x, int y) {
		return map.contains(new City(x, y));
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
		for (City city : map) {
			g.fillRect((int) (city.x * scale_factor - 2), (int) (city.y
					* scale_factor - 2), 5, 5);
		}

		return image;
	}

}
