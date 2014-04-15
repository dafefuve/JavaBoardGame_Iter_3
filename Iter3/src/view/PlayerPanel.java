package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sun.swing.SwingUtilities2;

public class PlayerPanel extends JPanel
{
	JLabel playerNameLabel;
	JLabel singleVillageBlocks;
	JLabel singleRiceBlocks;
	JLabel twoBlocks;
	JLabel actionTokens;
	JLabel developers;
	JLabel score;
	
	int numSingleVillageBlocks;
	int numSingleRiceBlocks;
	int numTwoBlocks;
	int numActionTokens;
	int numDevelopers;
	int scoreValue;
	
	
	public PlayerPanel(String s)
	{
		//Initialize the "model"
		numSingleVillageBlocks = 2;
		numSingleRiceBlocks = 3;
		numTwoBlocks = 5;
		numActionTokens = 3;
		numDevelopers = 12;
		scoreValue = 0;
		
		playerNameLabel = new JLabel(s);
		playerNameLabel.putClientProperty(SwingUtilities2.AA_TEXT_PROPERTY_KEY, null);
		playerNameLabel.setForeground(Color.WHITE);
		playerNameLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		
		//Setting a grid layout will make labels left align
		this.setLayout(new GridLayout(7,1));
		this.add(playerNameLabel);
		
		singleVillageBlocks = new JLabel("Single Village Blocks: " + numSingleVillageBlocks);
		singleVillageBlocks.setForeground(Color.WHITE);
		singleRiceBlocks = new JLabel("Single Rice Blocks: " + numSingleRiceBlocks);
		singleRiceBlocks.setForeground(Color.WHITE);
		twoBlocks = new JLabel("Two Blocks: " + numTwoBlocks);
		twoBlocks.setForeground(Color.WHITE);
		actionTokens = new JLabel("Action Tokens: " + numActionTokens);
		actionTokens.setForeground(Color.WHITE);
		developers = new JLabel("Developers: " + numDevelopers);
		developers.setForeground(Color.WHITE);
		score = new JLabel("Score: " + scoreValue);
		score.setForeground(Color.WHITE);
		
		this.add(singleVillageBlocks);
		this.add(singleRiceBlocks);
		this.add(twoBlocks);
		this.add(actionTokens);
		this.add(developers);
		this.add(score);
		
		this.setBackground(Color.decode("#00A779"));
		this.setMinimumSize(new Dimension(230,150));
		this.setPreferredSize(new Dimension(230,150));
		
		Border border = BorderFactory.createMatteBorder(0, 5, 5, 0, Color.WHITE);
	    Border margin = new EmptyBorder(5,5,5,5);
	    
	    this.setBorder(new CompoundBorder(border, margin));
	}
	
}
