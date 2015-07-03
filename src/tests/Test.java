package tests;

import static org.junit.Assert.*;
import aco.Ant;
import aco.Way;
import map.Map;
import map.Path;

public class Test {

	@org.junit.Test
	public void testAnt() {
		Map map = new Map(100, 100);
		map.addCity(1, 1, 1);
		map.addCity(1, 2, 2);
		map.createAllPaths();
		
		Ant ant = new Ant(map, 1);
		Way way = ant.walk();
		Path[] paths = way.getPaths();
		
		assertEquals(paths.length, 2);
		for(Path path:paths){
			assertNotNull(path);
		}
		
		assertSame(paths[0], paths[1]);
	}
		

}
