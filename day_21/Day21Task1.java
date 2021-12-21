import java.io.*;

class DeterministicDie {
    int nextVal = 1;
    final int maxVal = 100;
    int rolls = 0;

    public int roll() {
        int result = nextVal;
        nextVal = result == 100? 1 : result+1;
        rolls++;

        return result;
    }

    public int getNumberOfRolls() {
        return rolls;
    }
}

public class Day21Task1 {
    public static void main(String[] args) {
        try {
            var fs = new FileInputStream("./day_21/input.txt");
            var br = new BufferedReader(new InputStreamReader(fs));

            int player1Position = Integer.parseInt(br.readLine().split(": ")[1]);
            int player2Position = Integer.parseInt(br.readLine().split(": ")[1]);
            var die = new DeterministicDie();
            int player1Score = 0, player2Score = 0;

            while (true) {
                player1Position = makeTurn(player1Position, die.roll() + die.roll() + die.roll());
                player1Score += player1Position;
                if (player1Score >= 1000) {
                    break;
                }

                player2Position = makeTurn(player2Position, die.roll() + die.roll() + die.roll());
                player2Score += player2Position;
                if (player2Score >= 1000) {
                    break;
                }
            }

            int result = die.getNumberOfRolls()*Math.min(player1Score, player2Score);
            System.out.println(result);

            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    static int makeTurn(int start, int move) {
        int position = start+move;
        return position % 10 == 0 ? 10 : position%10;
    }
}