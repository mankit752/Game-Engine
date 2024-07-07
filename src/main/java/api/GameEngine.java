package api;

import boards.TicTacToeBoard;
import game.Board;
import game.Move;

public class GameEngine {
    public static void main(String[] args) {


    }

    public Board start(String type) {
        if (type.equals("TicTacToe")) {
            return new TicTacToeBoard();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void move(Board board, Move move) {
        if (board instanceof TicTacToeBoard board1) {
            board1.move(move);
        } else {
            throw new IllegalArgumentException();
        }
    }

}