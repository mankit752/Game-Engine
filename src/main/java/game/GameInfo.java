package game;

public class GameInfo {

    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Player player;

    public GameInfo(GameState gameState, boolean hasFork, Player player) {
        this.isOver = gameState.isOver();
        this.winner = gameState.getWinner();
        this.hasFork = hasFork;
        this.player = player;
    }
}
