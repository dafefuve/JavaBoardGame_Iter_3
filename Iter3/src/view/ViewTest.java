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
      
        boardPanel.requestFocus();
        playerView.requestFocus();
    }

    public static JPanel createContentPane()
    {
        JPanel mainPanel = new JPanel();
        playerView = new PlayerView(4);
        //playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.LINE_AXIS));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(playerView);


        BoardPanel bp = new BoardPanel();
        mainPanel.add(bp.getContentPane());

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



    public static void selectPlayer()
    {
        ((PlayerView) playerView).changeTurn();
    }

    public static void registerChangeTurn()
    {
        ((PlayerView)playerView).registerChangeTurn((PlayerView)playerView);
    }
}
