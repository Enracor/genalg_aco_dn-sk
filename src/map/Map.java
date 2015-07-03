package map;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Map {

	private List<City> cities;
	private List<Path> paths;
	private java.util.Map<String, Path> optimized_paths; // Zum schnelleren
															// Finden der Pfade
	private int width;
	private int height;

	public static Map fromFile(String path) {
		try {
			// Zeilen auslesen
			List<String> lines = Files.readAllLines(Paths.get(path)).stream()
					.filter((line) -> !line.contains("="))
					.collect(Collectors.toList());
			// Liste mit Zeilen. Zeilen enthalten Felder
			List<List<Integer>> fields = new ArrayList<List<Integer>>();

			// Felder auslesen und in Liste schreiben
			for (String line : lines) {
				List<Integer> row = new ArrayList<Integer>();
				fields.add(row);
				for (String field : line.split(" ")) {
					row.add(Integer.parseInt(field));
				}
			}

			// Map erzeugen
			Map map = new Map(fields.get(0).size(), fields.size());
			int x = 0, y = 0, city = 1;
			for (List<Integer> row : fields) {
				x = 0;
				for (int field : row) {
					if (field != 0) {
						map.addCity(x, y, city++);
					}
					x++;
				}
				y++;
			}

			map.createAllPaths();
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Map(int width, int height) {
		cities = new LinkedList<>();
		paths = new LinkedList<>();
		optimized_paths = new HashMap<String, Path>();
		this.width = width;
		this.height = height;
		createAllPaths();
	}

	public Path getPath(int cityNr1, int cityNr2) {
		int city1 = getMin(cityNr1, cityNr2);
		int city2 = getMax(cityNr1, cityNr2);

		Path path = optimized_paths.get(city1 + "," + city2);
		if (path == null)
			throw new IllegalStateException("Kein Pfad von " + cityNr1
					+ " nach " + cityNr2);
		return path;

		// for (Path path : paths) {
		// if (path.city1.nr == city1 && path.city2.nr == city2)
		// return path;
		// }
		//
		// throw new IllegalStateException("Kein Pfad von " + cityNr1 + " nach "
		// + cityNr2);
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

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void createAllPaths() {
		int acount = 0;
		for (City cityA : cities) {
			acount++;
			int bcount = 0;
			for (City cityB : cities) {
				bcount++;
				if (cityA == cityB || bcount < acount)
					continue;
				Path path = new Path(cityA, cityB);
				paths.add(path);
			}
		}

		// Optimierte Map für Pfade erezugen
		for (Path path : paths) {
			int city1 = getMin(path.city1.nr, path.city2.nr);
			int city2 = getMax(path.city1.nr, path.city2.nr);
			optimized_paths.put(city1 + "," + city2, path);
		}
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
