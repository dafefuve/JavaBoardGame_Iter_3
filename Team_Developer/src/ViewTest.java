import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import sun.swing.SwingUtilities2;


public class ViewTest
{
	static HashMap<Integer, Polygon> hashMap = new HashMap<Integer, Polygon>();
	static BoardHex[][] theBoard = new BoardHex[15][19];
	static int currentID;
	static JPanel boardPanel;
	static JFrame frame;
	static int currentRow = 0;
	static int currentCol = 0;
	static int currentPlayer;
	static JPanel player1;
	static JPanel player2;
	static JPanel player3;
	static JPanel player4;
	static JPanel playerPanel;
	static JScrollPane jsp;
    static Image dirt;
	
	public static void main(String[] args) 
	{
        try
        {
            dirt = ImageIO.read(new File("img/dirt.jpg"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
		frame = new JFrame();
		frame.setContentPane(createContentPane());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        highlightStartSpace();
        boardPanel.requestFocus();
	}

	public static JPanel createContentPane()
	{
		JPanel mainPanel = new JPanel();
<<<<<<< HEAD:Team_Developer/src/ViewTest.java
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
=======
		playerPanel = new PlayerView(4);
		//playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.LINE_AXIS));
		
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
>>>>>>> Alex'sBitchinBranch:Iter3/src/ViewTest.java
		mainPanel.add(playerPanel);

		int xCoord = 45;
		int yCoord = 45;
		int firstY = yCoord;
		int secondY = firstY + 26;
<<<<<<< HEAD:Team_Developer/src/ViewTest.java

=======
		
		currentID = 0;
		
		//15 rows 19 columns
>>>>>>> Alex'sBitchinBranch:Iter3/src/ViewTest.java
		final ArrayList<Polygon> hexes = new ArrayList<Polygon>();
		for(int i = 0; i < 15; i++)
		{
			for(int j = 0; j < 19; j++)
			{
<<<<<<< HEAD:Team_Developer/src/ViewTest.java
				final Polygon hex = new Polygon();

				//Generate the hexagon
				for(int k = 0; k<6; k++) 
				{
					int x = (int) (xCoord + 30*Math.cos(2*k*Math.PI/6));
					int y = (int) (yCoord + 30*Math.sin(2*k*Math.PI/6));
					hex.addPoint(x, y);
				}
				hexes.add(hex);
=======
				theBoard[i][j] = new BoardHex(xCoord, yCoord);
>>>>>>> Alex'sBitchinBranch:Iter3/src/ViewTest.java
				xCoord += 45;

				if(j % 2 == 0)
				{
					yCoord = secondY;
				}
				else
				{
					yCoord = firstY;
				}
<<<<<<< HEAD:Team_Developer/src/ViewTest.java

			}

			firstY = secondY + 26;
			secondY = firstY + 26;
=======
				
				currentID++;
				
			}
			
			//firstY = secondY + 26;
			//secondY = firstY + 26;
			firstY+=52;
			secondY+=52;
>>>>>>> Alex'sBitchinBranch:Iter3/src/ViewTest.java
			xCoord = 45;
			yCoord = firstY;
		}
<<<<<<< HEAD:Team_Developer/src/ViewTest.java



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
=======
				
		
		boardPanel = new JPanel(){
>>>>>>> Alex'sBitchinBranch:Iter3/src/ViewTest.java
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D)g;
                g2.setRenderingHint(
                        RenderingHints.KEY_ANTIALIASING, 
                        RenderingHints.VALUE_ANTIALIAS_ON);
               g2.setRenderingHint(
                        RenderingHints.KEY_COLOR_RENDERING, 
                        RenderingHints.VALUE_COLOR_RENDER_QUALITY);
               g2.setColor(Color.BLACK);
               g2.setStroke(new BasicStroke(2));
               g2.setPaint(Color.BLACK);
                
                for(int i = 0; i < 15; i++)
                {
                	for(int j = 0; j < 19; j++)
                	{
                		if(theBoard[i][j].getSelected() == true)
                		{
                			g2.setColor(Color.ORANGE);
                		}
                		else
                		{
                			g2.setColor(Color.WHITE);
                		}

                        if(i == 0 && j == 0)
                        {
                            g2.setClip(theBoard[0][0].getPolygon());
                            g2.drawImage(dirt, 30, 30, null );
                            g2.setClip(null);
                            g2.dispose();
                        }
                        else
                        {
                            g2.fillPolygon(theBoard[i][j].getPolygon());
                            //g2.drawPolygon(theBoard[i][j].getPolygon());
                        }



                	}
                }
            }
		};
<<<<<<< HEAD:Team_Developer/src/ViewTest.java
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

=======
		
		boardPanel.setBorder(new EmptyBorder(50, 50, 50, 50) );
		boardPanel.setMinimumSize(new Dimension(900,800));
		boardPanel.setPreferredSize(new Dimension(900,800));
		//boardPanel.setBackground(Color.decode("#80C7FF"));
		boardPanel.setFocusable(true);
		
		jsp = new JScrollPane(boardPanel);
		
		boardPanel.addKeyListener(new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				//Move up
				if(e.getKeyChar() == '8')
				{
					highlightSpace(8);
				}
				else if(e.getKeyChar() == '9')
				{
					highlightSpace(9);
				}
				else if(e.getKeyChar() == '3')
				{
					highlightSpace(3);
				}
				else if(e.getKeyChar() == '2')
				{
					highlightSpace(2);
				}
				else if(e.getKeyChar() == '1')
				{
					highlightSpace(1);
				}
				else if(e.getKeyChar() == '7')
				{
					highlightSpace(7);
				}
				//else for changing player
				else if(e.getKeyChar() == 'p')
				{
					selectPlayer();
				}
			}
			
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		mainPanel.add(jsp);
		
		JPanel bottomPane = new JPanel();
		bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.Y_AXIS));
		bottomPane.add(new CommunalView());
		bottomPane.add(new PalaceCardPanel());
		
