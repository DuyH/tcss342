public class Burger {

    MyStack<Ingredient> topStack = new MyStack<>();
    MyStack<Ingredient> botStack = new MyStack<>();
    MyStack<Ingredient> finalStack = new MyStack<>();

    private int orderNumber;
    private int pattyAmount = 1;

    public Burger(boolean theWorks) {

        if (theWorks) {
            // Construct the Burger Baron

            // (Maybe just create a stack that already has these ingredients)

            // TOP STACK:
            // add pickle
            // add top bun
            // add Mayo
            // add Baron
            // add lettuce
            // add tomato
            // add onion
            // (add extra patties)

            // BOTTOM STACK:
            // add bottom bun
            // add ketchup
            // add mustard
            // add mushroom
            // add patty
            // add cheddar
            // add mozza
            // add pepperjack

        } else {
            // Construct bun+patty only

            // Top stack:
            // add bun
            // add patty?

            // Bottom stack:
            // add bun
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

    public String toString() {

        // Combine the top and bottom parts of the burger
        combineStacks();

        final StringBuilder sb = new StringBuilder("[");

        sb.append(botStack.toString());

        sb.append("]");

        return sb.toString();
    }

    private void combineStacks() {
        while (topStack != null) {
            botStack.push(topStack.pop());
        }
    }

    private void makeTopPortion() {
        topStack.push(Ingredient.PICKLE);
        topStack.push(Ingredient.TOPBUN);
        topStack.push(Ingredient.MAYO);
        topStack.push(Ingredient.BARONSAUCE);
        topStack.push(Ingredient.LETTUCE);
        topStack.push(Ingredient.TOMATO);
        topStack.push(Ingredient.ONIONS);
    }

    private void makeBottomPortion() {

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
