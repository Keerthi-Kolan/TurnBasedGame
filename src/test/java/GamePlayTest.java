import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GamePlayTest {
    GameEngine gameEngine;
    RuleEngine ruleEngine;

    @Before
    public void setup(){
        gameEngine=new GameEngine();
        ruleEngine=new RuleEngine();

    }
    @Test
    public void checkforRowWin() {
        Board board=gameEngine.start("TicTacToe");

        int[][] moves=new int[][]{{1,0},{1,1},{1,2}};
        int[][] secondPlayerMoves=new int[][]{{0,0},{0,1},{0,2}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkforColWin() {

        Board board=gameEngine.start("TicTacToe");
        int[][] moves=new int[][]{{0,0},{1,0},{2,0}};
        int[][] secondPlayerMoves=new int[][]{{0,1},{0,2},{1,1}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkforDiagWin() {

        Board board=gameEngine.start("TicTacToe");
        int[][] moves=new int[][]{{0,0},{1,1},{2,2}};
        int[][] secondPlayerMoves=new int[][]{{0,1},{0,2},{1,0}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkforRevDiagWin() {

        Board board=gameEngine.start("TicTacToe");
        int[][] moves=new int[][]{{0,2},{1,1},{2,0}};
        int[][] secondPlayerMoves=new int[][]{{0,0},{0,1},{1,0}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkforSecondPlayerWin(){

        Board board=gameEngine.start("TicTacToe");
        int[][] moves=new int[][]{{1,0},{1,1},{2,0}};
        int[][] secondPlayerMoves=new int[][]{{0,0},{0,1},{0,2}};
        playGame(board,moves,secondPlayerMoves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"O");
    }
    public void playGame(Board board,int[][] firstPlayerMoves,int[][] secondPlayerMoves){
        int row,col;
        int next=0;
        while(!ruleEngine.getState(board).isOver()){
            Player first=new Player("X");
            Player second=new Player("O");
            row=firstPlayerMoves[next][0];
            col=firstPlayerMoves[next][1];

            Move oppMove=new Move(new Cell(row,col),first);
            gameEngine.move(board,oppMove);
            if(!ruleEngine.getState(board).isOver()){
                int sRow=secondPlayerMoves[next][0];
                int sCol=secondPlayerMoves[next][1];
                Move computerMove=new Move(new Cell(sRow,sCol),second);
                gameEngine.move(board,computerMove);
            }
            next++;
        }

    }
}
