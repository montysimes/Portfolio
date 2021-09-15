package graphics;

import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/*
 * Class used to generate the list of words (dictionary) to be used
 * in the game. It is based on the ukenglish.txt file that comes
 * with the project.
 * 
 * Dictionary obtained from http://www.gwicks.net/justwords.htm
 */
public class Dictionary {

	public static String[] standardDictionary() {
		
		String[] words = null;
		try {
			Scanner in = new Scanner(new FileReader("ukenglish.txt"));
			ArrayList<String> temp = new ArrayList<String>();
			while(in.hasNext())
				temp.add(in.next());
			words = new String[temp.size()];
			for(int i = 0; i < temp.size(); i++){
				words[i] = temp.get(i).toUpperCase();
			}
		}
		catch (IOException e) {
			System.out.println("Exception encountered: " + e);
		}
		return words;
	}
	
}
