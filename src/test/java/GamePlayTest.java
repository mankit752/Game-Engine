import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class GamePlayTest {

    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @BeforeEach
    public void setup() {
        this.gameEngine = new GameEngine();
        this.ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {
        Board board = gameEngine.start("TicTacToe");
        int next = 0;
        int[][] firstPlayerMoves = new int[][]{{0, 0}, {1, 0}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 1}, {0, 2}, {1, 1}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        int[][] secondPlayerMoves = new int[][]{{0, 1}, {0, 2}, {1, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {1, 0}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForSecondPlayerWin() {
        Board board = gameEngine.start("TicTacToe");
        int[][] firstPlayerMoves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        int[][] secondPlayerMoves = new int[][]{{0, 0}, {0, 1}, {0, 2}};
        playGame(board, firstPlayerMoves, secondPlayerMoves);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(Board board, int[][] firstPlayerMoves, int[][] secondPlayerMoves) {

        int next = 0;
        while (!ruleEngine.getState(board).isOver()) {
            Player second = new Player("O");
            Player first = new Player("X");
            System.out.println("Make your move!!");
            System.out.println("Board = " + board);
            int row = firstPlayerMoves[next][0];
            int col = firstPlayerMoves[next][1];
            Move firstPlayerMove = new Move(new Cell(row, col), first);
            gameEngine.move(board, firstPlayerMove);

            if (!ruleEngine.getState(board).isOver()) {
                int sRow = secondPlayerMoves[next][0];
                int sCol = secondPlayerMoves[next][1];
                Move secondPlayerMove = new Move(new Cell(sRow, sCol), second);
                gameEngine.move(board, secondPlayerMove);
            }
            next++;
        }
    }
}
