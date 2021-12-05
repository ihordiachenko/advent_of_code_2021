import java.io.*;
import java.util.HashMap;
import java.util.regex.Pattern;

public class SolutionDay5Task2 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_5/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var regex = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");
            var linesByCoordinate = new HashMap<String, Integer>();

            String line;
            while ((line = br.readLine()) != null) {
                var m = regex.matcher(line);
                m.find();

                int x1 = Integer.parseInt(m.group(1));
                int y1 = Integer.parseInt(m.group(2));
                int x2 = Integer.parseInt(m.group(3));
                int y2 = Integer.parseInt(m.group(4));

                for (int[] point: new VentLine(x1, y1, x2, y2).getPoints(true)) {
                    var key = String.format("%d:%d", point[0], point[1]);
                    linesByCoordinate.put(key, linesByCoordinate.getOrDefault(key, 0)+1);
                }
            }

            int score = 0;
            for (int val: linesByCoordinate.values()) {
                score += val >= 2 ? 1 : 0;
            }

            System.out.println(score);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}