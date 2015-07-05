package ga;

public class DoubleBest50Replicator extends Replicator{

	@Override
	public void replicate(Population population) {
		int pop_size = population.size();

		// Population sortieren
		population.sort();

		// Beste 50% finden
		int n = (int) (pop_size * 0.5);
		Gene[] bests = new Gene[n];
		for (int i = 0; i < n; i++) {
			bests[i] = population.get(pop_size - i - 1);
		}

		// population löschen und mit besten 50% auf alte Größe auffüllen
		population.clear();
		for (int i = 0; i < pop_size; i++) {
			population.add(bests[i % n].copy());
		}
	}

}
