package org.example;

public class Main {
    public static void main(String[] args) {
        Board b = new Board();
        Character[][] board = b.getBoard();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.println(board[i][j]);
            }
        }
    }
}