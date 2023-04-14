package org.example.board;

import org.example.Game;

import java.util.List;

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
            throw new IllegalArgumentException("Illegal move! " + x + " " + y);
        }

        if (Game.currentPlayer.equalsIgnoreCase("p")) {
            Game.currentPlayer = "C";
        } else if (Game.currentPlayer.equalsIgnoreCase("c")) {
            Game.currentPlayer = "P";
        }
    }

    @Override
    public boolean isMarkPlaced(int x, int y) {
        return GAME_BOARD[x][y] != null;
    }

    @Override
    public boolean isGameOver() {
        List<Integer> horizontals = STATE_CHECKER.checkHorizontals();
        List<Integer> verticals = STATE_CHECKER.checkVerticals();
        if (horizontals.contains(BOARD_WIDTH) || verticals.contains(BOARD_WIDTH)) {
            return true;
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

        if (Game.currentPlayer.equalsIgnoreCase("p")) {
            Game.currentPlayer = "C";
        } else if (Game.currentPlayer.equalsIgnoreCase("c")) {
            Game.currentPlayer = "P";
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
