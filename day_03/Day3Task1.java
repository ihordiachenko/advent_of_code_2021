import java.io.*;

public class Day3Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_03/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var ones = new int[31];
            int n = 0, bitLen = 0;
            String line;

            while ((line = br.readLine()) != null) {
                bitLen = line.length();
                int num = Integer.parseInt(line, 2);
                for (int i = 0; i < bitLen; i++) {
                    ones[i] += (num >> i) & 1;
                }
                n++;
            }

            int gamma = 0, epsilon = 0;
            for (int i = 0; i < bitLen; i++) {
                boolean oneIsMoreCommon = ones[i] > n/2;
                gamma |= oneIsMoreCommon ? (1 << i) : 0;
                epsilon |= oneIsMoreCommon ? 0 : (1 << i);
            }

            System.out.println(gamma*epsilon);
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}