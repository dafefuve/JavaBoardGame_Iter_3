package controller;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class ActiveController
{
    private BoardController boardController;
    private PlayerController playerController;
    private ResourceController resourceController;
    private ViewController viewController;

    public ActiveController( BoardController b,  PlayerController p, ResourceController r, ViewController v )
    {
        boardController = b;
        playerController = p;
        resourceController = r;
        viewController = v;

        //Set up all key bindings -- SUPER OOP
        viewController.registerChangeTurnAction();
       //Nvm, I have no idea what I'm doing
    }

    public void moveN()
    {

    }

    public void moveNE()
    {

    }

    public void moveNW()
    {

    }

    public void moveS()
    {

    }

    public void moveSE()
    {

    }

    public void moveSW()
    {

    }

    public void placeDeveloper()
    {

    }

    public void moveDeveloper()
    {

    }

    public void changeTurn()
    {
        viewController.changeTurn();
    }



}
