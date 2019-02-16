package start;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedList;
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
	private Set<String> visitedNeighbours;
	
	public static void main(String[] args){
		WordLader obj = new WordLader();
		obj.run();
	}
	
	private void run(){
		System.out.println("Welcome to CS 106B Word Ladder.\n"
				+ "Please give me two English words, and I will change the\n"
				+ "first into the second by changing one letter at a time.\n\n");
		makeDictionary();
		takeInputFromUser();
		findWordLader();
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
			scanner.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void takeInputFromUser(){
		Scanner sc = new Scanner(System.in);
		System.out.print("Word #1 :  ");
		word1 = sc.nextLine().trim().toLowerCase();
		while(true){
			System.out.print("Word #2 :  ");
			word2 = sc.nextLine().trim().toLowerCase();
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
	
	private void findWordLader(){
		//create an empty Queue of stacks
		queueOfstacks = new LinkedList<Stack<String>>();
		//Create/add a stack containing {w1} to the queue.
		Stack<String> word1Stack = new Stack<>();
		word1Stack.add(word1);
		queueOfstacks.add(word1Stack);
		while(queueOfstacks.size() > 0){
			//Dequeue the partial-ladder stack from the front of the queue.
			Stack<String> wordStack = queueOfstacks.remove();
			// now get top word from the stack
			String topWord = wordStack.pop();
			findNeighbours(topWord);
		}
	}
	
	private void findNeighbours(String topWord){
		String word = topWord;
		for(int i=0; i<word.length(); i++){
			for(char alphabet = 'a'; alphabet<='z'; alphabet++){
				String newBuiltNeighbour = getPossibleNeighbour(i, alphabet, word);
				if(dictionary.contains(newBuiltNeighbour)){
					// ok its valid word
				}
				
			}
			System.out.println();
		}
	}
	
	private String getPossibleNeighbour(int index, char alpha, String original){
		String newStr = "";
		for(int i=0; i<original.length(); i++){
			char c = original.charAt(i);
			if(i == index){
				c = alpha;
			}
			newStr += c;
		}
		return newStr;
	}
}
