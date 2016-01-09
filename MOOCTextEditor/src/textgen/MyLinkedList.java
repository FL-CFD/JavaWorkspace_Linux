package textgen;

import java.util.AbstractList;


/** A class that implements a doubly linked list
 * 
 * @author UC San Diego Intermediate Programming MOOC team
 *
 * @param <E> The type of the elements stored in the list
 */
public class MyLinkedList<E> extends AbstractList<E> {
	LLNode<E> head;
	LLNode<E> tail;
	int size;

	public static void main(String [] args) {
		MyLinkedList<String> shortList = new MyLinkedList<String>();
		MyLinkedList<Integer> emptyList = new MyLinkedList<Integer>();
		MyLinkedList<Integer> longerList = new MyLinkedList<Integer>();
		MyLinkedList<Integer> list1 = new MyLinkedList<Integer>();
		
		shortList.add("A");
		shortList.add("B");
		shortList.add("C");
		
		list1.add(65);
		list1.add(21);
		list1.add(42);
		
		list1.add(3,1007);
		
		System.out.println(list1.get(0));
		System.out.println(list1.get(1));
		System.out.println(list1.get(2));
		System.out.println(list1.get(3));

	}
	
	/** Create a new empty LinkedList */
	public MyLinkedList() {
		// TODO: Implement this method
		head = new LLNode<E>(null);
		tail = new LLNode<E>(null);
		head.next = tail;
		tail.prev = head;
		size = 0;
	}

	/**
	 * Appends an element to the end of the list
	 * @param element The element to add
	 */
	public boolean add(E element ) 
	{
		if(element == null){
			throw new NullPointerException();
		}
		LLNode<E> newnode = new LLNode<E>(element);
		newnode.next = tail;
		newnode.prev = tail.prev;
		newnode.next.prev = newnode;
		newnode.prev.next = newnode;
		size++;
		return true;
	}

	/** Get the element at position index 
	 * @throws IndexOutOfBoundsException if the index is out of bounds. */
	public E get(int index)
	{
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> target = head.next;
		for(int i=0; i<index; i++){
			target = target.next;
		}
		return target.data;
	}

	/**
	 * Add an element to the list at the specified index
	 * @param The index where the element should be added
	 * @param element The element to add
	 */
	public void add(int index, E element ) 
	{
		// TODO: Implement this method

		
		if(index < 0 || index > size){
			throw new IndexOutOfBoundsException();
		}
		if(element == null){
			throw new NullPointerException();
		}
		LLNode<E> n = new LLNode<E>(element);
		LLNode<E> target = head.next;
		for(int i=0; i<index; i++){
			target = target.next;
		}
		
		n.next = target;
		n.prev = target.prev;
		n.next.prev = n;
		n.prev.next = n;
		size++;

	}


	/** Return the size of the list */
	public int size() 
	{
		// TODO: Implement this method
		return size;
	}

	/** Remove a node at the specified index and return its data element.
	 * @param index The index of the element to remove
	 * @return The data element removed
	 * @throws IndexOutOfBoundsException If index is outside the bounds of the list
	 * 
	 */
	public E remove(int index) 
	{
		// TODO: Implement this method
		
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException();
		}
		LLNode<E> target = head.next;
		for(int i=0; i<index; i++){
			target = target.next;
		}
		
		target.prev.next = target.next;
		target.next.prev = target.prev;
		target.next = null;
		target.prev = null;
		
		size--;
		
		return target.data;
	}

	/**
	 * Set an index position in the list to a new element
	 * @param index The index of the element to change
	 * @param element The new element
	 * @return The element that was replaced
	 * @throws IndexOutOfBoundsException if the index is out of bounds.
	 */
	public E set(int index, E element) 
	{
		// TODO: Implement this method
		if(index < 0 || index > size-1){
			throw new IndexOutOfBoundsException();
		}
		if(element == null){
			throw new NullPointerException();
		}
		
		LLNode<E> target = head.next;
		for(int i=0; i<index; i++){
			target = target.next;
		}
		
		E old = target.data;
		target.data = element;
		
		return old;
	}
	
}

class LLNode<E> 
{
	LLNode<E> prev;
	LLNode<E> next;
	E data;

	// TODO: Add any other methods you think are useful here
	// E.g. you might want to add another constructor

	public LLNode(E e) 
	{
		this.data = e;
		this.prev = null;
		this.next = null;
	}

}
