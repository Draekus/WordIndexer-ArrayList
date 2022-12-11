import java.util.ArrayList;

public class WordInfo implements Comparable<WordInfo> {
	
	// Text of the word
	private String text;
	// Number of occurrences
	private Integer occurenceCount;  
	// List of occurrence locations
	private ArrayList<Occurrence> occurrenceList;

	// Constructor
	public WordInfo(String text, Integer lineNum, Integer wordNum) {
		this.text = text;
		this.occurenceCount = 1;
		this.occurrenceList = new ArrayList<Occurrence>();
		this.addOccurrence(lineNum, wordNum);
	}

	// Increment the number of occurrences
	public void incrementOccurenceCount() {
		this.occurenceCount += 1;
	}

	// Add an occurrence to the occurrence list
	public void addOccurrence(Integer lineNum, Integer wordNum) {
		Occurrence newOccurence = new Occurrence(lineNum, wordNum);
		occurrenceList.add(newOccurence);
	}

	// Compare this WordInfo object with another WordInfo object
	public int compareTo(WordInfo w) {
		return this.text.compareTo(w.text);
	}

	// Return the text as a String
	public String getText() {
		return this.text;
	}

	// Retrieve the occurrence list
	public ArrayList<Occurrence> getOccurrenceList() {
		return this.occurrenceList;
	}

	// Return the occurrence count as a string
	public String getOccurrenceCount() {
		return occurenceCount.toString();
	}

}