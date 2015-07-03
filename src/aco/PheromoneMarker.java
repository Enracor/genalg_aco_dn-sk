package aco;

import map.Map;
import map.Path;

public class PheromoneMarker {

	public static void mark(Way way) {
		// Pheromonauschüttung
		double pheromones = 1 / way.getLength();
		for (Path path : way.getPaths()) {
			path.addPheromones(pheromones);
		}
	}

	public static void evaporate(Map map, double evaporation) {
		// Verdampfung
		for (Path path : map.getPaths()) {
			path.pheromones *= evaporation;
		}
	}
}
