package ga;

import java.util.LinkedList;
import java.util.List;

import map.Path;

public class GreedyCrossover extends Recombinator {

	@Override
	public Gene[] recombinate(Gene parent1, Gene parent2) {
		// Kind mit leerem Weg erzeugen
		Gene gene = parent1.copy();
		for (int i = 0; i < gene.length(); i++)
			gene.set(i, (short) -1);

		// Noch nicht benutzte Städte merken
		List<Short> unused_cities = new LinkedList<>();
		for (short city = 1; city <= gene.length(); city++)
			unused_cities.add(city);

		// Erste Stadt in Child ist erste Stadt aus Parent 1
		int index = 0;
		short previous = parent1.get(0);
		gene.set(index++, previous);
		unused_cities.remove((Short) previous);

		// Folgende Städte finden
		while (unused_cities.size() > 0) {
			// mögliche Kandidaten
			short following1 = getFollowingCity(parent1, previous);
			short following2 = getFollowingCity(parent2, previous);
			short following = -1;

			// Ist eine der beiden Städte schon im neuen Weg vorhanden, wird die
			// andere genommen
			boolean contains_1 = contains(gene, following1);
			boolean contains_2 = contains(gene, following2);
			if (contains_1 && !contains_2)
				following = following2;
			else if (contains_2 && !contains_1)
				following = following1;

			// Sind beide schon vorhanden, wird eine zufällige, nicht vorhandene
			// gewählt.
			else if (contains_1 && contains_2) {
				int rand = Utils.randInt(0, unused_cities.size());
				following = unused_cities.get(rand);
			}

			// Ansonsten wird die mit dem kürzeren Pfad zur vorgänger Stadt
			// gewählt
			else {
				Path path1 = gene.getMap().getPath(previous, following1);
				Path path2 = gene.getMap().getPath(previous, following2);
				if (path1.length < path2.length)
					following = following1;
				else
					following = following2;
			}

			// nächste Stadt setzen
			gene.set(index++, following);
			unused_cities.remove((Short) following);
			previous = following;
		}

		return new Gene[] { gene };
	}

	private static short getFollowingCity(Gene gene, short previous) {
		// Suche Vorgänger
		for (int i = 0; i < gene.length() - 1; i++) {
			if (gene.get(i) == previous)
				return gene.get(i + 1);
		}

		// Ist Vorgänger letzte Stadt, dann ist erste Stadt Nachfolger
		if (gene.get(gene.length() - 1) == previous)
			return gene.get(0);

		throw new IllegalStateException("Konnte keinen Nachfolger von "
				+ previous + " finden! Gen: " + gene);
	}

	private static boolean contains(Gene gene, short city) {
		short c;
		for (int i = 0; i < gene.length(); i++) {
			c = gene.get(i);
			if (c == city)
				return true;
			if (c == -1)
				return false;
		}
		return false;
	}

}
