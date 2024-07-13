package boards;

public interface Board {

    Board move(game.Move move);

    Board copy();
}