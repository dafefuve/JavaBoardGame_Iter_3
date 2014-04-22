package view;

/**
 * Created by alexbujduveanu on 4/16/14.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;


public class MainView extends JFrame
{
    private BoardPanel boardPanel;
    private JPanel communalView;
    private JPanel playerPanel;
    private JPanel festivalCardPanel;

    private static MainMenu mainMenuView;

    private JPanel player1;
    private JPanel player2;
    private JPanel player3;
    private JPanel player4;

    private JPanel playerView;
    public static JScrollPane jsp;
    private int currentPlayer;

    private JPanel content;


   /* public static void main(String[] args)
    {
       createMainMenu();
    }
    */

    public MainView()
    {
       createMainMenu();
       content = createContentPane();
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
        mainPanel.add(playerPanel);


        boardPanel = new BoardPanel();
        jsp = new JScrollPane(boardPanel);
        mainPanel.add(jsp);

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
        playerPanel.requestFocusInWindow();
        playerPanel.requestFocus();
        return superPanel;
    }

    public void initializeGameView()
    {
        this.setContentPane(content);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);

        mainMenuView.dispose();
        content.requestFocusInWindow();
        content.requestFocus();
        //boardPanel.requestFocus();
        playerPanel.requestFocus();
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

    public void registerMainMenuKeyBindings(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        //Needs a JPanel to work?


        KeyStroke k = KeyStroke.getKeyStroke(KeyEvent.VK_N, 0);
        mainMenuView.getMainViewPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "new game");
        mainMenuView.getMainViewPanel().getActionMap().put("new game",keyBindings.get(k));

        k = KeyStroke.getKeyStroke(KeyEvent.VK_L, 0);
        mainMenuView.getMainViewPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "load game");
        mainMenuView.getMainViewPanel().getActionMap().put("load game",keyBindings.get(k));

        k = KeyStroke.getKeyStroke(KeyEvent.VK_Q, 0);
        mainMenuView.getMainViewPanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "quit game");
        mainMenuView.getMainViewPanel().getActionMap().put("quit game",keyBindings.get(k));
    }

    public void registerActiveKeyBindings(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        KeyStroke k = KeyStroke.getKeyStroke(KeyEvent.VK_8, 0);
        boardPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "move N");
        boardPanel.getActionMap().put("move N",keyBindings.get(k));

        k = KeyStroke.getKeyStroke(KeyEvent.VK_9, 0);
        boardPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "move NE");
        boardPanel.getActionMap().put("move NE",keyBindings.get(k));

        k = KeyStroke.getKeyStroke(KeyEvent.VK_3, 0);
        boardPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "move SE");
        boardPanel.getActionMap().put("move SE",keyBindings.get(k));

        k = KeyStroke.getKeyStroke(KeyEvent.VK_2, 0);
        boardPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "move S");
        boardPanel.getActionMap().put("move S",keyBindings.get(k));

        k = KeyStroke.getKeyStroke(KeyEvent.VK_1, 0);
        boardPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "move SW");
        boardPanel.getActionMap().put("move SW",keyBindings.get(k));

        k = KeyStroke.getKeyStroke(KeyEvent.VK_7, 0);
        boardPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "move NW");
        boardPanel.getActionMap().put("move NW",keyBindings.get(k));

        k = KeyStroke.getKeyStroke(KeyEvent.VK_V, 0);
        boardPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "place village");
        boardPanel.getActionMap().put("place village",keyBindings.get(k));

        k = KeyStroke.getKeyStroke(KeyEvent.VK_R, 0);
        boardPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "place rice");
        boardPanel.getActionMap().put("place rice",keyBindings.get(k));

        k = KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0);
        boardPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(k, "end placement");
        boardPanel.getActionMap().put("end placement",keyBindings.get(k));
    }

    public void registerPlanningKeyBindings(HashMap<KeyStroke, AbstractAction> keyBindings)
    {
        //DO SOMETHING
    }

    public int getCurrentSpace()
    {
        //Gets current space we are on when placing
        return boardPanel.getCurrentSpace();
    }

    public void startNewGame()
    {
        mainMenuView.setVisible(false);
        initializeGameView();
    }

    public void placeVillageTile()
    {
        boardPanel.placeSingleVillageTile();
    }

    public void moveNorth()
    {
        boardPanel.moveNorth();
        adjustScroll();
    }

    public void moveNorthEast()
    {
        boardPanel.moveNorthEast();
        adjustScroll();
    }

    public void moveSouthEast()
    {
        boardPanel.moveSouthEast();
        adjustScroll();
    }

    public void moveSouth()
    {
        boardPanel.moveSouth();
        adjustScroll();
    }

    public void moveSouthWest()
    {
        boardPanel.moveSouthWest();
        adjustScroll();
    }

    public void moveNorthWest()
    {
        boardPanel.moveNorthWest();
        adjustScroll();
    }

    public void endPlacement(boolean invalidPlacement)
    {
        boardPanel.endPlacement(invalidPlacement);
    }

    public void exitPlacement(){
        boardPanel.exitPlacement();
    }

    public void placeRiceTile()
    {
        boardPanel.placeSingleRiceTile();
    }

    public void adjustScroll()
    {
        //Scroll the panel based on what row we are on
        if(boardPanel.getCurrentRow() > 9)
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
