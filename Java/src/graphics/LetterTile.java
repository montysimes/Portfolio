package graphics;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;
import javax.swing.event.MouseInputAdapter;

import game.Letter;

/*
 * Class used to create the letter tiles representing the hand.
 */
public class LetterTile extends JComponent{

	private static final long serialVersionUID = 1038685640157206427L;
	// tile dimension
	private int height = 80;
	private int width = 80;
	private int arc = 20;
	
	// parameters used for dragging 
	private int startX;
	private int startY;
	private int mouseOffsetX;
	private int mouseOffsetY;

	// the Letter object represented by the tile
	private Letter letter;
	// the game board
	private GameBoard board;
	

	/*
	 * Constructor
	 * 
	 * sets the GameBoard object representing the board, and the
	 * x and y coordinate of the tile
	 */
	public LetterTile(GameBoard g, int x, int y, Letter letter) {
		this.board = g;
		this.letter = letter;

		this.startX = x;
		this.startY = y;
		this.mouseOffsetX = 0; 
		this.mouseOffsetY = 0;

		setBounds(x,y,width,height);
		addMouseListeners();
	}
	
	
	public void paintComponent(final Graphics g) {
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		super.paintComponent(g);
		// draw the background yellow (for the border)
		g.setColor(ColorDef.YELLOW);
		g.fillRoundRect(0,0,width,height,arc,arc);
		// place lighter yellow background on top
		g.setColor(ColorDef.LIGHTYELLOW);
		g.fillRoundRect(2,2,width-4,height-4,arc-4,arc-4);
		// draw the letter and the score
		g.setColor(ColorDef.PURPLE);
		if (letter != null) {
			g.setFont(new Font("TimesRoman", Font.PLAIN, 60));
			String lt = letter.getLetter()+"";
			g.drawString(lt,40-g.getFontMetrics().stringWidth(lt)/2,58);
			g.setFont(new Font("SansSerif", Font.BOLD, 16));
			String sc = letter.getScore()+"";
			g.drawString(sc, 72-g.getFontMetrics().stringWidth(sc), 74);
		}

	}
	
	public void addMouseListeners() {
		// create a reference for current LetterTile instance to use inside
		// the listeners (since 'this' would change scope)
		// p.s. not sure if this is the Java-way, but this is a common approach in javascript
		LetterTile _this = this;
		this.addMouseListener(new MouseInputAdapter() {
			
			// set mouse offsets for a better dragging experience
			// (used in mouseDragged further below)
			@Override
			public void mousePressed(MouseEvent e) {
				mouseOffsetX = e.getX(); 
				mouseOffsetY = e.getY(); 
				// set the z order of the clicked component to 0 (i.e. place it on top)
				getParent().setComponentZOrder(_this, 0);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				// get the x and y coordinate of the mouse pointer
				int x = _this.getX()+e.getX();
				int y = _this.getY()+e.getY();
				
				// if the dragged tile is not the leftmost tile, send it back to the hand
				if (_this.board.game.hand.leftmost() != _this.letter) {
					_this.setBounds(startX,startY,width,height);
				}
				// else if the mouse pointer is on either the left or right slot, 
				// move the current letter the the appropriate position on the word
				else if (_this.board.leftSlot != null && _this.board.leftSlot.getBounds().contains(x,y)) {
					_this.board.game.moveFromHandToStartOfWord();
				}
				else if (_this.board.rightSlot != null && _this.board.rightSlot.getBounds().contains(x,y)) {
					_this.board.game.moveFromHandToEndOfWord();
				}
				// in any other case, send it back
				else {
					_this.setBounds(startX,startY,width,height);
				}
				_this.board.redraw();
			}
		});
		// the following code changes the tile location as we drag it
		this.addMouseMotionListener(new MouseInputAdapter() {
			
			@Override
			public void mouseDragged(MouseEvent e) {
				setLocation(e.getX()-mouseOffsetX+getLocation().x,e.getY()-mouseOffsetY+ getLocation().y);
			}
		});

	}
	
}

