package org.example;

public class BoardCurrentStateChecker {

    private Board board;

    public BoardCurrentStateChecker(Board board) {
        this.board = board;
    }

    public int checkHorizontals() {
        int horizontalCounter = 0;

        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if (board.isMarkPlaced(i, j)) {
                    horizontalCounter++;
                }
            }

            if (horizontalCounter == 4) {
                break;
            } else {
                horizontalCounter = 0;
            }
        }

        return horizontalCounter;
    }

    public int checkVerticals() {
        int verticalCounter = 0;

        for (int i = 0; i < board.getBoard().length; i++) {
            for (int j = 0; j < board.getBoard()[i].length; j++) {
                if (board.isMarkPlaced(j, i)) {
                    verticalCounter++;
                }

                if (j == 3) {
                    if (verticalCounter == 4) {
                        break;
                    } else {
                        verticalCounter = 0;
                    }
                }
            }
        }

        return verticalCounter;
    }

    public int checkTopLeftLowRightCross() {
        int crossCounter = 0;

        for (int i = 0; i < board.getBoard().length; i++) {
            if (board.isMarkPlaced(i, i)) {
                crossCounter++;
            }
        }

        return crossCounter;
    }

    public int checkLowLeftTopRightCross() {
        int crossCounter = 0;
        int boardLength = board.getBoard().length;

        for (int i = 0; i < board.getBoard().length; i++) {
            if (board.isMarkPlaced(boardLength - 1, i)) {
                crossCounter++;
                boardLength--;
            }
        }

        return crossCounter;
    }
}
