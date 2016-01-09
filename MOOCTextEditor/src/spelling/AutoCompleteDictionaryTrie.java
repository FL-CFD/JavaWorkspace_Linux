package spelling;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;

/** 
 * An trie data structure that implements the Dictionary and the AutoComplete ADT
 * @author You
 *
 */
public class AutoCompleteDictionaryTrie implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;
    

    public AutoCompleteDictionaryTrie()
	{
		root = new TrieNode();
	}
	
	
	/** Insert a word into the trie.
	 * For the basic part of the assignment (part 2), you should ignore the word's case.
	 * That is, you should convert the string to all lower case as you insert it. */
	public boolean addWord(String word)
	{
	    //TODO: Implement this method.
		if(!word.equals("") && word!=null && !isWord(word)){
			String lowcase = word.toLowerCase();
			char[] charArray = lowcase.toCharArray();
			TrieNode curr = root;
			
			for(int i=0; i<charArray.length; i++){
				if(curr.getChild(charArray[i])!=null){
					curr = curr.getChild(charArray[i]);
				}else{
					curr.insert(charArray[i]);
					curr = curr.getChild(charArray[i]);
				}
			}
			curr.setEndsWord(true);
		    return true;
		}else{
			return false;
		}
	}
	
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
	    //TODO: Implement this method
	    return root.sizeChild();
	}


	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
	    // TODO: Implement this method
		TrieNode curr = root;
		
		if(!s.equals("") && s!=null){
			String lowcase = s.toLowerCase();
			char[] charArray = lowcase.toCharArray();
			TrieNode next = null;
			for(char c:charArray){
				next = curr.getChild(c);
				if(next == null) return false;
				curr = next;
			}
			
			if(curr.endsWord()) return true;
		}
		return false;
	}

	/** 
	 *  * Returns up to the n "best" predictions, including the word itself,
     * in terms of length
     * If this string is not in the trie, it returns null.
     * @param text The text to use at the word stem
     * @param n The maximum number of predictions desired.
     * @return A list containing the up to n best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	 // TODO: Implement this method
    	 // This method should implement the following algorithm:
    	 // 1. Find the stem in the trie.  If the stem does not appear in the trie, return an
    	 //    empty list
    	 // 2. Once the stem is found, perform a breadth first search to generate completions
    	 //    using the following algorithm:
    	 //    Create a queue (LinkedList) and add the node that completes the stem to the back
    	 //       of the list.
    	 //    Create a list of completions to return (initially empty)
    	 //    While the queue is not empty and you don't have enough completions:
    	 //       remove the first Node from the queue
    	 //       If it is a word, add it to the completions list
    	 //       Add all of its child nodes to the back of the queue
    	 // Return the list of completions
 		TrieNode curr = root;
 		TrieNode next = null;
 		List<String> toReturn = new LinkedList<String>();
 		Character c = null;
 		for (Character cWithCase : prefix.toCharArray())
 		{
 			c = Character.toLowerCase(cWithCase);
 			next = curr.getChild(c);
 			if (next == null)
 			{
 				return toReturn;
 			}
 			curr = next;
 		}
 		// Now build the list of predictions
 		LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
 		queue.add(curr);
 		int n = 0;
 		while (!queue.isEmpty() && n < numCompletions)
 		{
 			next = queue.removeFirst();
 			if (next.endsWord()) {
 				toReturn.add(next.getText());
 				n++;
 			}
 			for (Character cnext : next.getValidNextCharacters()) 
 			{
 				queue.addLast(next.getChild(cnext));
 			}
 		}
 		return toReturn; 

     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}