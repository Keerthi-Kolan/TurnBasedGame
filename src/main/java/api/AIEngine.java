package api;

import board.TicTacToeBoard;
import game.*;

public class AIEngine {

    public Move suggestMove(Player computer, Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1=(TicTacToeBoard) board;
            Move suggestion;
            int threshold=4;
            if(countMoves(board1)<threshold){
                suggestion=getBasicMove(computer,board1);
            }
            else{
                suggestion=getSmartMove(computer,board1);
            }
            if(suggestion!=null)
                return suggestion;

            throw new IllegalArgumentException();

        }
        else{
            throw new IllegalArgumentException();
        }
    }
    public Move getSmartMove(Player player, TicTacToeBoard board){
        RuleEngine ruleEngine=new RuleEngine();
        // Victorious moves
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getCell(i,j)!=null){
                    Move move=new Move(new Cell(i,j),player);
                    TicTacToeBoard boardCopy=board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return move;
                    }
                }
            }
        }
        // Defensive moves
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getCell(i,j)!=null){
                    Move move=new Move(new Cell(i,j),player.flip());
                    TicTacToeBoard boardCopy=board.copy();
                    boardCopy.move(move);
                    if(ruleEngine.getState(boardCopy).isOver()){
                        return new Move(new Cell(i,j),player);
                    }
                }
            }
        }
        return null;
    }
    private int countMoves(TicTacToeBoard board){
        int count=0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board.getCell(i,j)!=null){
                    count++;
                }
            }
        }
        return count;
    }
    private  Move getBasicMove(Player computer, TicTacToeBoard board1){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board1.getCell(i,j)==null){
                    return new Move(new Cell(i,j),computer);
                }
            }
        }
        return null;
    }
    public Move suggesSmarttMove(Player computer, Board board){
        if(board instanceof TicTacToeBoard){
            //make smart move

            throw new IllegalArgumentException();

        }
        else{
            throw new IllegalArgumentException();
        }
    }
}
