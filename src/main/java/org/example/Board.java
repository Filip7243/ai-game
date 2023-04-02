package org.example;

public class Board implements Playable {

    private final BoardCurrentStateChecker STATE_CHECKER = new BoardCurrentStateChecker(this);
    private static final Character[][] GAME_BOARD = new Character[4][4];
    private static final char MARK = 'X';

    @Override
    public void makeMove(int x, int y) {
        if (!isMarkPlaced(x, y) && !isGameOver()) {
            GAME_BOARD[x][y] = MARK;
        } else {
            throw new IllegalArgumentException("Illegal move! " + x + " " + y);
        }
    }

    @Override
    public boolean isMarkPlaced(int x, int y) {
        return GAME_BOARD[x][y] != null;
    }

    @Override
    public boolean isGameOver() {
        // TODO: check who did last move
        return STATE_CHECKER.checkHorizontals() == 4 || STATE_CHECKER.checkVerticals() == 4 ||
               STATE_CHECKER.checkTopLeftLowRightCross() == 4 || STATE_CHECKER.checkLowLeftTopRightCross() == 4;
    }

    @Override
    public void undoMove(int x, int y) {
        if (isMarkPlaced(x, y)) {
            GAME_BOARD[x][y] = null;
        } else {
            throw new IllegalArgumentException("Can't undo move, because no mark placed!");
        }
    }

    public Character[][] getBoard() {
        Character[][] boardCopy = new Character[4][4];
        System.arraycopy(GAME_BOARD, 0, boardCopy, 0, 4);

        return boardCopy;
    }

    public void printBoard() {
        System.out.print("   1   2   3   4 \n");
        for (int i = 0; i < GAME_BOARD.length; i++) {
            System.out.print(i + 1);

            for (int j = 0; j < GAME_BOARD[i].length; j++) {
                String markOnCurrentPosition = GAME_BOARD[i][j] == null ? " " : GAME_BOARD[i][j].toString();
                System.out.print(" [" + markOnCurrentPosition + "]");
            }
            System.out.println();
        }
    }
}
