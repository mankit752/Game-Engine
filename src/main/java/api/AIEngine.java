package api;

import boards.Board;
import boards.TicTacToeBoard;
import game.Cell;
import game.Move;
import game.Player;
import placements.OffensivePlacement;
import placements.Placement;

import java.util.Optional;

public class AIEngine {

    RuleEngine ruleEngine = new RuleEngine();

    public Move suggestMove(Player player, Board board) {
        Cell suggestion;
        if (board instanceof TicTacToeBoard board1) {
            int threshold = 4;
            if (countMoves(board1) < threshold) {
                suggestion = getBasicMove(board1);
            } else if (countMoves(board1) < threshold + 1) {
                suggestion = getCellToPlay(player, board1);
            } else {
                suggestion = getOptimalMove(player, board1);
            }
            if (suggestion != null) return new Move(suggestion, player);
            throw new IllegalStateException("No move exists");
        } else {
            throw new IllegalArgumentException("Board undefined");
        }
    }

    private Cell getOptimalMove(Player player, TicTacToeBoard board) {

        Placement placement = OffensivePlacement.get();
        while (placement.next() != null) {
            Optional<Cell> place = placement.place(board, player);
            if (place.isPresent()) {
                return place.get();
            }
        }
        return null;
    }

    private Cell getCellToPlay(Player player, TicTacToeBoard board) {
        //Offense
        Cell best = offense(player, board);
        if (best != null) return best;

        //Definsive move
        best = defense(player, board);
        if (best != null) return best;
        return getBasicMove(board);
    }

    private Cell offense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    TicTacToeBoard boardCopy = board.move(move);
                    if (ruleEngine.getState(board).isOver()) {
                        return move.getCell();
                    }

                }
            }
        }
        return null;
    }

    private Cell defense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player.flip());
                    TicTacToeBoard boardCopy = board.move(move);
                    if (ruleEngine.getState(board).isOver()) {
                        return new Cell(i, j);
                    }

                }
            }
        }
        return null;
    }

    private Cell getBasicMove(TicTacToeBoard board1) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board1.getSymbol(i, j) == null) {
                    return new Cell(i, j);
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
