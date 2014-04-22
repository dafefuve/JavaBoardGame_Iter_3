package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.HashMap;

import javax.swing.*;

public class PlayerView extends JPanel
{
    HashMap<String, PlayerPanel> hashMap;
	JPanel[] players;
	static int currentPlayer;
	
	public PlayerView(int numberOfPlayers)
	{
        hashMap = new HashMap<String, PlayerPanel>();
		currentPlayer = 0;
		players = new JPanel[numberOfPlayers];
		
		//Set up container 
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		//Initialize individual player panels 
		for(int i = 0; i < players.length; i++)
		{
			JPanel currentPanel = new PlayerPanel("Player " + (i + 1));
            hashMap.put("Player" + i, (PlayerPanel)currentPanel);
			players[i] = currentPanel;
			this.add(currentPanel);
		}
		highlightPlayer(currentPlayer);
		
	}
	
	public void changeTurn()
	{	
		int previousPlayer;
		currentPlayer++; 
		previousPlayer = currentPlayer - 1;
		
		if(currentPlayer == 4)
		{
			currentPlayer = 0;
			previousPlayer = 3;
		}
		players[previousPlayer].setBackground(Color.decode("#00A779"));
		players[currentPlayer].setBackground(Color.decode("#00AF64"));
		
		this.repaint();
		
	}
	
	//Used to highlight the first player ONLY when the game first starts
	public void highlightPlayer(int i)
	{
		players[i].setBackground(Color.decode("#00AF64"));
		this.repaint();
	}

    public static void registerChangeTurn(final PlayerView p)
    {
        if(p == null)
        {
            return;
        }
        Action myAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                p.changeTurn();
            }
        };
        p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "doSomething");

        p.getActionMap().put("doSomething",
                myAction);
    }

    public void setTwoBlocks(int numTwoBlocks, String currentPlayer)
    {
        hashMap.get(currentPlayer).setNumTwoBlocks(numTwoBlocks);
    }

    public void setVillageBlocks(int numVillageBlocks, String currentPlayer)
    {
        hashMap.get(currentPlayer).setNumVillageBlocks(numVillageBlocks);
    }

    public void setRiceBlocks(int numRiceBlocks, String currentPlayer)
    {
        hashMap.get(currentPlayer).setRiceBlocks(numRiceBlocks);
    }
}
