package ir.assignments.two.c;

import ir.assignments.two.a.Frequency;
import ir.assignments.two.a.Utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Count the total number of 2-grams and their frequencies in a text file.
 */
public final class TwoGramFrequencyCounter {
	/**
	 * This class should not be instantiated.
	 */
	private TwoGramFrequencyCounter() {}
	
	/**
	 * Takes the input list of words and processes it, returning a list
	 * of {@link Frequency}s.
	 * 
	 * This method expects a list of lowercase alphanumeric strings.
	 * If the input list is null, an empty list is returned.
	 * 
	 * There is one frequency in the output list for every 
	 * unique 2-gram in the original list. The frequency of each 2-grams
	 * is equal to the number of times that two-gram occurs in the original list. 
	 * 
	 * The returned list is ordered by decreasing frequency, with tied 2-grams sorted
	 * alphabetically. 
	 * 
	 * 
	 * 
	 * Example:
	 * 
	 * Given the input list of strings 
	 * ["you", "think", "you", "know", "how", "you", "think"]
	 * 
	 * The output list of 2-gram frequencies should be 
	 * ["you think:2", "how you:1", "know how:1", "think you:1", "you know:1"]
	 *  
	 * @param words A list of words.
	 * @return A list of two gram frequencies, ordered by decreasing frequency.
	 */
	//Check whether the list contains a word or not
	public static boolean isContain(String current,List<Frequency> freqs)
	{
		for(int j = 0; j < freqs.size(); j++)
		{
			if(current.equals(freqs.get(j).getText()))
			{
				return true;
			}
		}
		return false;		
	}
	private static List<Frequency> computeTwoGramFrequencies(ArrayList<String> words) {
		// TODO Write body!
		List<Frequency> freqs = new ArrayList<Frequency>();
		String current;
		for(int i = 0; i < words.size()-1; i++)
		{
			current = words.get(i) +" "+words.get(i+1);
			if(current.length() > 0)
			{
				if(!isContain(current, freqs))
				{
					Frequency newWord = new Frequency(current);
					newWord.incrementFrequency();
					freqs.add(newWord);
				}
				else
				{
					for(int j = 0; j < freqs.size(); j++)
					{
						if(current.equals(freqs.get(j).getText()))
						{
							freqs.get(j).incrementFrequency();
						}
					}
				}
			}
		}
		
		Comparator<Frequency> byFreq = (e1, e2) -> e2.getFrequency() - e1.getFrequency();

		Comparator<Frequency> byAlphabet = (e1, e2) -> e1.getText().compareTo(e2.getText());
		
		Collections.sort(freqs, byFreq.thenComparing(byAlphabet));  
		return freqs;
	}
	
	/**
	 * Runs the 2-gram counter. The input should be the path to a text file.
	 * 
	 * @param args The first element should contain the path to a text file.
	 */
	public static void main(String[] args) {
		File file = new File(args[0]);
		ArrayList<String> words = Utilities.tokenizeFile(file);
		List<Frequency> frequencies = computeTwoGramFrequencies(words);
		System.out.println("Two Gram Frequency Counter");
		Utilities.printFrequencies(frequencies);
	}
}
