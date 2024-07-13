package api;

import boards.CellBoard;
import game.GameState;

import java.util.function.Function;

public class Rule {

    Function<CellBoard, GameState> condition;

    public Rule(Function<CellBoard, GameState> condition) {
        this.condition = condition;
    }

    public Function<CellBoard, GameState> getCondition() {
        return condition;
    }
}
