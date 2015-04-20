public class Burger {

    MyStack<Ingredient> topStack = new MyStack<>();
    MyStack<Ingredient> botStack = new MyStack<>();
    MyStack<Ingredient> finalStack = new MyStack<>();

    private int orderNumber;
    private int pattyCount = 1;
    private int cheeseCount = 0;
    private String pattyType = Ingredient.BEEF.getName();

    public Burger(boolean theWorks) {

        if (theWorks) {
            // Construct the Burger Baron.
            cheeseCount = 3;
            baronTopStack();
            baronBottomStack();

        } else {
            // Construct plain burger.
            topStack.push(Ingredient.TOPBUN);
            botStack.push(Ingredient.BOTTOMBUN);
            botStack.push(Ingredient.BEEF);
        }
    }

    public void changePatties(String pattyType) {

        // Don't change patties if it's already of that type!
        if (!this.pattyType.equals(pattyType)) {
            final MyStack<Ingredient> tempStack = new MyStack<>();

            // change the first patty
            for (int i = 0; i < cheeseCount; i++) {
                tempStack.push(botStack.pop());
            }
            // Pop off old patty:
            botStack.pop();
            // Push new patty:
            botStack.push(Ingredient.getIngredient(pattyType));
            // Put back the cheeses
            for (int i = 0; i < cheeseCount; i++) {
                botStack.push(tempStack.pop());
            }

            // Pop off old patties, if any, from top stack
            for (int i = 0; i < pattyCount - 1; i++) {
                topStack.pop();
            }
            // Push new patties, if any, onto top stack
            for (int i = 0; i < pattyCount - 1; i++) {
                topStack.push(Ingredient.getIngredient(pattyType));
            }

            // Change patty type
            this.pattyType = pattyType;
        }
    }

    public void addPatty() {

        // Only add if pattyCount is 2 or less, bc max is 3
        // while(pattyAmount)
        // pattyAmount++;
        if (pattyCount < 3) {
            topStack.push(Ingredient.getIngredient(pattyType));
            pattyCount++;
        }

    }

    public void removePatty() {

        // Only able to remove if patty is 2 or more. min is 1 patty.
        if (pattyCount != 1) {
            topStack.pop();
            pattyCount--;
        }

    }

    public void addCategory(String type) {

        if ("Cheese".equals(type)) {
            for (int i = 0; i < cheeseCount; i++) {
                botStack.pop();
            }
            botStack.push(Ingredient.CHEDDAR);
            botStack.push(Ingredient.MOZZARELLA);
            botStack.push(Ingredient.PEPPERJACK);
            cheeseCount = 3;

        } else if ("Sauce".equals(type)) {

            // Add sauces
            final MyStack<Ingredient> tempStack = new MyStack<>();

            // Top stack sauces:
            topStack = flipStack(topStack);

            while (!topStack.isEmpty()) {
                tempStack.push(topStack.pop());
                if ("Sauce".equals(tempStack.peek().getCategory())) {
                    tempStack.pop();
                }
                if ("Bun".equals(tempStack.peek().getCategory())) {
                    tempStack.push(Ingredient.MAYO);
                    tempStack.push(Ingredient.BARONSAUCE);
                }
                System.out.println("Stuck");

            }
            topStack = tempStack;

            // Bottom stack sauces:
            botStack = flipStack(botStack);
            botStack.pop(); // Pop off bottom bun
            while ("Sauce".equals(botStack.peek().getCategory())) {
                botStack.pop();
            }
            botStack.push(Ingredient.MUSTARD);
            botStack.push(Ingredient.KETCHUP);
            botStack.push(Ingredient.BOTTOMBUN);
            botStack = flipStack(botStack);

        } else if ("Veggies".equals(type)) {

        }

    }

    public void removeCategory(String type) {

    }

    public void addIngredient(String type) {

    }

    public void removeIngredient(String type) {

        // Iterate through the
    }

    @Override
    public String toString() {

        // Combine the top and bottom parts of the burger

        final StringBuilder sb = new StringBuilder("[");

        // Flip the top stack and print it out, then flip again to restore top
        MyStack<Ingredient> temp = flipStack(topStack);
        sb.append(temp);
        topStack = flipStack(temp);
        sb.append(", ");
        sb.append(botStack.toString());

        sb.append("]");

        return sb.toString();
    }

    private MyStack<Ingredient> flipStack(MyStack<Ingredient> stackToFlip) {

        // Make a copy of the stack, but upside down

        MyStack<Ingredient> tempStack = new MyStack<>();
        while (!stackToFlip.isEmpty()) {
            tempStack.push(stackToFlip.pop());
        }
        return tempStack;
    }

    private void baronTopStack() {
        topStack.push(Ingredient.PICKLE);
        topStack.push(Ingredient.TOPBUN);
        topStack.push(Ingredient.MAYO);
        topStack.push(Ingredient.BARONSAUCE);
        topStack.push(Ingredient.LETTUCE);
        topStack.push(Ingredient.TOMATO);
        topStack.push(Ingredient.ONIONS);
    }

    private void baronBottomStack() {

        botStack.push(Ingredient.BOTTOMBUN);
        botStack.push(Ingredient.KETCHUP);
        botStack.push(Ingredient.MUSTARD);
        botStack.push(Ingredient.MUSHROOM);
        botStack.push(Ingredient.BEEF);
        botStack.push(Ingredient.CHEDDAR);
        botStack.push(Ingredient.MOZZARELLA);
        botStack.push(Ingredient.PEPPERJACK);

    }
}
