package graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

import game.Letter;

/*
 * Class used to create the tiles of letters representing
 * the word. It is different to LetterTile.java because
 * a WordTile cannot be moved.
 */
public class WordTile extends JComponent{

	private static final long serialVersionUID = 1038685640157206427L;
	
	// tile dimensions
	private int height = 80;
	private int width = 80;
	private int arc = 0;
	
	// the letter shown in the tile
	private Letter letter;
	
	
	/*
	 * Constructor.
	 * 
	 * sets the GameBoard object representing the board, and the
	 * x and y coordinate of the tile
	 */
	public WordTile(int x, int y, Letter letter) {
		this.letter = letter;
		setBounds(x,y,width,height);
	}
	
	
	public void paintComponent(final Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);
		// set background to yellow (for top and bottom borders)
		g.setColor(ColorDef.YELLOW);
		g.fillRoundRect(0,0,width,height,arc,arc);
		// put lighter yellow background on top
		g.setColor(ColorDef.LIGHTYELLOW);
		g.fillRoundRect(0,2,width,height-4,arc-4,arc-4);
		// draw the letter and the score
		g.setColor(ColorDef.DARKBLUE);
		g.setFont(new Font("SansSerif", Font.PLAIN, 20));
		if (letter != null) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
			String lt = letter.getLetter()+"";
			g.drawString(lt,40-g.getFontMetrics().stringWidth(lt)/2,58);
			g.setFont(new Font("SansSerif", Font.BOLD, 16));
			String sc = letter.getScore()+"";
			g.drawString(sc, 72-g.getFontMetrics().stringWidth(sc), 74);
		}
	}
	
}
