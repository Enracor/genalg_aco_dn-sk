package map;

public class Path {
	public City city1, city2;
	public int pheromones;
	public double length;

	public Path(City city1, City city2) {
		this.city1 = city1;
		this.city2 = city2;
		pheromones = 0;
		length = getDistance(city1, city2);
	}

	public void addPheromones(int value) {
		pheromones += value;
	}

	public double getLength() {
		return length;
	}

	private static double getDistance(City a, City b) {
		return 42.0;
	}

}