>>>>>>> Alex'sBitchinBranch:Iter3/src/ViewTest.java
		JPanel mainMainPanel = new JPanel();
		mainMainPanel.setLayout(new BoxLayout(mainMainPanel, BoxLayout.X_AXIS));
		mainMainPanel.add(mainPanel);
		mainMainPanel.add(bottomPane);
		//mainPanel.add(bottomPane);
		//mainPanel.setVisible(true);
		return mainMainPanel;
	}
<<<<<<< HEAD:Team_Developer/src/ViewTest.java
}
=======
	
	public static void highlightSpace(int key)
	{
		int newRow;
		int newCol;
		
		//Move up
		if(key == 8)
		{
			newRow = currentRow - 1;
			if(newRow < 0 )
			{
				displayAlert("You cannot move out of bounds!", null);
			}
			else
			{
				//Deselect previous space
				theBoard[currentRow][currentCol].setSelected(false);
				//Select new space
				currentRow = newRow;
				theBoard[currentRow][currentCol].setSelected(true);
				adjustScroll();
				//Reflect changes made
				boardPanel.repaint();
			}
			boardPanel.requestFocus();

		}
		//Move NE
		else if(key == 9)
		{
			if(currentCol % 2 == 0)
			{
				newRow = currentRow - 1;
				newCol = currentCol + 1;
				
				if(newRow < 0 || newCol >= 15)
				{
					displayAlert("You cannot move out of bounds!", null);
					return;
				}
				else
				{
					//Deselect current space
					theBoard[currentRow][currentCol].setSelected(false);
					
					//Update current col and row
					currentRow = newRow;
					currentCol = newCol;
					
					//Select new space
					theBoard[currentRow][currentCol].setSelected(true);
					adjustScroll();
					//Show changes
					boardPanel.repaint();
				}
			}
			else
			{
				newRow = currentRow;
				newCol = currentCol + 1;
				
				if(newRow < 0 || newCol >= 15)
				{
					displayAlert("You cannot move out of bounds!", null);
					return;
				}
				else
				{
					//Deselect current space
					theBoard[currentRow][currentCol].setSelected(false);
					
					//Update current col since row didn't change
					currentCol = newCol;
					
					//Select new space
					theBoard[currentRow][currentCol].setSelected(true);
					adjustScroll();
					//Display changes
					boardPanel.repaint();
				}
			}
			boardPanel.requestFocus();
			
		}
		//Move SW
		else if(key == 3)
		{
			if(currentCol % 2 == 0)
			{
				newRow = currentRow;
				newCol = currentCol + 1;
				
				if(newCol >= 19)
				{
					displayAlert("You cannot move out of bounds!", null);
					return;
				}
				else
				{
					//Deselect previous space
					theBoard[currentRow][currentCol].setSelected(false);
					currentCol = newCol;
					//Select new space
					theBoard[currentRow][currentCol].setSelected(true);
					adjustScroll();
					//Reflect changes made
					boardPanel.repaint();
				}
			}
			else
			{
				newRow = currentRow + 1;
				newCol = currentCol + 1;
				
				if(newRow >= 15 || newCol >= 19)
				{
					displayAlert("You cannot move out of bounds!", null);
					return;
				}
				else
				{
					//Deselect previous space
					theBoard[currentRow][currentCol].setSelected(false);
					
					currentCol = newCol;
					currentRow = newRow;
					
					//Select new space
					theBoard[currentRow][currentCol].setSelected(true);
					adjustScroll();
					//Reflect changes made
					boardPanel.repaint();
				}
			}
			
			boardPanel.requestFocus();
			
		}
		else if(key == 2)
		{
			newRow = currentRow + 1;
			if(newRow >= 15 )
			{
				displayAlert("You cannot move out of bounds!", null);
			}
			else
			{
				//Deselect previous space
				theBoard[currentRow][currentCol].setSelected(false);
				//Select new space
				currentRow = newRow;
				
				theBoard[currentRow][currentCol].setSelected(true);
				adjustScroll();
				//Reflect changes made
				boardPanel.repaint();
			}
			boardPanel.requestFocus();
		}
		else if(key == 1)
		{
			if(currentCol % 2 == 0)
			{
				newRow = currentRow;
				newCol = currentCol - 1;
				
				if(newCol < 0 || newRow >= 15)
				{
					displayAlert("You cannot move out of bounds!", null);
					return;
				}
				else
				{
					//Deselect current space
					theBoard[currentRow][currentCol].setSelected(false);
					
					//Update current col
					currentCol = newCol;
					
					//Select new space
					theBoard[currentRow][currentCol].setSelected(true);
					adjustScroll();
					//Show changes
					boardPanel.repaint();
				}
			}
			else
			{
				newRow = currentRow + 1;
				newCol = currentCol - 1;
				
				if(newCol < 0 || newRow >= 15)
				{
					displayAlert("You cannot move out of bounds!", null);
					return;
				}
				else
				{
					//Deselect current space
					theBoard[currentRow][currentCol].setSelected(false);
					
					//Update current row and col
					currentCol = newCol;
					currentRow = newRow;
					
					//Select new space
					theBoard[currentRow][currentCol].setSelected(true);
					adjustScroll();
					//Display changes
					boardPanel.repaint();
				}
			}
			boardPanel.requestFocus();
		}
		else if(key == 7)
		{
			if(currentCol % 2 == 0)
			{
				newRow = currentRow - 1;
				newCol = currentCol - 1;
				
				if(newRow < 0 || newCol < 0)
				{
					displayAlert("You cannot move out of bounds!", null);
					return;
				}
				else
				{
					//Deselect current space
					theBoard[currentRow][currentCol].setSelected(false);
					
					//Update current col and row
					currentRow = newRow;
					currentCol = newCol;
					
					//Select new space
					theBoard[currentRow][currentCol].setSelected(true);
					adjustScroll();
					//Show changes
					boardPanel.repaint();
				}
			}
			else
			{
				newRow = currentRow;
				newCol = currentCol - 1;
				
				if(newRow < 0 || newCol < 0)
				{
					displayAlert("You cannot move out of bounds!", null);
					return;
				}
				else
				{
					//Deselect current space
					theBoard[currentRow][currentCol].setSelected(false);
					
					//Update current col since row didn't change
					currentCol = newCol;
					
					//Select new space
					theBoard[currentRow][currentCol].setSelected(true);
					adjustScroll();
					//Display changes
					boardPanel.repaint();
				}
			}
			boardPanel.requestFocus();

		}
		
	}
	
	public static void highlightStartSpace()
	{
		theBoard[0][0].setSelected(true);
		boardPanel.repaint();
	}
	
	public static void selectPlayer()
	{
		((PlayerView) playerPanel).changeTurn();
	}
	
	public static void displayAlert(String message, String title)
	{
		JOptionPane.showMessageDialog(frame,
			    "You cannot exit the board's bounds.",
			    "Out of Bounds Warning",
			    JOptionPane.WARNING_MESSAGE);
	}
	
	public static void adjustScroll()
	{
		//Scroll the panel based on what row we are on
		if(currentRow > 9)
		{
			JScrollBar verticalBar = jsp.getVerticalScrollBar();
			verticalBar.setValue(verticalBar.getMaximum ());
		}
		else
		{
			JScrollBar verticalBar = jsp.getVerticalScrollBar();
			verticalBar.setValue(0);
		}
		
	}
	
	public static void highlightStartHex()
	{
		theBoard[0][0].getBoardHex().setSelected(true);
		boardPanel.repaint();
	}
}


>>>>>>> Alex'sBitchinBranch:Iter3/src/ViewTest.java
