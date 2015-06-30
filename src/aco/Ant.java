/**
 * @author Sebastian
 *         created 23.06.2015
 *         2015 copyright sebastian koch. all rights reserved
 *         mailto: sebastian.koch@mni.thm.de
 */

package aco;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import utils.Utils;
import map.City;
import map.Map;
import map.Path;

/**
 * @author Sebastian
 */
public class Ant {
	
	private Map					map;
	
	private int					pos;
	
	private int startPos;
	
	private Way					way;
	
	private ArrayList<Integer>	visitedCities;
	
	public Ant(Map map, int startPos) {
		this.map = map;
		pos = startPos;
		this.startPos = startPos;
		way = new Way(map.getCityCount());
		visitedCities = new ArrayList<>();
		visitedCities.add(startPos);
	}
	
	public Way walk() {
		
		for (int i = 0; i < map.getCityCount(); i++) {
			choosePath();
		}
		// add last path to complete circle
		way.addPath(map.getPath(startPos, pos));
		return way;
	}
	
	public void nextStep() {
		
		// determine City to go to
		choosePath();
	}
	
	private void choosePath() {
		
		Path[] pa = map.getPaths(pos);
		double sum = 0, d;
		List<ValueSet> availablePaths = new ArrayList<>();
		int i = 0;
		// berechne gesamt Wahrscheinlichkeit
		for (Path p : pa) {
			if (visitedCities.contains(p.city1.nr) || visitedCities.contains(p.city2.nr)) {
				i++;
				continue;
			}
			sum += p.weight;
		}
		
		// prüfe ob noch Städte vorhanden sind
		if (i >= map.getCityCount()) { throw new IllegalStateException("es sind keine Städte mehr vorhanden"); }
		
		// berechne einzel Wahrscheinlichkeiten
		final double DELTA = 1E-7;
		for (Path p : pa) {
			if (visitedCities.contains(p.city1.nr) || visitedCities.contains(p.city2.nr)) {
				continue;
			}
			if (sum - 0 < DELTA) {
				availablePaths.add(new ValueSet(p, p.weight / sum));
			}
		}
		Collections.sort(availablePaths);
		
		// erzeuge Random Wert und wähle Path
		d = Utils.randDouble(0, 1);
		for (ValueSet vs : availablePaths) {
			if (Double.compare(d, vs.probability) < 0) {
				way.addPath(vs.path);
				visitedCities.add(pos);
				if (vs.path.city1.nr == pos) {
					pos = vs.path.city2.nr;
				} else {
					pos = vs.path.city1.nr;
				}
				break;
			}
		}
	}
	
	private class ValueSet implements Comparable<ValueSet> {
		
		public Path		path;
		
		public double	probability;
		
		public ValueSet(Path path, double probability) {
			this.path = path;
			this.probability = probability;
		}
		
		/* (non-Javadoc)
		 * @see java.lang.Comparable#compareTo(java.lang.Object) */
		@Override
		public int compareTo(ValueSet vs) {
			
			return Double.compare(probability, vs.probability);
		}
	}
	
	public Way getWay() {
		
		return way;
	}
}
