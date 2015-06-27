package map;

import java.util.LinkedList;
import java.util.List;

/**
 * Setzt die weights in allen Pfaden.
 * 
 * @author Daniel Nassauer
 *
 */
public class PathWeightCalculator {
	public static double alpha;
	public static double beta;

	/**
	 * Repräsentiert den oberen Teil der Formel, der für jeden Pfad ausgerechnet
	 * werden muss.
	 */
	private static class FormulaTop {
		Path path;
		double top;

		public FormulaTop(Path path) {
			this.path = path;
			double attractiveness = 1 / path.length;
			top = Math.pow(path.pheromones, alpha)
					* Math.pow(attractiveness, beta);
		}
	}

	private static List<FormulaTop> formulas = new LinkedList<>();

	public static void calculate(Map map) {
		for (Path path : map.getPaths()) {
			formulas.add(new FormulaTop(path));
		}
	}
}
