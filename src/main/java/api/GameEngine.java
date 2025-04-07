package api;

import game.*;
import board.TicTacToeBoard;

public class GameEngine {
    public static void main(String[] args) {

    }
    public Board start(String type){
        if(type.equals("TicTacToe")){
            return new TicTacToeBoard();
        }
        else{
            throw new IllegalArgumentException();
        }

    }
    public void move(Board board, Move move){
            if(board instanceof TicTacToeBoard){
                board.move(move);

            }
            else{
                throw new IllegalArgumentException();
            }
    }

}

