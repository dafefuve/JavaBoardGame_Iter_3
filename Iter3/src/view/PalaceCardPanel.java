package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;


public class PalaceCardPanel extends JPanel
{
	private Image drumCard;
	private Image puppetCard;
	private Image maskCard;
	private Image drumPuppetCard;
	private Image puppetMaskCard;
	private Image drumMaskCard;
	private Image deck;
	
	public PalaceCardPanel()
	{
		try
		{
			//Initialize images
			drumCard = ImageIO.read(new File("img/drumcard.png"));
			puppetCard = ImageIO.read(new File("img/puppetcard.png"));
			maskCard = ImageIO.read(new File("img/maskcard.png"));
			drumPuppetCard = ImageIO.read(new File("img/drumpuppet.png"));
			puppetMaskCard = ImageIO.read(new File("img/puppetmaskcard.png"));
			drumMaskCard = ImageIO.read(new File("img/drummaskcard.png"));
			deck = ImageIO.read(new File("img/deck.png"));
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		this.setBorder(new EmptyBorder(10, 10, 10, 10) );
		this.setMinimumSize(new Dimension(300,325));
		this.setPreferredSize(new Dimension(300,325));
		
		JLabel palaceCardLabel = new JLabel("Current Festival Card");
		this.add(palaceCardLabel);
		palaceCardLabel.setFont(new Font("Helvetica", Font.BOLD, 24));
		palaceCardLabel.setForeground(Color.WHITE);
		
		this.setBackground(Color.decode("#00A779"));
		Border borderPalaceCard = BorderFactory.createMatteBorder(10, 20, 0, 0, Color.WHITE);
	    Border marginPalaceCard = new EmptyBorder(15,15,15,15);
	    this.setBorder(new CompoundBorder(borderPalaceCard, marginPalaceCard));
	}
	
	public void setFestivalCard()
	{
		
	}
	
	public void getFestivalCard()
	{
		
	}
	
	@Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(
                RenderingHints.KEY_ANTIALIASING, 
                RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(
                RenderingHints.KEY_COLOR_RENDERING, 
                RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2.setColor(Color.WHITE); 
        g2.fillRoundRect(90, 80, 120, 200, 30, 30);
        g2.drawImage(drumCard, 100, 90, 100, 180, null);
    }
}
