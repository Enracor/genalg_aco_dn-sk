package ga;

import map.Map;

public class Simulator {
	private Population population;
	private Replicator replicator;
	private Recombinator recombinator;
	private FitnessFunction fitness_func;
	private double recombination_rate;
	private boolean protect_best;
	private Map map;

	public Simulator(int population_size, Map map, double mutation_rate,
			double recombination_rate, boolean protect_best,
			Replicator replicator, Recombinator recombinator,
			FitnessFunction fitness_func) {
		this.replicator = replicator;
		this.recombinator = recombinator;
		this.fitness_func = fitness_func;
		this.recombination_rate = recombination_rate;
		this.protect_best = protect_best;
		this.map = map;

		// Generiere 1. Generation
		population = new Population();
		for (int i = 0; i < population_size; i++) {
			population.add(new Gene(map.getCityCount(), mutation_rate, map));
		}

		// Fitness berechnen
		for (Gene gene : population) {
			gene.setFitness(fitness_func.calculateFitness(gene));
		}
	}

	/**
	 * Erzeugt die nächste Generation.
	 * 
	 * @return true, wenn maximale Fitness erreicht
	 */
	public boolean next() {
		Gene best = getBest();
		if (best.getFitness() >= fitness_func.maxFitness(best))
			return true;

		// ####################################################################
		// # MUTIEREN
		// ####################################################################
		for (Gene gene : population) {
			if (protect_best && gene == best) // Bestes Gen nicht verändern
				continue;
			gene.mutate();
		}

		// ####################################################################
		// # REKOMBINIEREN
		// ####################################################################
		// Mit der Wahrscheinlickteit PC (Rekombinationsrate) werden die Eltern
		// kombiniert. Ansonsten werden nur Kopien der Eltern erzeugt. Dies wird
		// solange gemacht, bis die Anzahl der Kinder gleich der Größe der alten
		// Generation ist. Die Kinder sind dann die neue Generation.
		Population new_pop = new Population();

		if (protect_best) // Bestes Gen übernehmen
			new_pop.add(best);

		while (new_pop.size() < population.size()) {
			// Vater und Mutter bestimmen
			Gene father = population.get(Utils.randInt(0, population.size()));
			Gene mother = population.get(Utils.randInt(0, population.size()));
			while (mother == father) {
				mother = population.get(Utils.randInt(0, population.size()));
			}

			// Kinder erzeugen und neuer Population hinzufügen
			Gene[] children;
			if (Utils.randBool(recombination_rate)) {
				children = recombinator.recombinate(father, mother);
			} else {
				children = new Gene[] { father.copy(), mother.copy() };
			}
			for (Gene child : children) {
				if (new_pop.size() < population.size())
					new_pop.add(child);
			}
		}
		population = new_pop;

		// ####################################################################
		// # FITNESS BERECHNEN
		// ####################################################################
		for (Gene gene : population) {
			gene.setFitness(fitness_func.calculateFitness(gene));
		}

		// ####################################################################
		// # SELEKTIEREN / REPLIZIEREN
		// ####################################################################
		replicator.replicate(population);

		return false;
	}

	public Gene getBest() {
		Gene best = null;
		for (Gene gene : population) {
			if (best == null || gene.getFitness() > best.getFitness())
				best = gene;
		}
		return best;
	}
}
