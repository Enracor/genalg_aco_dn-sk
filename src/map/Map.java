package map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Map {

	private List<City> cities;
	private List<Path> paths;
	private int width;
	private int height;

	public static Map fromFile(String path) {
		try {
			for (String line : Files.readAllLines(Paths.get(path))) {
				if (line.contains("="))
					continue;
				for (String field : line.split(" ")) {
					// TODO
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

	public Path getPath(int cityNr1, int cityNr2) {
		int city1 = getMin(cityNr1, cityNr2);
		int city2 = getMax(cityNr1, cityNr2);

		for (Path path : paths) {
			if (path.city1.nr == city1 && path.city2.nr == city2)
				return path;
		}
		return null;
	}

	/**
	 * Gibt alle Pfade , die von der Ursprungsstadt origin abgehen zurück.
	 * 
	 * @param origin
	 *            Ursprungsstadt
	 * @return Pfade von origin augehend
	 */
	public Path[] getPaths(int origin) {
		Path[] paths = new Path[getCityCount() - 1];
		int j = 0;
		for (int i = 1; i < getCityCount() + 1; i++) {
			if (i == origin)
				continue;
			paths[j++] = getPath(origin, i);
		}
		return paths;
	}

	public void addCity(int x, int y, int nr) {
		cities.add(new City(x, y, nr));
	}

	public int getCityCount() {
		return cities.size();
	}

	public List<City> getCities() {
		return cities;
	}

	public List<Path> getPaths() {
		return paths;
	}

	public void createAllPaths() {
		for (City cityA : cities) {
			for (City cityB : cities) {
				Path path = new Path(cityA, cityB);
				paths.add(path);
			}
		}
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	private static int getMin(int a, int b) {
		if (a < b)
			return a;
		return b;
	}

	private static int getMax(int a, int b) {
		if (a >= b)
			return a;
		return b;
	}

}
