import java.io.*;

public class Day18Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_18/test.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            String line;
            SnailfishNumber sum = null;
            while ((line = br.readLine()) != null) {
                var num = SnailfishNumber.parse(line);
                sum = sum == null ? num : sum.add(num);
            }

            System.out.println(sum.calcMagnitude());

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}