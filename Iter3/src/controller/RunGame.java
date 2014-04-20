package controller;

/**
 * Created by alexbujduveanu on 4/20/14.
 */
public class RunGame {
    private static GameStateManager gameManager;
    public static void main(String[] args)
    {
        runGame();
    }

    public static void runGame()
    {
        gameManager = new GameStateManager();
    }
}
