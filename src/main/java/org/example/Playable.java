package org.example;

public interface Playable {

    void makeMove(int x, int y);
    boolean isMarkPlaced(int x, int y);
    boolean isGameOver();
    void undoMove(int x, int y);

}
