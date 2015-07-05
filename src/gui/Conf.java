package gui;

import ga.Recombinator;
import ga.Replicator;

public class Conf {

	public String file;
	public int aco_ant_count;
	public int aco_nr_of_runs;
	public double aco_alpha;
	public double aco_beta;
	public double aco_evaporation;
	public int population_size;
	public double mutation_rate;
	public double recombination_rate;
	public int max_generations;
	public Replicator replicator;
	public Recombinator recombinator;
	public boolean protect_best;

	public Conf(String file, int aco_ant_count, int aco_nr_of_runs,
			double aco_alpha, double aco_beta, double aco_evaporation,
			int population_size, double mutation_rate,
			double recombination_rate, int max_generations,
			Replicator replicator, Recombinator recombinator,
			boolean protect_best) {
		this.file = file;
		this.aco_ant_count = aco_ant_count;
		this.aco_nr_of_runs = aco_nr_of_runs;
		this.aco_alpha = aco_alpha;
		this.aco_beta = aco_beta;
		this.aco_evaporation = aco_evaporation;
		this.population_size = population_size;
		this.mutation_rate = mutation_rate;
		this.recombination_rate = recombination_rate;
		this.max_generations = max_generations;
		this.replicator = replicator;
		this.recombinator = recombinator;
		this.protect_best = protect_best;
	}
}
