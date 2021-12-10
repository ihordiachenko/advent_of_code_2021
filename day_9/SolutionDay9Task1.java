import java.io.*;
import java.util.*;

public class SolutionDay9Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_9/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var heatmap = new ArrayList<List<Integer>>();
            String line;
            while ((line = br.readLine()) != null) {
                var row = new ArrayList<Integer>();
                for (var ch: line.toCharArray()) {
                    row.add((int)(ch-'0'));
                }
                heatmap.add(row);
            }

            int m = heatmap.size(), n = heatmap.get(0).size();
            int riskScore = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    int depth = at(heatmap, i, j);
                    if (
                        depth < at(heatmap, i-1, j)
                        && depth < at(heatmap, i, j-1)
                        && depth < at(heatmap, i+1, j)
                        && depth < at(heatmap, i, j+1)
                    ) {
                        riskScore += depth+1;
                    }
                }
            }

            System.out.println(riskScore);
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
}