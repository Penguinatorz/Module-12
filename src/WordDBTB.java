public class WordDBTB {
	
    private String word_value;
    private int occurrences ;
    private int primaryKey;
    public WordDBTB(String word_value, int occurrences ) {
    	this.word_value = word_value;
    	this.occurrences  = occurrences ;
    }

    public WordDBTB(String word_value, int occurrences, int primaryKey ) {
    	this.word_value = word_value;
    	this.occurrences  = occurrences ;
    	this.primaryKey = primaryKey;
    }
    
    public WordDBTB() {
    }

	public String getWord_value() {
		return word_value;
	}

	public void setWord_value(String word_value) {
		this.word_value = word_value;
	}

	public int getOccurrences () {
		return occurrences ;
	}

	public void setOccurrences (int occurrences ) {
		this.occurrences  = occurrences ;
	}
    
    @Override
    public String toString() {
        return "word{" +
        		"primaryKey=" + primaryKey +
                ", word_value='" + word_value + '\'' +
                ", occurrences=" + occurrences +
                '}';
    }
}