import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SolutionDay3Task2 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_3/2/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            int bitLen = 0;
            var nums = new ArrayList<Integer>();
            String line;

            while ((line = br.readLine()) != null) {
                bitLen = line.length();
                int num = Integer.parseInt(line, 2);
                nums.add(num);
            }

            int oxygenRating = 0;
            var oxygenMeasures = new HashSet<Integer>(nums);
            for (int i = bitLen-1; i >= 0; i--) {
                int ones = countOnes(oxygenMeasures, i);
                var validMeasures = new HashSet<Integer>();
                int targetBit = ones*2 >= oxygenMeasures.size() ? 1 : 0;
                for (int measure: oxygenMeasures) {
                    if (((measure>>i)&1) == targetBit) {
                        validMeasures.add(measure);
                    }
                }

                if (validMeasures.size() == 1) {
                    oxygenRating = (int)(validMeasures.toArray()[0]);
                    break;
                }

                oxygenMeasures = validMeasures;
            }

            int co2Rating = 0;
            var co2Measures = new HashSet<Integer>(nums);
            for (int i = bitLen - 1; i >= 0; i--) {
                int ones = countOnes(co2Measures, i);
                var validMeasures = new HashSet<Integer>();
                int targetBit = ones*2 >= co2Measures.size() ? 0 : 1;
                for (int measure : co2Measures) {
                    if (((measure>>i)&1) == targetBit) {
                        validMeasures.add(measure);
                    }
                }

                if (validMeasures.size() == 1) {
                    co2Rating = (int) (validMeasures.toArray()[0]);
                    break;
                }

                co2Measures = validMeasures;
            }

            System.out.println(oxygenRating * co2Rating);
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int countOnes(Set<Integer> nums, int offset) {
        int count = 0;
        for (int num: nums) {
            count += (num >> offset) & 1;
        }

        return count;
    }
}
