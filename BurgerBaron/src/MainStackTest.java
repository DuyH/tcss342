public class MainStackTest {

    public static <T> void main(String[] args) {
        MainStack<Integer> myStack = new MainStack<Integer>();

        Integer myInt = new Integer(11);
        System.out.println(myInt);

        myStack.push(10);
        myStack.push(11);
        myStack.push(12);
        myStack.push(13);
        System.out.println(myStack.isEmpty());
        System.out.println(myStack.toString());

        System.out.println(myStack.size());
        System.out.println(myStack.peek());

    }
}
