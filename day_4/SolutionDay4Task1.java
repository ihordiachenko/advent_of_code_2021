import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SolutionDay4Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_4/input.txt");
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

            int score = 0;
outer:      for (int draw: draws) {
                for (var board: boardsByValue.getOrDefault(draw, new ArrayList<>())) {
                    if (board.markDraw(draw)) {
                        score = draw*board.getScore();
                        break outer;
                    }
                }
            }

            System.out.println(score);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}