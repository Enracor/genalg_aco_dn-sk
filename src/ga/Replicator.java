package ga;

/**
 * Ein Replicator implementiert ein Replikations-Schema. Er bekommt die
 * Population übergeben und bestimmt die neue Population.
 */
public abstract class Replicator {

	/**
	 * Erzeugt die neue Population. Die übergebene Population wird abgeändert.
	 * 
	 * @param population
	 *            Die Population.
	 */
	public abstract void replicate(Population population);
}
