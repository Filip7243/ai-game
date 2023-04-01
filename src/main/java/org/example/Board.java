package org.example;

public class Board implements Playable {

    private final BoardCurrentStateChecker STATE_CHECKER = new BoardCurrentStateChecker(this);
    private static final Character[][] BOARD = new Character[4][4];
    private static final char MARK = 'X';

    @Override
    public void makeMove(int x, int y) {
        if (!isMarkPlaced(x, y) && !isGameOver()) {
            BOARD[x][y] = MARK;
        } else {
            throw new IllegalArgumentException("Illegal move! " + x + " " + y);
        }
    }

    @Override
    public boolean isMarkPlaced(int x, int y) {
        return BOARD[x][y] != null;
    }

    @Override
    public boolean isGameOver() {
        return STATE_CHECKER.checkHorizontals() || STATE_CHECKER.checkVerticals();
    }

    public Character[][] getBoard() {
        Character[][] boardCopy = new Character[4][4];
        System.arraycopy(BOARD, 0, boardCopy, 0, 4);

        return boardCopy;
    }

    public void printBoard() {
        System.out.print("   1   2   3   4 \n");
        for (int i = 0; i < BOARD.length; i++) {
            System.out.print(i + 1);

            for (int j = 0; j < BOARD[i].length; j++) {
                String markOnCurrentPosition = BOARD[i][j] == null ? " " : BOARD[i][j].toString();
                System.out.print(" [" + markOnCurrentPosition + "]");
            }
            System.out.println();
        }
    }
}
