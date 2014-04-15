package view;

/**
 * Created by Alex on 4/14/2014.
 */
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
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
    public static JPanel playerView;
    public static JScrollPane jsp;
    static BufferedImage dirt;
    static BufferedImage water;
    static BufferedImage land;
    static BufferedImage mainland;
    static int[] irrigationIDs;

    public static void main(String[] args)
    {
        frame = new JFrame();
        frame.setContentPane(createContentPane());
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        highlightStartSpace();
        boardPanel.requestFocus();
        playerView.requestFocus();
    }

    public static JPanel createContentPane()
    {
        try
        {
            dirt = ImageIO.read(new File("Iter3/src/view/images/dirt.jpg"));
            water = ImageIO.read(new File("Iter3/src/view/images/water.jpg"));
            land = ImageIO.read(new File("Iter3/src/view/images/land.png"));
            mainland = ImageIO.read(new File("Iter3/src/view/images/mainland.jpg"));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

        irrigationIDs = new int[3];
        irrigationIDs[0] = 99;
        irrigationIDs[1] = 108;
        irrigationIDs[2] = 160;

        JPanel mainPanel = new JPanel();
        playerView = new PlayerView(4);
        //playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.LINE_AXIS));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(playerView);

        int xCoord = 45;
        int yCoord = 45;
        int firstY = yCoord;
        int secondY = firstY + 26;

        currentID = 0;

        //15 rows 19 columns
        final ArrayList<Polygon> hexes = new ArrayList<Polygon>();
        for(int i = 0; i < 15; i++)
        {
            for(int j = 0; j < 19; j++)
            {
                theBoard[i][j] = new BoardHex(xCoord, yCoord);
                xCoord += 45;

                if(j % 2 == 0)
                {
                    yCoord = secondY;
                }
                else
                {
                    yCoord = firstY;
                }

                currentID++;

            }

            //firstY = secondY + 26;
            //secondY = firstY + 26;
            firstY+=52;
            secondY+=52;
            xCoord = 45;
            yCoord = firstY;
        }

        //Set highland tiles
        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 19; j++)
            {
                theBoard[i][j].getBoardHex().setMountain();
            }
        }

        //Set lowland tiles
        for(int i = 8; i < 15; i++)
        {
            for(int j = 0; j < 19; j++)
            {
                theBoard[i][j].getBoardHex().setLowlands();
            }
        }

        //Hardcode awkward board shape
        theBoard[1][4].getBoardHex().resetMountain();
        theBoard[1][7].getBoardHex().resetMountain();
        theBoard[1][10].getBoardHex().resetMountain();
        theBoard[1][13].getBoardHex().resetMountain();
        theBoard[2][4].getBoardHex().resetMountain();
        theBoard[2][5].getBoardHex().resetMountain();
        theBoard[2][7].getBoardHex().resetMountain();
        theBoard[2][8].getBoardHex().resetMountain();
        theBoard[2][10].getBoardHex().resetMountain();
        theBoard[2][11].getBoardHex().resetMountain();
        theBoard[2][13].getBoardHex().resetMountain();
        theBoard[2][14].getBoardHex().resetMountain();


        //Set lowland tiles
        for(int i = 3; i < 4; i++)
        {
            for(int j = 3; j < 17; j++)
            {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        boardPanel = new JPanel(){
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
                g2.setStroke(new BasicStroke(20));
                g2.setPaint(Color.BLACK);

                for(int i = 0; i < 15; i++)
                {
                    for(int j = 0; j < 19; j++)
                    {


                        if(theBoard[i][j].getBoardHex().getSpaceID() == irrigationIDs[0]
                                || theBoard[i][j].getBoardHex().getSpaceID() == irrigationIDs[1]
                                || theBoard[i][j].getBoardHex().getSpaceID() == irrigationIDs[2] )
                        {

                            g2.setPaint(new TexturePaint(water, new Rectangle(300,300,300,300)));

                            g2.fillPolygon(theBoard[i][j].getPolygon());
                        }
                        else if(theBoard[i][j].getBoardHex().getIsLowlands() == true)
                        {
                            g2.setPaint(new TexturePaint(land, new Rectangle(700,700,700,700)));

                            g2.fillPolygon(theBoard[i][j].getPolygon());
                            g2.setColor(Color.WHITE);
                            g2.setStroke(new BasicStroke(1f));
                            g2.drawPolygon(theBoard[i][j].getPolygon());
                            //g2.drawPolygon(theBoard[i][j].getPolygon());
                            g2.drawString(String.valueOf(theBoard[i][j].getBoardHex().getLevel()), theBoard[i][j].getBoardHex().getCenterX(), theBoard[i][j].getBoardHex().getCenterY());

                        }
                        else if(theBoard[i][j].getBoardHex().getIsMountain() == true)
                        {
                            g2.setPaint(new TexturePaint(dirt, new Rectangle(300,300,300,300)));
                            g2.fillPolygon(theBoard[i][j].getPolygon());
                            g2.setColor(Color.WHITE);
                            g2.setStroke(new BasicStroke(1f));
                            g2.drawPolygon(theBoard[i][j].getPolygon());
                            g2.setFont(new Font("Courier New", Font.PLAIN, 16));
                            g2.setColor(Color.BLACK);

                            g2.drawString("fuck", theBoard[i][j].getBoardHex().getCenterX(), theBoard[i][j].getBoardHex().getCenterY());
                        }
                        else
                        {
                            g2.setPaint(new TexturePaint(mainland, new Rectangle(300,300,300,300)));
                            g2.fillPolygon(theBoard[i][j].getPolygon());
                            g2.setColor(Color.WHITE);
                            g2.setStroke(new BasicStroke(1f));
                            g2.drawPolygon(theBoard[i][j].getPolygon());
                            g2.setFont(new Font("Courier New", Font.PLAIN, 16));
                            g2.setColor(Color.BLACK);

                        }

                        if(theBoard[i][j].getSelected() == true)
                        {
                            g2.setColor(Color.ORANGE);
                            g2.fillPolygon(theBoard[i][j].getBoardHex().getPolygon());
                        }

                    }
                }

            }
        };

        boardPanel.setBorder(new EmptyBorder(50, 50, 50, 50) );
        boardPanel.setMinimumSize(new Dimension(900,850));
        boardPanel.setPreferredSize(new Dimension(900,850));
        boardPanel.setBackground(Color.decode("#80C7FF"));
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
                /*
                else if(e.getKeyChar() == 'p')
                {
                    selectPlayer();
                }*/
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

        JPanel mainMainPanel = new JPanel();
        mainMainPanel.setLayout(new BoxLayout(mainMainPanel, BoxLayout.X_AXIS));
        mainMainPanel.add(mainPanel);
        mainMainPanel.add(bottomPane);
        //mainPanel.add(bottomPane);
        //mainPanel.setVisible(true);
        registerChangeTurn();
        playerView.requestFocusInWindow();
        playerView.requestFocus();
        return mainMainPanel;
    }

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

                if(newRow < 0 || newCol >= 19)
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

                if(newRow < 0 || newCol >= 19)
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
        ((PlayerView) playerView).changeTurn();
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

    public static void registerChangeTurn()
    {
        ((PlayerView)playerView).registerChangeTurn((PlayerView)playerView);
    }
}
