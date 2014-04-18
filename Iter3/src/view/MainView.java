package view;

/**
 * Created by alexbujduveanu on 4/16/14.
 */

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MainView extends JFrame
{
    private JPanel boardPanel;
    private JPanel communalView;
    private JPanel playerPanel;
    private JPanel festivalCardPanel;

    private static JFrame mainMenuView;

    private JPanel player1;
    private JPanel player2;
    private JPanel player3;
    private JPanel player4;

    private JPanel playerView;
    public static JScrollPane jsp;
    private int currentPlayer;


    public static void main(String[] args)
    {
       createMainMenu();
    }

    public MainView()
    {
       mainMenuView = new MainMenu();
    }

    public static void createMainMenu()
    {
        mainMenuView = new MainMenu();
    }

    public JPanel createContentPane()
    {
        JPanel mainPanel = new JPanel();
        playerPanel = new PlayerView(4);
        //playerPanel.setLayout(new BoxLayout(playerPanel, BoxLayout.LINE_AXIS));

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(playerView);

        boardPanel = new BoardPanel();
        mainPanel.add(boardPanel);

        JPanel bottomPane = new JPanel();
        bottomPane.setLayout(new BoxLayout(bottomPane, BoxLayout.Y_AXIS));
        communalView = new CommunalView();
        bottomPane.add(communalView);
        festivalCardPanel = new PalaceCardPanel();
        bottomPane.add(festivalCardPanel);

        JPanel superPanel = new JPanel();
        superPanel.setLayout(new BoxLayout(superPanel, BoxLayout.X_AXIS));
        superPanel.add(mainPanel);
        superPanel.add(bottomPane);
        //mainPanel.add(bottomPane);
        //mainPanel.setVisible(true);
        registerChangeTurn();
        playerView.requestFocusInWindow();
        playerView.requestFocus();
        return superPanel;
    }

    public void initializeGameView()
    {
        this.setContentPane(createContentPane());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        boardPanel.requestFocus();
        playerView.requestFocus();
    }



    public void selectPlayer()
    {
        ((PlayerView) playerPanel).changeTurn();
    }

    public void registerChangeTurn()
    {
        ((PlayerView)playerView).registerChangeTurn((PlayerView)playerView);
    }

    public static void showPauseMenu()
    {
        /*
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
        */
    }
}
