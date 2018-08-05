package ua.nure.thao.Practice6.part1;

public class Word implements Comparable<Word>{
	
	private String word;
	
	private int frequency;

	public Word(String word) {
		this.word = word;
		frequency = 1;
	}
	
	public Word(String word, int frequency) {
		this.word = word;
		this.frequency = frequency;
	}
	
	public void printWord() {
		System.out.println(this.word + " : " + this.frequency);
	}

	@Override
	public int compareTo(Word o) {
		int value = o.frequency - this.frequency;
		if (value != 0) return value;
		value = this.word.compareTo(o.word);
		return value;
	}
	
	
}
