import java.io.*;
import java.util.ArrayList;

public class Day7Task2 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_07/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            String line = br.readLine();
            br.close();

            // Just bruteforce it!...or use binary search?
            int minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
            var coordinates = new ArrayList<Integer>();
            for (var raw : line.split(",")) {
                int coordinate = Integer.parseInt(raw);
                minValue = Math.min(minValue, coordinate);
                maxValue = Math.max(maxValue, coordinate);
                coordinates.add(coordinate);
            }

            int minFuelSpent = Integer.MAX_VALUE;
            for (int target = minValue; target <= maxValue; target++) {
                int fuelSpent = 0;
                for (int coordinate : coordinates) {
                    int distance = Math.abs(target - coordinate);
                    // algebraic seq sum
                    fuelSpent += ((1+distance)*distance)/2;
                }
                minFuelSpent = Math.min(minFuelSpent, fuelSpent);
            }

            System.out.println(minFuelSpent);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}