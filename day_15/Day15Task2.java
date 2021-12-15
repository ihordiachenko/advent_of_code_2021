import java.io.*;
import java.util.*;

class QItem {
    public int i;
    public int j;
    public int risk;

    public QItem(int i, int j, int risk) {
        this.i = i;
        this.j = j;
        this.risk = risk;
    }
}

public class Day15Task2 {
    static final int INF = Integer.MAX_VALUE;
    static final int SCALE = 5;

    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_15/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            List<List<Integer>> matrix = new ArrayList<List<Integer>>();
            String line;
            while ((line = br.readLine()) != null) {
                var row = new ArrayList<Integer>();
                for (var ch : line.toCharArray()) {
                    row.add(Character.getNumericValue(ch));
                }
                matrix.add(row);
            }
            // Use good old Djikstra to find the optimal path
            int m = SCALE*matrix.size(), n = SCALE*matrix.get(0).size();

            var risk = new int[m][n];
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    risk[i][j] = INF;
                }
            }
            var pq = new PriorityQueue<QItem>((a, b) -> a.risk - b.risk);
            // risk of the starting point is ignored as stated in the task
            pq.offer(new QItem(0, 0, 0));

            var moves = new int[][] { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
            while (!pq.isEmpty()) {
                var item = pq.poll();
                if (risk[item.i][item.j] != INF) {
                    continue;
                }

                risk[item.i][item.j] = item.risk;
                if (item.i == m - 1 && item.j == n - 1) {
                    break;
                }

                for (var move : moves) {
                    int nextI = item.i + move[0], nextJ = item.j + move[1];
                    if (nextI < 0 || nextI >= m || nextJ < 0 || nextJ >= n) {
                        continue;
                    }
                    if (risk[nextI][nextJ] != INF) {
                        continue;
                    }

                    pq.add(new QItem(nextI, nextJ, item.risk + at(matrix, nextI, nextJ)));
                }
            }

            System.out.println(risk[m - 1][n - 1]);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int at(List<List<Integer>> matrix, int i, int j) {
        int m = matrix.size(), n = matrix.get(0).size();
        int value = matrix.get(i%m).get(j%n);
        for (int k = 1; k <= i/m; k++) {
            value = value == 9 ? 1 : value+1;
        }
        for (int k = 1; k <= j/n; k++) {
            value = value == 9 ? 1 : value + 1;
        }

        return value;
    }
}