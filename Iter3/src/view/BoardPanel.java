package view;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

/**
 * Created by alexbujduveanu on 4/15/14.
 */
public class BoardPanel extends JPanel
{
   private BoardHex[][] theBoard = new BoardHex[15][19];;
   private BufferedImage dirt;
   private BufferedImage water;
   private BufferedImage land;
   private BufferedImage mainland;
   private int[] irrigationIDs;
   private ArrayList<Polygon> hexes;
   private static int currentID = 0;
   private static int currentRow = 0;
   private static int currentCol = 0;
   private JScrollPane jsp;

   public BoardPanel()
   {
       readTextures();
       setUpIrrigationCoordinates();

       initializeHexes();
       setUpKeyListener();


       this.setBorder(new EmptyBorder(50, 50, 50, 50) );
       this.setMinimumSize(new Dimension(900,850));
       this.setPreferredSize(new Dimension(900,850));
       this.setBackground(Color.decode("#80C7FF"));
       this.setFocusable(true);

       jsp = new JScrollPane(this);

   }

   public JScrollPane getContentPane()
   {
       return this.jsp;
   }

   public void readTextures()
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
   }

    public void setUpIrrigationCoordinates()
    {
        irrigationIDs = new int[3];
        irrigationIDs[0] = 99;
        irrigationIDs[1] = 108;
        irrigationIDs[2] = 160;
    }

   public void initializeHexes()
   {
       int xCoord = 45;
       int yCoord = 45;
       int firstY = yCoord;
       int secondY = firstY + 26;

       currentID = 0;

       //15 rows 19 columns
       final ArrayList<Polygon> hexes = new ArrayList<Polygon>();
       for (int i = 0; i < 15; i++) {
           for (int j = 0; j < 19; j++) {
               theBoard[i][j] = new BoardHex(xCoord, yCoord);
               xCoord += 45;

               if (j % 2 == 0) {
                   yCoord = secondY;
               } else {
                   yCoord = firstY;
               }

               currentID++;

           }

           //firstY = secondY + 26;
           //secondY = firstY + 26;
           firstY += 52;
           secondY += 52;
           xCoord = 45;
           yCoord = firstY;
       }

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


       //Set lowland tiles
       for (int i = 3; i < 4; i++) {
           for (int j = 3; j < 17; j++) {
               theBoard[i][j].getBoardHex().resetMountain();
           }
       }
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

   public void setUpKeyListener()
   {
       this.addKeyListener(new KeyListener() {

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
                theBoard[currentRow][currentCol].setSelected(false);
                //Select new space
                currentRow = newRow;
                theBoard[currentRow][currentCol].setSelected(true);
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
                theBoard[currentRow][currentCol].setSelected(false);
                //Select new space
                currentRow = newRow;

                theBoard[currentRow][currentCol].setSelected(true);
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
           this.requestFocus();

        }

    }

    public void highlightStartSpace()
    {
        theBoard[0][0].setSelected(true);
        this.repaint();
    }
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
}


