package boards;

import api.RuleSet;
import game.*;

import java.util.function.BiFunction;
import java.util.function.Function;

public class TicTacToeBoard implements Board {
    String[][] cells = new String[3][3];

    public static RuleSet<TicTacToeBoard> getRules() {
        RuleSet rules = new RuleSet();
        rules.add(new Rule<TicTacToeBoard>(board -> outerTraversal((i, j) -> board.getSymbol(i, j))));
        rules.add(new Rule<TicTacToeBoard>(board -> outerTraversal((i, j) -> board.getSymbol(j, i))));
        rules.add(new Rule<TicTacToeBoard>(board -> traverse(i2 -> board.getSymbol(i2, i2))));
        rules.add(new Rule<TicTacToeBoard>(board -> traverse(i2 -> board.getSymbol(i2, 2 - i2))));
        rules.add(new Rule<TicTacToeBoard>(board -> {
            int countOfFillerCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board.getSymbol(i, j) != null) {
                        countOfFillerCells++;
                    }
                }
            }
            if (countOfFillerCells == 9) {
                return new GameState(true, "-");
            }
            return new GameState(false, "-");
        }));
        return rules;
    }

    public String getSymbol(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        if (cells[cell.getRow()][cell.getCol()] == null) {
            cells[cell.getRow()][cell.getCol()] = symbol;
        } else {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void move(Move move) {
        this.setCell(move.getCell(), move.getPlayer().symbol());
    }

    @Override
    public TicTacToeBoard copy() {
        TicTacToeBoard board = new TicTacToeBoard();
        for (int i = 0; i < 3; i++) {
            System.arraycopy(cells[i], 0, board.cells[i], 0, 3);
        }
        return board;
    }

    private static GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
        GameState result = new GameState(false, "-");
        for (int i = 0; i < 3; i++) {
            int ii = i;
            GameState traversal = traverse(j -> next.apply(ii, j));
            if (traversal.isOver()) {
                result = traversal;
                break;
            }
        }
        return result;
    }

    private static GameState traverse(Function<Integer, String> traversal) {
        GameState result = new GameState(false, "-");
        boolean possibleStreak = true;
        for (int j = 0; j < 3; j++) {
            if (traversal.apply(j) == null || !traversal.apply(0).equals(traversal.apply(j))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            result = new GameState(true, traversal.apply(0));
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.append(cells[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }
}