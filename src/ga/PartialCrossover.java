package ga;

import java.util.HashMap;
import java.util.Map;

public class PartialCrossover extends Recombinator {

	@Override
	public Gene[] recombinate(Gene gene1, Gene gene2) {
		gene1 = gene1.copy();
		gene2 = gene2.copy();

		// Zufällige Teilsequenz bestimmen
		int pos1 = Utils.randInt(0, gene1.length() - 1);
		int pos2 = Utils.randInt(pos1 + 1, gene1.length());
		short[] part1 = gene1.get(pos1, pos2);
		short[] part2 = gene2.get(pos1, pos2);

		// Zuordnungen bestimmen
		Map<Short, Short> mapping = new HashMap<>();
		for (int i = 0; i < part1.length; i++) {
			if(mapping.containsKey(part1[i]) || mapping.containsKey(part2[i]))
				continue;
			mapping.put(part1[i], part2[i]);
			mapping.put(part2[i], part1[i]);
		}

		// Beie Gene entsprechend den Zuordnungen abändern
		for (int i = 0; i < gene1.length(); i++) {			
			if (mapping.containsKey(gene1.get(i)))
				gene1.set(i, mapping.get(gene1.get(i)));
			if (mapping.containsKey(gene2.get(i)))
				gene2.set(i, mapping.get(gene2.get(i)));
		}
		
		return new Gene[] { gene1, gene2 };		
	}
	
	private static void check(Gene gene){
		for(short nr=1; nr <=gene.length(); nr++){
			boolean found=false;			
			for(int i=0; i<gene.length();i++){
				if(gene.get(i) == nr){
					found=true;
					break;
				}					
			}
			if(!found)
				throw new IllegalStateException("GEN FALSCH: " + nr+ " nicht gefunden!");
		}
	}

}
