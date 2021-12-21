import java.io.*;
import java.util.ArrayList;

public class Day18Task2 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_18/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            String line;
            var nums = new ArrayList<SnailfishNumber>();
            while ((line = br.readLine()) != null) {
                nums.add(SnailfishNumber.parse(line));
            }

            int maxMagnitude = 0;
            for (int i = 0; i < nums.size(); i++) {
                for (int j = 0; j < nums.size(); j++) {
                    if (i == j) {
                        continue;
                    }

                    var sum = nums.get(i).clone().add(nums.get(j).clone());
                    maxMagnitude = Math.max(maxMagnitude, sum.calcMagnitude());
                }
            }

            System.out.println(maxMagnitude);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}