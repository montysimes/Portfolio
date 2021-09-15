package graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

/*
 * Class used to create the deck tile (containing all the letters
 * in the game).
 */
public class DeckTile extends JComponent {
	
	private static final long serialVersionUID = 6971234506254005611L;
	
	// tile dimension
	private int width = 80;
	private int height = 80;
	private int arc = 20;

	// (x,y) location on game board
	private int x;
	private int y;

	// the game board
	private GameBoard board;
	
	/*
	 * Constructor
	 * 
	 * sets the GameBoard object representing the board, and the
	 * x and y coordinate of the tile
	 */
	public DeckTile(GameBoard board, int x, int y) {
		this.board = board;
		setBounds(x,y,width,height);
		addMouseListeners();
	}

	public void paintComponent(final Graphics g) {
		((Graphics2D) g).setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		super.paintComponent(g);
		// draw the tile
		g.setColor(ColorDef.PURPLE);
		g.fillRoundRect(x,y,width,height,arc,arc);
		// draw the plus sign
		g.setColor(ColorDef.LIGHTGRAY);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
		g.drawString("+",23,55);
	}
	
	public void addMouseListeners() {
		
		this.addMouseListener(new MouseInputAdapter() {
			// when the user clicks the deck, then the game should draw
			// one letter and place it on the hand
			@Override
			public void mouseReleased(MouseEvent e) {
				board.game.draw();
				board.redraw();
			}
		});
	}
}
		