package graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

/*
 * Class used to create the score tile (see assignment specification
 * for more information).
 */

public class ScoreTile extends JComponent {
	
	private static final long serialVersionUID = 129872935L;

	// tile dimension
	private int width = 160;
	private int height = 80;
	private int arc = 20;

	// (x,y) location on the board
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
	public ScoreTile(GameBoard board, int x, int y) {
		this.board = board;
	
		setBounds(x,y,width,height);
		addMouseListeners();
		
	}

	public void paintComponent(final Graphics g) {
		((Graphics2D) g).setRenderingHint( RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON );
		super.paintComponent(g);
		// draw the tile
		g.setColor(ColorDef.ORANGE);
		g.fillRoundRect(x,y,width,height,arc,arc);
		
		// draw the score
		g.setColor(ColorDef.PURPLE);
		g.setFont(new Font("SansSerif", Font.PLAIN, 40));
		String sc = this.board.game.score+"";
		g.drawString(sc,130-g.getFontMetrics().stringWidth(sc),52);
	}
	
	public void addMouseListeners() {
		ScoreTile _this = this;
		// if the user clicks the tile, attempt to score the word
		// (see assignment specification for more information
		//  on what scoring a word means)
		this.addMouseListener(new MouseInputAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				if (_this.board.game.word.size() > 2) {
					_this.board.game.scoreWord();
				}
				_this.board.redraw();
			}
		});
	}
}
