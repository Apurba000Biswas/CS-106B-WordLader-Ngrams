package start;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class WordLader {
	
	private static final String FILE_NAME = "D:/Project/CS 106B WordLader Ngrams/dictionary.txt";
	private Set<String> dictionary;
	
	public static void main(String[] args){
		WordLader obj = new WordLader();
		obj.run();
	}
	
	private void run(){
		makeDictionary();
	}
	
	private void makeDictionary(){
		dictionary = new HashSet<String>();
		Path path = Paths.get(FILE_NAME);
		try {
			Scanner scanner = new Scanner(path);
			while(scanner.hasNext()){
				String word = scanner.next();
				if(!dictionary.contains(word)){
					dictionary.add(word);
				}
			}
			System.out.println("Done");
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
