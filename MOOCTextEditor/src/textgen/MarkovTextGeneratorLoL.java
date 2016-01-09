package textgen;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.Set;

import org.w3c.dom.NodeList;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The random number generator
	private Random rnGenerator;
	
	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}
	

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		
		// TODO: Implement this method
		String splitOn = "[\\s]+";
		String[] sourceArray = sourceText.split(splitOn);
		
		String prevWord = starter;

		for(int i=0; i<sourceArray.length; i++){
			
			String current = sourceArray[i];
			ListNode prevNode = findNode(prevWord);
			
			if(prevNode == null){
				
				ListNode pn = new ListNode(prevWord);
				wordList.add(pn);
				pn.addNextWord(current);
			}else{
				prevNode.addNextWord(current);
			}
			
			prevWord = current;
		}
		
		
		// last treatment
		prevWord = sourceArray[sourceArray.length-1];
		String current = sourceArray[0];
		
		ListNode prevNode = findNode(prevWord);
		
		if(prevNode == null){
			
			ListNode pn = new ListNode(prevWord);
			wordList.add(pn);
			pn.addNextWord(current);
		}else{
			prevNode.addNextWord(current);
		}
		

		/*
		String splitOn = "[\\s]+";
		String[] words = sourceText.split(splitOn);
		String prev = starter;
		for (String w : words) 
		{
			ListNode toModify = null;
			// Find the previous word is in the list
			ListIterator<ListNode> it = wordList.listIterator();
			while (it.hasNext())
			{
				ListNode n = it.next();
				if (n.getWord().equals(prev)) {
					toModify = n;
					break;
				}
			}
			if (toModify == null) 
			{
				toModify = new ListNode(prev);
				wordList.add(toModify);
			}
			
			toModify.addNextWord(w);
			
			prev = w;
		}
		
		*/
	}
	
	
	
	private ListNode findNode(String key)
	{
		for (ListNode n : wordList)
		{
			if (n.getWord().equals(key)) {
				return n;
			}
		}
		return null;
	}
	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords) {
		/*
		if (wordList.size() == 0) {
			// printing currently messes up the grader - working to resolve.
			//System.out.println("Warning: attempt to generate text on untrained generator.  No text generated");
			return "";
		}
		String theText = "";
		String current = starter;
		for (int i = 0; i < numWords; i++)
		{
			ListNode currNode = findNode(current);
			if (currNode == null) {
				currNode = findNode(starter);
			}
			String nextWord = currNode.getRandomNextWord(rnGenerator);
			theText += nextWord + " ";
			current = nextWord;
		}
		return theText;
		*/
		
	    // TODO: Implement this method
		
		if(wordList.size()==0){
			return "";
		}
		
		ArrayList<String> output = new ArrayList<String>();
		String currWord = starter;
		
		for(int j=0; j<numWords; j++){
			
			int index = 0;
			for(int i=0; i<wordList.size(); i++){
				if(wordList.get(i).getWord().equals(currWord)){
					index = i;
					break;
				}
			}
			String next = wordList.get(index).getRandomNextWord(rnGenerator);
			output.add(next);
			currWord = next;
		}
		
		if(output.size()==0){
			return "";
		}else{
			String out = output.get(0);
			for (int j=1; j<output.size(); j++){
				out = out + " " + output.get(j);
			}
			return out;
		}
		
	}
	
	
	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		// TODO: Implement this method.
		wordList = new LinkedList<ListNode>();
		train(sourceText);
		
	}
	
	// TODO: Add any private helper methods you need here.
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		
		String textString = "Hi there hi Leo";
		
		System.out.println(textString);
		
		
		gen.train(textString);
		System.out.println(gen);
		
		
		
		
		
		/*
		System.out.println(gen.generateText(20));
		
		
		
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		*/
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;
	
	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}
	
	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}
	
	public String getRandomNextWord(Random generator)
	{
		// TODO: Implement this method
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		int index = generator.nextInt(nextWords.size());
		return nextWords.get(index);
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}
	
}


