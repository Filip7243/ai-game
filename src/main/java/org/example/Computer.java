package org.example;

public class Computer implements AI {

    @Override
    public int[] findBestMove(Board board) {
        int[] bestMoveCoordinates = new int[2];
        int bestValue = Integer.MIN_VALUE;

        int boardWidth = board.getBoardWidth();
        for (int i = 0; i < boardWidth; i++) {
            for (int j = 0; j < boardWidth; j++) {
                if (!board.isMarkPlaced(i, j)) {
                    board.makeMove(i, j);
                    int valueAfterMove = minMaxAlphaBeta(board, 5, true, Integer.MIN_VALUE, Integer.MAX_VALUE);
//                    int valueAfterMove = minMax(board, 4, true);
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
    public int minMax(Board board, int depth, boolean isMax) {
        int currentStateOfBoard = getCurrentStateOfBoard(board);

        if (depth == 0 || board.isGameOver()) {
            return currentStateOfBoard;
        }

        int boardWidth = board.getBoardWidth();
        if (isMax) {
            int bestValue = Integer.MIN_VALUE;

            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardWidth; j++) {
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

            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardWidth; j++) {
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
    public int minMaxAlphaBeta(Board board, int depth, boolean isMax, int alpha, int beta) {
        int currentStateOfBoard = getCurrentStateOfBoard(board);

        if (depth == 0 || board.isGameOver()) {
            return currentStateOfBoard;
        }

        int boardWidth = board.getBoardWidth();
        if (isMax) {
            int bestValue = Integer.MIN_VALUE;

            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardWidth; j++) {
                    if (!board.isMarkPlaced(i, j)) {
                        board.makeMove(i, j);
                        bestValue = Math.max(bestValue, minMax(board, depth - 1, false));
                        board.undoMove(i, j);
                        alpha = Math.max(alpha, bestValue);
                        if (alpha >= beta) {
                            return bestValue;
                        }
                    }
                }
            }

            return bestValue;
        } else {
            int worstValue = Integer.MAX_VALUE;

            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardWidth; j++) {
                    if (!board.isMarkPlaced(i, j)) {
                        board.makeMove(i, j);
                        worstValue = Math.min(worstValue, minMax(board, depth - 1, true));
                        board.undoMove(i, j);
                        beta = Math.min(beta, worstValue);
                        if (beta <= alpha) {
                            return worstValue;
                        }
                    }
                }
            }

            return worstValue;
        }
    }

    @Override
    public int getCurrentStateOfBoard(Board currentBoardState) {
        BoardCurrentStateChecker stateChecker = new BoardCurrentStateChecker(currentBoardState);
        int boardWidth = currentBoardState.getBoardWidth();
        int value = 0;

        int[] horizontals = stateChecker.checkHorizontals();
        for (int horizontal : horizontals) {
            if (horizontal == boardWidth - 1) {
                value++;
            }
        }

        int[] verticals = stateChecker.checkVerticals();
        for (int vertical : verticals) {
            if (vertical == boardWidth - 1) {
                value++;
            }
        }

        int lowDiagonal = stateChecker.checkLowLeftTopRightCross();
        if (lowDiagonal == boardWidth - 1) {
            value++;
        }

        int highDiagonal = stateChecker.checkTopLeftLowRightCross();
        if (highDiagonal == boardWidth - 1) {
            value++;
        }

        return value;
    }
}
