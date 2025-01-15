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
//        for (int e : temps)
//            System.out.print(e + " ");
//        System.out.println();
//        System.out.println("--------------------------------------------------------------------------------");
        int size = temperatures.length;


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


        // Only check temperatures that are greater than the current starting point.
        int longestRun = 0;

        for (int i = 0; i < size - longestRun; i++) {
            int[] map = new int[181];
            // Iterate through the rest of the temperatures from the starting location i but
            // only check for values larger than the starting point
            for (int j = i; j < size; j++) {
//                System.out.println("Current Number: " + j + " temp: " + temps[j]);
                if (temps[j] >= temps[i]) {
                    map[temps[j] + 50] = findCurrentRun(j, temps, map, longestRun);
                    longestRun = map[temps[j] + 50] > longestRun ? map[temps[j] + 50] : longestRun;
                }
//                System.out.println("--------------------------------------------------------------------------------");
            }
//            System.out.println("best run: " + longestRun);
        }

        // TODO: Write your code here!

        return longestRun;
    }

    public static int findCurrentRun(int end, int[] temps, int[] map, int longestRun) {
//        System.out.print("Temperatures: ");
        for (int i = 0; i < end; i++) {
//            System.out.print(temps[i] + " | ");
        }
//        System.out.println();
        int currentRun = 0;
        int currentTemp = temps[end] + 50;
//        System.out.println("Current Temp: " + currentTemp);
        for (int i = longestRun; i < currentTemp; i++) {
            if (map[i] > currentRun)
                currentRun = map[i];
        }
//        System.out.println("current best run: " + (currentRun + 1));
        return currentRun + 1;
    }


}
