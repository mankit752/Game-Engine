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
    AIEngine aiEngine;
    RuleEngine ruleEngine;

    @BeforeEach
    public void setup() {
        this.gameEngine = new GameEngine();
        this.aiEngine = new AIEngine();
        this.ruleEngine = new RuleEngine();
    }

    @Test
    public void checkForRowWin() {
        Board board = gameEngine.start("TicTacToe");
        int next = 0;
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {1, 2}};
        playGame(board, moves, next);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForColWin() {
        Board board = gameEngine.start("TicTacToe");
        int next = 0;
        int[][] moves = new int[][]{{0, 0}, {1, 0}, {2, 0}};
        playGame(board, moves, next);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int next = 0;
        int[][] moves = new int[][]{{0, 0}, {1, 1}, {2, 2}};
        playGame(board, moves, next);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForRevDiagWin() {
        Board board = gameEngine.start("TicTacToe");
        int next = 0;
        int[][] moves = new int[][]{{0, 2}, {1, 1}, {2, 0}};
        playGame(board, moves, next);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "X");
    }

    @Test
    public void checkForSecondPlayerWin() {
        Board board = gameEngine.start("TicTacToe");
        int next = 0;
        int[][] moves = new int[][]{{1, 0}, {1, 1}, {2, 0}};
        playGame(board, moves, next);
        Assertions.assertTrue(ruleEngine.getState(board).isOver());
        Assertions.assertEquals(ruleEngine.getState(board).getWinner(), "O");
    }

    private void playGame(Board board, int[][] moves, int next) {
        while (!ruleEngine.getState(board).isOver()) {

            Player computer = new Player("O");
            Player human = new Player("X");
            System.out.println("Make your move!!");
            System.out.println("Board = " + board);
            int row = moves[next][0];
            int col = moves[next][1];
            next++;
            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);

            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }
    }
}
