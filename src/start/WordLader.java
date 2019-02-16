package start;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.Queue;
import java.util.Stack;

public class WordLader {
	
	private static final String FILE_NAME = "D:/Project/CS 106B WordLader Ngrams/dictionary.txt";
	private Set<String> dictionary;
	private Queue<Stack<String>> queueOfstacks;
	private String word1;
	private String word2;
	
	public static void main(String[] args){
		WordLader obj = new WordLader();
		obj.run();
	}
	
	private void run(){
		makeDictionary();
		takeInputFromUser();
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
	
	private void takeInputFromUser(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter First Word:  ");
		word1 = sc.nextLine().trim();
		while(true){
			System.out.print("Enter Second Word:  ");
			word2 = sc.nextLine().trim();
			if(isValidInput(word1.length(), word2)){
				break;
			}else{
				System.out.println("Second Word should match the first words length");
			}
		}
		
		sc.close();
	}
	
	private boolean isValidInput(int length, String word2){
		return word2.length() == length;
	}
}
