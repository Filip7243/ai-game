package org.example;

import java.util.List;

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
                    int valueAfterMove = minMaxAlphaBeta(board, 16, false, Integer.MIN_VALUE, Integer.MAX_VALUE);
//                    int valueAfterMove = minMax(board, 4, true);
                    board.undoMove(i, j);
                    if (valueAfterMove > bestValue) {
                        bestMoveCoordinates[0] = i;
                        bestMoveCoordinates[1] = j;

                        bestValue = valueAfterMove;
                    }
                }
                System.out.println("BEST VALUE = " + bestValue + ", BEST COORDS(x,y) = " + i + ", " + j);
            }
        }

        return bestMoveCoordinates;
    }

    @Override
    public int minMax(Board board, int depth, boolean isMax) {
        int currentStateOfBoard = getCurrentStateOfBoard(board, isMax);

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
        if (board.isGameOver()) {
            return Game.currentPlayer.equalsIgnoreCase("p") ? -100 + (16 - depth) : 100 - (16 - depth);
        }

        int boardWidth = board.getBoardWidth();
        if (isMax) {
            int bestValue = Integer.MIN_VALUE;

            for (int i = 0; i < boardWidth; i++) {
                for (int j = 0; j < boardWidth; j++) {
                    if (!board.isMarkPlaced(i, j)) {
                        board.makeMove(i, j);
                        bestValue = Math.max(bestValue, minMaxAlphaBeta(board, depth - 1, false, alpha, beta));
                        board.undoMove(i, j);
                        alpha = Math.max(alpha, bestValue);
                        if (alpha >= beta) {
                            break;
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
                        worstValue = Math.min(worstValue, minMaxAlphaBeta(board, depth - 1, true, alpha, beta));
                        board.undoMove(i, j);
                        beta = Math.min(beta, worstValue);
                        if (beta <= alpha) {
                            break;
                        }
                    }
                }
            }

            return worstValue;
        }
    }

    @Override
    public int getCurrentStateOfBoard(Board currentBoardState, boolean isMax) {
        BoardCurrentStateChecker stateChecker = new BoardCurrentStateChecker(currentBoardState);
        int boardWidth = currentBoardState.getBoardWidth();
        int value = 0;
        String startingPlayer = Game.currentPlayer;

        List<Integer> horizontals = stateChecker.checkHorizontals();
        value += horizontals.stream()
                .filter(horizontal -> horizontal == 3)
                .count();

        List<Integer> verticals = stateChecker.checkVerticals();
        value += verticals.stream()
                .filter(horizontal -> horizontal == 3)
                .count();

        int lowDiagonal = stateChecker.checkLowLeftTopRightCross();
        if (lowDiagonal == boardWidth - 1) {
            value += 2;
        }

        int highDiagonal = stateChecker.checkTopLeftLowRightCross();
        if (highDiagonal == boardWidth - 1) {
            value += 2;
        }


        if (isMax && startingPlayer.equalsIgnoreCase("C")) { // Computer is on move
            if (horizontals.contains(boardWidth) || verticals.contains(boardWidth) ||
                    lowDiagonal == boardWidth || highDiagonal == boardWidth) {
                return -100;
            }
            return value;
        } else if (isMax && startingPlayer.equalsIgnoreCase("P")) { // Player on move
            if (horizontals.contains(boardWidth) || verticals.contains(boardWidth) ||
                    lowDiagonal == boardWidth || highDiagonal == boardWidth) {
                return 100;
            }
            return value;
        } else if (!isMax && startingPlayer.equalsIgnoreCase("C")) { // Player on move
            if (horizontals.contains(boardWidth) || verticals.contains(boardWidth) ||
                    lowDiagonal == boardWidth || highDiagonal == boardWidth) {
                return 100;
            }
            return -value;
        } else if (!isMax && startingPlayer.equalsIgnoreCase("P")) { // Computer on Move
            if (horizontals.contains(boardWidth) || verticals.contains(boardWidth) ||
                    lowDiagonal == boardWidth || highDiagonal == boardWidth) {
                return -100;
            }
            return -value;
        }

        return value;
    }
}
