public class MainStackTest {

    public static <T> void main(String[] args) {
        MainStack<Integer> myStack = new MainStack<Integer>();

        System.out.println("Is stack empty:" + myStack.isEmpty());

        // Push 10
        System.out.print("Pushed: ");
        myStack.push(10);
        System.out.println(myStack.peek());

        // Push 11
        System.out.print("Pushed: ");
        myStack.push(11);
        System.out.println(myStack.peek());

        // Push 12
        System.out.print("Pushed: ");
        myStack.push(12);
        System.out.println(myStack.peek());

        // Push 13
        System.out.print("Pushed: ");
        myStack.push(13);
        System.out.println(myStack.peek());

        System.out.println("Is stack empty: " + myStack.isEmpty());

        // System.out.println(myStack.toString());

        // Pop off 13
        System.out.println("Stack size: " + myStack.size());
        System.out.println("Popped: " + myStack.pop());
        System.out.println("Peeked: " + myStack.peek());

        // Pop off 12
        System.out.println("Stack size: " + myStack.size());
        System.out.println("Popped: " + myStack.pop());
        System.out.println("Peeked: " + myStack.peek());

        // Pop off 11
        System.out.println("Stack size: " + myStack.size());
        System.out.println("Popped: " + myStack.pop());
        System.out.println("Peeked: " + myStack.peek());

        // Pop off 10
        System.out.println("Stack size: " + myStack.size());
        System.out.println("Popped: " + myStack.pop());
        System.out.println("Stack size: " + myStack.size());

    }
}
