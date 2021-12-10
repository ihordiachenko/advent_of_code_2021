import java.io.*;
import java.util.*;

public class SolutionDay9Task2 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_9/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var heatmap = new ArrayList<List<Integer>>();
            String line;
            while ((line = br.readLine()) != null) {
                var row = new ArrayList<Integer>();
                for (var ch : line.toCharArray()) {
                    row.add((int) (ch - '0'));
                }
                heatmap.add(row);
            }

            int m = heatmap.size(), n = heatmap.get(0).size();
            var lowPoints = new ArrayList<int[]>();
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int depth = at(heatmap, i, j);
                    if (depth < at(heatmap, i - 1, j)
                            && depth < at(heatmap, i, j - 1)
                            && depth < at(heatmap, i + 1, j)
                            && depth < at(heatmap, i, j + 1)) {
                        lowPoints.add(new int[]{i,j});
                    }
                }
            }

            var pq = new PriorityQueue<Integer>(Collections.reverseOrder());
            for (var point: lowPoints) {
                pq.offer(dfs(heatmap, point[0], point[1]));
            }

            int result = pq.poll() * pq.poll() * pq.poll();
            System.out.println(result);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int at(List<List<Integer>> heatmap, int i, int j) {
        if (i < 0 || i >= heatmap.size() || j < 0 || j >= heatmap.get(0).size()) {
            return 10;
        }

        return heatmap.get(i).get(j);
    }

    static int dfs(List<List<Integer>> map, int i, int j) {
        if (i < 0 || i >= map.size() || j < 0 || j >= map.get(0).size()) {
            return 0;
        }
        if (at(map, i, j) == 9) {
            return 0;
        }

        map.get(i).set(j, 9);

        return 1 + dfs(map, i-1, j) + dfs(map, i, j-1) + dfs(map, i+1, j) + dfs(map, i, j+1);
    }
}