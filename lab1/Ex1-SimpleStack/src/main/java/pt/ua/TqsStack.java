package pt.ua;

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class TqsStack<T> implements Stack<T> {

	private LinkedList<T> collection;
	private int boundary;

	public TqsStack() {
		this.collection = new LinkedList<>();
	}

	public TqsStack(int boundary) {
		this.collection = new LinkedList<>();
		this.boundary = boundary;
	}

	@Override
	public T pop(){
		if(isEmpty())
			throw new NoSuchElementException();

		return collection.removeLast();
	}

	@Override
	public int size(){
		return collection.size();
	}

	@Override
	public T peek(){
		if(isEmpty())
			throw new NoSuchElementException();

		return collection.getLast();
	}

	@Override
	public void push(T t){
		 collection.add(t);
	}

	@Override
	public void push(T t, int boundary){
		if(size() == boundary)
			throw new IllegalStateException();
		else
			collection.add(t);
	}

	@Override
	public boolean isEmpty(){
		return collection.isEmpty();
	}
}
