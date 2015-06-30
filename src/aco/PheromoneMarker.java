package aco;

import map.Map;
import map.Path;

public class PheromoneMarker {

	public static void mark(Map map, Ant[] ants, double evaporation) {
		// Verdampfung
		for (Path path : map.getPaths()) {
			path.pheromones *= evaporation;
		}

		// Pheromonauschüttung
		for (Ant ant : ants) {
			Way way = ant.getWay();
			double pheromones = 1 / way.getLength();
			for (Path path : way.getPaths()) {
				path.addPheromones(pheromones);
			}
		}
	}
}
