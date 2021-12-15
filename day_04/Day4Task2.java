import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Day4Task2 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_04/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var draws = new ArrayList<Integer>();
            String line = br.readLine();
            for (var draw: line.split(",")) {
                draws.add(Integer.parseInt(draw));
            }

            var boardsByValue = new HashMap<Integer, List<Board>>();

            while ((line = br.readLine()) != null) {
                var board = new Board();

                for (int i = 0; i < 5; i++) {
                    line = br.readLine();
                    // System.out.println(line);
                    var row = line.trim().split("\\s+");
                    for (int j = 0; j < 5; j++) {
                        int draw = Integer.parseInt(row[j]);
                        board.setValue(i, j, draw);
                        var list = boardsByValue.getOrDefault(draw, new ArrayList<Board>());
                        list.add(board);
                        boardsByValue.put(draw, list);
                    }
                }
            }

            var winners = new HashSet<Board>();
            int lastScore = 0;
            for (int draw: draws) {
                for (var board: boardsByValue.getOrDefault(draw, new ArrayList<>())) {
                    if (winners.contains(board)) {
                        continue;
                    }
                    if (board.markDraw(draw)) {
                        winners.add(board);
                        lastScore = draw*board.getScore();
                    }
                }
            }

            System.out.println(lastScore);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}