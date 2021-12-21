import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Day21Task2 {
    public static void main(String[] args) {
        try {
            // Use backtracking?
            var fs = new FileInputStream("./day_21/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            var positions = new int[] {
                Integer.parseInt(br.readLine().split(": ")[1]),
                Integer.parseInt(br.readLine().split(": ")[1])
            };
            var scores = new int[]{0,0};
            var wins = dp(positions, scores, 0, new HashMap<>());

            long result = Math.max(wins[0], wins[1]);
            System.out.println(result);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static long[] dp(int[] positions, int[] scores, int playerId, Map<String, long[]> memo) {
        var key = String.format(
            "%d:%d:%d:%d:%d",
            positions[0], positions[1], scores[0], scores[1], playerId
        );
        if (memo.containsKey(key)) {
            return memo.get(key);
        }

        var result = new long[]{0,0};
        for (int roll1 = 1; roll1 <= 3; roll1++) {
            for (int roll2 = 1; roll2 <= 3; roll2++) {
                for (int roll3 = 1; roll3 <= 3; roll3++) {
                    int oldPosition = positions[playerId], oldScore = scores[playerId];
                    positions[playerId] = makeTurn(oldPosition, roll1+roll2+roll3);
                    scores[playerId] += positions[playerId];

                    if (scores[playerId] >= 21) {
                        result[playerId]++;
                    } else {
                        var res = dp(positions, scores, (playerId+1)%2, memo);
                        result[0] += res[0];
                        result[1] += res[1];
                    }

                    positions[playerId] = oldPosition;
                    scores[playerId] = oldScore;
                }
            }
        }

        memo.put(key, result);

        return result;
    }

    static int makeTurn(int start, int move) {
        int position = start + move;
        return position % 10 == 0 ? 10 : position % 10;
    }
}