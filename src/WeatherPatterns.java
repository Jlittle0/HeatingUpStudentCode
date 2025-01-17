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

    public static int longestWarmingTrend(int[] temperatures) {
        int[] temps = temperatures;
        int size = temperatures.length;

        int longestRun = 0;

        // Choose a starting temperature represented by i
        for (int i = 0; i < size - longestRun; i++) {
            // Create a map to store longest run at each individual index
            int[] map = new int[180];
            // Iterate through the rest of the temperatures that are larger than starting location
            for (int j = i; j < size; j++) {
                if (temps[j] >= temps[i]) {
                    map[temps[j] + 50] = findCurrentRun(j, temps, map, longestRun);
                    longestRun = map[temps[j] + 50] > longestRun ? map[temps[j] + 50] : longestRun;
                }
            }
        }

        return longestRun;
    }

    public static int findCurrentRun(int end, int[] temps, int[] map, int longestRun) {
        // Sets current run to 0 and adds offset to current temp
        int currentRun = 0;
        int currentTemp = temps[end] + 50;
        // Runs through runs for each temperature to find the best overall less than current temp.
        for (int i = longestRun; i < currentTemp; i++) {
            if (map[i] > currentRun)
                currentRun = map[i];
        }
        // Returns the previous longest below the current temperature + 1.
        return currentRun + 1;
    }

    // Concept code for aforementioned new idea
    public static int testRun(int[] temperatures) {
        int longestRun = 0;
        // Don't know how to do arraylists so just doing this for now
        int[][] map = new int[180][temperatures.length];

        // Creates a map of all temperatures for each given
        for (int i = 0; i < temperatures.length; i++)
            for (int j = 0; j < temperatures[i] + 50; j++)
                map[j] = temperatures[i];


        return longestRun;
    }

    // Bunch of comments from original ideas:

    // Potential idea is to run through the entirety of the data, record it into a 2D map
    // that stores the temperatures at the indicies and then their locations in a second
    // array inside each of the indicies (or arrayList to be more specific). Then, go from
    // temperature to temperature, finding the longest run with that as the starting temp.
    // Only need to start at each temperature once, because there's no reason to skip the
    // rest

    // Maybe keep a duplicate map that stores whether or not a location has already been visited to limit the number of paths that
    // can be produced from that location or keep a score and the number of potential things after it at that point that are bigger.


    // The one condition where you would want to skip a number that's greater than the
    // current value if it's not just the next greatest, would be if its present after the next greatest that's later on.

    // For example, if the current temp was 56 degrees and there was 58 degrees right next to it, if
    // there was a 57 later on with a 58 after it as well, then you could skip.

    // Highest string below it that's below the actual number and then add +1
    // create a map that stores the longest current run for specific values. For example,
    // if there were the number 1 4 7 20 9, you would go that the max is 3 for 7
    // so when you reach 20, the answer would be 4, but when you reach 9, it would also
    // be 4 because the longest run less than 9 would be stored at 7 which is 3.
    // 1 3 2 3 4


    // Offset everything from -50 to 130 to start at 0
    // Map that stores the greatest current run that ends with a temperature correlated to
    // the index of the map with an offset of 50 to fit all temps from -50° to 130°.

    // A new idea is to only be checking relevant temperatures instead of interative through
    // a ton and the idea of doing that is to create a map that stores a bunch of arraylists
    // and then I want to interate through all the temperatures held in the array, and then
    // add the index of that temperature to every arraylist that is placed in an index in my map
    // with a lower value that would indicate the temperature is greater than that temperature.

    // In this case, if my starting temperature was 70°, the location on the map for that
    // temperature would only hold the indicies of numbers in temps that are greater than
    // 70° so that I don't have to do so many checks for every number.


    // Only check temperatures that are greater than the current starting point.

}
