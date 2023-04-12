package org.example;

import java.util.Arrays;

public class Board implements Playable {

    private static final int BOARD_WIDTH = 4;
    private final BoardCurrentStateChecker STATE_CHECKER = new BoardCurrentStateChecker(this);
    private static final Character[][] GAME_BOARD = new Character[BOARD_WIDTH][BOARD_WIDTH];
    private static final char MARK = 'X';

    @Override
    public void makeMove(int x, int y) {
        if (!isMarkPlaced(x, y)) {
            GAME_BOARD[x][y] = MARK;
        } else {
            System.out.println("IS AMRKED PLACED = " + isMarkPlaced(x, y));
            System.out.println(GAME_BOARD[x][y]);
            System.out.println("IS GAME OVER = " + isGameOver());
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

        int[] horizontals = STATE_CHECKER.checkHorizontals();
        int[] verticals = STATE_CHECKER.checkVerticals();

        for (int i = 0; i < horizontals.length; i++) {
            if (horizontals[i] == BOARD_WIDTH || verticals[i] == BOARD_WIDTH) {
                return true;
            }
        }

        return STATE_CHECKER.checkTopLeftLowRightCross() == BOARD_WIDTH || STATE_CHECKER.checkLowLeftTopRightCross() == BOARD_WIDTH;
    }

    @Override
    public void undoMove(int x, int y) {
        if (isMarkPlaced(x, y)) {
            GAME_BOARD[x][y] = null;
        } else {
            throw new IllegalArgumentException("Can't undo move, because no mark placed!");
        }
    }

    public void printBoard() {
        System.out.print("   0   1   2   3 \n");

        for (int i = 0; i < BOARD_WIDTH; i++) {
            System.out.print(i);

            for (int j = 0; j < BOARD_WIDTH; j++) {
                String markOnCurrentPosition = GAME_BOARD[i][j] == null ? " " : GAME_BOARD[i][j].toString();
                System.out.print(" [" + markOnCurrentPosition + "]");
            }
            System.out.println();
        }
    }

    public int getBoardWidth() {
        return BOARD_WIDTH;
    }
}
