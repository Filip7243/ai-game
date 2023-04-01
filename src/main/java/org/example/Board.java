package org.example;

import java.util.Collection;
import java.util.Collections;

public class Board {

    private static final Character[][] board = new Character[4][4];

    public Character[][] getBoard() {
        Character[][] boardCopy = new Character[4][4];
        System.arraycopy(board, 0, boardCopy, 0, 4);

        return boardCopy;
    }

    public void printBoard() {
        System.out.print("   1   2   3   4 \n");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i + 1);

            for (int j = 0; j < board[i].length; j++) {
                String xOnPosition = board[i][j] == null ? " " : board[i][j].toString();
                System.out.print(" [" + xOnPosition + "]");
            }
            System.out.println();
        }
    }
}
