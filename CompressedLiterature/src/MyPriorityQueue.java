/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 3 - Compressed Literature
 * MyPriorityQueue.java
 * 
 * NOTE: This doesn't quite work as it should be, so I don't use it in CodingTree.
 * I left this here so I may work on it in the future.
 * 
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyPriorityQueue<T extends Comparable> {

	private T[] heapAsArray;

	private int size;

	@SuppressWarnings("unchecked")
	public MyPriorityQueue() {
		heapAsArray = (T[]) new Comparable[10];
		size = 0;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public void offer(T newItem) {

		// If array is too small, create new array of double size and copy over
		if (size + 1 >= heapAsArray.length) {
			heapAsArray = Arrays.copyOf(heapAsArray, heapAsArray.length * 2);
		}

		// Add the array + increase size
		heapAsArray[size] = newItem;
		size++;

		// Fix the array by bubbling up if small item if needed
		int parent = (int) Math.floor((size - 1) / 2);
		boolean noSwaps = false;
		while (!noSwaps && parent != 0) {
			if (heapAsArray[size] != null
					&& heapAsArray[size].compareTo(heapAsArray[parent]) < 0) {
				// Swap
				T temp = heapAsArray[size];
				heapAsArray[size] = heapAsArray[parent];
				heapAsArray[parent] = temp;
				noSwaps = false;
				parent = (int) Math.floor((parent - 1) / 2);
			} else {
				noSwaps = true;
			}
		}

	}

	public T poll() {

		if (size == 0) {
			return null;
		}
		if (size == 1) {
			T itemToReturn = heapAsArray[0];
			heapAsArray[0] = null;
			return itemToReturn;
		}

		// Return the top element
		T itemToReturn = heapAsArray[0];

		// Put the last item in heap as root
		heapAsArray[0] = heapAsArray[size];

		// Bubble down
		int index = size;
		int leftChild = 2 * index + 1;
		int rightChild = 2 * index + 2;

		if (!(leftChild > size) || (!(rightChild > size))) {
			while (heapAsArray[leftChild] != null
					&& heapAsArray[rightChild] != null) {

				// If left child smaller than right OR there is no right, check
				// element with left child
				if (heapAsArray[leftChild].compareTo(heapAsArray[rightChild]) < 0
						|| heapAsArray[rightChild] == null) {
					// Left child is smaller, so check for swap
					if (heapAsArray[leftChild] != null) {
						if (heapAsArray[leftChild]
								.compareTo(heapAsArray[index]) < 0) {
							T temp = heapAsArray[index];
							heapAsArray[index] = heapAsArray[leftChild];
							heapAsArray[leftChild] = temp;
							index = leftChild;
							leftChild = 2 * index + 1;
							rightChild = 2 * index + 2;
						}

					}
				} else if (heapAsArray[index]
						.compareTo(heapAsArray[index * 2 + 2]) < 0) {
					T temp = heapAsArray[index];
					heapAsArray[index] = heapAsArray[rightChild];
					heapAsArray[rightChild] = temp;
					index = rightChild;
					leftChild = 2 * index + 1;
					rightChild = 2 * index + 2;
				} else {
					break;
				}
			}
		}

		return itemToReturn;
	}

	public final class Node {

		// Fields:
		/** An element of the queue. */
		private T myElement;

		// Constructor:
		/**
		 * A node in the linked data structure.
		 * 
		 * @param theElement
		 *            The node in the linked list.
		 * @param theNextNode
		 *            The next node in the list.
		 */
		private Node(final T theElement, final Node theNextNode) {
			this.myElement = theElement;
		}

		/**
		 * Return the element.
		 * 
		 * @return The element to return.
		 */
		private T getElement() {
			return myElement;
		}

		@Override
		public String toString() {
			return myElement.toString();
		}

	}
}
