package placements;

import boards.TicTacToeBoard;
import game.Cell;
import game.Move;
import game.Player;
import utils.Utils;

import java.util.Optional;

public class OffensivePlacement implements Placement{

    private OffensivePlacement() {
    }

    private static OffensivePlacement offensivePlacement;

    public static synchronized OffensivePlacement get() {
        offensivePlacement = (OffensivePlacement) Utils.getIfNull(offensivePlacement, OffensivePlacement::new);
        return offensivePlacement;
    }

    @Override
    public Optional<Cell> place(TicTacToeBoard board, Player player) {
        return Optional.ofNullable(offense(player, board));
    }

    @Override
    public Placement next() {
        return DefensivePlacement.get();
    }

    private Cell offense(Player player, TicTacToeBoard board) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board.getSymbol(i, j) == null) {
                    Move move = new Move(new Cell(i, j), player);
                    board.move(move);
                    if (ruleEngine.getState(board).isOver()) {
                        return move.getCell();
                    }

                }
            }
        }
        return null;
    }
}
