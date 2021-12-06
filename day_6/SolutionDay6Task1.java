import java.io.*;
import java.util.ArrayList;

public class SolutionDay6Task1 {
    static final int lastDay = 80;

    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_6/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            String line = br.readLine();
            br.close();

            var states = new ArrayList<Integer>();
            for (var fish: line.split(",")) {
                states.add(Integer.parseInt(fish));
            }

            var dp = new int[7];
            for (int i = 1; i <= 6; i++) {
                dp[i] = sumOfFish(i, 0);
            }

            int score = 0;
            for (int state: states) {
                score += dp[state];
            }

            System.out.println(score);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int sumOfFish(int state, int currentDay) {
        // 0 1 2 3 4 5 [6] -> breeding day
        int sum = 1; // self
        for (int day = currentDay+state+1; day <= lastDay; day += 7) {
            sum += sumOfFish(8, day);
        }

        return sum;
    }
}