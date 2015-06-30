package aco;

import utils.Utils;
import map.Map;
import map.Path;
import map.PathWeightCalculator;

public class ACO {

	private int antCount;
	private Ant[] ants;
	private Map map;
	double evaporation;

	public ACO(int antCount, Map map, double alpha, double beta, double evaporation) {
		this.antCount = antCount;
		this.evaporation = evaporation;
		ants = new Ant[antCount];
		this.map = map;
		PathWeightCalculator.alpha = alpha;
		PathWeightCalculator.beta = beta;
	}

	public Way next() {
		PathWeightCalculator.calculate(map);

		// Ameisen erzeugen
		createAnts();

		// Ameisen laufen lassen
		Way shortestWay = null;
		for (Ant ant : ants) {
			Way way = ant.walk();
			if (shortestWay == null
					|| way.getLength() < shortestWay.getLength()) {
				shortestWay = way;
			}
		}

		// Pfade mit Pheromonen markieren
		PheromoneMarker.mark(ants, evaporation);

		return shortestWay;
	}

	private void createAnts() {
		for (int i = 0; i < antCount; i++) {
			// neue Ameise erzeugen und in zufällige Stadt setzen
			ants[i] = new Ant(map, Utils.randInt(1, map.getCityCount() + 1));
		}
	}
}
