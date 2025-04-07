package game;

public class Player {
    private String playerSymbol;
    public Player(String playerSymbol){
        this.playerSymbol=playerSymbol;
    }
    public Player flip(){
       return new Player(playerSymbol.equals("X")?"O":"X");
    }
    public String symbol(){
        return playerSymbol;
    }

}
