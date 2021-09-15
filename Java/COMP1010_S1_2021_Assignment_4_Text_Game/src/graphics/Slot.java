package graphics;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;


/*
 * Class used to create the yellow tiles on either end of a word that 
 * is used by the user to add letters to the word.
 */
public class Slot extends JComponent{

	private static final long serialVersionUID = 478985038574675818L;
	
	// tile dimension
	private int height = 80;
	private int width = 80;
	private int arc = 20;

	// parameter to signify if the slot is on the left or right of the word
	private String pos;
	
	// the game board
	private GameBoard board;
	
	/*
	 * Constructor
	 * 
	 * sets the GameBoard object representing the board, and the
	 * x and y coordinate of the tile
	 * 
	 * pos determines if the slot is on the left or on the right
	 * of the word
	 */
	public Slot(GameBoard board, int x, int y, String pos) {
		this.board = board;
		this.pos = pos;
		setBounds(x,y,width,height);
	}
	
	public void paintComponent(final Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);
		// if there is only one slot, then make it rounded
		if (pos.equals("left") && this.board.rightSlot == null) {
			g.setColor(ColorDef.YELLOW);
			g.fillRoundRect(0,0,width,height,arc,arc);
		}
		// draw the slot if it's the left slot (rounded corners on left side)
		else if (pos.equals("left")) {
			g.setColor(ColorDef.YELLOW);
			g.fillRoundRect(0,0,width,height,arc,arc);
			g.fillRoundRect(20,0,width,height,0,0);
		}
		// draw the slot if it's the right slot (rounded corners on the right side)
		else if (pos.equals("right")) {
			g.setColor(ColorDef.YELLOW);
			g.fillRoundRect(0,0,width,height,arc,arc);
			g.fillRoundRect(0,0,width-20,height,0,0);
		}
	}
}