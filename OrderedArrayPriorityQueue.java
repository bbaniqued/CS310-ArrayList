/* 	
Program #1
Brandon Baniqued
cssc0886
*/

package data_structures;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class OrderedArrayPriorityQueue<E extends Comparable<E>> implements PriorityQueue<E> {

	private int maxSize, currentSize;
	private E[] storage;
	

	public OrderedArrayPriorityQueue(int max) {
		maxSize = max;
		currentSize = 0;
		storage = (E[]) new Comparable[maxSize];

	}

	public OrderedArrayPriorityQueue() {
		this(DEFAULT_MAX_CAPACITY);
	}

	// Inserts a new object into the priority queue. Returns true if
	// the insertion is successful. If the PQ is full, the insertion
	// is aborted, and the method returns false.
	public boolean insert(E object) {
		if (isFull())
			return false;
		int where = findInsertionPoint(object, 0, currentSize - 1);
		for (int i = currentSize - 1; i >= where; i--)
			storage[i + 1] = storage[i];
		storage[where] = object;
		currentSize++;
		return true;
	}

	// Removes the object of highest priority that has been in the
	// PQ the longest, and returns it. Returns null if the PQ is empty.
	public E remove() {
		if (isEmpty())
			return null;
		return storage[--currentSize];

	}

	// Deletes all instances of the parameter obj from the PQ if found, and
	// returns true. Returns false if no match to the parameter obj is found.
	public boolean delete(E obj) {
		int firstIndex = -1;
		int lastIndex = -1;
		int copyFrom = 0;
		
		for (int i = 0; i < currentSize; i++) {
			

			if (((Comparable<E>) obj).compareTo((E) storage[i]) == 0 && firstIndex == -1) {
				firstIndex = i;
			}
			

			if (((Comparable<E>) obj).compareTo((E) storage[i]) > 0 && firstIndex != -1) {
				lastIndex = i - 1;
				break;
			}

		}
		copyFrom = lastIndex + (lastIndex + 1) - firstIndex;
		for (int i = lastIndex; i >= firstIndex; i--) {

			storage[i] = storage[copyFrom--];

		}

		currentSize -= (lastIndex + 1) - firstIndex;
		return true;
		

	}

	// Returns the object of highest priority that has been in the
	// PQ the longest, but does NOT remove it.
	// Returns null if the PQ is empty.
	public E peek() {
		if (isEmpty())
			return null;
		return storage[currentSize - 1];
	}

	// Returns true if the priority queue contains the specified element
	// false otherwise.
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

	//Binary Search
	private int findInsertionPoint(E obj, int lo, int hi) {
		if (hi < lo)
			return lo;
		int mid = (lo + hi) >> 1;
		if (((Comparable<E>) obj).compareTo(storage[mid]) >= 0)
			return findInsertionPoint(obj, lo, mid - 1);
		return findInsertionPoint(obj, mid + 1, hi);
	}

}