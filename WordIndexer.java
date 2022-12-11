/*
 * CSC-239 Project 3: Word Indexer
 * Student: Patrick Gould
 * Date: 12/10/2022
 * Description: This program takes in the name of a text file as
 * an argument. It then reads in that file and creates
 * an index of each word containing the number of times it occurs
 * as well as the location of each occurrence in the text file.
 *
 * Example command : "Gettysburg.txt"
 *
 */

import java.io.*;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class WordIndexer {

	private static String fileName;
	private static File document;
	private static ArrayList<WordInfo> wordList;

	public static void main(String[] args) {
		
		// Prompt user to enter name of a text file
		System.out.print("Please enter the name of a file: ");

		// Create input scanner and save file name
		Scanner scan = new Scanner(System.in);
		String fileName = scan.nextLine();

		// Initialize array list
		wordList = new ArrayList<WordInfo>();
    	
    	// Set file name
		document = new java.io.File(fileName);

        // Create a Scanner for the file and attempt to read it
        try {
        	Scanner input = new Scanner(document);

        	// Notify user of the file that is being indexed
			System.out.printf("\nIndex for file \"%s\"\n\n", fileName);

			// Initialize line number
        	int lineNum = 1;

        	// Read text from file
        	while (input.hasNext()) {
        		
        		// Save current line
            	String line = input.nextLine();
            	
            	// Split current line into individual words
            	String[] words = line.split(" ");
            	
            	// Initialize word number
            	int wordNum = 1;
            	
            	// Iterate over array of words
            	for (String word : words) {
          			
            		// Convert word to lowercase and remove punctuation
          			word = word.toLowerCase().replaceAll("[^a-z]", "");
          			word = word.replaceAll(" ", "");
          			
          			// Create new WordInfo object
          			WordInfo newWord = new WordInfo(word, lineNum, wordNum);

          			// If the wordlist is empty just add the word to the ArrayList
          			if (wordList.isEmpty()) {
          				wordList.add(newWord);
          			}
          			else {
          				// Word found flag
          				boolean wordFound = false;

          				// Loop over the ArrayList
          				for (int i = 0; i < wordList.size(); i++) {
          					WordInfo currentWord = wordList.get(i);
          					
          					// If the new word is found in the ArrayList increment its counter
          					// and add a new Occurrence object to its occurrence list
          					// then update the ArrayList
          					if (newWord.compareTo(currentWord) == 0) {
          						WordInfo temp = wordList.get(i);
          						temp.incrementOccurenceCount();
          						temp.addOccurrence(lineNum, wordNum);
          						wordList.set(i, temp);
          						wordFound = true;
          						break;
          					}
          				}

          				// If the word was not found in the Array List
          				if (!wordFound) {
          					
          					// Word inserted flag
          					boolean wordInserted = false;

          					// Loop over the ArrayList
          					for (int i = 0; i < wordList.size(); i++) {
          						WordInfo currentWord = wordList.get(i);

          						// If the new word is alphabetically less than the current word
          						// insert the new word into the ArrayList behind the current word
          						if ( newWord.compareTo(currentWord) < 0 && !newWord.getText().equals("")) {
          							wordList.add(i, newWord);
          							wordInserted = true;
          							break;
          						}
          					}

          					// If a word wasn't inserted in the middle of the list
          					// it must be alphabetically larger than all current words
          					// so insert it into the ArrayList last
          					if (!wordInserted && !newWord.getText().equals("")) {
          						wordList.add(newWord);
          					}
          				}
          				
          			}
          			// Increment the word number
          			wordNum ++;
        		}
        	    // Increment the line number
            	lineNum++;
        	}
         	// Close the file
        	input.close();
        }
        // If the file can't be found let the user know
        catch(FileNotFoundException e) {
        	System.out.printf("Error could not find file named \"%s\"\n", fileName);
        	e.printStackTrace();
        }
        
        // Loop over ArrayList of words
        for (WordInfo w : wordList) {

        	// Print number of occurrences
        	System.out.printf("%s(%s) [", w.getText(), w.getOccurrenceCount());

        	// Loop over each word's occurrence list
        	ArrayList<Occurrence> occurrenceList = w.getOccurrenceList();
        	for (int i = 0; i < occurrenceList.size(); i++) {

        		// Print out each occurrence location
        		Occurrence currentOccurence = occurrenceList.get(i);
        		if (i == occurrenceList.size() - 1) {
        			System.out.printf("%s]\n", currentOccurence.toString());
        		}
        		else {
        			System.out.printf("%s, ", currentOccurence);
        		}
        	}
        }      
      
	}
}