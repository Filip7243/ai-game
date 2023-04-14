package org.example;

public interface AI {

    int[] findBestMove(Board board);

    int minMax(Board board, int depth, boolean isMax);

    int minMaxAlphaBeta(Board board, int depth, boolean isMax, int alpha, int beta);

    int getCurrentStateOfBoard(Board currentBoardState, boolean isMax);
}
