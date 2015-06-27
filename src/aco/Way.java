/**
 * @author Sebastian
 *         created 23.06.2015
 *         2015 copyright sebastian koch. all rights reserved
 *         mailto: sebastian.koch@mni.thm.de
 */

package aco;

import map.Path;

/**
 * @author Sebastian
 */
public class Way {
	
	private Path[]	paths;
	
	private int		count;
	
	private double	length;
	
	public Way(int size) {
		paths = new Path[size];
		count = 0;
		length = 0;
	}
	
	public void addPath(Path path) {
		
		paths[count] = path;
		count++;
		length += path.getLength();
	}
	
	public Path getPath(int pos) {
		
		return paths[pos];
	}
	
	public Path[] getPaths() {
		
		return paths;
	}
	
	public double getLength() {
		
		return length;
	}
}
