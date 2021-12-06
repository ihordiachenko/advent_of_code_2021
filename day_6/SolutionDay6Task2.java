import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class SolutionDay6Task2 {
    static final int lastDay = 256;

    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_6/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            String line = br.readLine();
            br.close();

            var states = new ArrayList<Integer>();
            for (var fish : line.split(",")) {
                states.add(Integer.parseInt(fish));
            }

            var dp = new long[7];
            var memo = new HashMap<Integer, Long>();
            for (int i = 1; i <= 6; i++) {
                dp[i] = sumOfFish(i, 0, memo);
            }

            long score = 0;
            for (int state : states) {
                score += dp[state];
            }

            System.out.println(score);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static long sumOfFish(int state, int currentDay, Map<Integer, Long> memo) {
        int key = 10000 * currentDay + state;
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        // 0 1 2 3 4 5 [6] -> breeding day
        long sum = 1; // self
        for (int day = currentDay + state + 1; day <= lastDay; day += 7) {
            sum += sumOfFish(8, day, memo);
        }

        memo.put(key, sum);

        return sum;
    }
}