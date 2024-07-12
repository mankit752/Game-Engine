import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import boards.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine();
        AIEngine aiEngine = new AIEngine();
        RuleEngine ruleEngine = new RuleEngine();
        Board board = gameEngine.start("TicTacToe");

        //Make moves
        while (!ruleEngine.getState(board).isOver()) {

            System.out.println("Make your move!!");
            System.out.println("Board = " + board);
            Scanner scanner = new Scanner(System.in);
            int row = scanner.nextInt();
            int col = scanner.nextInt();
            Player computer = new Player("O");
            Player human = new Player("X");
            Move oppMove = new Move(new Cell(row, col), human);
            gameEngine.move(board, oppMove);

            if (!ruleEngine.getState(board).isOver()) {
                Move computerMove = aiEngine.suggestMove(computer, board);
                gameEngine.move(board, computerMove);
            }
        }
        System.out.println("Game Result = " + ruleEngine.getState(board));
        System.out.println("End board " + board);
    }
}
