package ga;

public abstract class FitnessFunction {

	/**
	 * Die Fitness-Funktion bekommt ein Gen übergeben, und berechnet dessen
	 * Fitness.
	 * 
	 * @param gene
	 *            Das Gen, dessen Fitness berechnet werden soll.
	 * @return Fitness des Gens.
	 */
	public abstract double calculateFitness(Gene gene);
	
	public abstract double maxFitness(Gene gene);
}
