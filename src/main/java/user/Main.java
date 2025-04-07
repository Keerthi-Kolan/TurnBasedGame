package user;

import api.AIEngine;
import api.GameEngine;
import api.RuleEngine;
import game.Board;
import game.Cell;
import game.Move;
import game.Player;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine=new GameEngine();
        RuleEngine ruleEngine=new RuleEngine();
        AIEngine aiEngine =new AIEngine();
        Board board=gameEngine.start("TicTacToe");
        Scanner scanner=new Scanner(System.in);
        while(!ruleEngine.getState(board).isOver()){
            System.out.println("Make your Move!");
            System.out.println(board);
            int row=scanner.nextInt();
            int col=scanner.nextInt();
            Player computer=new Player("O");
            Player opponent=new Player("X");
            Move oppMove=new Move(new Cell(row,col),opponent);
            gameEngine.move(board,oppMove);
            if(!ruleEngine.getState(board).isOver()){
                Move computerMove= aiEngine.suggestMove(computer, board);
                gameEngine.move(board,computerMove);
            }

        }
        System.out.println("Game Result: "+ruleEngine.getState(board).getWinner());
        System.out.println(board);
    }
}
