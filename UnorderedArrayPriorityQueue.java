/* 	
Program #1
Brandon Baniqued
cssc0886
*/

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class UnorderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {
	
	private int currentSize, maxSize;
	private E[] storage;
	
	public UnorderedArrayPriorityQueue(int size) {
		currentSize = 0;
		maxSize = size;
		storage = (E[]) new Comparable[maxSize];
	}

	public UnorderedArrayPriorityQueue() {
		this(DEFAULT_MAX_CAPACITY);
	}

	// Inserts a new object into the priority queue. Returns true if
	// the insertion is successful. If the PQ is full, the insertion
	// is aborted, and the method returns false.
	public boolean insert(E object) {
		if (isFull())
			return false;
		storage[currentSize++] = object;
		return true;
	}

	// Removes the object of highest priority that has been in the
	// PQ the longest, and returns it. Returns null if the PQ is empty.
	public E remove() {
		if (isEmpty())
			return null;

		int priorityIndex = 0;
		for (int i = 1; i < currentSize; i++) {
			if (storage[i].compareTo(storage[priorityIndex]) < 0)
				priorityIndex = i;
		}
		E result = storage[priorityIndex];

		while (priorityIndex < currentSize - 1) {

			storage[priorityIndex] = storage[priorityIndex + 1];
			priorityIndex++;

		}
		currentSize--;

		return (E) result;

	}

	// Deletes all instances of the parameter obj from the PQ if found, and
	// returns true. Returns false if no match to the parameter obj is found.
	public boolean delete(E obj) {
		int copyFrom = 0;
		int count = 0;
		for (int i = 0; i < currentSize; i++) {
			if (storage[i].compareTo(obj) == 0) {
				storage[i] = storage[copyFrom];
				count++;
				while (storage[copyFrom++].compareTo(obj) == 0) {
				}
			}
		}
		currentSize -= count;
		return true;
	}

	// Returns the object of highest priority that has been in the
	// PQ the longest, but does NOT remove it.
	// Returns null if the PQ is empty.
	public E peek() {
		if (isEmpty())
			return null;

		int priorityIndex = 0;

		for (int i = 1; i < currentSize; i++) {

			if (storage[i].compareTo(storage[priorityIndex]) < 0) {
				priorityIndex = i;
			}
		}
		return storage[priorityIndex];
	}

	// Returns true if an item that matches the parameter E object
	// is in the PQ, false otherwise. This method uses the Comparable
	// interface, and does not use the equals() method.
	public boolean contains(E object) {
		for (int i = 0; i < currentSize; i++)
			if (((Comparable<E>) object).compareTo(((E) storage[i])) == 0)
				return true;
		return false;
	}

	// Returns the number of objects currently in the PQ.
	public int size() {
		return currentSize;
	}

	// Returns the PQ to an empty state.
	public void clear() {
		currentSize = 0;
	}

	// Returns true if the PQ is empty, otherwise false
	public boolean isEmpty() {
		return currentSize == 0;
	}

	// Returns true if the PQ is full, otherwise false. List based
	// implementations should always return false.
	public boolean isFull() {
		return currentSize == maxSize;
	}

	// Returns an iterator of the objects in the PQ, in no particular
	// order.
	public Iterator<E> iterator() {

		return new Iterator<E>() {
			private int index = 0;

			public boolean hasNext() {
				return index < currentSize;
			}

			public E next() {
				if (isEmpty())
					throw new NoSuchElementException();
				return storage[index++];
			}

			public void remove() {
				throw new UnsupportedOperationException();
			}
		};
	}

}