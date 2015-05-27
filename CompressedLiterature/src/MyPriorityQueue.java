import java.util.ArrayList;
import java.util.List;

public class MyPriorityQueue<T> {

    private List<T> heapAsArray;

    private int size;

    public MyPriorityQueue() {
        heapAsArray = new ArrayList<T>();
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void offer(T newItem) {

        if (heapAsArray.isEmpty()) {
            heapAsArray.add(newItem);
            size++;
            return;
        } else {
            heapAsArray.add(newItem);

        }

    }

    private void bubbleUp(T item) {

        int parent = (heapAsArray.indexOf(item) - 1) / 2;
        if (item < heapAsArray.get(parent)) {

        }

    }

    public T poll() {

    }

    private final class Node {

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
