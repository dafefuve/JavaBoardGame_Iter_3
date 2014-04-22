package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


public class CommunalView extends JPanel
{
	JLabel threeSpaceTiles;
	JLabel irrigationTiles;
	JLabel lvl2PalaceTiles;
	JLabel lvl4PalaceTiles;
	JLabel lvl6PalaceTiles;
	JLabel lvl8PalaceTiles;
	JLabel lvl10PalaceTiles;
	JLabel modeLabel;
	
	int numThreeSpaceTiles;
	int numIrrigationTiles;
	int numLvl2PalaceTiles;
	int numLvl4PalaceTiles;
	int numLvl6PalaceTiles;
	int numLvl8PalaceTiles;
	int numLvl10PalaceTiles;
	
	String mode;
	
	public CommunalView()
	{
		//Initialize "model" variables
		numThreeSpaceTiles = 3;
		numIrrigationTiles = 16;
		numLvl2PalaceTiles = 6;
		numLvl4PalaceTiles = 7;
		numLvl6PalaceTiles = 8;
		numLvl8PalaceTiles = 9;
		numLvl10PalaceTiles = 10;
		mode = "Active";
		
		this.setBorder(new EmptyBorder(10, 10, 10, 10) );
		this.setMinimumSize(new Dimension(300,325));
		this.setPreferredSize(new Dimension(300,325));
		this.setBackground(Color.decode("#00A779"));
		
		JLabel communalLabel = new JLabel("Communal Resources");
		communalLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		communalLabel.setForeground(Color.WHITE);
		Border communalLabelBorder = BorderFactory.createMatteBorder(5, 0, 0, 0, Color.WHITE);
		Border communalLabelMargin = new EmptyBorder(10, 0, 0, 0);
		communalLabel.setBorder(new CompoundBorder(communalLabelBorder, communalLabelMargin));

		
		threeSpaceTiles = new JLabel("Three Space Tiles: " + numThreeSpaceTiles);
		threeSpaceTiles.setForeground(Color.WHITE);
		irrigationTiles = new JLabel("Irrigation Tiles: " + numIrrigationTiles + "  ");
		irrigationTiles.setForeground(Color.WHITE);
		lvl2PalaceTiles = new JLabel("Lvl 2 Palace Tiles: " + numLvl2PalaceTiles);
		lvl2PalaceTiles.setForeground(Color.WHITE);
		lvl4PalaceTiles = new JLabel("Lvl 4 Palace Tiles: " + numLvl4PalaceTiles);
		lvl4PalaceTiles.setForeground(Color.WHITE);
		lvl6PalaceTiles = new JLabel("Lvl 6 Palace Tiles: " + numLvl6PalaceTiles);
		lvl6PalaceTiles.setForeground(Color.WHITE);
		lvl8PalaceTiles = new JLabel("Lvl 8 Palace Tiles: " + numLvl8PalaceTiles);
		lvl8PalaceTiles.setForeground(Color.WHITE);
		lvl10PalaceTiles = new JLabel("Lvl 10 Palace Tiles: " + numLvl10PalaceTiles);
		lvl10PalaceTiles.setForeground(Color.WHITE);
		modeLabel = new JLabel("Current Mode: " + mode);
		modeLabel.setForeground(Color.WHITE);
		modeLabel.setFont(new Font("Helvetica", Font.BOLD, 24));

		Border marginForMode = new EmptyBorder(50, 0, 0, 0);
		Border borderForMode = BorderFactory.createMatteBorder(5, 0, 0, 0, Color.WHITE);
		modeLabel.setBorder(new CompoundBorder(borderForMode, marginForMode));
		
		this.add(communalLabel);
		this.add(threeSpaceTiles);
		this.add(irrigationTiles);
		this.add(lvl2PalaceTiles);
		this.add(lvl4PalaceTiles);
		this.add(lvl6PalaceTiles);
		this.add(lvl8PalaceTiles);
		this.add(lvl10PalaceTiles);
		this.add(modeLabel);
		
		Border borderCommunal = BorderFactory.createMatteBorder(10, 20, 0, 0, Color.WHITE);
	    Border marginCommunal = new EmptyBorder(15,15,15,15);
	    this.setBorder(new CompoundBorder(borderCommunal, marginCommunal));
	}
	
	public void setCurrentMode(String mode)
	{
		this.mode = mode;
	}
	
	public void useThreeSpaceTile()
	{
		numThreeSpaceTiles--;
		updateNumThreeSpaceTiles();
	}
	
	public void updateNumThreeSpaceTiles()
	{
		threeSpaceTiles.setText("Three Space Tiles: " + numThreeSpaceTiles);
	}

    public void setIrrigationTiles(int numIrrigationTiles)
    {
        this.numIrrigationTiles = numIrrigationTiles;
        irrigationTiles.setText("Irrigation Tiles: " + this.numIrrigationTiles);
    }

    public void setPlanningMode()
    {
        modeLabel.setText("Current Mode: Planning");
    }

    public void setActiveMode()
    {
        modeLabel.setText("Current Mode: Active");
    }
}
