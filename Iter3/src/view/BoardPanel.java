package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.GlyphVector;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Stack;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class BoardPanel extends JPanel
{
   private ViewBoard board;
   private int[][] IDs;
   private BufferedImage dirt;
   private BufferedImage water;
   private BufferedImage land;
   private BufferedImage mainland;
   private int[] irrigationIDs;
   private ArrayList<Polygon> hexes;
   private static int currentID = 0;
   private static int currentRow = 0;
   private static int currentCol = 0;
   private static int currentRow2 = 1;
   private static int currentCol2 = 0;
   private static int currentRow3 = 0;
   private static int currentCol3 = 0;
   private JScrollPane jsp;
   private JPanel panel;
   private boolean placing;
   private boolean placingDouble;
   //private ViewHexVillage viewHexVillage;

   public BoardPanel()
   {
       board = new ViewBoard(15, 19);
       //setUpKeyListener();
       //panel = this;
       placing = false;
       //viewHexVillage = new ViewHexVillage();

       this.setBorder(new EmptyBorder(50, 50, 50, 50) );
       this.setMinimumSize(new Dimension(900,850));
       this.setPreferredSize(new Dimension(900,850));
       this.setBackground(Color.decode("#80C7FF"));
       this.setFocusable(true);

       IDs = new int[15][19];
       int k = 0;
       for(int i = 0; i < 15; i++)
       {
           for(int j = 0; j < 19; j++)
           {
               IDs[i][j] = k;
               k++;
           }
       }

       //jsp = new JScrollPane(this);

   }

   public JScrollPane getContentPane()
   {
       return this.jsp;
   }

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
               //Draw the texture in hex shape
               BufferedImage texture = board.getStackAt(i, j).peekIntoStack().getImage();
               g2.setPaint(board.getStackAt(i, j).peekIntoStack().getTP());
               g2.fillPolygon(board.getPolygonAt(i, j));

               //drawLevel(g2, board.getPolygonAt(i, j), board.getStackAt(i, j));

               //Set outline for the shape
               g2.setColor(Color.WHITE);
               g2.setStroke(new BasicStroke(1f));
               g2.drawPolygon(board.getPolygonAt(i, j));

               boolean selected = board.getSelectedAt(i, j);
               if(selected)
               {
                   Color c = new Color(0f,1f,1f,.3f);
                   g2.setColor(c);
                   g2.fillPolygon(board.getPolygonAt(i, j));
               }

               //Render levels
               //Avoid irrigation tiles and central java
               if(board.getStackAt(i, j).size() == 1)
               {
                   continue;
               }
               if(i == 5 && j == 4)
               {
                   continue;
               }
               if(i == 5 && j == 13)
               {
                   continue;
               }
               if(i == 8 && j == 8)
               {
                   continue;
               }
               if(placing && currentRow == i && currentCol == j)
               {
                   continue;
               }
               drawLevel(g2, board.getPolygonAt(i, j), board.getStackAt(i, j));



           }
       }
   }


    public void highlightSpace(int key)
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
                board.setDeselectedAt(currentRow, currentCol);
                //Select new space
                currentRow = newRow;
                board.setSelectedAt(currentRow, currentCol);

                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();

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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current col and row
                    currentRow = newRow;
                    currentCol = newCol;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);

                    //Show changes
                    this.repaint();
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current col since row didn't change
                    currentCol = newCol;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);

                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();

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
                    board.setDeselectedAt(currentRow, currentCol);
                    currentCol = newCol;
                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);

                    //Reflect changes made
                    this.repaint();
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
                    board.setDeselectedAt(currentRow, currentCol);

                    currentCol = newCol;
                    currentRow = newRow;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);

                    //Reflect changes made
                    this.repaint();
                }
            }

            this.requestFocus();

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
                board.setDeselectedAt(currentRow, currentCol);
                //Select new space
                currentRow = newRow;

                board.setSelectedAt(currentRow, currentCol);

                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current col
                    currentCol = newCol;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);

                    //Show changes
                    this.repaint();
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current row and col
                    currentCol = newCol;
                    currentRow = newRow;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);

                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current col and row
                    currentRow = newRow;
                    currentCol = newCol;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);

                    //Show changes
                    this.repaint();
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current col since row didn't change
                    currentCol = newCol;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);

                    //Display changes
                    this.repaint();
                }
            }
           this.requestFocus();

        }

    }

   /* public void highlightDoubleSpace(int key)
    {
        int newRow;
        int newCol;
        int newRow2;
        int newCol2;

        //Move up
        if(key == 8)
        {
            newRow = currentRow - 1;
            newRow2 = currentRow2 - 1;
            if(newRow < 0 || newRow2 < 0)
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous spaces
                theBoard[currentRow][currentCol].setSelected(false);
                theBoard[currentRow2][currentCol2].setSelected(false);

                //Select new spaces
                currentRow = newRow;
                currentRow2 = newRow2;

                theBoard[currentRow][currentCol].setSelected(true);
                theBoard[currentRow2][currentCol2].setSelected(true);

                adjustScroll();
                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();

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
                    this.repaint();
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
                    this.repaint();
                }
            }

            if(currentCol2 % 2 == 0)
            {
                newRow2 = currentRow2 - 1;
                newCol2 = currentCol2 + 1;

                if(newRow2 < 0 || newCol2 >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[currentRow2][currentCol2].setSelected(false);

                    //Update current col and row
                    currentRow2 = newRow2;
                    currentCol2 = newCol2;

                    //Select new space
                    theBoard[currentRow2][currentCol2].setSelected(true);
                    adjustScroll();
                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow2 = currentRow2;
                newCol2 = currentCol2 + 1;

                if(newRow2 < 0 || newCol2 >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[currentRow2][currentCol2].setSelected(false);

                    //Update current col since row didn't change
                    currentCol2 = newCol2;

                    //Select new space
                    theBoard[currentRow2][currentCol2].setSelected(true);
                    adjustScroll();
                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();

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
                    this.repaint();
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
                    this.repaint();
                }
            }

            if(currentCol2 % 2 == 0)
            {
                newRow2 = currentRow2;
                newCol2 = currentCol2 + 1;

                if(newCol2 >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect previous space
                    theBoard[currentRow2][currentCol2].setSelected(false);
                    currentCol = newCol;
                    //Select new space
                    theBoard[currentRow2][currentCol2].setSelected(true);
                    adjustScroll();
                    //Reflect changes made
                    this.repaint();
                }
            }
            else
            {
                newRow2 = currentRow2 + 1;
                newCol2 = currentCol2 + 1;

                if(newRow2 >= 15 || newCol2 >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect previous space
                    theBoard[currentRow2][currentCol2].setSelected(false);

                    currentCol2 = newCol2;
                    currentRow2 = newRow2;

                    //Select new space
                    theBoard[currentRow2][currentCol2].setSelected(true);
                    adjustScroll();
                    //Reflect changes made
                    this.repaint();
                }
            }

            this.requestFocus();

        }
        else if(key == 2)
        {
            newRow = currentRow + 1;
            newRow2 = currentRow2 + 1;
            if(newRow >= 15 || newRow2 >= 15)
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous space
                theBoard[currentRow][currentCol].setSelected(false);
                theBoard[currentRow2][currentCol2].setSelected(false);
                //Select new space
                currentRow = newRow;
                currentRow2 = newRow2;

                theBoard[currentRow][currentCol].setSelected(true);
                theBoard[currentRow2][currentCol2].setSelected(true);
                adjustScroll();
                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();
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
                    this.repaint();
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
                    this.repaint();
                }
            }

            if(currentCol2 % 2 == 0)
            {
                newRow2 = currentRow2;
                newCol2 = currentCol2 - 1;

                if(newCol2 < 0 || newRow2 >= 15)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[currentRow2][currentCol2].setSelected(false);

                    //Update current col
                    currentCol2 = newCol2;

                    //Select new space
                    theBoard[currentRow2][currentCol2].setSelected(true);
                    adjustScroll();
                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow2 = currentRow2 + 1;
                newCol2 = currentCol2 - 1;

                if(newCol2 < 0 || newRow2 >= 15)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[currentRow2][currentCol2].setSelected(false);

                    //Update current row and col
                    currentCol2 = newCol2;
                    currentRow2 = newRow2;

                    //Select new space
                    theBoard[currentRow2][currentCol2].setSelected(true);
                    adjustScroll();
                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();
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
                    this.repaint();
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
                    this.repaint();
                }
            }

            if(currentCol2 % 2 == 0)
            {
                newRow2 = currentRow2 - 1;
                newCol2 = currentCol2 - 1;

                if(newRow2 < 0 || newCol2 < 0)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[currentRow2][currentCol2].setSelected(false);

                    //Update current col and row
                    currentRow2 = newRow2;
                    currentCol2 = newCol2;

                    //Select new space
                    theBoard[currentRow2][currentCol2].setSelected(true);
                    adjustScroll();
                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow2 = currentRow2;
                newCol2 = currentCol2 - 1;

                if(newRow2 < 0 || newCol2 < 0)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[currentRow2][currentCol2].setSelected(false);

                    //Update current col since row didn't change
                    currentCol2 = newCol2;

                    //Select new space
                    theBoard[currentRow2][currentCol2].setSelected(true);
                    adjustScroll();
                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();

        }

    }
*/
    public void highlightStartSpace()
    {
        board.setSelectedAt(0,0);
        this.repaint();
    }
