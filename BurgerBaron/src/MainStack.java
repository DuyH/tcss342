/*
 * Duy Huynh
 * TCSS 342
 * Assignment 1 - Burger Baron
 * Spring '15
 */

/**
 * A stack made from scratch, using linked-list.
 * 
 * @author Duy Huynh
 * @version 3 April 2015
 *
 * @param <T>
 *            Generic type.
 */
public class MainStack<T> {

    // fields:
    /** "Top" node in stack. */
    private Node top;

    /** Pointer indicating size. **/
    private int myPointer;

    /**
     * Determines if the stack is empty.
     * 
     * @return Whether stack is empty or not.
     */
    public boolean isEmpty() {
        return top == null;
    }

    public void push(T element) {
        if (top == null) {
            top = new Node(element, null);
        } else {
            top.next = top;
            top = new Node(element, top.next);
        }
        myPointer++;

    }

    public Object pop() {

        if (top == null) {
            throw new NullPointerException("Stack is empty dude!");
        }

        Node temp = top;

        myPointer--;
        return top.getData();

    }

    public Object peek() {
        return top.getData();

    }

    public int size() {
        return myPointer;
    }

    public String toString() {
        return "Top of stack to bottom: " + top.toString()
                + top.next.toString();
    }

    // inner node class for linked structure
    private static class Node<T> {

        // Fields:
        private T data;
        private Node next;

        // Constructor:
        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }

        private T getData() {
            return data;
        }

    }
}
