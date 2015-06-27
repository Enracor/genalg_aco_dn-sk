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
		for (Path path : paths) {
			if (path.city1.nr == cityNr1 && path.city2.nr == cityNr2)
				return path;
		}
		return null;
	}

	public void addCity(int x, int y, int nr) {
		cities.add(new City(x, y, nr));
	}

	public int getCityCount() {
		return cities.size();
	}
	
	public List<City> getCities(){
		return cities;
	}
	
	public List<Path> getPaths(){
		return paths;
	}

	public void createAllPaths() {
		for (City cityA : cities) {
			for (City cityB : cities) {
				Path path = new Path(cityA, cityB, this);
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

}
