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
        System.out.println("Choose who is starting (C, P):");
        String startingPlayer = scanner.nextLine();

        switch (startingPlayer.toUpperCase()) {
            case "C" -> {
                while (true) {
                    computerPlays();
                    if (board.isGameOver()) {
                        board.printBoard();
                        System.out.println("COMPUTER LOSE");
                        break;
                    }

                    board.printBoard();

                    playerPlays();
                    if (board.isGameOver()) {
                        board.printBoard();
                        System.out.println("YOU LOSE!");
                        break;
                    }
                }
            }
            case "P" -> {
                while (true) {
                    board.printBoard();
                    playerPlays();
                    if (board.isGameOver()) {
                        board.printBoard();
                        System.out.println("YOU LOSE!");
                        break;
                    }

                    computerPlays();
                    if (board.isGameOver()) {
                        board.printBoard();
                        System.out.println("COMPUTER LOSE");
                        break;
                    }
                }
            }
        }


    }

    private void computerPlays() {
        int[] bestMove = computer.findBestMove(board);
        board.makeMove(bestMove[0], bestMove[1]);
    }

    private void playerPlays() {
        System.out.print("Make a move (coords separated by comma): ");
        String move = scanner.nextLine();
        String[] moves = move.split(",");
        board.makeMove(Integer.parseInt(moves[0]), Integer.parseInt(moves[1]));
    }
}
