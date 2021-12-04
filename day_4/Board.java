public class Board {
    int[][] board = new int[5][5];
    int[] rowMatches = new int[5];
    int[] colMatches = new int[5];

    public void setValue(int i, int j, int value) {
        board[i][j] = value;
    }

    public boolean markDraw(int value) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == value) {
                    board[i][j] = -1;
                    rowMatches[i]++;
                    colMatches[j]++;

                    return rowMatches[i] == 5 || colMatches[j] == 5;
                }
            }
        }
        return false;
    }

    public int getScore() {
        int sum = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] != -1) {
                    sum += board[i][j];
                }
            }
        }

        return sum;
    }
}
