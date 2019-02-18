package start;

import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class N_Grams {
	
	private static final String FILE_NAME = "D:/Project/CS 106B WordLader Ngrams/tiny.txt";
	
	private int N;
	private Map<List<String>, List<String>> N_Map;
	
	public static void main(String[] args){
		N_Grams obj = new N_Grams();
		obj.run();
	}
	
	private void run(){
		
		System.out.println("Welcome to CS 106B Random Writer ('N-Grams').\n"
				+ "This program makes random text based on a document.\n"
				+ "Give me an input file and an 'N' value for groups\n"
				+ "of words, and I'll create random text for you.\n\n");
		System.out.print("Value of N?: ");
		Scanner userInputScanner = new Scanner(System.in);
		N = userInputScanner.nextInt();
		biuldN_Map();
		System.out.println("Map is\n");
		printN_map();
		System.out.print("# of random words to generate (0 to quit)? : ");
		int numOfWord = userInputScanner.nextInt();
		if(numOfWord != 0){
			generateText(numOfWord);
		}
		System.out.println("\nHave a good day");
		userInputScanner.close();
	}
	
	private void generateText(int numOfWord){
		System.out.println("Generating Text .. ");
	}
	
	private void biuldN_Map(){
		Path path = Paths.get(FILE_NAME);
		N_Map = new HashMap<>();
		Queue<String> window = new LinkedList<String>();
		try{
			Scanner sc = new Scanner(path);
			List<String> firstKey = new ArrayList<String>();
			while(sc.hasNext()){
				int windowSize = window.size();
				if(windowSize<(N-1)){
					String word = sc.next();
					window.add(word);
					firstKey.add(word);
				}
				if(windowSize == (N-1)){
					List<String> key = getKey(window);
					System.out.print(key + " Follwed word is: ");
					String followedWord = sc.next();
					System.out.println(followedWord);
					
					putInN_Map(key, followedWord);
					shiftByOneWindow(window, followedWord);
				}
			}
			wrapAroundTheMap(window, firstKey);
			sc.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	private void wrapAroundTheMap(Queue<String> lastWindow, List<String> firstKey){
		for(int i=0; i<(N-1); i++){
			String topFollowedWord = firstKey.get(i);
			List<String> lastKey = getKey(lastWindow);
			putInN_Map(lastKey, topFollowedWord);
			System.out.print("Top Word (" + i + ") : " + topFollowedWord + " ");
			shiftByOneWindow(lastWindow, topFollowedWord);
		}
	}
	
	private void shiftByOneWindow(Queue<String> window , String followedWord){
		window.add(followedWord);
		window.remove();
	}
	
	private void putInN_Map(List<String> key, String follwedWord){
		if(N_Map.containsKey(key)){
			List<String> value = N_Map.get(key);
			value.add(follwedWord);
			N_Map.put(key, value);
		}else{
			List<String> newValue = new ArrayList<>();
			newValue.add(follwedWord);
			N_Map.put(key, newValue);
		}
	}
	
	private List<String> getKey(Queue<String> window){
		Queue<String> copiedWindow = new LinkedList<>();
		copiedWindow.addAll(window);
		List<String> key = new ArrayList<String>();
		while(!copiedWindow.isEmpty()){
			key.add(copiedWindow.remove());
		}
		return key;
	}
	
	private void printN_map(){
		for(List<String> key: N_Map.keySet()){
			System.out.println(key + " : " + N_Map.get(key));
		}
	}
}
