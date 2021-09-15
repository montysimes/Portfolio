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
 * Total marks in this section: 16 marks (12 Pass + 4 Credit)

 * 
 * Before working on the code, please read the assignment specification first
 * to understand how the game works and what the game components are (the 
 * hand, the deck, and the word).
 * 
 * The Deck class represents the deck, that is, the collection of letter tiles
 * in a random order. 
 */
public class Deck {

	// you may add attributes as needed
	// letter at the top of the deck
	public Letter top;
	// letter at the bottom of the deck
	public Letter bottom;
	//tracks the size of the deck
	public int deckSize;

	/**
	 * 4 marks - Pass level
	 * 
	 * Constructor: initialise the deck with the given Letter array. The letters
	 * should be added to the deck such that the first letter in the array is (i.e.
	 * letters[0]) is placed on top of the deck (i.e. the first letter we get if we
	 * remove a letter from the deck).
	 * 
	 * IMPORTANT: you should NOT make new instances of the Letter object but instead
	 * copy the references.
	 * 
	 * You need to handle null or empty array appropriately (some unit tests will
	 * pass a null or empty array).
	 * 
	 * @param letters - the array of letters to be added to the deck.
	 */
	public Deck(Letter[] letters) {
		// set top and bottom to null
		top = null;
		bottom = null;

		// check array has at least one item and not null
		if (letters != null && letters.length != 0) {
			top = letters[0];
			deckSize++;
			bottom = top;

			// link letters within array to each other
			Letter current = top;
			for (int i = 0; i < letters.length - 1; i++) {
				current.next = letters[i + 1];
				current = current.next;
				deckSize++;
				bottom = current;
			}
		}
	}

	/**
	 * 4 marks - Pass level
	 * 
	 * Method to return the size of the deck. You can assume that the test will only
	 * modify the deck by adding or removing Letter.
	 * 
	 * @return the size of the deck
	 */
	public int size() {
//		int count = 0;
//		Letter current = top;
//		while (current != null) {
//			count++;
//			current = current.next;
//		}
//		return count;
		return deckSize;
	}

	/**
	 * 4 marks - Pass level
	 * 
	 * Method to add a Letter to the bottom of the deck.
	 * 
	 * The method is not used by the game. It is included here in case we need to
	 * add a letter and score to the game.
	 * 
	 * @param ch    : the letter (character) to be added, as a char
	 * @param score : the letter's score
	 */
	public void add(char ch, int score) {
		// if no letters in deck add letter
		if (top == null) {
			top = new Letter(ch, score, top);
			bottom = top;
			deckSize++;
			// if letters in deck add new letter to the end
		} else {
			bottom.next = new Letter(ch, score);
			bottom = bottom.next;
			deckSize++;
		}

	}

	/**
	 * 4 marks - Credit level
	 * 
	 * Method to remove a Letter from the top of the deck and return it. Return null
	 * if the deck is empty.
	 * 
	 * @return the removed Letter or null if the deck is empty.
	 */
	public Letter remove() {
		// check if deck is empty
		if (this.size() == 0) {
			return null;
		}
		// remove the tile from the top
		Letter temp = top;
		top = top.next;;
		temp.next = null;
		deckSize--;
		return temp;
	}

}
