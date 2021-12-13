import java.io.*;
import java.util.*;

public class Day13Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_13/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var set = new HashSet<Integer>();
            String line;
            while ((line = br.readLine()).length() != 0) {
                var parts = line.split(",");
                set.add(encode(Integer.parseInt(parts[0]), Integer.parseInt(parts[1])));
            }

            line = br.readLine();
            var parts = line.split("=");
            int foldLine = Integer.parseInt(parts[1]);

            if (line.contains("x=")) {
                horizontalFold(set, foldLine);
            } else {
                verticalFold(set, foldLine);
            }

            System.out.println(set.size());

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static void horizontalFold(Set<Integer> set, int x) {
        var toTranslate = new ArrayList<int[]>(); // [x, y, key]
        for (int key: set) {
            var coordinates = decode(key);
            if (coordinates[0] > x) {
                toTranslate.add(new int[]{coordinates[0], coordinates[1], key});
            }
        }

        for (var vector: toTranslate) {
            set.remove(vector[2]);
            int translatedX = x-(vector[0] - x);
            System.out.format("x=%d, translatedX=%d\n", vector[0], translatedX);

            int key = encode(translatedX, vector[1]);
            set.add(key);
        }
    }

    static void verticalFold(Set<Integer> set, int y) {
        var toTranslate = new ArrayList<int[]>(); // [x, y, key]
        for (int key : set) {
            var coordinates = decode(key);
            if (coordinates[1] > y) {
                toTranslate.add(new int[] { coordinates[0], coordinates[1], key });
            }
        }

        for (var vector : toTranslate) {
            set.remove(vector[2]);
            int translatedY = y - (vector[1] - y);
            System.out.format("y=%d, translatedY=%d\n", vector[1], translatedY);

            int key = encode(vector[0], translatedY);
            set.add(key);
        }
    }

    static int encode(int x, int y) {
        return 10000*x + y;
    }

    static int[] decode(int key) {
        return new int[]{key/10000, key%10000};
    }
}