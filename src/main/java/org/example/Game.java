package org.example;

import java.util.Scanner;

public class Game {

    private Board board;
    private static Scanner scanner = new Scanner(System.in);

    public Game(Board board) {
        this.board = board;

        play();
    }

    private void play() {
        boolean flag = true;

        while (flag) {
            try {
                if (!board.isGameOver()) {
                    board.printBoard();
                    System.out.print("Make a move (coords separated by comma): ");
                    String move = scanner.nextLine();
                    String[] moves = move.split(",");
                    board.makeMove(Integer.parseInt(moves[0]), Integer.parseInt(moves[1]));
                } else {
                    flag = false;
                    System.out.println("GAME OVER!");
                }
            } catch (IllegalArgumentException e) {
                flag = false;
            }
        }
    }
}
