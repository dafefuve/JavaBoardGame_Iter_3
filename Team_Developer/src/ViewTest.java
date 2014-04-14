import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sun.swing.SwingUtilities2;


public class ViewTest
{
	public static void main(String[] args) 
	{
		JFrame frame = new JFrame();
		frame.setContentPane(createContentPane());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
	}

	public static JPanel createContentPane()
	{
		JPanel mainPanel = new JPanel();
		JPanel playerPanel = new JPanel();
		playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.LINE_AXIS));
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

		JPanel player1 = new JPanel();
		JLabel firstPlayerLabel = new JLabel("First Player");
		firstPlayerLabel.putClientProperty(SwingUtilities2.AA_TEXT_PROPERTY_KEY, null);
		firstPlayerLabel.setForeground(Color.WHITE);
		firstPlayerLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		//Setting a grid layout will make labels left align
		//player1.setLayout(new GridLayout(1,1));
		player1.add(firstPlayerLabel);
		JLabel p1SingleVillageBlocks = new JLabel("Single Village Blocks:");
		p1SingleVillageBlocks.setForeground(Color.WHITE);
		player1.add(p1SingleVillageBlocks);
		player1.setBackground(Color.decode("#00A779"));
		player1.setMinimumSize(new Dimension(200,150));
		player1.setPreferredSize(new Dimension(200,150));
		Border border = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.WHITE);
	    Border margin = new EmptyBorder(5,5,5,5);
	    player1.setBorder(new CompoundBorder(border, margin));

	    //Set border for players 2-4
	    border = BorderFactory.createMatteBorder(0, 5, 0, 0, Color.WHITE);
		//player1.setBorder(new EmptyBorder(5, 5, 5, 5) );

		JPanel player2 = new JPanel();
		player2.setBackground(Color.decode("#00A779"));
		JLabel secondPlayerLabel = new JLabel("Second Player");
		secondPlayerLabel.setForeground(Color.WHITE);
		secondPlayerLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		player2.add(secondPlayerLabel);
		JLabel p2SingleVillageBlocks = new JLabel("Single Village Blocks:");
		p2SingleVillageBlocks.setForeground(Color.WHITE);
		player2.add(p2SingleVillageBlocks);
		player2.setMinimumSize(new Dimension(200,150));
		player2.setPreferredSize(new Dimension(200,150));
		player2.setBorder(new CompoundBorder(border, margin));
		//player2.setBorder(new EmptyBorder(10, 10, 10, 10) );

		JPanel player3 = new JPanel();
		player3.setBackground(Color.decode("#00A779"));
		JLabel thirdPlayerLabel = new JLabel("Third Player");
		thirdPlayerLabel.setForeground(Color.WHITE);
		thirdPlayerLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		player3.add(thirdPlayerLabel);
		JLabel p3SingleVillageBlocks = new JLabel("Single Village Blocks:");
		p3SingleVillageBlocks.setForeground(Color.WHITE);
		player3.add(p3SingleVillageBlocks);
		player3.setMinimumSize(new Dimension(200,150));
		player3.setPreferredSize(new Dimension(200,150));
		player3.setBorder(new CompoundBorder(border, margin));
		//player3.setBorder(new EmptyBorder(10, 10, 10, 10) );

		JPanel player4 = new JPanel();
		player4.setBackground(Color.decode("#00A779"));
		JLabel fourthPlayerLabel = new JLabel("Fourth Player");
		fourthPlayerLabel.setForeground(Color.WHITE);
		fourthPlayerLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		player4.add(fourthPlayerLabel);
		JLabel p4SingleVillageBlocks = new JLabel("Single Village Blocks:");
		p4SingleVillageBlocks.setForeground(Color.WHITE);
		player4.add(p4SingleVillageBlocks);
		player4.setMinimumSize(new Dimension(200,150));
		player4.setPreferredSize(new Dimension(200,150));
		player4.setBorder(new CompoundBorder(border, margin));
		//player4.setBorder(new EmptyBorder(10, 10, 10, 10) );

		playerPanel.add(player1);
		playerPanel.add(player2);
		playerPanel.add(player3);
		playerPanel.add(player4);
		mainPanel.add(playerPanel);

		int xCoord = 45;
		int yCoord = 45;
		int firstY = yCoord;
		int secondY = firstY + 26;

		final ArrayList<Polygon> hexes = new ArrayList<Polygon>();
		for(int i = 0; i < 15; i++)
		{
			for(int j = 0; j < 15; j++)
			{
				final Polygon hex = new Polygon();

				//Generate the hexagon
				for(int k = 0; k<6; k++) 
				{
					int x = (int) (xCoord + 30*Math.cos(2*k*Math.PI/6));
					int y = (int) (yCoord + 30*Math.sin(2*k*Math.PI/6));
					hex.addPoint(x, y);
				}
				hexes.add(hex);
				xCoord += 45;

				if(j % 2 == 0)
				{
					yCoord = secondY;
				}
				else
				{
					yCoord = firstY;
				}

			}

			firstY = secondY + 26;
			secondY = firstY + 26;
			xCoord = 45;
		}



	/*
      
				//Polygon Stuff
				final Polygon hex1 = new Polygon();
				for(int i = 0; i<6; i++) {
					int x = (int) (30 + 30*Math.cos(2*i*Math.PI/6));
					int y = (int) (30 + 30*Math.sin(2*i*Math.PI/6));
					   hex1.addPoint(x, y);
					}
				
				final Polygon hex2 = new Polygon();
				for(int i = 0; i<6; i++) {
					int x = (int) (120 + 30*Math.cos(2*i*Math.PI/6));
					int y = (int) (30 + 30*Math.sin(2*i*Math.PI/6));
					   hex2.addPoint(x, y);
					}
				
				final Polygon hex3 = new Polygon();
				for(int i = 0; i<6; i++) {
					int x = (int) (75 + 30*Math.cos(2*i*Math.PI/6));
					int y = (int) (56 + 30*Math.sin(2*i*Math.PI/6));
					   hex3.addPoint(x, y);
					}
				
				final Polygon hex4 = new Polygon();
				for(int i = 0; i<6; i++) {
					int x = (int) (30 + 30*Math.cos(2*i*Math.PI/6));
					int y = (int) (82 + 30*Math.sin(2*i*Math.PI/6));
					   hex4.addPoint(x, y);
					}
				
				final Polygon hex5 = new Polygon();
				for(int i = 0; i<6; i++) {
					int x = (int) (75 + 30*Math.cos(2*i*Math.PI/6));
					int y = (int) (108 + 30*Math.sin(2*i*Math.PI/6));
					   hex5.addPoint(x, y);
					}
		*/		


		JPanel boardPanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ((Graphics2D) g).setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING, 
                        RenderingHints.VALUE_ANTIALIAS_ON);
                ((Graphics2D) g).setRenderingHint(
                        RenderingHints.KEY_COLOR_RENDERING, 
                        RenderingHints.VALUE_COLOR_RENDER_QUALITY);
                g.setColor(Color.BLACK);
                ((Graphics2D) g).setStroke(new BasicStroke(1));
                
                for(int i = 0; i < hexes.size(); i++)
                {
                	g.drawPolygon(hexes.get(i));
                }
                
                //g.setClip(hex1);
               // g.drawImage
               /* g.drawPolygon(hex1);
                g.drawString("0",35,35);
                g.drawPolygon(hex2);
                g.drawPolygon(hex3);
                g.drawPolygon(hex4);
                g.drawPolygon(hex5);
                */
            }
		};
		//JLabel label = new JLabel("board goes here");
		//boardPanel.add(label);
		boardPanel.setBorder(new EmptyBorder(50, 50, 50, 50) );
		boardPanel.setMinimumSize(new Dimension(800,600));
		boardPanel.setPreferredSize(new Dimension(800,600));



		mainPanel.add(boardPanel);

		JPanel communalPanel = new JPanel();
		communalPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
		communalPanel.setMinimumSize(new Dimension(300,325));
		communalPanel.setPreferredSize(new Dimension(300,325));
		communalPanel.setBackground(Color.decode("#00A779"));
		JLabel communalLabel = new JLabel("Communal Pieces");
		communalLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		communalLabel.setForeground(Color.WHITE);
		communalPanel.add(communalLabel);
		//communalPanel.setBackground(Color.WHITE);

		Border borderCommunal = BorderFactory.createMatteBorder(10, 20, 0, 0, Color.WHITE);
	    Border marginCommunal = new EmptyBorder(15,15,15,15);
	    communalPanel.setBorder(new CompoundBorder(borderCommunal, marginCommunal));

		JPanel palaceCardPanel = new JPanel();
		palaceCardPanel.setBorder(new EmptyBorder(10, 10, 10, 10) );
		palaceCardPanel.setMinimumSize(new Dimension(300,325));
		palaceCardPanel.setPreferredSize(new Dimension(300,325));
		JLabel palaceCardLabel = new JLabel("Palace Card Stuff");
		palaceCardPanel.add(palaceCardLabel);
		palaceCardLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		palaceCardLabel.setForeground(Color.WHITE);
		palaceCardPanel.setBackground(Color.decode("#00A779"));

		Border borderPalaceCard = BorderFactory.createMatteBorder(10, 20, 0, 0, Color.WHITE);
	    Border marginPalaceCard = new EmptyBorder(15,15,15,15);
	    palaceCardPanel.setBorder(new CompoundBorder(borderPalaceCard, marginPalaceCard));

		JPanel bottomPane = new JPanel();
		bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.Y_AXIS));
		bottomPane.add(communalPanel);
		bottomPane.add(palaceCardPanel);

		JPanel mainMainPanel = new JPanel();
		mainMainPanel.setLayout(new BoxLayout(mainMainPanel, BoxLayout.X_AXIS));
		mainMainPanel.add(mainPanel);
		mainMainPanel.add(bottomPane);
		//mainPanel.add(bottomPane);
		//mainPanel.setVisible(true);
		return mainMainPanel;
	}
}