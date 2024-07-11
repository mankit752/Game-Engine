package game;

public class Player {

    private int timeUsedInMillis;
    private User id;
    private String playerSymbol;

    public Player(String playerSymbol) {
        this.playerSymbol = playerSymbol;
    }

    public String symbol() {
        return playerSymbol;
    }

    public Player flip() {
        return new Player(playerSymbol.equals("X") ? "X" : "O");
    }

    public int getTimeUsedInMillis() {
        return timeUsedInMillis;
    }

    public void setTimeTaken(int timeInMillis) {
        timeUsedInMillis += timeInMillis;
    }
}
