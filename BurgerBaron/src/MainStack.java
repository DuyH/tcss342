/*
 * Duy Huynh
 * TCSS 342
 * Assignment 1 - Burger Baron
 * Spring '15
 */

public class MainStack<T> {

    // fields:
    private Node top;
    private int pointer = 0;

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
        pointer++;

    }

    public Object pop() {

        if (top == null) {
            throw new NullPointerException("Stack is empty dude!");
        }

        Node temp = top;

        pointer--;
        return top.getData();

    }

    public Object peek() {
        return top.getData();

    }

    public int size() {
        return pointer;
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
