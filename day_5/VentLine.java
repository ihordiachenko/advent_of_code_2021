import java.util.ArrayList;
import java.util.List;

public class VentLine {
    int x1, x2, y1, y2;

    public VentLine(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    public List<int[]> getPoints(boolean enableDiagonals) {
        var result = new ArrayList<int[]>();
        if (x1 == x2) {
            for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {
                result.add(new int[]{x1, y});
            }
        } else if (y1 == y2) {
            for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {
                result.add(new int[] { x, y1 });
            }
        } else if (enableDiagonals) {
            boolean up = y1 > y2, right = x1 > x2;
            int x = x1, y = y1;
            while (true) {
                result.add(new int[]{x, y});
                if (x == x2 && y == y2) {
                    break;
                }
                x += right ? -1 : 1;
                y +=  up ? -1 : 1;
            }
        }

        return result;
    }
}
