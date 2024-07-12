package game;

import boards.Board;

public class Game {

    private GameConfig gameConfig;
    private Board board;
    Player winner;
    private int lastMovedTimeInMillis;
    private int maxTimePerPlayer;
    private int maxTimePerMove;


    public void move(Move move, int timeStampInMillis) {
        int timeTakenSinceLastMove = timeStampInMillis - lastMovedTimeInMillis;
        move.getPlayer().setTimeTaken(timeTakenSinceLastMove);
        if (gameConfig.timed) {
            moveForTimedGame(move, timeTakenSinceLastMove);
        } else {
            board.move(move);
        }
    }

    private void moveForTimedGame(Move move, int timeTakenSinceLastMove) {
        if (gameConfig.getTimePerMove() != null) {
            if (moveMadeInTime(timeTakenSinceLastMove)) {
                board.move(move);
            } else {
                winner = move.getPlayer().flip();
            }
        } else {
            if (moveMadeInTime(move.getPlayer())) {
                board.move(move);
            } else {
                winner = move.getPlayer().flip();
            }
        }
    }

    private boolean moveMadeInTime(int timeTakenSinceLastMove) {
        return timeTakenSinceLastMove < maxTimePerMove;
    }

    private boolean moveMadeInTime(Player player) {
        return player.getTimeUsedInMillis() < maxTimePerPlayer;
    }
}
