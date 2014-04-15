package view;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.*;

public class PlayerView extends JPanel
{
	JPanel[] players;
	static int currentPlayer;
	
	public PlayerView(int numberOfPlayers)
	{
		currentPlayer = 0;
		players = new JPanel[numberOfPlayers];
		
		//Set up container 
		this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
		
		//Initialize individual player panels 
		for(int i = 0; i < players.length; i++)
		{
			JPanel currentPanel = new PlayerPanel("Player " + (i + 1));
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
        Action myAction = new AbstractAction() {
            public void actionPerformed(ActionEvent e) {
                p.changeTurn();
            }
        };
        p.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_P, 0), "doSomething");

        p.getActionMap().put("doSomething",
                myAction);
    }
}
