import java.io.*;
import java.util.regex.Pattern;

public class Day17Task2 {
    final static int bottom = -1000;

    public static void main(String[] args) {
        try {
            // just brute-force it?

            var fs = new FileInputStream("./day_17/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            String regex = "^target area: x=(-?\\d+)\\.\\.(-?\\d+), y=(-?\\d+)\\.\\.(-?\\d+)$";
            var pattern = Pattern.compile(regex);
            var m = pattern.matcher(br.readLine());
            m.find();

            int x1 = Integer.parseInt(m.group(1));
            int x2 = Integer.parseInt(m.group(2));
            int y1 = Integer.parseInt(m.group(3));
            int y2 = Integer.parseInt(m.group(4));
            var targetArea = new TargetArea(x1, x2, y1, y2);

            int result = 0;
            for (int dx = 0; dx <= 1000; dx++) {
                for (int dy = -1000; dy <= 1000; dy++) {
                    // shoot
                    int x = 0, y = 0, vx = dx, vy = dy;
                    while (x <= Math.max(x1, x2) && y > bottom) {
                        x += vx;
                        y += vy;
                        if (targetArea.contains(x, y)) {
                            result++;
                            break;
                        }
                        vx = Math.max(vx - 1, 0);
                        vy--;
                    }
                }
            }

            System.out.println(result);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}