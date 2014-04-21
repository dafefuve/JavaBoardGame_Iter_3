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

       jsp = new JScrollPane(this);

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


               /*
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

               }
               else if(theBoard[i][j].getBoardHex().getIsMountain() == true)
               {
                   g2.setPaint(new TexturePaint(dirt, new Rectangle(300,300,300,300)));
                   g2.fillPolygon(theBoard[i][j].getPolygon());
                   g2.setColor(Color.WHITE);
                   g2.setStroke(new BasicStroke(1f));
                   g2.drawPolygon(theBoard[i][j].getPolygon());
               }
               else
               {
                   g2.setPaint(new TexturePaint(mainland, new Rectangle(300,300,300,300)));
                   g2.fillPolygon(theBoard[i][j].getPolygon());
                   g2.setColor(Color.WHITE);
                   g2.setStroke(new BasicStroke(1f));
                   g2.drawPolygon(theBoard[i][j].getPolygon());

                   //Draw the level string
                   drawLevel(g2, theBoard[i][j].getBoardHex());

               }

               if(theBoard[i][j].getSelected() == true)
               {
                   Color c = new Color(0f,1f,1f,.3f );
                   g2.setColor(c);
                   g2.fillPolygon(theBoard[i][j].getBoardHex().getPolygon());
               }
            */
           }
       }
   }

  /* public void setUpKeyListener()
   {
       this.addKeyListener(new KeyListener() {

           @Override
           public void keyTyped(KeyEvent e) {
               // TODO Auto-generated method stub
               //Move up
               if(e.getKeyChar() == '8')
               {
                   moveSpace(8);
               }
               else if(e.getKeyChar() == '9')
               {
                   moveSpace(9);
               }
               else if(e.getKeyChar() == '3')
               {
                   moveSpace(3);
               }
               else if(e.getKeyChar() == '2')
               {
                   moveSpace(2);
               }
               else if(e.getKeyChar() == '1')
               {
                   moveSpace(1);
               }
               else if(e.getKeyChar() == '7')
               {
                   moveSpace(7);
               }
               else if(e.getKeyChar() == 'w')
               {
                    endPlacement();

               }
               else if(e.getKeyChar() == 'v')
               {
                   beginPlacement();
                   placeSingleVillageTile();
               }
               else if(e.getKeyChar() == 'r')
               {
                   beginPlacement();
                   placeSingleRiceTile();
               }
               else if(e.getKeyChar() == 'i')
               {
                   beginPlacement();
                   placeIrrigationTile();
               }


               //else for changing player
                /*
                else if(e.getKeyChar() == 'p')
                {
                    selectPlayer();
                }*/
           /*}

           @Override
           public void keyReleased(KeyEvent e) {
               // TODO Auto-generated method stub

           }

           @Override
           public void keyPressed(KeyEvent e) {
               // TODO Auto-generated method stub

           }
       });
   }
*/
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current col and row
                    currentRow = newRow;
                    currentCol = newCol;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current col since row didn't change
                    currentCol = newCol;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);
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
                    board.setDeselectedAt(currentRow, currentCol);
                    currentCol = newCol;
                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);
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
                    board.setDeselectedAt(currentRow, currentCol);

                    currentCol = newCol;
                    currentRow = newRow;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current col
                    currentCol = newCol;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current row and col
                    currentCol = newCol;
                    currentRow = newRow;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current col and row
                    currentRow = newRow;
                    currentCol = newCol;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);
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
                    board.setDeselectedAt(currentRow, currentCol);

                    //Update current col since row didn't change
                    currentCol = newCol;

                    //Select new space
                    board.setSelectedAt(currentRow, currentCol);
                    adjustScroll();
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

    public void adjustScroll()
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
/*
    public void drawLevel(Graphics2D g, BoardHex hex)
    {
        g.setFont(new Font("Helvetica", Font.BOLD, 16));
        g.setColor(Color.WHITE);
        g.drawString(String.valueOf(hex.getLevel()), hex.getCenterX(), hex.getCenterY());
    }

    public void setUpMainLand()
    {
        //Set highland tiles
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 19; j++) {
                theBoard[i][j].getBoardHex().setMountain();
            }
        }

        //Set lowland tiles
        for (int i = 8; i < 15; i++) {
            for (int j = 0; j < 19; j++) {
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


        //Set mainland tiles
        for (int i = 3; i < 4; i++) {
            for (int j = 3; j < 17; j++) {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        for (int i = 4; i < 5; i++) {
            for (int j = 1; j < 18; j++) {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        for (int i = 5; i < 6; i++) {
            for (int j = 2; j < 18; j++) {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        for (int i = 6; i < 7; i++) {
            for (int j = 1; j < 18; j++) {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        for (int i = 7; i < 8; i++) {
            for (int j = 2; j < 18; j++) {
                theBoard[i][j].getBoardHex().resetMountain();
            }
        }

        for (int i = 8; i < 9; i++) {
            for (int j = 3; j < 16; j++) {
                theBoard[i][j].getBoardHex().resetLowlands();
            }
        }

        for (int i = 9; i < 10; i++) {
            for (int j = 3; j < 17; j++) {
                theBoard[i][j].getBoardHex().resetLowlands();
            }
        }

        for (int i = 10; i < 11; i++) {
            for (int j = 3; j < 16; j++) {
                theBoard[i][j].getBoardHex().resetLowlands();
            }
        }

        for (int i = 11; i < 12; i++) {
            for (int j = 4; j < 14; j++) {
                theBoard[i][j].getBoardHex().resetLowlands();
            }
        }

        theBoard[12][5].getBoardHex().resetLowlands();
        theBoard[12][6].getBoardHex().resetLowlands();
        theBoard[12][7].getBoardHex().resetLowlands();
        theBoard[12][9].getBoardHex().resetLowlands();
        theBoard[12][11].getBoardHex().resetLowlands();
        theBoard[12][12].getBoardHex().resetLowlands();
        theBoard[12][13].getBoardHex().resetLowlands();
        theBoard[13][5].getBoardHex().resetLowlands();
        theBoard[12][6].getBoardHex().resetLowlands();
        theBoard[13][11].getBoardHex().resetLowlands();
        theBoard[13][12].getBoardHex().resetLowlands();

    }
    */

    public void placeSingleVillageTile()
    {
        board.getStackAt(0, 0).pushIntoStack(new ViewHexVillage());
        this.repaint();
    }

    public void placeSingleRiceTile()
    {
        board.getStackAt(0, 0).pushIntoStack(new ViewHexRice());
        this.repaint();
    }

    public void placeIrrigationTile()
    {
        board.getStackAt(0, 0).pushIntoStack(new ViewHexIrrigation());
        this.repaint();
    }

    public void moveSpace(int key)
    {
        int newRow;
        int newCol;
        ViewHex v;
        if(placing)
        {
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
                    v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                    board.getStackAt(currentRow, currentCol).popFromStack();
                    //Select new space
                    currentRow = newRow;
                    board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                        v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                        board.getStackAt(currentRow, currentCol).popFromStack();

                        //Update current col and row
                        currentRow = newRow;
                        currentCol = newCol;

                        //Select new space
                        board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                        v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                        board.getStackAt(currentRow, currentCol).popFromStack();

                        //Update current col since row didn't change
                        currentCol = newCol;

                        //Select new space
                        board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                        v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                        board.getStackAt(currentRow, currentCol).popFromStack();
                        currentCol = newCol;
                        //Select new space
                        board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                        v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                        board.getStackAt(currentRow, currentCol).popFromStack();

                        currentCol = newCol;
                        currentRow = newRow;

                        //Select new space
                        board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                if(newRow >= 15 )
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
                        v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                        board.getStackAt(currentRow, currentCol).popFromStack();

                        //Update current col
                        currentCol = newCol;

                        //Select new space
                        board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                        v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                        board.getStackAt(currentRow, currentCol).popFromStack();

                        //Update current row and col
                        currentCol = newCol;
                        currentRow = newRow;

                        //Select new space
                        board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                        v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                        board.getStackAt(currentRow, currentCol).popFromStack();

                        //Update current col and row
                        currentRow = newRow;
                        currentCol = newCol;

                        //Select new space
                        board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                        v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                        board.getStackAt(currentRow, currentCol).popFromStack();

                        //Update current col since row didn't change
                        currentCol = newCol;

                        //Select new space
                        v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                        board.getStackAt(currentRow, currentCol).popFromStack();
                        adjustScroll();
                        //Display changes
                        this.repaint();
                    }
                }
                this.requestFocus();

            }
        }


    }

    public void beginPlacement()
    {
        placing = true;
    }


    public void endPlacement()
    {
        placing = false;
        currentRow = 0;
        currentCol = 0;
        repaint();
    }


    public void drawLevel(Graphics2D g, Polygon hex, ViewHexStack v)
    {

        Font f = new Font("Helvetica", Font.BOLD, 16);
        g.setFont(f);
        g.setColor(Color.WHITE);
        int[] x = hex.xpoints;
        int[] y = hex.ypoints;

        g.drawString(String.valueOf(v.size() - 2), x[0] - 40, y[0]);

    }

    public int getCurrentSpace()
    {
        //returns id of the currently selected space
        return IDs[currentRow][currentCol];

    }


    public void moveNorth()
    {
        int newRow;
        int newCol;
        ViewHex v;

        if(placing)
        {
            //Move up
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
                adjustScroll();
                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();

        }
    }

    public void moveNorthEast()
    {
        int newRow;
        int newCol;

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
                ViewHex v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                board.getStackAt(currentRow, currentCol).popFromStack();

                //Update current col and row
                currentRow = newRow;
                currentCol = newCol;

                //Select new space
                board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                ViewHex v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                board.getStackAt(currentRow, currentCol).popFromStack();

                //Update current col since row didn't change
                currentCol = newCol;

                //Select new space
                board.getStackAt(currentRow, currentCol).pushIntoStack(v);
                adjustScroll();
                //Display changes
                this.repaint();
            }
        }
        this.requestFocus();
    }

    public void moveSouthEast()
    {
        int newRow;
        int newCol;

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
                board.setDeselectedAt(currentRow, currentCol);

                currentCol = newCol;
                currentRow = newRow;

                //Select new space
                board.setSelectedAt(currentRow, currentCol);
                adjustScroll();
                //Reflect changes made
                this.repaint();
            }
        }

        this.requestFocus();
    }

    public void moveSouth()
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
            adjustScroll();
            //Reflect changes made
            this.repaint();
        }
        this.requestFocus();
    }

    public void moveSouthWest()
    {
        int newRow;
        int newCol;

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
                ViewHex v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                board.getStackAt(currentRow, currentCol).popFromStack();

                //Update current col
                currentCol = newCol;

                //Select new space
                board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                ViewHex v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                board.getStackAt(currentRow, currentCol).popFromStack();

                //Update current row and col
                currentCol = newCol;
                currentRow = newRow;

                //Select new space
                board.getStackAt(currentRow, currentCol).pushIntoStack(v);
                adjustScroll();
                //Display changes
                this.repaint();
            }
        }
        this.requestFocus();
    }

    public void moveNorthWest()
    {
        int newRow;
        int newCol;

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
                ViewHex v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                board.getStackAt(currentRow, currentCol).popFromStack();

                //Update current col and row
                currentRow = newRow;
                currentCol = newCol;

                //Select new space
                board.getStackAt(currentRow, currentCol).pushIntoStack(v);
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
                ViewHex v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                board.getStackAt(currentRow, currentCol).popFromStack();

                //Update current col since row didn't change
                currentCol = newCol;

                //Select new space
                v = board.getStackAt(currentRow, currentCol).peekIntoStack();
                board.getStackAt(currentRow, currentCol).popFromStack();
                adjustScroll();
                //Display changes
                this.repaint();
            }
        }
        this.requestFocus();

    }


}


