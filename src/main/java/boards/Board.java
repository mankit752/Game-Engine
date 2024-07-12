package boards;

public interface Board {

    void move(game.Move move);

    Board copy();
}