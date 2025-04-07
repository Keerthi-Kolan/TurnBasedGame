package board;

import game.Board;
import game.Cell;
import game.Move;

public class TicTacToeBoard extends Board {
    String[][] cells = new String[3][3];
    public String getCell(int row, int column){
        return cells[row][column];
    }
    public void setCell( Cell cell,String symbol){
            cells[cell.getRow()][cell.getCol()]=symbol;
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

    @Override
    public void move(Move move) {
        setCell(move.getCell(),move.getPlayer().symbol());
    }
}
