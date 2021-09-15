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
 * Total marks in this section: 28 (24 Pass + 4 High Distinction) 
 * 
 * Before working on the code, please read the assignment specification first
 * to understand how the game works and what the game components are (the 
 * hand, the deck, and the word).
 * 
 * The Word class represent the word that the player is constructing. 
 * You can add a letter only to either the start or the end of the word.
 */
public class Word {

	// start of the list
	public Letter start;
	// end of the list
	public Letter end;
	// size of the word
	public int wordSize;

	/**
	 * Constructor: you can modify the constructor, but make sure it is still an
	 * empty constructor (i.e. doesn't take any parameter). No marks are given for
	 * this method.
	 */
	public Word() {
		start = null;
	}

	/**
	 * 4 marks - Pass level.
	 * 
	 * Method to return the size of the word.
	 * 
	 * @return the size of the word.
	 */
	public int size() {
//		int count = 0;
//		Letter current = start;
//		while (current != null) {
//			count++;
//			current = current.next;
//		}
//		return count;// to be completed
		return wordSize;
	}

	/**
	 * 4 marks - Pass level.
	 * 
	 * Method to calculate the score of the word. You can obtain this by adding the
	 * score of each letter in the word.
	 * 
	 * @return the score of the word.
	 */
	public int getScore() {
		int sum = 0;
		Letter current = start;
		while (current != null) {
			sum += current.score;
			current = current.next;
		}
		return sum;
	}

	/**
	 * 4 marks - Pass level.
	 * 
	 * Method to add a letter to the start of the word.
	 * 
	 * @param letter - the letter to be added.
	 */
	public void addToStart(Letter letter) {
		if (start == null) {
			start = letter;
			end = letter;
			wordSize++;
		} else {
			letter.next = start;
			if (start.next == null) {
				end = start;
			}
			start = letter;
			wordSize++;
		}

	}

	/**
	 * 4 marks - Pass level.
	 * 
	 * Method to add a letter to the end of the word.
	 * 
	 * @param letter - the letter to be added.
	 */
	public void addToEnd(Letter letter) {
		if (start == null) {
			start = letter;
			end = letter;
			wordSize++;
		} else {
			end.next = letter;
			end = end.next;
			wordSize++;
		}
	}

	/**
	 * 4 marks - Pass level.
	 * 
	 * Method to check if the word is a valid word in the given dictionary. Return
	 * false if word is empty.
	 * 
	 * @param dictionary - an array of String containing all valid words.
	 * @return true if the word is in the dictionary, false otherwise.
	 */
	public boolean isWord(String[] dictionary) {

		// convert list to string
		String word = "";
		Letter current = start;
		while (current != null) {
			word += current.letter;
			current = current.next;
		}

		// check if string equals dictionary
		for (int i = 0; i < dictionary.length; i++) {
			if (word.equals(dictionary[i])) {
				return true;
			}
		}

		return false;
	}

	/**
	 * 4 marks - Pass level.
	 * 
	 * Method to check if the word is a substring of a valid word in the given
	 * dictionary. The purpose of this method is to check whether or not it is
	 * possible to add more letters to the current word to make it valid (see
	 * assignment specification for more information).
	 * 
	 * Return true is the word is empty.
	 * 
	 * @param dictionary - an array of String containing all valid words.
	 * @return true if the word is empty or a substring of a valid word, false
	 *         otherwise.
	 */
	public boolean isPossibleWord(String[] dictionary) {
		if (this.size() == 0) {
			return true;
		}

		// convert list to string
		String word = "";
		Letter current = start;
		while (current != null) {
			word += current.letter;
			current = current.next;
		}

		// check if string equals dictionary
		for (int i = 0; i < dictionary.length; i++) {
			if (dictionary[i].contains(word)) {
				return true;
			}
		}

		return false;

	}

	/**
	 * 4 marks - High Distinction level.
	 * 
	 * Method to create an array of letters representing the word. You should return
	 * a reference copy and not an instance copy of the Letter objects.
	 * 
	 * You may have to complete other methods in this/other classes in order to
	 * receive full marks for this method.
	 * 
	 * @return an array containing the letters in the word.
	 */
	public Letter[] toArray() {

		// find required length of the array
		int arrLength = this.size();
		Letter[] wordArr = new Letter[arrLength];
		// check word is not empty
		if (this.size() != 0) {
			//populate array
			Letter current = start;
			for (int i = 0; i < wordArr.length; i++) {
				wordArr[i] = current;
				current = current.next;
			}
		}
		return wordArr;
	}
}