/*
    public void highlightDoubleStartSpace()
    {
        theBoard[0][0].setSelected(true);
        theBoard[1][0].setSelected(true);
        this.repaint();
    }
    */

    public void displayAlert(String message, String title)
    {
        JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
                "You cannot exit the board's bounds.",
                "Out of Bounds Warning",
                JOptionPane.WARNING_MESSAGE);
    }

   /* public void adjustScroll()
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
    */


    public void placeSingleVillageTile()
    {
        placing = true;
        board.getStackAt(0, 0).pushIntoStack(new ViewHexVillage());
        this.repaint();
    }

    public void placeSingleRiceTile()
    {
        placing = true;
        board.getStackAt(0, 0).pushIntoStack(new ViewHexRice());
        this.repaint();
    }

    public void placeDoubleTile()
    {
        beginDoublePlacement();
        board.getStackAt(0, 0).pushIntoStack(new ViewHexVillage());
        board.getStackAt(1, 0).pushIntoStack(new ViewHexRice());
        this.repaint();
    }

    //Three block is village as pivot and two rice

    public void placeIrrigationTile()
    {
        board.getStackAt(0, 0).pushIntoStack(new ViewHexIrrigation());
        this.repaint();
    }


    public void beginPlacement()
    {
        placing = true;
    }

    public void beginDoublePlacement()
    {
        placingDouble = true;
    }


    public void endPlacement(boolean invalidPlacement)
    {
        if(!invalidPlacement){
            placing = false;
            currentRow = 0;
            currentCol = 0;
            repaint();
        }else {
            JOptionPane.showMessageDialog(this.getTopLevelAncestor(),
                    "You cannot preform that move",
                    "Invalid Action",
                    JOptionPane.WARNING_MESSAGE);
        }

    }


    public void drawLevel(Graphics2D g, Polygon hex, ViewHexStack v)
    {

        Font f = new Font("Helvetica", Font.BOLD, 16);

        g.setFont(f);
        g.setColor(Color.WHITE);
        g.setStroke(new BasicStroke(4));
        int[] x = hex.xpoints;
        int[] y = hex.ypoints;

        g.drawString(String.valueOf(v.size() - 2), x[0] - 40, y[0]);

    }

    public int getCurrentSpace()
    {
        //returns id of the currently selected space
        return IDs[currentRow][currentCol];

    }

    public void exitPlacement(){
        board.getStackAt(currentRow, currentCol).popFromStack();
        endPlacement(false);
        this.repaint();
    }
    public void moveNorth()
    {
        if(placing)
        {
            int newRow;
            int newCol;
            ViewHex v;

            newRow = currentRow - 1;
            if(newRow < 0 )
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous space
                v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                board.getStackAt(currentRow, currentCol).popFromStack();
                //Select new space
                currentRow = newRow;
                board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();
        }

    }


    public void moveNorthEast()
    {
        if(placing)
        {
            int newRow;
            int newCol;
            ViewHex v;

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
                    v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                    board.getStackAt(currentRow, currentCol).popFromStack();

                    //Update current col and row
                    currentRow = newRow;
                    currentCol = newCol;

                    //Select new space
                    board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                    //Show changes
                    this.repaint();
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
                    v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                    board.getStackAt(currentRow, currentCol).popFromStack();

                    //Update current col since row didn't change
                    currentCol = newCol;

                    //Select new space
                    board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();
        }

    }

    public void moveSouthEast()
    {
        if(placing)
        {
            int newRow;
            int newCol;
            ViewHex v;

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
                    v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                    board.getStackAt(currentRow, currentCol).popFromStack();
                    currentCol = newCol;
                    //Select new space
                    board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                    //Reflect changes made
                    this.repaint();
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
                    v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                    board.getStackAt(currentRow, currentCol).popFromStack();

                    currentCol = newCol;
                    currentRow = newRow;

                    //Select new space
                    board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                    //Reflect changes made
                    this.repaint();
                }
            }

            this.requestFocus();
        }


    }

    public void moveSouth()
    {
        if(placing)
        {
            int newRow;
            int newCol;

            newRow = currentRow + 1;
            if(newRow >= 15 )
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous space
                ViewHex v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                board.getStackAt(currentRow, currentCol).popFromStack();
                //Select new space
                currentRow = newRow;

                board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();
        }

    }

    public void moveSouthWest()
    {
        if(placing)
        {
            int newRow;
            int newCol;
            ViewHex v;

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
                    v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                    board.getStackAt(currentRow, currentCol).popFromStack();

                    //Update current col
                    currentCol = newCol;

                    //Select new space
                    board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                    //Show changes
                    this.repaint();
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
                    v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                    board.getStackAt(currentRow, currentCol).popFromStack();

                    //Update current row and col
                    currentCol = newCol;
                    currentRow = newRow;

                    //Select new space
                    board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();
        }

    }

    public void moveNorthWest()
    {
        if(placing)
        {
            int newRow;
            int newCol;
            ViewHex v;

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
                    v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                    board.getStackAt(currentRow, currentCol).popFromStack();

                    //Update current col and row
                    currentRow = newRow;
                    currentCol = newCol;

                    //Select new space
                    board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                    //Show changes
                    this.repaint();
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
                    v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                    board.getStackAt(currentRow, currentCol).popFromStack();

                    //Update current col since row didn't change
                    currentCol = newCol;

                    //Select new space
                    board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();
        }


    }

    public int getCurrentRow()
    {
        return currentRow;
    }

    public void moveNorthDouble()
    {
        if(placingDouble)
        {
            int newRow;
            int newCol;
            ViewHex v;

            newRow = currentRow - 1;
            if(newRow < 0 )
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous space
                v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                board.getStackAt(currentRow, currentCol).popFromStack();
                //Select new space
                currentRow = newRow;
                board.getStackAt(currentRow, currentCol).pushIntoStack(v);

                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();
        }
    }


}


