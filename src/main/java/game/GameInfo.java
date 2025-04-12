package game;

public class GameInfo {
    private boolean isOver;
    private String winner;
    private boolean hasFork;
    private Player player;

    public GameInfo(GameState gamestate, boolean hasFork, Player player) {
        isOver = gamestate.isOver();
        this.hasFork = hasFork;
        this.player = player;
        winner = gamestate.getWinner();
    }
}
