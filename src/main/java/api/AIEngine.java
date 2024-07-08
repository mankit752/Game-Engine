package api;

import boards.TicTacToeBoard;
import game.*;

public class AIEngine {

    public Move suggestMove(Player computer, Board board) {
        Move suggestion;
        if (board instanceof TicTacToeBoard board1) {
            int threshold = 4;
            if(countMoves(board1) < threshold) {
                suggestion = getBasicMove(computer, board1);
            } else {
                suggestion = getSmartMove(computer, board1);
            }
            if (suggestion != null) return suggestion;
            throw new IllegalStateException("No move exists");
        } else {
            throw new IllegalArgumentException("Board undefined");
        }
    }

    private Move getSmartMove(Player player, TicTacToeBoard board) {
        RuleEngine ruleEngine = new RuleEngine();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(board).isOver()) {
                        return move;
                    }

                }
            }
        }

        //Definsive move
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.copy();
                    boardCopy.move(move);
                    if (ruleEngine.getState(board).isOver()) {
                        return new Move(new Cell(i, j), player);
                    }

                }
            }
        }
        return getBasicMove(player, board);
    }

    private Move getBasicMove(Player computer, TicTacToeBoard board1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getSymbol(i, j) == null) {
                    return new Move(new Cell(i, j), computer);
                }
            }
        }
        return null;
    }

    private int countMoves(TicTacToeBoard board1) {
        int count = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getSymbol(i, j) != null) {
                    count++;
                }
            }
        }
        return count;
    }
}
