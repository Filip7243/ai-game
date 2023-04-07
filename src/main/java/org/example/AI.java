package org.example;

public interface AI {

    int minMax(Board board, int depth, boolean isMax);
    int[] findBestMove(Board board);
    int getCurrentStateOfBoard(Board currentBoardState);
    int minMaxAlphaBeta(Board board, int depth, boolean isMax, int alpha, int beta);
}
