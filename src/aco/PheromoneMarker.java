package aco;

import map.Path;

public class PheromoneMarker {

	public static void mark(Ant[] ants, double evaporation) {
		for (Ant ant : ants) {
			Way way = ant.getWay();
			double pheromones = 1 / way.getLength();
			for (Path path : way.getPaths()) {
				path.pheromones *= evaporation;
				path.addPheromones(pheromones);
			}
		}
	}
}
