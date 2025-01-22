import java.util.ArrayList;

/**
 * The class WeatherPatterns finds the longest span of days in which
 * each day’s temperature is higher than on the previous day in that sequence.
 *
 * @author Zach Blick
 * @author Josh Little
 */

public class WeatherPatterns {


    /**
     * Longest Warming Trend
     * @param temperatures
     * @return the longest run of days with increasing temperatures
     */
    public static int[] map;

    public static int longestWarmingTrend(int[] temperatures) {
        // Initialize the map that stores current best run for each index
        map = new int[temperatures.length];

        // Create adjacencyList and fill it out with the indicies of lesser temperatures
        ArrayList<ArrayList<Integer>> adjacencyList = new ArrayList<>();
        // Set the starting index and iterate through all temperatures
        for (int i = 0; i < temperatures.length; i++) {
            ArrayList<Integer> temp = new ArrayList<>();
            // For all indicies less than current one, check if the value is also less and add if so
            for (int j = 0; j < i; j++) {
                if (temperatures[j] < temperatures[i])
                    temp.add(j);
            }
            adjacencyList.add(temp);
        }

        int[] map = new int[temperatures.length];
        // For every element in the adjacencyList, iterate through it and find longest path.
        for (int i = 0; i < adjacencyList.size(); i++) {
            findCurrentRun(i, i, adjacencyList, map);
        }

        // Go through all the best runs for each index, find the longest one, and then return it.
        int longestRun = 0;
        for (int i : map)
            longestRun = i > longestRun ? i : longestRun;

        return longestRun;
    }

    public static void findCurrentRun(int oIndex, int cIndex, ArrayList<ArrayList<Integer>> adjacencyList, int[] map) {
        // If the current index already has a calculated longestRun, check if it's longer than the
        // original index's longest or equal and if so, add one and make that original's longest.
        if (map[cIndex] != 0) {
            map[oIndex] = map[cIndex] >= map[oIndex] ? map[cIndex] + 1 : map[oIndex];
            return;
        }
        // If the current index has no elements that are less than it and doesn't have a longest
        // run value already, set its longestRun value to 1.
        if (adjacencyList.get(cIndex).size() == 0) {
            map[cIndex] = 1;
            return;
        }

        // Recurse on all elements that are less than the current index stores in adjacencyList.
        for (Integer i : adjacencyList.get(cIndex)) {
            findCurrentRun(oIndex, i, adjacencyList, map);
        }
    }
}
