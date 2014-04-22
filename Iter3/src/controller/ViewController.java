package controller;

import view.ViewTest;

import javax.swing.*;
import java.util.HashMap;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class ViewController {
    view.MainView mainView;
    private boolean invalidPlacement;
    public ViewController()
    {
        mainView = new view.MainView();
        invalidPlacement = false;

    }

    public void initializeView(String[] players)
    {

    }
    //public void bindKeyListener(InputListener listener )
    {

    }
    public void registerChangeTurnAction()
    {
        ViewTest.registerChangeTurn();
    }

    public void recieveMainMenuKeyBindings(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        mainView.registerMainMenuKeyBindings(keyBindings);
    }

    public void recieveActiveKeyBindings(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        mainView.registerActiveKeyBindings(keyBindings);
    }

    public void recievePlanningKeyBindings(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        mainView.registerPlanningKeyBindings(keyBindings);
    }

    public int getCurrentSpace()
    {
        return mainView.getCurrentSpace();
    }

    public void startNewGame()
    {
        mainView.startNewGame();
    }

    public void placeVillageTile()
    {
        mainView.placeVillageTile();

    }

    public void moveNorth()
    {
        mainView.moveNorth();
        invalidPlacement = false;
    }

    public void moveNorthEast()
    {
        mainView.moveNorthEast();
        invalidPlacement = false;
    }

    public void moveSouth()
    {
        mainView.moveSouth();
        invalidPlacement = false;
    }

    public void moveSouthEast()
    {
        mainView.moveSouthEast();
        invalidPlacement = false;
    }

    public void moveSouthWest()
    {
        mainView.moveSouthWest();
        invalidPlacement = false;
    }

    public void moveNorthWest()
    {
        mainView.moveNorthWest();
        invalidPlacement = false;
    }

    public void endPlacement(boolean invalidPlacement)
    {
        mainView.endPlacement(invalidPlacement);
    }

    public void notifyInvalidPlacement()
    {
        invalidPlacement = true;
    }

    public void exitPlacement(){
        mainView.exitPlacement();
    }

    public void placeRiceTile()
    {
        mainView.placeRiceTile();
    }

    public void placeTwoBlock()
    {
        mainView.placeTwoBlock();
    }


    public void setTwoBlocks(int numTwoBlocks, String playerName)
    {
        mainView.setTwoBlocks(numTwoBlocks, playerName);
    }

    public void setSingleVillageBlocks(int numVillageBlocks, String playerName)
    {
        mainView.setSingleVillageBlocks(numVillageBlocks, playerName);
    }

    public void setRiceBlocks(int numRiceBlocks, String playerName)
    {
        mainView.setRiceBlocks(numRiceBlocks, playerName);
    }
/*
    public void setActionTokens(int numActionTokens, String playerName)
    {
        mainView.setActionTokens(numActionTokens, playerName);
    }

    public void setDevelopers(int numDevelopers, String playerName)
    {
        mainView.setDevelopers(numActionTokens, playerName);
    }

    public void setScore(int score, String playerName)
    {
        mainView.setScore(score, playerName);
    }
    */
/*
    //Shared Resources
    public void setThreeBlocks(int numThreeBlocks)
    {
        mainView.setThreeBlocks(numThreeBlocks);
    }


    public void setLvl2PalaceTiles(int numLvl2PalaceTiles)
    {
        mainView.setLvl2PalaceTiles(int numLvl2PalaceTiles);
    }

    */

    public void placeIrrigationTile()
    {
        mainView.placeIrrigationTile();
    }

    public void changeTurn()
    {
        mainView.changeTurn();
    }

    public void setIrrigationTiles(int numIrrigationTiles)
    {
        mainView.setIrrigationTiles(numIrrigationTiles);
    }

    public void setPlanningMode()
    {
        mainView.setPlanningMode();
    }

}
