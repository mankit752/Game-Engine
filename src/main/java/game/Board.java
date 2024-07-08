package game;

public interface Board {

    void move(game.Move move);

    Board copy();
}