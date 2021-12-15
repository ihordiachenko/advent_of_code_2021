import java.io.*;
import java.util.LinkedList;

public class Day1Task2 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_01/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            String line;
            int tripletDepth = 0, result = 0;
            var q = new LinkedList<Integer>();

            while ((line = br.readLine()) != null) {
                int depth = Integer.parseInt(line);
                if (q.size() < 3) {
                    q.add(depth);
                    tripletDepth += depth;
                } else {
                    int prevDepth = tripletDepth;
                    tripletDepth += depth - q.pollFirst();
                    q.add(depth);

                    result += tripletDepth > prevDepth ? 1 : 0;
                }
            }

            System.out.println(result);
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}