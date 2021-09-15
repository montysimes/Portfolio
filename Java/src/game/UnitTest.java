package game;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.lang.reflect.Method;
import java.time.Duration;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Timeout;

import graphics.Dictionary;
import graphics.LetterGenerator;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UnitTest {
	
	static String[] dictionary; 
	static Letter[] standardLetters; 
	static Letter[] evilLetters;
	static Letter[] veryEvilLetters;
	
	static Letter l0, l1, l2, l3, l4;

	public static int score = 0;
	public static int seed = 10;
	public static String result = "";
	public static String currentMethodName = null;
	ArrayList<String> methodsPassed = new ArrayList<String>();

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@BeforeEach
	public void setUp() throws Exception {
		dictionary = Dictionary.standardDictionary();
		standardLetters = LetterGenerator.standardLetters(seed);
		currentMethodName = null;

		l0 = new Letter(standardLetters[0].letter, standardLetters[0].score);
		l1 = new Letter(standardLetters[1].letter, standardLetters[1].score);
		l2 = new Letter(standardLetters[2].letter, standardLetters[2].score);
		l3 = new Letter(standardLetters[3].letter, standardLetters[3].score);
		l4 = new Letter(standardLetters[4].letter, standardLetters[4].score);
		
	}
	
	@Test
	@Order(1)
	@Timeout(1)
	@Graded(description="Deck.add", marks=4)
	public void testAddDeck() {
		
		Deck d = new Deck(null);
		assertNull(d.top);
		
		// Test case 1:
		standardLetters = LetterGenerator.standardLetters(seed);
		d.add(standardLetters[0].letter, standardLetters[0].score);
		d.add(standardLetters[1].letter, standardLetters[1].score);
		d.add(standardLetters[2].letter, standardLetters[2].score);
		
		assertNotNull(d.top);
		assertNotNull(d.top.next);
		assertNotNull(d.top.next.next);
		assertNull(d.top.next.next.next);
		assertEquals(standardLetters[0].letter,d.top.getLetter());
		assertEquals(standardLetters[0].score,d.top.getScore());
		
		
		// Test case 2:
		standardLetters = LetterGenerator.standardLetters(seed);
		d.add(standardLetters[3].letter, standardLetters[3].score);
		Letter lt = d.top.next.next.next;
		assertNotNull(lt);
		assertEquals(standardLetters[3].letter,lt.getLetter());
		assertEquals(standardLetters[3].score,lt.getScore());
		
		
		// Test case 3:
		standardLetters = LetterGenerator.standardLetters(seed);
		d = new Deck(null);
		for(int i = 0; i < 80; i++) {
			d.add(standardLetters[i].letter, standardLetters[i].score);
		}
		Letter x = d.top;
		for(int i = 0; i < 39; i++) 
			x = x.next;
		assertEquals(standardLetters[39].letter,x.letter);
		for(int i = 0; i < 40; i++) 
			x = x.next;
		assertEquals(standardLetters[79].letter,x.letter);

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}
	
	@Test
	@Order(2)
	@Timeout(1)
	@Graded(description="Hand.add", marks=4)
	public void testAddHand() {
		
		Hand h = new Hand();
		assertNull(h.leftmost());
		
		// Test case 1:
		standardLetters = LetterGenerator.standardLetters(seed);
		h.add(l0);
		h.add(l1);
		h.add(l2);
		
		assertNotNull(h.leftmost());
		assertNotNull(h.leftmost().next);
		assertNotNull(h.leftmost().next.next);
		assertNull(h.leftmost().next.next.next);
		assertEquals(l2.letter,h.leftmost().letter);
		assertEquals(l2,h.leftmost());
		
		// Test case 2:
		standardLetters = LetterGenerator.standardLetters(seed);
		h.add(l3);
		assertEquals(l3.letter,h.leftmost().letter);
		assertEquals(l3,h.leftmost());
		
		assertEquals(l1.score,h.leftmost().next.next.score);
		assertEquals(l1,h.leftmost().next.next);
		
		// Test case 3: 
		standardLetters = LetterGenerator.standardLetters(seed);
		h = new Hand();
		for(int i = 0; i < 80; i++) {
			h.add(standardLetters[i]);
		}
		Letter x = h.leftmost();
		for(int i = 0; i < 30; i++) 
			x = x.next;
		assertEquals(standardLetters[49].letter,x.letter);
		for(int i = 0; i < 49; i++) 
			x = x.next;
		assertEquals(standardLetters[0].letter,x.letter);
		

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}
	
	@Test
	@Order(3)
	@Timeout(1)
	@Graded(description="Word.addToStart", marks=4)
	public void testAddToStartOfWord() {
		
		Word w = new Word();
		assertNull(w.start);
		
		// Test Case 1:
		standardLetters = LetterGenerator.standardLetters(seed);
		w.addToStart(l2);
		w.addToStart(l3);
		w.addToStart(l4);
		
		assertNotNull(w.start);
		assertNotNull(w.start.next);
		assertNotNull(w.start.next.next);
		assertNull(w.start.next.next.next);
		assertEquals(l4.letter,w.start.letter);
		assertEquals(l4,w.start);
		assertEquals(l3.letter,l4.next.letter);
		assertEquals(l3,l4.next);
		
		// Test Case 2:
		standardLetters = LetterGenerator.standardLetters(seed);
		w.addToStart(l0);
		assertEquals(l0.letter,w.start.letter);
		assertEquals(l0,w.start);
		
		assertEquals(l3.score,w.start.next.next.score);
		assertEquals(l3,w.start.next.next);

		// Test case 3: 
		standardLetters = LetterGenerator.standardLetters(seed);
		w = new Word();
		for(int i = 0; i < 80; i++) {
			w.addToStart(standardLetters[i]);
		}
		Letter x = w.start;
		for(int i = 0; i < 30; i++) 
			x = x.next;
		assertEquals(standardLetters[49].letter,x.letter);
		for(int i = 0; i < 49; i++) 
			x = x.next;
		assertEquals(standardLetters[0].letter,x.letter);
		
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}
	
	@Test
	@Order(4)
	@Timeout(1)
	@Graded(description="Word.addToEnd", marks=4)
	public void testAddToEndOfWord() {
		
		Word w = new Word();
		assertNull(w.start);
	
		// Test case 1:
		w.addToEnd(l2);
		w.addToEnd(l3);
		w.addToEnd(l4);
		
		assertNotNull(w.start);
		assertNotNull(w.start.next);
		assertNotNull(w.start.next.next);
		assertNull(w.start.next.next.next);
		assertEquals(l2.letter,w.start.letter);
		assertEquals(l2,w.start);
		assertEquals(l3.letter,w.start.next.letter);
		assertEquals(l3,w.start.next);
		
		// Test case 2:
		w.addToEnd(l0);
		assertEquals(l0.letter,w.start.next.next.next.letter);
		assertEquals(l0,w.start.next.next.next);
		assertEquals(l0.letter,l4.next.letter);
		assertEquals(l0,l4.next);
		
		// Test case 3: 
		standardLetters = LetterGenerator.standardLetters(seed);
		w = new Word();
		for(int i = 0; i < 80; i++) {
			w.addToEnd(standardLetters[i]);
		}
		Letter x = w.start;
		for(int i = 0; i < 30; i++) 
			x = x.next;
		assertEquals(standardLetters[30].letter,x.letter);
		for(int i = 0; i < 49; i++) 
			x = x.next;
		assertEquals(standardLetters[79].letter,x.letter);
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}

	@Test
	@Order(5)
	@Timeout(1)
	@Graded(description="Deck.size", marks=4)
	public void testSizeDeck() {
		
		Deck d = new Deck(null);

		// Test case 1:
		standardLetters = LetterGenerator.standardLetters(seed);
		for(int i = 0; i < 20; i++) {
			d.add(standardLetters[i].letter, standardLetters[i].score);
		}
		assertEquals(20,d.size());
		assertEquals(20,d.size());
		assertEquals(standardLetters[6].letter,d.top.next.next.next.next.next.next.letter);
		
		
		// Test case 2:
		for(int i = 20; i < 40; i++) {
			d.add(standardLetters[i].letter, standardLetters[i].score);
		}
		Letter x = d.top;
		assertEquals(40,d.size());
		assertEquals(40,d.size());
		for(int i = 0; i < 27; i++) 
			x = x.next;
		assertEquals(standardLetters[27].letter,x.letter);
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}
	
	@Test
	@Order(6)
	@Timeout(1)
	@Graded(description="Hand.size", marks=4)
	public void testSizeHand() {
		
		Hand h = new Hand();
		
		// Test case 1:
		standardLetters = LetterGenerator.standardLetters(seed);
		for(int i = 0; i < 20; i++) {
			h.add(standardLetters[i]);
		}
		assertEquals(20,h.size());
		assertEquals(20,h.size());
		assertEquals(standardLetters[13].letter,h.leftmost().next.next.next.next.next.next.letter);
		
		// Test case 2:
		for(int i = 20; i < 40; i++) {
			h.add(standardLetters[i]);
		}
		Letter x = h.leftmost();
		for(int i = 0; i < 27; i++) 
			x = x.next;
		assertEquals(standardLetters[12].letter,x.letter);
		assertEquals(40,h.size());
		assertEquals(40,h.size());
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}
	
	@Test
	@Order(7)
	@Timeout(1)
	@Graded(description="Word.size", marks=4)
	public void testSizeWord() {
		
		Word w = new Word();

		// Test case 1:
		standardLetters = LetterGenerator.standardLetters(seed);
		for(int i = 0; i < 10; i++) {
			w.addToStart(standardLetters[i]);
		}
		assertEquals(10,w.size());
		assertEquals(10,w.size());
		assertEquals(standardLetters[6].letter,w.start.next.next.next.next.next.next.next.letter);
		
		// Test case 2:
		standardLetters = LetterGenerator.standardLetters(seed);
		w = new Word();
		for(int i = 0; i < 10; i++) {
			w.addToEnd(standardLetters[i]);
		}
		assertEquals(10,w.size());
		assertEquals(10,w.size());
		assertEquals(standardLetters[6].letter,w.start.next.next.letter);
		
		// Test case 3: 
		standardLetters = LetterGenerator.standardLetters(seed);
		w = new Word();
		for(int i = 0; i < 10; i++) {
			w.addToEnd(standardLetters[i]);
		}
		for(int i = 10; i < 20; i++) {
			w.addToStart(standardLetters[i]);
		}
		assertEquals(20,w.size());
		assertEquals(20,w.size());
		Letter x = w.start;
		assertEquals(standardLetters[19],w.start);
		for(int i = 0; i < 3; i++)
			x = x.next;
		assertEquals(standardLetters[16],x);
		for(int i = 3; i < 6; i++)
			x = x.next;
		assertEquals(standardLetters[13],x);
		for(int i = 6; i < 10; i++)
			x = x.next;
		assertEquals(standardLetters[0],x);
		for(int i = 10; i < 15; i++)
			x = x.next;
		assertEquals(standardLetters[5],x);
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}	
	
	@Test
	@Order(8)
	@Timeout(1)
	@Graded(description="Deck constructor and Game constructor", marks=7)
	public void testConstructors() {
		
		
		// Testing Deck constructor
		standardLetters = LetterGenerator.standardLetters(seed);
		Deck d = new Deck(standardLetters);
		assertEquals(98,d.size());
		assertEquals(standardLetters[2],d.top.next.next);
		assertEquals(standardLetters[5],d.top.next.next.next.next.next);
		
		// Testing Game constructor
		standardLetters = LetterGenerator.standardLetters(seed);
		Game g = new Game(dictionary, standardLetters);
		assertNotNull(g.hand);
		assertNotNull(g.word);
		assertNotNull(g.deck);
		assertEquals(0,g.score);
		assertEquals(98,g.deck.size());
		assertEquals(standardLetters[3],g.deck.top.next.next.next);

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}	
		
	
	@Test
	@Order(9)
	@Timeout(1)
	@Graded(description="Word.getScore", marks=4)
	public void testGetScore() {
		
		// Test case 1:
		Word w = new Word();
		assertEquals(0,w.getScore());
		
		// Test case 2:
		w = new Word();
		w.addToStart(new Letter('U',1));
		w.addToStart(new Letter('A',1));
		w.addToStart(new Letter('T',1));
		w.addToEnd(new Letter('R',1));
		w.addToEnd(new Letter('U',1));
		w.addToEnd(new Letter('S',1));
		assertEquals(6,w.getScore());
		
		// Test case 3:
		w = new Word();
		w.addToEnd(new Letter('S',1));
		w.addToEnd(new Letter('K',5));
		w.addToEnd(new Letter('U',1));
		w.addToEnd(new Letter('N',1));
		w.addToEnd(new Letter('K',5));
		w.addToEnd(new Letter('S',1));
		assertEquals(14,w.getScore());
		
		// Test case 4:
		w = new Word();
		w.addToStart(new Letter('A',1));
		w.addToStart(new Letter('H',4));
		w.addToStart(new Letter('C',3));
		w.addToEnd(new Letter('L',1));
		assertEquals(9,w.getScore());
		w.addToEnd(new Letter('L',1));
		w.addToEnd(new Letter('E',1));
		w.addToEnd(new Letter('N',1));
		w.addToEnd(new Letter('G',2));
		w.addToEnd(new Letter('E',1));
		assertEquals(15,w.getScore());

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}		
	
	@Test
	@Order(10)
	@Timeout(1)
	@Graded(description="Word.isWord", marks=4)
	public void testIsWord() {
		
		// Test case 1:
		Game g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('V',4));
		g.word.addToEnd(new Letter('A',1));
		g.word.addToEnd(new Letter('N',1));
		assertTrue(g.word.isWord(dictionary));

		// Test case 2:
		g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('C',3));
		g.word.addToEnd(new Letter('H',4));
		g.word.addToEnd(new Letter('A',1));
		g.word.addToEnd(new Letter('L',1));
		g.word.addToEnd(new Letter('K',5));
		assertTrue(g.word.isWord(dictionary));

		// Test case 3:
		g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('C',3));
		g.word.addToEnd(new Letter('K',5));
		g.word.addToEnd(new Letter('K',5));
		assertFalse(g.word.isWord(dictionary));
		
		// Test case 4:
		g = new Game(dictionary,standardLetters);
		assertFalse(g.word.isWord(dictionary));

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}		
	
	@Test
	@Order(11)
	@Timeout(1)
	@Graded(description="Word.isPossibleWord", marks=4)
	public void testIsPossibleWord() {
		
		// Test case 1:
		Game g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('V',4));
		g.word.addToEnd(new Letter('A',1));
		g.word.addToEnd(new Letter('N',1));
		assertTrue(g.word.isPossibleWord(dictionary));

		// Test case 2:
		g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('C',3));
		g.word.addToEnd(new Letter('H',4));
		g.word.addToEnd(new Letter('A',1));
		g.word.addToEnd(new Letter('L',1));
		g.word.addToEnd(new Letter('K',5));
		assertTrue(g.word.isPossibleWord(dictionary));
		
		// Test case 3:
		g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('C',3));
		g.word.addToEnd(new Letter('H',4));
		g.word.addToEnd(new Letter('A',1));
		g.word.addToEnd(new Letter('L',1));
		assertTrue(g.word.isPossibleWord(dictionary));
		
		// Test case 4:
		g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('C',3));
		g.word.addToEnd(new Letter('H',4));
		g.word.addToEnd(new Letter('A',1));
		g.word.addToEnd(new Letter('L',1));
		g.word.addToEnd(new Letter('K',5));
		g.word.addToEnd(new Letter('K',5));
		assertFalse(g.word.isPossibleWord(dictionary));

		// Test case 5:
		g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('C',3));
		g.word.addToEnd(new Letter('K',5));
		assertTrue(g.word.isPossibleWord(dictionary));
		
		// Test case 6:
		g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('C',3));
		g.word.addToEnd(new Letter('K',5));
		g.word.addToEnd(new Letter('K',5));
		assertTrue(g.word.isPossibleWord(dictionary));
		
		// Test case 7:
		g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('C',3));
		g.word.addToEnd(new Letter('K',5));
		g.word.addToEnd(new Letter('K',5));
		g.word.addToEnd(new Letter('K',5));
		assertFalse(g.word.isPossibleWord(dictionary));

		// Test case 8:
		g = new Game(dictionary,standardLetters);
		assertTrue(g.word.isPossibleWord(dictionary));

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}	
	
	@Test
	@Order(12)
	@Timeout(1)
	@Graded(description="Game.scoreWord", marks=4)
	public void testScoreWord() {
		
		Game g = new Game(dictionary,standardLetters);
		g.word.addToEnd(new Letter('C',3));
		g.word.addToEnd(new Letter('H',4));
		g.word.addToEnd(new Letter('A',1));
		g.word.addToEnd(new Letter('L',1));
		g.word.addToEnd(new Letter('K',5));
		g.scoreWord();
		
		assertEquals(14,g.score);
		assertEquals(0,g.word.size());

		g.word.addToEnd(new Letter('C',3));
		g.word.addToEnd(new Letter('K',5));
		g.scoreWord();
		assertEquals(14,g.score);
		assertEquals(2,g.word.size());

		g.word.addToStart(new Letter('A',1));
		g.word.addToStart(new Letter('L',1));
		g.word.addToStart(new Letter('S',1));
		g.scoreWord();
		assertEquals(25,g.score);
		assertEquals(0,g.word.size());

		g.word.addToStart(new Letter('Z',10));
		g.word.addToStart(new Letter('Z',10));
		g.scoreWord();
		assertEquals(25,g.score);
		assertEquals(2,g.word.size());

		g.word.addToStart(new Letter('Z',10));
		g.scoreWord();
		assertEquals(25,g.score);
		assertEquals(0,g.word.size());

		g.word.addToStart(new Letter('Z',10));
		g.word.addToStart(new Letter('Z',10));
		g.scoreWord();
		assertEquals(25,g.score);
		assertEquals(2,g.word.size());

		g.word.addToEnd(new Letter('Y',4));
		g.word.addToStart(new Letter('I',1));
		g.word.addToStart(new Letter('D',2));
		g.scoreWord();
		assertEquals(52,g.score);
		assertEquals(0,g.word.size());

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}	
	
	@Test
	@Order(13)
	@Timeout(1)
	@Graded(description="Deck.remove", marks=4)
	public void testRemoveDeck() {

		Deck d = new Deck(null);

		// Test case 1:
		standardLetters = LetterGenerator.standardLetters(seed);
		for(int i = 0; i < 40; i++) {
			d.add(standardLetters[i].letter, standardLetters[i].score);
		}
		assertEquals(40,d.size());
		for(int i = 0; i < 20; i++) {
			d.remove();
		}
		assertEquals(standardLetters[20].letter,d.top.letter);
		assertEquals(standardLetters[21].letter,d.top.next.letter);
		assertEquals(standardLetters[22].letter,d.top.next.next.letter);
		assertEquals(standardLetters[23].letter,d.top.next.next.next.letter);
		
		
		// Test case 2:
		standardLetters = LetterGenerator.standardLetters(seed);
		d = new Deck(null);
		d.remove();
		assertEquals(0,d.size());
		
		// Test case 3: 
		standardLetters = LetterGenerator.standardLetters(seed);
		d = new Deck(standardLetters);
		for(int i = 0; i < 30; i++)
			d.remove();
		assertEquals(68,d.size());
		for(int i = 0; i < 64; i++) 
			d.remove();
		assertEquals(4,d.size());
		assertEquals(standardLetters[94].letter,d.top.letter);
		d.remove();
		assertEquals(standardLetters[95].letter,d.top.letter);
		d.remove();
		assertEquals(standardLetters[96].letter,d.top.letter);
		d.remove();
		assertEquals(standardLetters[97].letter,d.top.letter);
		d.remove();
		assertEquals(0,d.size());
		d.remove();
		assertEquals(0,d.size());

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}	
	
	@Test
	@Order(14)
	@Timeout(1)
	@Graded(description="Hand.remove", marks=4)
	public void testRemoveHand() {
		Hand h = new Hand();
		assertNull(h.leftmost());
		
		// Test case 1:
		standardLetters = LetterGenerator.standardLetters(seed);
		h = new Hand();
		h.remove();
		h.remove();
		assertEquals(0,h.size());
		
		// Test case 2:
		standardLetters = LetterGenerator.standardLetters(seed);
		for(int i = 0; i < 80; i++) 
			h.add(standardLetters[i]);
		for(int i = 0; i < 76; i++) 
			h.remove();
		assertEquals(standardLetters[3],h.leftmost());
		h.remove();
		assertEquals(standardLetters[2],h.leftmost());
		h.remove();
		assertEquals(standardLetters[1],h.leftmost());
		h.remove();
		assertEquals(standardLetters[0],h.leftmost());
		h.remove();
		assertEquals(0,h.size());
		
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}	

	@Test
	@Order(15)
	@Timeout(1)
	@Graded(description="Game logic (draw, moveFromHandToStartOfWord, moveFromHandToEndOfWord)", marks=4)
	public void testGameLogic() {

Game g = new Game(dictionary,standardLetters);
		
		// Test case 1:
		g.draw();
		g.draw();
		g.draw();
		g.draw();
		assertEquals(4,g.hand.size());
		assertEquals(0,g.word.size());
		
		g.moveFromHandToEndOfWord();
		assertEquals(3,g.hand.size());
		assertEquals(1,g.word.size());
		assertEquals(standardLetters[2],g.hand.leftmost());
		assertEquals(standardLetters[3],g.word.start);
		
		// Test case 2:
		g.draw();
		g.draw();
		g.draw();
		g.draw();
		assertEquals(7,g.hand.size());
		assertEquals(1,g.word.size());
		
		g.moveFromHandToStartOfWord();
		assertEquals(6,g.hand.size());
		assertEquals(2,g.word.size());
		assertEquals(standardLetters[7],g.word.start);
		assertEquals(standardLetters[3],g.word.start.next);
		
		// Test case 3:
		standardLetters = LetterGenerator.standardLetters(seed);
		g = new Game(dictionary,standardLetters);
		g.moveFromHandToEndOfWord();
		g.moveFromHandToStartOfWord();
		g.moveFromHandToEndOfWord();
		assertEquals(0,g.hand.size());
		assertEquals(0,g.word.size());
		
		// Test case 4:
		standardLetters = LetterGenerator.standardLetters(seed);
		g = new Game(dictionary,standardLetters);
		for(int i = 0; i < 100; i++)
			g.draw();
		g.moveFromHandToStartOfWord();
		g.moveFromHandToStartOfWord();
		assertEquals(96,g.hand.size());
		assertEquals(2,g.word.size());
		assertEquals(standardLetters[95],g.hand.leftmost());
		assertEquals(standardLetters[96],g.word.start);
		assertEquals(standardLetters[97],g.word.start.next);
			
		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}	
	
	@Test
	@Order(16)
	@Timeout(1)
	@Graded(description="Hand.get", marks=4)
	public void testGet() {
		
		Hand h = new Hand();
		
		// Test case 1:
		standardLetters = LetterGenerator.standardLetters(seed);
		h = new Hand();
		assertNull(h.get(0));
		assertNull(h.get(40));
		
		// Test case 2:
		standardLetters = LetterGenerator.standardLetters(seed);
		for(int i = 0; i < 20; i++) 
			h.add(standardLetters[i]);
		assertEquals(standardLetters[19],h.get(0));
		h.remove();
		assertEquals(standardLetters[18],h.get(0));
		assertEquals(standardLetters[16],h.get(2));
		assertEquals(standardLetters[14],h.get(4));
		assertEquals(standardLetters[0],h.get(18));
		assertNull(h.get(19));

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}	
	
	@Test
	@Order(17)
	@Timeout(1)
	@Graded(description="Word.toArray", marks=4)
	public void testToArray() {
		
		Game g = new Game(dictionary,standardLetters);

		// Test case 1:
		Letter[] actual = g.word.toArray();
		assertNotNull(actual);
		assertEquals(0,actual.length);

		// Test case 2:
		standardLetters = LetterGenerator.standardLetters(seed);
		g.word.addToStart(standardLetters[0]);
		g.word.addToStart(standardLetters[1]);
		g.word.addToStart(standardLetters[2]);
		g.word.addToStart(standardLetters[3]);
		actual = g.word.toArray();
		assertEquals(4,actual.length);
		assertEquals(standardLetters[3],actual[0]);
		assertEquals(standardLetters[2],actual[1]);
		assertEquals(standardLetters[1],actual[2]);
		assertEquals(standardLetters[0],actual[3]);

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}
	
	@Test
	@Order(18)
	@Timeout(1)
	@Graded(description="add methods (large)", marks=4)
	public void testAddLarge() {
		evilLetters = LetterGenerator.evilLetters(seed);
		
		Deck d = new Deck(null);
		assertNull(d.top);
		
		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
			for(int i = 0; i < evilLetters.length; i++) {
				d.add(evilLetters[i].letter, evilLetters[i].score);
			}
		});
		assertNotNull(d.top);
		assertNotNull(d.top.next);
		assertNotNull(d.top.next.next);
		assertEquals(evilLetters[0].letter,d.top.getLetter());
		assertEquals(evilLetters[0].score,d.top.getScore());
		
		
		Letter lt = d.top;
		for(int i = 0; i < 1000; i++) 
			lt = lt.next;
		assertNotNull(lt);
		assertEquals(evilLetters[1000].letter,lt.getLetter());

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}
	
	@Test
	@Order(19)
	@Timeout(10)
	@Graded(description="size methods (large)", marks=5)
	public void testSizeLarge() {
		veryEvilLetters = LetterGenerator.veryEvilLetters(seed);

		Game g = new Game(dictionary,veryEvilLetters);
		
		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
			for (int i = 0; i < 100; i++)
				g.deck.size();
		});

		assertEquals(980000,g.deck.size());
		for(int i = 0; i < 500000; i++) 
			g.draw();

		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
			for (int i = 0; i < 100; i++)
				g.deck.size();
		});
		assertEquals(480000,g.deck.size());

		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
			for (int i = 0; i < 100; i++)
				g.hand.size();
		});
		assertEquals(500000,g.hand.size());

		for(int i = 0; i < 100000; i++) 
			g.hand.remove();

		assertTimeoutPreemptively(Duration.ofMillis(1000), () -> {
			for (int i = 0; i < 100; i++)
				g.hand.size();
		});
		assertEquals(400000,g.hand.size());

		currentMethodName = new Throwable().getStackTrace()[0].getMethodName();
	}
	

	
	@AfterEach
	public void logSuccess() throws NoSuchMethodException, SecurityException {
		if(currentMethodName != null && !methodsPassed.contains(currentMethodName)) {
			methodsPassed.add(currentMethodName);
			Method method = getClass().getMethod(currentMethodName);
			Graded graded = method.getAnnotation(Graded.class);
			score+=graded.marks();
			result+=graded.description()+" passed. Marks awarded: "+graded.marks()+"\n";
		}
	}
	
	@AfterAll
	public static void wrapUp() throws IOException {
		if(result.length() != 0) {
			result = result.substring(0, result.length()-1); //remove the last "\n"
		}
		System.out.println(result);
		System.out.println("Indicative mark: "+ score);
		System.out.println();
	}	
}
