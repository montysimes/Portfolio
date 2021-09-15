package graphics;

import game.Letter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/* 
 * Class used to generate the list of letters used in the game
 */
public class LetterGenerator {

	// the list of each letter, its score, and its frequency (based on Scrabble
	// letter distribution)
	public static char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z' };
	public static int[] score    = {  1,  3,  3,  2,  1,  4,  2,  4,  1,  8,  5,  1,  3,  1,  1,  3, 10,  1,  1,  1,  1,  4,  4,  8,  4, 10 };
	public static int[] freq     = {  9,  2,  2,  4, 12,  2,  3,  2,  9,  1,  1,  4,  2,  6,  8,  2,  1,  6,  4,  6,  4,  2,  2,  1,  2,  1 }; 

	// creates an array of letter using standard Scrabble letter distribution and
	// score (i.e. using the arrays above)
	public static Letter[] standardLetters(int seed) {
		ArrayList<Letter> temp = new ArrayList<Letter>();
		for(int i = 0; i < letters.length; i++) {
			for(int count = 0; count < freq[i]; count++) {
				temp.add(new Letter(letters[i],score[i]));
			}
		}
		Collections.shuffle(temp, new Random(seed));
		
		Letter[] letterArray = new Letter[temp.size()];
		for(int i = 0; i < letterArray.length; i++)
			letterArray[i] = temp.get(i);
		return letterArray;
	}
	
	// smaller sized array of letters that you can use to test
	// your implementation
	public static Letter[] small(int seed) {
		ArrayList<Letter> temp = new ArrayList<Letter>();
		for(int i = 0; i < letters.length; i++) {
			if (freq[i] >= 6) {
				for(int count = 0; count < freq[i]/4; count++) {
					temp.add(new Letter(letters[i],score[i]));
				}
			}
		}
		Collections.shuffle(temp, new Random(seed));
		
		Letter[] letterArray = new Letter[temp.size()];
		for(int i = 0; i < letterArray.length; i++)
			letterArray[i] = temp.get(i);
		return letterArray;

	}
	
	// the evil letter distribution
	public static Letter[] evilLetters(int seed) {
		ArrayList<Letter> temp = new ArrayList<Letter>();
		for(int i = 0; i < letters.length; i++) {
			for(int count = 0; count < freq[i] * 1000; count++) {
				temp.add(new Letter(letters[i],score[i]));
			}
		}
		Collections.shuffle(temp, new Random(seed));
		
		Letter[] letterArray = new Letter[temp.size()];
		for(int i = 0; i < letterArray.length; i++)
			letterArray[i] = temp.get(i);
		return letterArray;
	}
	
	// the very evil letter distribution
	public static Letter[] veryEvilLetters(int seed) {
		ArrayList<Letter> temp = new ArrayList<Letter>();
		for(int i = 0; i < letters.length; i++) {
			for(int count = 0; count < freq[i] * 10_000; count++) {
				temp.add(new Letter(letters[i],score[i]));
			}
		}
		Collections.shuffle(temp, new Random(seed));
		
		Letter[] letterArray = new Letter[temp.size()];
		for(int i = 0; i < letterArray.length; i++)
			letterArray[i] = temp.get(i);
		return letterArray;
	}
	
}
