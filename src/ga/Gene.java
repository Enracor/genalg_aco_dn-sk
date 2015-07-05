package ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import map.Map;
import map.Path;
import aco.Way;

public class Gene implements Comparable<Gene> {
	private short[] gene;
	private int length;
	private double mutation_rate;
	private double fitness;
	private Way way;
	private Map map;
	private static Random rand = new Random();

	/**
	 * Erzeugt und initialisiert ein Gen.
	 * 
	 * @param length
	 *            Länge des Gens.
	 * @param mutation_rate
	 *            Mutations-Rate.
	 * @param init_rate
	 *            Init-Rate.
	 */
	public Gene(int length, double mutation_rate, Map map) {
		gene = new short[length];
		this.length = length;
		this.mutation_rate = mutation_rate;
		this.map = map;
		init();
	}

	/**
	 * Erzeugt ein Gen anhand eines bereits vorhandenen BitSets.
	 * 
	 * @param mutation_rate
	 *            MutationsRate
	 * @param gene
	 *            BitSet des Gens.
	 */
	private Gene(double mutation_rate, short[] gene) {
		this.mutation_rate = mutation_rate;
		this.gene = gene;
	}

	/**
	 * Erzeugt eine Kopie des Gens. Kopiert werden Fitness, Mutationsrate und
	 * das BitSet.
	 * 
	 * @return
	 */
	public Gene copy() {
		short[] cp = new short[length];
		for (int i = 0; i < length; i++) {
			cp[i] = gene[i];
		}
		Gene copy = new Gene(mutation_rate, cp);
		copy.fitness = fitness;
		copy.length = length;
		copy.map = map;
		return copy;
	}

	/**
	 * Liefert die Länge des Gens.
	 * 
	 * @return Länge des Gens.
	 */
	public int length() {
		return length;
	}

	/**
	 * Gibt den Wert an einer Position des Gens zurück.
	 * 
	 * @param index
	 *            Position im Gen.
	 * @return Zahl an gewählter Position.
	 */
	public short get(int index) {
		return gene[index];
	}

	/**
	 * Liefert eine Teil-Sequenz des Gens als short array.
	 * 
	 * @param from
	 *            Start-Position.
	 * @param to
	 *            End-Position (exclusive).
	 * @return Teil-Sequenz als short array.
	 */
	public short[] get(int from, int to) {
		short[] part = new short[to - from];
		for (int i = from; i < to; i++)
			part[i - from] = gene[i];
		return part;
	}

	public void set(int index, short value) {
		gene[index] = value;
	}

	/**
	 * Setzt eine Teilsequenz des Gens ab einer bestimmten Position neu.
	 * 
	 * @param from
	 *            Start-Position.
	 * @param values
	 *            Teil-Sequenz als short array.
	 */
	public void set(int from, short[] values) {
		for (int i = from; i < from + values.length; i++) {
			gene[i] = values[i - from];
		}
	}

	public Way getWay() {
		if (way != null)
			return way;
		way = new Way(length);
		for (int i = 0; i < length - 1; i++) {
			Path path = map.getPath(gene[i], gene[i + 1]);
			way.addPath(path);
		}
		way.addPath(map.getPath(gene[length - 1], gene[0]));
		return way;
	}

	/**
	 * Setzt die Fitness des Gens.
	 * 
	 * @param fitness
	 */
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}

	/**
	 * Liefert die Fitness des Gens.
	 * 
	 * @return Fitness des Gens.
	 */
	public double getFitness() {
		return fitness;
	}

	/**
	 * Lässt das Gen mutieren.
	 */
	public void mutate() {
		int n = (int) (mutation_rate * length);

		// Positionen ermitteln und werte tauschen
		int[] positions = new int[n];
		for (int i = 0; i < n; i++) {
			int pos1 = randInt(0, length);
			int pos2 = randInt(0, length);
			short tmp = gene[pos1];
			gene[pos1] = gene[pos2];
			gene[pos2] = tmp;
		}
	}

	private void init() {
		List<Short> cities = new ArrayList<Short>(length);
		for (short i = 1; i <= length; i++) {
			cities.add(i);
		}
		Collections.shuffle(cities);
		for (int i = 0; i < length; i++)
			gene[i] = cities.get(i);
	}

	private static int randInt(int min, int max) {
		return rand.nextInt((max - min)) + min;
	}

	@Override
	public int compareTo(Gene o) {
		double diff = fitness - o.fitness;
		if (diff < 0)
			return -1;
		if (diff > 0)
			return 1;
		return 0;
	}

	@Override
	public String toString() {
		String s = "";
		for (int i = 0; i < length; i++) {
			s += gene[i] + " ";
		}
		return s;
	}
}
