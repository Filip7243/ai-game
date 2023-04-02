package org.example;

import java.util.Scanner;

public class Game {

    private Board board;
    private Computer computer;
    private static Scanner scanner = new Scanner(System.in);

    public Game(Board board) {
        this.board = board;
        this.computer = new Computer();
        play();
    }

    private void play() {
        boolean flag = true;

        while (flag) {
            try {
                if (!board.isGameOver()) {
                    board.printBoard();
                    int[] bestMove = computer.findBestMove(board);
                    board.makeMove(bestMove[0], bestMove[1]);
                    board.printBoard();

                    System.out.print("Make a move (coords separated by comma): ");
                    String move = scanner.nextLine();
                    String[] moves = move.split(",");
                    board.makeMove(Integer.parseInt(moves[0]), Integer.parseInt(moves[1]));


                } else {
                    board.printBoard();
                    flag = false;
                    System.out.println("GAME OVER!");
                }
            } catch (IllegalArgumentException e) {
                board.printBoard();
                flag = false;
            }
        }
    }
}
