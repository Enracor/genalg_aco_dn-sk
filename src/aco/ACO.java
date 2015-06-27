package aco;

import utils.Utils;
import map.Map;
import map.Path;
import map.PathWeightCalculator;

public class ACO {

	private int antCount;
	private Ant[] ants;
	private Map map;

	public ACO(int antCount, Map map, double alpha, double beta) {
		this.antCount = antCount;
		ants = new Ant[antCount];
		this.map = map;
		PathWeightCalculator.alpha = alpha;
		PathWeightCalculator.beta = beta;
	}

	public void next() {
		createAnts();
		for (Ant ant : ants) {
			ant.nextStep();
		}
	}

	private void createAnts() {
		for (int i = 0; i < antCount; i++) {
			// neue Ameise erzeugen und in zufällige Stadt setzen
			ants[i] = new Ant(map, Utils.randInt(1, map.getCityCount() + 1));
		}
	}
}
