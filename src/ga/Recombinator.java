package ga;

/**
 * Ein Recombinator implementiert ein Rekombinations-Schema. Er bekommt zwei
 * Eltern-Gene und erzeugt daraus Kind-Gene.
 */
public abstract class Recombinator {

	/**
	 * Erzeugt Kinder von zwei Genen.
	 * 
	 * @param gene1
	 *            Erstes Eltern-Gen
	 * @param gene2
	 *            Zweites Eltern-Gen
	 * @return Array von Kind-Genen.
	 */
	public abstract Gene[] recombinate(Gene gene1, Gene gene2);

}
