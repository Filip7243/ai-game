package org.example;

public class BoardCurrentStateChecker {

    private Board board;

    public BoardCurrentStateChecker(Board board) {
        this.board = board;
    }

    public int[] checkHorizontals() {
        int horizontalCounter = 0;
        int boardWidth = board.getBoardWidth();
        int[] resultForHorizontals = new int[boardWidth];

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board.isMarkPlaced(i, j)) {
                    horizontalCounter++;
                }
            }

            resultForHorizontals[i] = horizontalCounter;
            horizontalCounter = 0;
        }

        return resultForHorizontals;
    }

    public int[] checkVerticals() {
        int verticalCounter = 0;
        int boardWidth = board.getBoardWidth();
        int[] resultForColumns = new int[boardWidth];

        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (board.isMarkPlaced(j, i)) {
                    verticalCounter++;
                }
            }
            resultForColumns[i] = verticalCounter;
            verticalCounter = 0;
        }

        return resultForColumns;
    }

    public int checkTopLeftLowRightCross() {
        int crossCounter = 0;

        for (int i = 0; i < board.getBoardWidth(); i++) {
            if (board.isMarkPlaced(i, i)) {
                crossCounter++;
            }
        }

        return crossCounter;
    }

    public int checkLowLeftTopRightCross() {
        int crossCounter = 0;
        int boardWidth = board.getBoardWidth();

        for (int i = 0; i < boardWidth; i++) {
            if (board.isMarkPlaced(boardWidth - 1, i)) {
                crossCounter++;
                boardWidth--;
            }
        }

        return crossCounter;
    }
}
