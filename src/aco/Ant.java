/**
 * @author Sebastian
 *
 * created 23.06.2015
 * 2015 copyright sebastian koch. all rights reserved
 * mailto: sebastian.koch@mni.thm.de
 */

package aco;

import java.util.ArrayList;

import utils.Utils;
import map.City;
import map.Map;

/**
 * @author Sebastian
 *
 */
public class Ant
{
    private Map                map;
    private int                pos;
    private Way                way;
    private ArrayList<Integer> visitedCities;
    
    public Ant(Map map, int startPos)
    {
        this.map = map;
        pos = startPos;
        way = new Way(map.getCityCount());
        visitedCities = new ArrayList<>();
    }
    
    public void nextStep()
    {
        int i = 0;
        // determine City to go to
        do
        {
            i = Utils.randInt(0, map.getCityCount() + 1);
        } while (visitedCities.contains(i));
    }
}
