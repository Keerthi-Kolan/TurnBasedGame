package board;

import game.Board;
import game.Cell;
import game.Move;

public class TicTacToeBoard implements Board {
    String[][] cells = new String[3][3];
    public String getCell(int row, int column){
        return cells[row][column];
    }
    public void setCell( Cell cell,String symbol){
        if(cells[cell.getRow()][cell.getCol()]==null){
            cells[cell.getRow()][cell.getCol()]=symbol;
        }
        else{
            throw new IllegalArgumentException();
        }
    }
    @Override
    public String toString(){
        StringBuilder string=new StringBuilder();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(cells[i][j]==null){
                    string.append("_");
                    continue;
                }
                string.append(cells[i][j]);
            }
            string.append("\n");
        }
        return string.toString();
    }
    public String getSymbol(int row, int col) {
        return cells[row][col];
    }
    @Override
    public Board move(Move move) {
        setCell(move.getCell(),move.getPlayer().symbol());
        return null;
    }
    public TicTacToeBoard copy(){
        TicTacToeBoard ticTacToeBoard=new TicTacToeBoard();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                ticTacToeBoard.cells[i][j]=cells[i][j];
            }
        }
        return ticTacToeBoard;
    }
}
