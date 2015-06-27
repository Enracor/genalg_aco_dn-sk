package map;

import java.util.HashMap;

/**
 * Setzt die weights in allen Pfaden.
 * 
 * @author Daniel Nassauer
 *
 */
public class PathWeightCalculator {
	public static double alpha;
	public static double beta;
	public static java.util.Map<Path, Double> topValues = new HashMap<>();

	public static void calculate(Map map) {
		topValues.clear();

		// Für alle Pfade: Oberen Teil der Formel berechnen
		for (Path path : map.getPaths()) {
			double attractiveness = 1 / path.length;
			double top = Math.pow(path.pheromones, alpha)
					* Math.pow(attractiveness, beta);
			topValues.put(path, top);
		}

		// Unteren Teil der Formel berechnen
		double bottom = 0;
		for (Path path : topValues.keySet()) {
			bottom += topValues.get(path);
		}

		// weights für Pfade bestimmen
		for (Path path : map.getPaths()) {
			double top = topValues.get(path);
			path.weight = top / (bottom - top);
		}
	}
}
