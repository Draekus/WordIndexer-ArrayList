public class Occurrence {
	
	// Line number of occurrence
	private Integer lineNum;
	// Word number of occurrence
	private Integer wordNum;

	// Constructor
	public Occurrence(Integer lineNum, Integer wordNum) {
		this.lineNum = lineNum;
		this.wordNum = wordNum;
	}

	// Returns the location as a string
	public String toString() {
		return "(" + lineNum + "," + wordNum + ")";
	}
}