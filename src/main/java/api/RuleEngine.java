package api;

import boards.TicTacToeBoard;
import game.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {

    Map<String, List<Rule<TicTacToeBoard>>> ruleMap = new HashMap<>();

    public RuleEngine() {
        String key = TicTacToeBoard.class.getName();
        ruleMap.put(key, new ArrayList<>());
        ruleMap.get(key).add(new Rule<>(board -> outerTraversal((i, j) -> board.getSymbol(i, j))));
        ruleMap.get(key).add(new Rule<>(board -> outerTraversal((i, j) -> board.getSymbol(j, i))));
        ruleMap.get(key).add(new Rule<>(board -> traverse(i2 -> board.getSymbol(i2, i2))));
        ruleMap.get(key).add(new Rule<>(board -> traverse(i2 -> board.getSymbol(i2, 2 - i2))));
        ruleMap.get(key).add(new Rule<>(board -> {
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
    }

    public GameInfo getInfo(Board board) {
        if (board instanceof TicTacToeBoard board1) {
            GameState gameState = getState(board);

            String[] players = new String[]{"X", "O"};
            for (int index = 0; index < 2; index++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Board b = board.copy();
                        Player player = new Player(players[index]);
                        b.move(new Move(new Cell(i, j), new Player("X")));
                        boolean canStillWin = true;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 3; l++) {
                                Board b1 = board.copy();
                                b1.move(new Move(new Cell(k, l), player.flip()));
                                if (!getState(b1).getWinner().equals(player.flip().symbol())) {
                                    canStillWin = true;
                                    break;
                                }
                            }
                            if (!canStillWin) {
                                break;
                            }
                        }
                        if (canStillWin) {
                            return new GameInfo(gameState, true, player.flip());
                        }
                    }
                }
            }
            return new GameInfo(gameState, false, null);
        } else {
            throw new IllegalArgumentException();
        }
    }


    public GameState getState(Board board) {

        if (board instanceof TicTacToeBoard board1) {
            for (Rule<TicTacToeBoard> r: ruleMap.get(TicTacToeBoard.class.getName())) {
                GameState gameState = r.getCondition().apply(board1);
                if(gameState.isOver()) {
                    return gameState;
                }
            }
            return new GameState(false, "-");
        } else {
            return new GameState(false, "-");
        }
    }

    private GameState outerTraversal(BiFunction<Integer, Integer, String> next) {
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

    private GameState traverse(Function<Integer, String> traversal) {
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
        return "RuleEngine{}";
    }
}
