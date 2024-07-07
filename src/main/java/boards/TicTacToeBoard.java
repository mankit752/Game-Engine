package boards;

import game.Board;
import game.Cell;
import game.Move;

public class TicTacToeBoard extends Board {
    String[][] cells = new String[3][3];

    public String getCell(int row, int col) {
        return cells[row][col];
    }

    public void setCell(Cell cell, String symbol) {
        cells[cell.getRow()][cell.getCol()] = symbol;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                result.append(cells[i][j]).append(" ");
            }
            result.append("\n");
        }
        return result.toString();
    }

    @Override
    public void move(Move move) {
        this.setCell(move.getCell(), move.getPlayer().symbol());
    }
}