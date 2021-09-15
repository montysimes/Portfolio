package game;

/*
* IMPORTANT!!! 
* 20 marks will be deducted if the following information is not filled.
* STUDENT NAME: Montgomery Simes 
* STUDENT ID: 46437819
* [x] 	I confirm this is my own work (in design and implementation) 
*		and that I have not viewed another student's code OR design.
*
* EXAMPLE:
* GAURAV GUPTA
* 17299271
* [x] 	I confirm this is my own work (in design and implementation) 
*		and that I have not viewed another student's code OR design.

*/

/*
 * Total marks in this section: 16 marks (8 Pass + 4 Credit + 4 Distinction)
 * 
 * Before working on the code, please read the assignment specification first
 * to understand how the game works and what the game components are (the 
 * hand, the deck, and the word).
 * 
 * The Hand class represents the player's hand, that is, the collection of letters
 * that can be played to form a word. Only the leftmost letter in the hand can
 * be moved to form a word. 
 */
public class Hand {
	
	public Letter leftmost;
	//tracks the size of the hand
	public int handSize;
	
	/*
	 * Constructor: you can modify the constructor, but make sure 
	 * it is still an empty constructor (i.e. doesn't take any 
	 * parameter). No marks are given for this method.
	 */
	public Hand() {
		leftmost = null;
	}
	
	/**
	 * Method to return the leftmost letter in the hand (the only
	 * letter that can be moved). No marks are given for this method.
	 *  
	 * @return the leftmost Letter in the hand.
	 */
	public Letter leftmost() {
		return leftmost;
	}

	/**
	 * 4 marks - Pass level.
	 * 
	 * Method to return the size of the hand.
	 * 
	 * @return the size of the hand.
	 */
	public int size() {
//	int count = 0;
//	Letter current = leftmost;
//	while(current != null) {
//		count ++;
//		current = current.next;
//	}
//		return count;// to be completed
		return handSize;
	}

	/** 
	 * 4 marks - Pass level.
	 * 
	 * Method to add a letter to the hand, at the leftmost position.
	 * 
	 * @param letter - the letter to be added.
	 */
	public void add(Letter letter) {
		letter.next = leftmost;
		leftmost = letter;
		handSize ++;
		
		//may need to come back and add an end of list
		
		
	}
	
	/**
	 * 4 marks - Credit level.
	 * 
	 * Method to remove the leftmost letter from the hand. Return null
	 * if the hand is empty.
	 * 
	 * @return the leftmost letter in the hand if it exists, or null otherwise
	 */
	public Letter remove() {
		// check hand size
		if(this.size() == 0) {
			return null;
		}
		//remove leftmost letter
		leftmost = leftmost.next;
		handSize --;
		return leftmost;
	}
	
	/**
	 * 4 marks - Distinction level.
	 * 
	 * Method to get the i-th letter in the hand, starting with 0 
	 * for the leftmost letter. Return null if the index given is 
	 * invalid.
	 * 
	 * @param index - the index of the letter to be returned.
	 * @return the letter at the given index.
	 */
	public Letter get(int index) {
		// check if hand is empty or index exists
		if(index > this.size()|| index < 0 || this.size() ==0) {
			return null;
		}
		//create current and traverse list to idx
		Letter current = leftmost;
		for (int i = 0; i < index; i++) {
			current = current.next;
		}
		//return letter
		return current;
	}

}
