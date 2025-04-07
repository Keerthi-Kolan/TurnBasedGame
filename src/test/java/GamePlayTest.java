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
    AIEngine aiEngine;
    @Before
    public void setup(){
        gameEngine=new GameEngine();
        ruleEngine=new RuleEngine();
        aiEngine =new AIEngine();
    }
    @Test
    public void checkforRowWin() {
        Board board=gameEngine.start("TicTacToe");

        int[][] moves=new int[][]{{1,0},{1,1},{1,2}};
        playGame(board,moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkforColWin() {

        Board board=gameEngine.start("TicTacToe");
        int[][] moves=new int[][]{{0,0},{1,0},{2,0}};
        playGame(board,moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkforDiagWin() {

        Board board=gameEngine.start("TicTacToe");
        int[][] moves=new int[][]{{0,0},{1,1},{2,2}};
        playGame(board,moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkforRevDiagWin() {

        Board board=gameEngine.start("TicTacToe");
        int[][] moves=new int[][]{{0,2},{1,1},{2,0}};
        playGame(board,moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"X");
    }
    @Test
    public void checkforSecondPlayerWin(){

        Board board=gameEngine.start("TicTacToe");
        int[][] moves=new int[][]{{1,0},{1,1},{2,0}};
        playGame(board,moves);
        Assert.assertTrue(ruleEngine.getState(board).isOver());
        Assert.assertEquals(ruleEngine.getState(board).getWinner(),"O");
    }
    public void playGame(Board board,int[][] moves){
        int row,col;
        int next=0;
        while(!ruleEngine.getState(board).isOver()){
            Player computer=new Player("O");
            Player opponent=new Player("X");
            row=moves[next][0];
            col=moves[next][1];
            next++;
            Move oppMove=new Move(new Cell(row,col),opponent);
            gameEngine.move(board,oppMove);
            if(!ruleEngine.getState(board).isOver()){
                Move computerMove=aiEngine.suggestMove(computer, board);
                gameEngine.move(board,computerMove);
            }

        }

    }
}
