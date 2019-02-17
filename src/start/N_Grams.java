package start;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class N_Grams {
	
	private static final String FILE_NAME = "D:/Project/CS 106B WordLader Ngrams/tiny.txt";
	
	private int N;
	
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
	}
	
	private void biuldN_Map(){
		Path path = Paths.get(FILE_NAME);
		try{
			Scanner sc = new Scanner(path);
			while(sc.hasNext()){
				System.out.println(sc.next());
			}
			sc.close();
		}catch(IOException e){
			
		}
	}

}
