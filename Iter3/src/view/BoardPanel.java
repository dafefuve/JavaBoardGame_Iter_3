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

   /*private static int mainViewLocation.getViewRow() = 0;
   private static int mainViewLocation.getViewCol() = 0;
   private static int mainViewLocation.getViewRow()2 = 1;
   private static int mainViewLocation.getViewCol()2 = 0;
   private static int mainViewLocation.getViewRow()3 = 0;
   private static int mainViewLocation.getViewCol()3 = 0;
   */

    private ViewLocation mainViewLocation;
    private ArrayList<ViewLocation> auxHexagons;

   private JScrollPane jsp;
   private JPanel panel;
   private boolean placing;
   private boolean placingDeveloper;
   private boolean placingDouble;
   //private ViewHexVillage viewHexVillage;

   public BoardPanel()
   {
       board = new ViewBoard(15, 19);
       //setUpKeyListener();
       //panel = this;
       placing = false;
       //viewHexVillage = new ViewHexVillage();

       mainViewLocation = new ViewLocation();
       auxHexagons = new ArrayList<ViewLocation>();
       //auxHexagons.add(new ViewLocation());
       //auxHexagons.add(new ViewLocation());

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
               if(placing && mainViewLocation.getViewRow() == i && mainViewLocation.getViewCol() == j)
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
            newRow = mainViewLocation.getViewRow() - 1;
            if(newRow < 0 )
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous space
                board.setDeselectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());
                //Select new space
                mainViewLocation.setViewRow(newRow);
                board.setSelectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();

        }
        //Move NE
        else if(key == 9)
        {
            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                newRow = mainViewLocation.getViewRow() - 1;
                newCol = mainViewLocation.getViewCol() + 1;

                if(newRow < 0 || newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    board.setDeselectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Update current col and row
                    mainViewLocation.setViewRow(newRow);
                    mainViewLocation.setViewCol(newCol);

                    //Select new space
                    board.setSelectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow = mainViewLocation.getViewRow();
                newCol = mainViewLocation.getViewCol() + 1;

                if(newRow < 0 || newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    board.setDeselectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Update current col since row didn't change
                    mainViewLocation.setViewCol(newCol);

                    //Select new space
                    board.setSelectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();

        }
        //Move SW
        else if(key == 3)
        {
            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                newRow = mainViewLocation.getViewRow();
                newCol = mainViewLocation.getViewCol() + 1;

                if(newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect previous space
                    board.setDeselectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());
                    mainViewLocation.setViewCol(newCol);
                    //Select new space
                    board.setSelectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Reflect changes made
                    this.repaint();
                }
            }
            else
            {
                newRow = mainViewLocation.getViewRow() + 1;
                newCol = mainViewLocation.getViewCol() + 1;

                if(newRow >= 15 || newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect previous space
                    board.setDeselectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    mainViewLocation.setViewCol(newCol);
                    mainViewLocation.setViewRow(newRow);

                    //Select new space
                    board.setSelectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Reflect changes made
                    this.repaint();
                }
            }

            this.requestFocus();

        }
        else if(key == 2)
        {
            newRow = mainViewLocation.getViewRow() + 1;
            if(newRow >= 15 )
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous space
                board.setDeselectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());
                //Select new space
                mainViewLocation.setViewRow(newRow);

                board.setSelectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();
        }
        else if(key == 1)
        {
            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                newRow = mainViewLocation.getViewRow();
                newCol = mainViewLocation.getViewCol() - 1;

                if(newCol < 0 || newRow >= 15)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    board.setDeselectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Update current col
                    mainViewLocation.setViewCol(newCol);

                    //Select new space
                    board.setSelectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow = mainViewLocation.getViewRow() + 1;
                newCol = mainViewLocation.getViewCol() - 1;

                if(newCol < 0 || newRow >= 15)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    board.setDeselectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Update current row and col
                    mainViewLocation.setViewCol(newCol);
                    mainViewLocation.setViewRow(newRow);

                    //Select new space
                    board.setSelectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();
        }
        else if(key == 7)
        {
            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                newRow = mainViewLocation.getViewRow() - 1;
                newCol = mainViewLocation.getViewCol() - 1;

                if(newRow < 0 || newCol < 0)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    board.setDeselectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Update current col and row
                    mainViewLocation.setViewRow(newRow);
                    mainViewLocation.setViewCol(newCol);

                    //Select new space
                    board.setSelectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow = mainViewLocation.getViewRow();
                newCol = mainViewLocation.getViewCol() - 1;

                if(newRow < 0 || newCol < 0)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    board.setDeselectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

                    //Update current col since row didn't change
                    mainViewLocation.setViewCol(newCol);

                    //Select new space
                    board.setSelectedAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol());

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
            newRow = mainViewLocation.getViewRow() - 1;
            newRow2 = mainViewLocation.getViewRow()2 - 1;
            if(newRow < 0 || newRow2 < 0)
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous spaces
                theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(false);
                theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(false);

                //Select new spaces
                mainViewLocation.setViewRow(newRow);
                mainViewLocation.getViewRow()2 = newRow2;

                theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(true);
                theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(true);

                adjustScroll();
                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();

        }
        //Move NE
        else if(key == 9)
        {
            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                newRow = mainViewLocation.getViewRow() - 1;
                newCol = mainViewLocation.getViewCol() + 1;

                if(newRow < 0 || newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(false);

                    //Update current col and row
                    mainViewLocation.setViewRow(newRow);
                    mainViewLocation.setViewCol(newCol);

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(true);
                    adjustScroll();
                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow = mainViewLocation.getViewRow();
                newCol = mainViewLocation.getViewCol() + 1;

                if(newRow < 0 || newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(false);

                    //Update current col since row didn't change
                    mainViewLocation.setViewCol(newCol);

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(true);
                    adjustScroll();
                    //Display changes
                    this.repaint();
                }
            }

            if(mainViewLocation.getViewCol()2 % 2 == 0)
            {
                newRow2 = mainViewLocation.getViewRow()2 - 1;
                newCol2 = mainViewLocation.getViewCol()2 + 1;

                if(newRow2 < 0 || newCol2 >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(false);

                    //Update current col and row
                    mainViewLocation.getViewRow()2 = newRow2;
                    mainViewLocation.getViewCol()2 = newCol2;

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(true);
                    adjustScroll();
                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow2 = mainViewLocation.getViewRow()2;
                newCol2 = mainViewLocation.getViewCol()2 + 1;

                if(newRow2 < 0 || newCol2 >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(false);

                    //Update current col since row didn't change
                    mainViewLocation.getViewCol()2 = newCol2;

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(true);
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
            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                newRow = mainViewLocation.getViewRow();
                newCol = mainViewLocation.getViewCol() + 1;

                if(newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect previous space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(false);
                    mainViewLocation.setViewCol(newCol);
                    //Select new space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(true);
                    adjustScroll();
                    //Reflect changes made
                    this.repaint();
                }
            }
            else
            {
                newRow = mainViewLocation.getViewRow() + 1;
                newCol = mainViewLocation.getViewCol() + 1;

                if(newRow >= 15 || newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect previous space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(false);

                    mainViewLocation.setViewCol(newCol);
                    mainViewLocation.setViewRow(newRow);

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(true);
                    adjustScroll();
                    //Reflect changes made
                    this.repaint();
                }
            }

            if(mainViewLocation.getViewCol()2 % 2 == 0)
            {
                newRow2 = mainViewLocation.getViewRow()2;
                newCol2 = mainViewLocation.getViewCol()2 + 1;

                if(newCol2 >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect previous space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(false);
                    mainViewLocation.setViewCol(newCol);
                    //Select new space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(true);
                    adjustScroll();
                    //Reflect changes made
                    this.repaint();
                }
            }
            else
            {
                newRow2 = mainViewLocation.getViewRow()2 + 1;
                newCol2 = mainViewLocation.getViewCol()2 + 1;

                if(newRow2 >= 15 || newCol2 >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect previous space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(false);

                    mainViewLocation.getViewCol()2 = newCol2;
                    mainViewLocation.getViewRow()2 = newRow2;

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(true);
                    adjustScroll();
                    //Reflect changes made
                    this.repaint();
                }
            }

            this.requestFocus();

        }
        else if(key == 2)
        {
            newRow = mainViewLocation.getViewRow() + 1;
            newRow2 = mainViewLocation.getViewRow()2 + 1;
            if(newRow >= 15 || newRow2 >= 15)
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous space
                theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(false);
                theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(false);
                //Select new space
                mainViewLocation.setViewRow(newRow);
                mainViewLocation.getViewRow()2 = newRow2;

                theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(true);
                theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(true);
                adjustScroll();
                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();
        }
        else if(key == 1)
        {
            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                newRow = mainViewLocation.getViewRow();
                newCol = mainViewLocation.getViewCol() - 1;

                if(newCol < 0 || newRow >= 15)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(false);

                    //Update current col
                    mainViewLocation.setViewCol(newCol);

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(true);
                    adjustScroll();
                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow = mainViewLocation.getViewRow() + 1;
                newCol = mainViewLocation.getViewCol() - 1;

                if(newCol < 0 || newRow >= 15)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(false);

                    //Update current row and col
                    mainViewLocation.setViewCol(newCol);
                    mainViewLocation.setViewRow(newRow);

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(true);
                    adjustScroll();
                    //Display changes
                    this.repaint();
                }
            }

            if(mainViewLocation.getViewCol()2 % 2 == 0)
            {
                newRow2 = mainViewLocation.getViewRow()2;
                newCol2 = mainViewLocation.getViewCol()2 - 1;

                if(newCol2 < 0 || newRow2 >= 15)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(false);

                    //Update current col
                    mainViewLocation.getViewCol()2 = newCol2;

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(true);
                    adjustScroll();
                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow2 = mainViewLocation.getViewRow()2 + 1;
                newCol2 = mainViewLocation.getViewCol()2 - 1;

                if(newCol2 < 0 || newRow2 >= 15)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(false);

                    //Update current row and col
                    mainViewLocation.getViewCol()2 = newCol2;
                    mainViewLocation.getViewRow()2 = newRow2;

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(true);
                    adjustScroll();
                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();
        }
        else if(key == 7)
        {
            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                newRow = mainViewLocation.getViewRow() - 1;
                newCol = mainViewLocation.getViewCol() - 1;

                if(newRow < 0 || newCol < 0)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(false);

                    //Update current col and row
                    mainViewLocation.setViewRow(newRow);
                    mainViewLocation.setViewCol(newCol);

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(true);
                    adjustScroll();
                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow = mainViewLocation.getViewRow();
                newCol = mainViewLocation.getViewCol() - 1;

                if(newRow < 0 || newCol < 0)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(false);

                    //Update current col since row didn't change
                    mainViewLocation.setViewCol(newCol);

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()].setSelected(true);
                    adjustScroll();
                    //Display changes
                    this.repaint();
                }
            }

            if(mainViewLocation.getViewCol()2 % 2 == 0)
            {
                newRow2 = mainViewLocation.getViewRow()2 - 1;
                newCol2 = mainViewLocation.getViewCol()2 - 1;

                if(newRow2 < 0 || newCol2 < 0)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(false);

                    //Update current col and row
                    mainViewLocation.getViewRow()2 = newRow2;
                    mainViewLocation.getViewCol()2 = newCol2;

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(true);
                    adjustScroll();
                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                newRow2 = mainViewLocation.getViewRow()2;
                newCol2 = mainViewLocation.getViewCol()2 - 1;

                if(newRow2 < 0 || newCol2 < 0)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(false);

                    //Update current col since row didn't change
                    mainViewLocation.getViewCol()2 = newCol2;

                    //Select new space
                    theBoard[mainViewLocation.getViewRow()2][mainViewLocation.getViewCol()2].setSelected(true);
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
        if(mainViewLocation.getViewRow() > 9)
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
        placing = true;
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
            mainViewLocation.setViewRow(0);
            mainViewLocation.setViewCol(0);
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
        return IDs[mainViewLocation.getViewRow()][mainViewLocation.getViewCol()];

    }

    public void exitPlacement(){
        board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();
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

            //newRow = mainViewLocation.getViewRow() - 1;
            int minAuxRow = 14;
            if (auxHexagons.size()>0)
            {
                minAuxRow = Math.min(auxHexagons.get(0).getViewRow(), auxHexagons.get(1).getViewRow());
            }
            newRow = Math.min(mainViewLocation.getViewRow(), minAuxRow) - 1;

            if(newRow < 0 )
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous space
                v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();
                board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();
                //Select new space
                //mainViewLocation.setViewRow(newRow);

                mainViewLocation.setViewRow(newRow);

                for (ViewLocation vl : auxHexagons)
                {
                    vl.setViewRow(vl.getViewRow()-1);
                }

                board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

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

            int minAuxRow = 14; int maxAuxCol = 0;
            if (auxHexagons.size()>0)
            {
                minAuxRow = Math.min(auxHexagons.get(0).getViewRow(), auxHexagons.get(1).getViewRow());
                maxAuxCol = Math.max(auxHexagons.get(0).getViewCol(), auxHexagons.get(1).getViewCol());
            }

            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                //newRow = mainViewLocation.getViewRow() - 1;
                //newCol = mainViewLocation.getViewCol() + 1;

                newRow = Math.min(mainViewLocation.getViewRow(), minAuxRow) - 1;
                newCol = Math.max(mainViewLocation.getViewCol(), maxAuxCol) + 1;

                if(newRow < 0 || newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();

                    //Update current col and row
                    mainViewLocation.setViewRow(newRow);
                    mainViewLocation.setViewCol(newCol);

                    for(ViewLocation vl : auxHexagons)
                    {
                        vl.setViewRow(vl.getViewRow()-1);
                        vl.setViewCol(vl.getViewCol() + 1);
                    }

                    //Select new space
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                //newRow = mainViewLocation.getViewRow();
                //newCol = mainViewLocation.getViewCol() + 1;

                newRow = Math.min(mainViewLocation.getViewRow(), minAuxRow);
                newCol = Math.max(mainViewLocation.getViewCol(), maxAuxCol) + 1;

                if(newRow < 0 || newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();

                    //Update current col since row didn't change
                    mainViewLocation.setViewCol(newCol);
                    mainViewLocation.setViewRow(newRow);

                    for (ViewLocation vl : auxHexagons)
                    {
                        vl.setViewCol(vl.getViewCol()+1);
                    }

                    //Select new space
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

                    //Display changes
                    this.repaint();
                }
            }

            this.requestFocus();
        }
    }

    public void moveSouthEast()
    {
        System.out.println("Inside the moveSouthEast() method");
        if(placing)
        {
            System.out.println("placing is true");
            int newRow;
            int newCol;
            ViewHex v;

            int maxAuxRow = 0; int maxAuxCol = 0;

            System.out.println("maxAuxRow " + maxAuxRow);
            System.out.println("maxAuxCol " + maxAuxCol);

            if (auxHexagons.size()>0)
            {
                maxAuxRow = Math.max(auxHexagons.get(0).getViewRow(), auxHexagons.get(1).getViewRow());
                maxAuxCol = Math.max(auxHexagons.get(0).getViewCol(), auxHexagons.get(1).getViewCol());

                System.out.println("maxAuxRow " + maxAuxRow);
                System.out.println("maxAuxCol " + maxAuxCol);
            }

            System.out.println("mvl row: " + mainViewLocation.getViewRow());
            System.out.println("mvl col: " + mainViewLocation.getViewCol());

            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                //newRow = mainViewLocation.getViewRow();
                //newCol = mainViewLocation.getViewCol() + 1;

                newRow = Math.max(mainViewLocation.getViewRow(), maxAuxRow);
                newCol = Math.max(mainViewLocation.getViewCol(), maxAuxCol) + 1;

                if(newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect previous space

                    v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();

                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();
                    mainViewLocation.setViewCol(newCol);

                    for (ViewLocation vl : auxHexagons)
                    {
                        vl.setViewCol(vl.getViewCol()+1);
                    }

                    //Select new space
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

                    //Reflect changes made
                    this.repaint();
                }
            }
            else
            {
                //newRow = mainViewLocation.getViewRow() + 1;
                //newCol = mainViewLocation.getViewCol() + 1;

                newRow = Math.max(mainViewLocation.getViewRow(), maxAuxRow) + 1;
                newCol = Math.max(mainViewLocation.getViewCol(), maxAuxCol) + 1;

                if(newRow >= 15 || newCol >= 19)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect previous space
                    v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();

                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();

                    mainViewLocation.setViewCol(newCol);
                    mainViewLocation.setViewRow(newRow);

                    for (ViewLocation vl : auxHexagons)
                    {
                        vl.setViewRow(vl.getViewRow()+1);
                        vl.setViewCol(vl.getViewCol()+1);
                    }

                    //Select new space
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

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

            int maxAuxRow = 0;
            if (auxHexagons.size()>0)
            {
                maxAuxRow = Math.max(auxHexagons.get(0).getViewRow(), auxHexagons.get(1).getViewRow());
            }
            newRow = Math.max(mainViewLocation.getViewRow(), maxAuxRow) + 1;

            //newRow = mainViewLocation.getViewRow() + 1;
            if(newRow >= 15 )
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous space
                ViewHex v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();
                board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();
                //Select new space
                mainViewLocation.setViewRow(newRow);

                for (ViewLocation vl : auxHexagons)
                {
                    vl.setViewRow(vl.getViewRow()+1);
                }

                board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();
        }
    }

    public void moveSouthWest()
    {
        System.out.println("Inside moveSouthWest");
        if(placing)
        {
            System.out.println("Placing is true");
            int newRow;
            int newCol;
            ViewHex v;

            int maxAuxRow = 0; int minAuxCol = 19;
            if (auxHexagons.size()>0)
            {
                maxAuxRow = Math.max(auxHexagons.get(0).getViewRow(), auxHexagons.get(1).getViewRow());
                minAuxCol = Math.min(auxHexagons.get(0).getViewCol(), auxHexagons.get(1).getViewCol());
            }

            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                //newRow = mainViewLocation.getViewRow();
                //newCol = mainViewLocation.getViewCol() - 1;

                newRow = Math.max(mainViewLocation.getViewRow(), maxAuxRow);
                newCol = Math.min(mainViewLocation.getViewCol(), minAuxCol) - 1;

                if(newCol < 0 || newRow >= 15)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();

                    //Update current col
                    mainViewLocation.setViewCol(newCol);
                    //Update row
                    mainViewLocation.setViewRow(newRow);

                    for (ViewLocation vl : auxHexagons)
                    {
                        vl.setViewCol(vl.getViewCol()-1);
                    }

                    //Select new space
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                //newRow = mainViewLocation.getViewRow() + 1;
                //newCol = mainViewLocation.getViewCol() - 1;

                newRow = Math.max(mainViewLocation.getViewRow(), maxAuxRow) + 1;
                newCol = Math.min(mainViewLocation.getViewCol(), minAuxCol) - 1;

                System.out.println("newRow " + newRow);
                System.out.println("newCol " + newCol);

                if(newCol < 0 || newRow >= 15)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();

                    //Update current row and col
                    mainViewLocation.setViewCol(newCol);
                    mainViewLocation.setViewRow(newRow);

                    for (ViewLocation vl : auxHexagons)
                    {
                        vl.setViewRow(vl.getViewRow()+1);
                        vl.setViewCol(vl.getViewCol()-1);
                    }

                    //Select new space
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

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

            int minAuxRow = 14; int minAuxCol = 19;
            if (auxHexagons.size()>0)
            {
                minAuxRow = Math.min(auxHexagons.get(0).getViewRow(), auxHexagons.get(1).getViewRow());
                minAuxCol = Math.min(auxHexagons.get(0).getViewCol(), auxHexagons.get(1).getViewCol());
            }

            if(mainViewLocation.getViewCol() % 2 == 0)
            {
                //newRow = mainViewLocation.getViewRow() - 1;
                //newCol = mainViewLocation.getViewCol() - 1;

                newRow = Math.min(mainViewLocation.getViewRow(), minAuxRow) - 1;
                newCol = Math.min(mainViewLocation.getViewCol(), minAuxCol) - 1;

                if(newRow < 0 || newCol < 0)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();

                    //Update current col and row
                    mainViewLocation.setViewRow(newRow);
                    mainViewLocation.setViewCol(newCol);

                    for (ViewLocation vl : auxHexagons)
                    {
                        vl.setViewRow(vl.getViewRow() - 1);
                        vl.setViewCol(vl.getViewCol()-1);
                    }


                    //Select new space
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

                    //Show changes
                    this.repaint();
                }
            }
            else
            {
                //newRow = mainViewLocation.getViewRow();
                //newCol = mainViewLocation.getViewCol() - 1;

                newRow = Math.min(mainViewLocation.getViewRow(), minAuxRow);
                newCol = Math.min(mainViewLocation.getViewCol(), minAuxCol) - 1;

                if(newRow < 0 || newCol < 0)
                {
                    displayAlert("You cannot move out of bounds!", null);
                    return;
                }
                else
                {
                    //Deselect current space
                    v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();

                    //Update current col since row didn't change
                    mainViewLocation.setViewCol(newCol);
                    mainViewLocation.setViewRow(newRow);

                    for (ViewLocation vl : auxHexagons)
                    {
                        vl.setViewCol(vl.getViewCol()-1);
                    }
                      
                    //Select new space
                    board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

                    //Display changes
                    this.repaint();
                }
            }
            this.requestFocus();
        }
    }

    public int getCurrentRow()
    {
        return mainViewLocation.getViewRow();
    }

    public void moveNorthDouble()
    {
        if(placingDouble)
        {
            int newRow;
            int newCol;
            ViewHex v;

            newRow = mainViewLocation.getViewRow() - 1;
            if(newRow < 0 )
            {
                displayAlert("You cannot move out of bounds!", null);
            }
            else
            {
                //Deselect previous space
                v = board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).peekIntoStack();
                board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).popFromStack();
                //Select new space
                mainViewLocation.setViewRow(newRow);
                board.getStackAt(mainViewLocation.getViewRow(), mainViewLocation.getViewCol()).pushIntoStack(v);

                //Reflect changes made
                this.repaint();
            }
            this.requestFocus();
        }
    }

    public void beginDeveloperPlacement()
    {
        placingDeveloper = true;
    }

    public void endDeveloperPlacement()
    {
        placingDeveloper = false;
    }




}


