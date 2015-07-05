package ga;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class Population extends ArrayList<Gene> {

	public Population() {
		
	}

	public void sort() {
		Collections.sort(this);
	}
}
