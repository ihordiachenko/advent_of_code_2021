import java.io.*;

public class Day1Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_01/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            String line;
            int prevDepth = Integer.MAX_VALUE, result = 0;
            while ((line = br.readLine()) != null) {
                int depth = Integer.parseInt(line);
                result += depth > prevDepth ? 1 : 0;
                prevDepth = depth;
            }

            System.out.println(result);
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}