import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MainMenu {
	static JPanel panel; 
	static JFrame frame;
	static JPanel mainView;
	public static void main(String[] args) 
	{

		frame = new JFrame();
		frame.setContentPane(createContentPane());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setPreferredSize(new Dimension(400,400));
        frame.setMinimumSize(new Dimension(400,400));
        frame.setVisible(true);
	}

	public static JPanel createContentPane()
	{
		panel = new JPanel();
		panel.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				if(e.getKeyChar() == 'n')
				{
					frame.setVisible(false); //you can't see me!
					frame.dispose();

					JFrame frame = new JFrame();
					frame.setContentPane(ViewTest.createContentPane());
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			        frame.pack();
			        frame.setVisible(true);
				}
				else if(e.getKeyChar() == 'l')
				{
					final JFileChooser jfc = new JFileChooser();
					int retVal = jfc.showOpenDialog(frame);
				}
				else if(e.getKeyChar() == 'q')
				{
					frame.setVisible(false); //you can't see me!
					frame.dispose();
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub

			}
         });


		panel.setBorder(new EmptyBorder(50, 50, 50, 50) );
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		JLabel titleLabel = new JLabel("Main Menu");
		titleLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
		titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		titleLabel.setBorder(new EmptyBorder(20, 20, 20, 20) );
		panel.add(titleLabel);

		JLabel newGameLabel = new JLabel("New Game (Press N)");
		newGameLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		newGameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		newGameLabel.setBorder(new EmptyBorder(20, 20, 20, 20) );
		panel.add(newGameLabel);

		JLabel loadGameLabel = new JLabel("Load Game (Press L)");
		loadGameLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		loadGameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		loadGameLabel.setBorder(new EmptyBorder(20, 20, 20, 20) );
		panel.add(loadGameLabel);

		JLabel quitGameLabel = new JLabel("Quit (Press Q)");
		quitGameLabel.setFont(new Font("Helvetica", Font.PLAIN, 18));
		quitGameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
		quitGameLabel.setBorder(new EmptyBorder(20, 20, 20, 20) );
		panel.add(quitGameLabel);

		panel.setFocusable(true);
		panel.requestFocusInWindow();

		panel.setVisible(true);

		return panel;
	}

}