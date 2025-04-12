package api;

import board.TicTacToeBoard;
import game.*;
import game.GameState;
import game.GameInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

public class RuleEngine {
    //This map is used to store all the rules for the  game
    Map<String, List<Rule>> ruleMap=new HashMap<>();
    public RuleEngine(){
        ruleMap.put(TicTacToeBoard.class.getName(),new ArrayList<>());
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule(board-> outerTraversals.apply(board)));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule(board-> outerTraversals.apply(board)));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule(board-> traverse.apply(board)));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule(board-> traverse.apply(board)));
        ruleMap.get(TicTacToeBoard.class.getName()).add(new Rule(board-> countMoves.apply(board)));
    }
    public GameInfo getInfo(Board board){
        if(board instanceof TicTacToeBoard){
            GameState gameState=getState(board);
            final String[] players=new String[]{"X","O"};
            for(int index=0;index<2;index++) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        Board b = board.copy();
                        Player player = new Player(players[index]);
                        b.move(new Move(new Cell(i, j), new Player("X")));
                        boolean canStillWin = false;
                        for (int k = 0; k < 3; k++) {
                            for (int l = 0; l < 2; l++) {
                                Board b1 = board.copy();
                                b1.move(new Move(new Cell(i, j), new Player(")")));
                                if (!getState(b1).getWinner().equals("0")) {
                                    canStillWin = true;
                                    break;
                                }
                            }
                            if (canStillWin) {
                                break;
                            }
                        }
                        if (canStillWin) {
                            return new GameInfo(gameState, true, player.flip());
                        }
                    }
                }

            }
            return new GameInfo(gameState,false,null);
        }
    else {
        throw new IllegalArgumentException();
        }
    }
    public GameState getState(Board board){
        if(board instanceof TicTacToeBoard){
            TicTacToeBoard board1=(TicTacToeBoard) board;
            List<Rule> rules= ruleMap.get(TicTacToeBoard.class.getName());
            for(Rule r: rules){
                if(r.condition.apply(board1)){
                    return new GameState(true,"X");
                }
            }
            GameState rowWin= outerTraversal((i, j)->board1.getSymbol(i,j));
            if(rowWin.isOver()) return rowWin;
            GameState colWin= outerTraversal((i, j)->board1.getSymbol(j,i));
            if(colWin.isOver()) return colWin;

            GameState diagWin= traverse(i->board1.getSymbol(i,i));
            if(diagWin.isOver()) return diagWin;

            GameState revDiagWin= traverse(i->board1.getSymbol(i,2-i));
            if(revDiagWin.isOver()) return revDiagWin;

            int countOfFilledCells=0;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(board1.getCell(i,j)!=null)
                    {
                        countOfFilledCells++;
                    }
                }
            }
            if(countOfFilledCells==9){
                return new GameState(true,"No Winner");
            }
                return new GameState(false,"-");
        }
        else{
            return new GameState(false,"-");
        }
    }
    private GameState traverse(Function<Integer, String> traversal){
        GameState result=new GameState(false,"-");
        boolean possibleStreak=true;
        for(int j=0;j<3;j++){
            if(traversal.apply(j)==null||!traversal.apply(0).equals(traversal.apply(j)))
            {
                possibleStreak =false;
                break;
            }
        }
        if(possibleStreak){
            result= new GameState(true,traversal.apply(0));
        }
        return result;
    }
    private Function<TicTacToeBoard,GameState> outerTraversals=(board->outerTraversal(board::getSymbol));
    private Function<TicTacToeBoard,GameState> traverse=(board->outerTraversal(board::getSymbol));
    public GameState outerTraversal(BiFunction<Integer,Integer,String> next){
        GameState result=new GameState(false,"-");
        for(int i=0;i<3;i++){
            final int ii=i;
            GameState traversal=traverse(j->next.apply(ii,j));
            if(traversal.isOver()){
                result= traversal;
                break;
            }
        }
        return result;
    }
}
class Rule{
    Function<Board,Boolean > condition;
    public Rule(Function<Board,Boolean> condition){
        this.condition=condition;
    }
}