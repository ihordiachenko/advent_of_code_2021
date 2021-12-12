import java.io.*;
import java.util.*;

public class Day12Task1 {
    public static void main(String[] args) {
        try {
            /**
             * Step 1: build a graph
             * Step 2: find all paths using backtracking
             */
            var fs = new FileInputStream("./day_12/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var adj = new HashMap<String, List<String>>();
            String line;
            while ((line = br.readLine()) != null) {
                var pair = line.split("-");
                addEdge(adj, pair[0], pair[1]);
                addEdge(adj, pair[1], pair[0]);
            }

            var visitedSmall = new HashSet<String>();
            int result = backtrack(adj, "start", visitedSmall);

            System.out.println(result);
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int backtrack(
        HashMap<String, List<String>> adj,
        String node,
        Set<String> visitedSmall
    ) {
        if (node.equals("end")) {
            return 1;
        }
        if (visitedSmall.contains(node)) {
            return 0;
        }

        if (isSmallCave(node)) {
            visitedSmall.add(node);
        }

        int result = 0;
        for (var adjNode: adj.get(node)) {
            result += backtrack(adj, adjNode, visitedSmall);
        }

        if (isSmallCave(node)) {
            visitedSmall.remove(node);
        }

        return result;
    }

    static boolean isSmallCave(String node) {
        return Character.isLowerCase(node.charAt(0));
    }

    static void addEdge(Map<String, List<String>> adj, String u, String v) {
        var adjList = adj.getOrDefault(u, new ArrayList<String>());
        adjList.add(v);
        adj.put(u, adjList);
    }
}