package graphics;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import game.Game;
import game.Letter;

/*
 * The following class is a graphical interface for the TextGame board. You can
 * play around with this class (and any other class in the graphics package) to
 * change the visualisation of the game.
 */

public class GameBoard extends JLayeredPane{
	
	private static final long serialVersionUID = -8522390763634730320L;
	
	// The game that we are visualising.
	protected Game game;
	
	// The main tiles on the board. Please see the assignment specification
	// to know what these tiles do.
	private DeckTile deck;
	private ScoreTile score;
	protected Slot leftSlot;
	protected Slot rightSlot;
	
	/*
	 * Constructor.
	 * 
	 * Creates the game using the given dictionary and array of letters,
	 * then create the visualisation. 
	 */
	public GameBoard(String[] dictionary, Letter[] letters) {
		// creates a game using the input dictionary and array of letters
		game = new Game(dictionary,letters);
		
		this.setPreferredSize(new Dimension(1280,248));
		this.setBackground(ColorDef.LIGHTGRAY);
		
		// create the deck tile (the tile that the user clicks to add letters
		// to the hand
		deck = new DeckTile(this,30,30);
		// create the score tile (the tile that displays the score, and can also
		// can be clicked to score/discard a word)
		score = new ScoreTile(this,30,130);
		redraw();
	}
	
	
	/*
	 * The main method for drawing the elements on the GameBoard. The method
	 * used is very simple: every time redraw() is called, we simply erase
	 * every game element and redraw them again based on the game's hand, deck, 
	 * and word. 
	 */
	protected void redraw(){
		
		// add the deck, if there are any letters left
		this.removeAll();
		if (this.game.deck.size() > 0)
			this.add(deck);

		// add the score button
		this.add(score);
		
		// draw the hand
		for(int i = 0; i < game.hand.size(); i++) {
			this.add(new LetterTile(this,70+(i+1)*60,30,game.hand.get(i)));
		}
	
		// draw the word (including the end slots where you can enter letters)
		rightSlot = null;
		leftSlot = new Slot(this,200,130,"left");
		this.add(leftSlot);
		// if word size is >= 1, then draw the right slot as well 
		if (game.word.size() > 0) {
			Letter[] w = game.word.toArray();
			int i = 0; 
			// the following loop adds all the letters in the word
			while(i < w.length) {
				this.add(new WordTile(210+(i+1)*70,130,w[i]));
				i++;
			}
			rightSlot = new Slot(this,220+(i+1)*70,130,"right");
			this.add(rightSlot);
		}
		
		revalidate();
		repaint();
	}
	
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
	}
	
	
	/* This is the application's entry point. You can randomise the letters
	 * by changing the integer input (it is the seed for the random number
	 * generator, see LetterGenerator.java). 
	 */
	public static void main(String[] args) {
	
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame root = new JFrame("TextGame");
				root.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

				String[] dictionary = Dictionary.standardDictionary();
				Letter[] letters = LetterGenerator.standardLetters(9999);
				
				// you can also use the smaller sized array of 12 letters
				// Letter[] letters = LetterGenerator.small(100);
				
				GameBoard b = new GameBoard(dictionary,letters);
				root.add(b);
				root.setContentPane(b);
				root.pack();
				root.setVisible(true);
				
			}
		});
	}
}
