/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 1 - Burger Baron
 * MyStack.java
 * 
 */

/**
 * A stack made from scratch, using linked-list.
 * 
 * @author Duy Huynh
 * @version 3 April 2015
 *
 * @param <T> Generic type.
 */
public class MyStack<T> {

    // fields:
    /** "Top" node in stack. */
    private Node top;

    /** Pointer indicating size. **/
    private int myPointer;

    /**Create a Stack.  */
    public MyStack() {
        top = null;
        myPointer = 0;
    }

    /**
     * Determines if the stack is empty.
     * 
     * @return Whether stack is empty or not.
     */
    public boolean isEmpty() {
        return top == null;
    }

    /**
     * Push element onto the stack.
     * 
     * @param element
     *            Element to push onto the stack.
     */
    public void push(final T element) {

        if (top == null) {
            top = new Node(element, null);
        } else {
            top = new Node(element, top);
        }
        myPointer++;

    }

    /**
     * Pops off the top element of the stack, removing it in the process.
     * 
     * @return Returns the top element of the stack.
     */
    public T pop() {
        
        T temp;
        if (top == null) {
            throw new NullPointerException("Nothing to pop off stack!");
        } else {
            temp = (T) top.getElement();
            top = top.myNextNode;
            myPointer--;
        }
        return temp;

    }

    /**
     * Peek at the first element of the stack; does not remove from stack.
     * 
     * @return Returns the top element of the stack.
     */
    public T peek() {
        return (T) top.getElement();
    }

    /**
     * Size of the stack.
     * 
     * @return The number of elements in the stack.
     */
    public int size() {
        return myPointer;
    }

    /**
     * String representation of this stack.
     * 
     * @return String representation of this stack.
     */
    public String toString() {

        final StringBuilder sb = new StringBuilder();

        Node temp = top;

        while (temp != null) {
            sb.append(temp.toString());
            if (temp.myNextNode != null) {
                sb.append(", ");
            }
            temp = temp.myNextNode;
        }

        return sb.toString();
    }

    /**
     * Inner node class for linked structure.
     * 
     * @author Duy Huynh
     */
    private final class Node {

        // Fields:
        /**An element of the stack.*/
        private T myElement;
        
        /**The next node in the list.*/
        private Node myNextNode;

        // Constructor:
        /**
         * A node in the linked data structure.
         * 
         * @param theElement The node in the linked list.
         * @param theNextNode The next node in the list.
         */
        private Node(final T theElement, final Node theNextNode) {
            this.myElement = theElement;
            this.myNextNode = theNextNode;
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
