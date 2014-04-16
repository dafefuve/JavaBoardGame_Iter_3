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
import javax.swing.*;
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

        showPauseMenu();
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

    public static void showPauseMenu()
    {
        JFrame pauseFrame = new JFrame();
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(50, 50, 50, 50) );
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel unpauseLabel = new JLabel("Unpause (Press U)");
        unpauseLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
        unpauseLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        unpauseLabel.setBorder(new EmptyBorder(20, 20, 20, 20) );
        panel.add(unpauseLabel);

        JLabel controlsLabel = new JLabel("View controls (Press C)");
        controlsLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
        controlsLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        controlsLabel.setBorder(new EmptyBorder(20, 20, 20, 20) );
        panel.add(controlsLabel);

        JLabel saveAndQuitLabel = new JLabel("Save and quit (Press Q)");
        saveAndQuitLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
        saveAndQuitLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        saveAndQuitLabel.setBorder(new EmptyBorder(20, 20, 20, 20) );
        panel.add(saveAndQuitLabel);

        frame.getRootPane().setGlassPane(new JComponent() {
            public void paintComponent(Graphics g) {
                g.setColor(new Color(0, 0, 0, 100));
                g.fillRect(0, 0, frame.getWidth(), frame.getHeight());
                super.paintComponent(g);
            }
        });

        frame.getRootPane().getGlassPane().setVisible(true);
        frame.repaint();

        pauseFrame.setContentPane(panel);
        pauseFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pauseFrame.pack();
        pauseFrame.setVisible(true);

        System.out.println("got here");
        JOptionPane.showMessageDialog(frame,
                "You cannot exit the board's bounds.",
                "Out of Bounds Warning",
                JOptionPane.WARNING_MESSAGE);

        System.out.println("got here2");
    }
}
