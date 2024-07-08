package api;

import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.GameState;

import java.util.Iterator;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    public GameState getState(Board board) {

        if (board instanceof TicTacToeBoard board1) {
            String firstCharacter;

            GameState rowWin = findStreak((i, j) -> board1.getSymbol(i, j));
            if (rowWin != null) return rowWin;

            GameState colWin = findStreak((i, j) -> board1.getSymbol(j, i));
            if (colWin != null) return colWin;

            GameState diagWin = findDiagStreak(i -> board1.getSymbol(i, i));
            if (diagWin != null) return diagWin;

            GameState revDiagWin = findDiagStreak(i -> board1.getSymbol(i, 2 - i));
            if (revDiagWin != null) return revDiagWin;

            int countOfFillerCells = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (board1.getSymbol(i, j) != null) {
                        countOfFillerCells++;
                    }
                }
            }
            if (countOfFillerCells == 9) {
                return new GameState(true, "-");
            } else {
                return new GameState(false, "-");
            }
        } else {
            return new GameState(false, "-");
        }
    }

    private GameState findDiagStreak(Function<Integer, String> diag) {
        String firstCharacter = diag.apply(0);
        boolean possibleStreak = firstCharacter != null;
        for (int i = 0; i < 3; i++) {
            if (diag.apply(0) != null && !diag.apply(0).equals(diag.apply(i))) {
                possibleStreak = false;
                break;
            }
        }
        if (possibleStreak) {
            return new GameState(true, diag.apply(0));
        }
        return null;
    }

    private static GameState findStreak(BiFunction<Integer, Integer, String> next) {

        for (int i = 0; i < 3; i++) {
            boolean possibleStreak = true;
            for (int j = 0; j < 3; j++) {
                if (next.apply(i, j) == null || !next.apply(i, 0).equals(next.apply(i, j))) {
                    possibleStreak = false;
                    break;
                }
            }
            if (possibleStreak) {
                return new GameState(true, next.apply(i, 0));
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "RuleEngine{}";
    }
}
