package game;

import java.util.function.Function;

public class Rule<T extends Board> {

    Function<T, GameState> condition;

    public Rule(Function<T, GameState> condition) {
        this.condition = condition;
    }

    public Function<T, GameState> getCondition() {
        return condition;
    }
}
