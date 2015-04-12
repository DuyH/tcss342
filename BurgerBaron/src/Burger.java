public class Burger {

    MyStack<Ingredient> topStack = new MyStack<>();
    MyStack<Ingredient> botStack = new MyStack<>();
    MyStack<Ingredient> finalStack = new MyStack<>();

    private int orderNumber;
    private int pattyAmount = 1;

    public Burger(boolean theWorks) {

        if (theWorks) {
            // Construct the Burger Baron.
            baronTopStack();
            baronBottomStack();

        } else {
            // Construct plain burger.
            botStack.push(Ingredient.BOTTOMBUN);
            botStack.push(Ingredient.BEEF);
            botStack.push(Ingredient.TOPBUN);
        }
    }

    public void changePatties(String pattyType) {
    }

    public void addPatty() {

        // while(pattyAmount)
        // pattyAmount++;
    }

    public void removePatty() {

    }

    public void addCategory(String type) {

        if (type == "Cheese") {

        } else if (type == "Sauce") {

        } else if (type == "Veggies") {

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
        combineStacks();

        final StringBuilder sb = new StringBuilder("[");

        sb.append(botStack.toString());

        sb.append("]");

        return sb.toString();
    }

    private void combineStacks() {

        // Burger will be on bottom stack now
        while (!topStack.isEmpty()) {
            botStack.push(topStack.pop());
        }
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
