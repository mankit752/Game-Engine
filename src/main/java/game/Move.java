package game;

public class Move {

    private game.Cell cell;
    private game.Player player;

    public Move(game.Cell cell, game.Player player) {
        this.cell = cell;
        this.player = player;
    }

    public game.Cell getCell() {
        return cell;
    }

    public game.Player getPlayer() {
        return player;
    }
}