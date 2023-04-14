package org.example;

import java.util.ArrayList;
import java.util.List;

public class BoardCurrentStateChecker {

    private Board board;

    public BoardCurrentStateChecker(Board board) {
        this.board = board;
    }

    public List<Integer> checkHorizontals() {
        int horizontalCounter = 0;
        int boardWidth = board.getBoardWidth();
        List<Integer> resultForHorizontals = new ArrayList<>(boardWidth);

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board.isMarkPlaced(i, j)) {
                    horizontalCounter++;
                }
            }

            resultForHorizontals.add(horizontalCounter);
            horizontalCounter = 0;
        }

        return resultForHorizontals;
    }

    public List<Integer> checkVerticals() {
        int verticalCounter = 0;
        int boardWidth = board.getBoardWidth();
        List<Integer> resultForColumns = new ArrayList<>(boardWidth);

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board.isMarkPlaced(j, i)) {
                    verticalCounter++;
                }
            }

            resultForColumns.add(verticalCounter);
            verticalCounter = 0;
        }

        return resultForColumns;
    }

    public int checkTopLeftLowRightCross() {
        int crossCounter = 0;
        int boardWidth = board.getBoardWidth();

        for (int i = 0; i < boardWidth; i++) {
            if (board.isMarkPlaced(i, i)) {
                crossCounter++;
            }
        }

        return crossCounter;
    }

    public int checkLowLeftTopRightCross() {
        int crossCounter = 0;
        int boardWidth = board.getBoardWidth();
        int temp = boardWidth;

        for (int i = 0; i < boardWidth; i++) {
            if (board.isMarkPlaced(--temp, i)) {
                crossCounter++;
            }
        }

        return crossCounter;
    }
}
