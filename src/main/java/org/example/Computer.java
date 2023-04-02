package org.example;

public class Computer implements AI {

    @Override
    public int minMax(Board board, int depth, boolean isMax) {
        int currentStateOfBoard = getCurrentStateOfBoard(board);
        Character[][] gameBoard = board.getBoard();

        if (Math.abs(currentStateOfBoard) == 1 || depth == 0 || board.isGameOver()) {
            return currentStateOfBoard;
        }

        if (isMax) {
            int bestValue = Integer.MIN_VALUE;

            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {
                    if (!board.isMarkPlaced(i, j)) {
                        board.makeMove(i, j);
                        bestValue = Math.max(bestValue, minMax(board, depth - 1, false));
                        board.undoMove(i, j);
                    }
                }
            }

            return bestValue;
        } else {
            int worstValue = Integer.MAX_VALUE;

            for (int i = 0; i < gameBoard.length; i++) {
                for (int j = 0; j < gameBoard[i].length; j++) {
                    if (!board.isMarkPlaced(i, j)) {
                        board.makeMove(i, j);
                        worstValue = Math.min(worstValue, minMax(board, depth - 1, true));
                        board.undoMove(i, j);
                    }
                }
            }

            return worstValue;
        }
    }

    @Override
    public int[] findBestMove(Board board) {
        int[] bestMoveCoordinates = new int[2];
        int bestValue = Integer.MIN_VALUE;

        Character[][] gameBoard = board.getBoard();

        for (int i = 0; i < gameBoard.length; i++) {
            for (int j = 0; j < gameBoard[i].length; j++) {
                if (!board.isMarkPlaced(i, j)) {
                    board.makeMove(i, j);
                    int valueAfterMove = minMax(board, 6, false);
                    board.undoMove(i, j);
                    if (valueAfterMove > bestValue) {
                        bestMoveCoordinates[0] = i;
                        bestMoveCoordinates[1] = j;

                        bestValue = valueAfterMove;
                    }
                }
            }
        }

        return bestMoveCoordinates;
    }

    @Override
    public int getCurrentStateOfBoard(Board currentBoardState) {
        BoardCurrentStateChecker stateChecker = new BoardCurrentStateChecker(currentBoardState);

        if (stateChecker.checkVerticals() == 4) {
            return -1; // made losing move vertically
        }

        if (stateChecker.checkHorizontals() == 4) {
            return -1; // made losing move horizontally
        }

        if (stateChecker.checkTopLeftLowRightCross() == 4) {
            return -1; // made losing move cross
        }

        if (stateChecker.checkTopLeftLowRightCross() == 4) {
            return - 1; // made losing move cross
        }

        return 1;
    }
}
