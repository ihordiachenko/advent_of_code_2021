import java.io.*;
import java.util.*;

public class Day11Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_11/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var grid = new ArrayList<List<Integer>>();
            String line;
            while ((line = br.readLine()) != null) {
                var row = new ArrayList<Integer>();
                for (var ch : line.toCharArray()) {
                    row.add((int) (ch - '0'));
                }
                grid.add(row);
            }

            int result = 0;
            for (int day = 0; day < 100; day++) {
                result += simulateRound(grid);
            }

            System.out.println(result);
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int simulateRound(List<List<Integer>> grid) {
        int m = grid.size(), n = grid.get(0).size();
        var flashQ = new LinkedList<int[]>();
        var flashed = new HashSet<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                inc(grid, i, j);
                if (at(grid, i, j) > 9) {
                    int key = i * n + j;
                    flashed.add(key);
                    flashQ.push(new int[]{i, j});
                }
            }
        }

        // BFS
        int result = 0;

        while (!flashQ.isEmpty()) {
            var point = flashQ.pollFirst();
            result++;
            for (int di = 1; di >= -1; di--) {
                for (int dj = 1; dj >= -1; dj--) {
                    int i = point[0]+di, j = point[1]+dj;
                    inc(grid, i, j);

                    if (at(grid, i, j) <= 9) {
                        continue;
                    }
                    int key = i*n + j;
                    if (flashed.contains(key)) {
                        continue;
                    }

                    flashed.add(key);
                    flashQ.push(new int[]{i,j});
                }
            }
        }

        // Reset flashed nodes;
        for (int key: flashed) {
            int i = key/n, j = key%n;
            grid.get(i).set(j, 0);
        }

        return result;
    }

    static int at(List<List<Integer>> grid, int i, int j) {
        if (i < 0 || i >= grid.size() || j < 0 || j >= grid.get(0).size()) {
            return -1;
        }

        return grid.get(i).get(j);
    }

    static void inc(List<List<Integer>> grid, int i, int j) {
        int val = at(grid, i, j);
        if (val < 0) {
            return;
        }
        grid.get(i).set(j, val + 1);
    }
}