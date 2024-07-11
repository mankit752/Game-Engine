package placements;

import api.RuleEngine;
import boards.TicTacToeBoard;
import game.Board;
import game.Cell;
import game.Player;

import java.util.Optional;
import java.util.Timer;

public interface Placement {

    RuleEngine ruleEngine = new RuleEngine();

    Optional<Cell> place(TicTacToeBoard board, Player player);

    Placement next();
}
