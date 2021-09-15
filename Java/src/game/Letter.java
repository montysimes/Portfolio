package game;

/*
 * The Letter class is the nodes of 
 */
public class Letter {
	
	// the letter 
	public char letter;
	// the letter's score
	public int score;
	// the next letter in the list
	public Letter next;
	
	/**
	 * Constructor
	 * 
	 * @param letter - the letter  
	 * @param score  - the score of the letter
	 */
	public Letter(char letter, int score) {
		this.letter = letter;
		this.score = score;
		this.next = null;
	}
		
	/**
	 * Constructor
	 * 
	 * @param letter - the letter  
	 * @param score  - the score of the letter
	 * @param next   - the next letter in the list
	 */
	public Letter(char letter, int score, Letter next) {
		this.letter = letter;
		this.score = score;
		this.next = next;
		
	}
		
	public String toString() {
		return "(" + this.letter + "," + this.score + ")";
	}
	
	public int getScore() {
		return score;
	}
	
	public char getLetter() {
		return letter;
	}
}
