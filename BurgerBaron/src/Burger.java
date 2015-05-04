/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 1 - Burger Baron
 * Burger.java
 * 
 */

/**
 * Represents a burger.
 * 
 * @author Duy Huynh
 * @version 6 April 2015
 *
 */
public class Burger {

    /** Maximum number of patties. */
    public static final int PATTY_MAX = 3;
    
    /** Minimum number of patties. */
    public static final int PATTY_MIN = 1;

    /** Maximum cheese count. */
    public static final int CHEESE_MAX = 3;
    
    /** Top stack starting weight. */
    public static final int TOP_START = 7;

    /** The top stack of the burger. */
    private MyStack<Ingredient> topStack = new MyStack<>();

    /** The bottom stack of the burger. */
    private MyStack<Ingredient> botStack = new MyStack<>();

    /** Keep track of the number of patties. */
    private int pattyCount = 1;

    /** Keep track of the number of cheeses. */
    private int cheeseCount = 0;

    /** Keep track of the patty type. */
    private String pattyType = Ingredient.BEEF.getName();

    /**
     * Create a burger.
     * 
     * @param theWorks Whether or not this burger is a Baron Burger.
     */
    public Burger(final boolean theWorks) {

        if (theWorks) {
            // Construct the Burger Baron.
            cheeseCount = CHEESE_MAX;
            baronTopStack();
            baronBottomStack();

        } else {
            // Construct plain burger.
            topStack.push(Ingredient.TOPBUN);
            botStack.push(Ingredient.BOTTOMBUN);
            botStack.push(Ingredient.BEEF);
        }
    }

    /**
     * Change the burger's patty type.
     * 
     * @param pattyType The patty type.
     */
    public void changePatties(final String pattyType) {

        // Don't change patties if it's already of that type!
        if (!this.pattyType.equals(pattyType)) {
            final MyStack<Ingredient> tempStack = new MyStack<>();

            // Change the patty that is underneath the cheeses

            // 1. pop off and store the cheeses:
            for (int i = 0; i < cheeseCount; i++) {
                tempStack.push(botStack.pop());
            }

            // 2. Dispose the old patty
            botStack.pop();

            // 3. Push new patty:
            botStack.push(Ingredient.getIngredient(pattyType));
            
            // 4. Put back the cheeses
            for (int i = 0; i < cheeseCount; i++) {
                botStack.push(tempStack.pop());
            }

            // Work on the patties of the other stack
            
            // 5. Pop off old patties, if any, from top stack
            for (int i = 0; i < pattyCount - 1; i++) {
                topStack.pop();
            }
            
            // 6. Push new patties, if any, onto top stack
            for (int i = 0; i < pattyCount - 1; i++) {
                topStack.push(Ingredient.getIngredient(pattyType));
            }

            // 7. Change patty type
            this.pattyType = pattyType;
        }
    }

    /** Add a patty to the burger. */
    public void addPatty() {

        // Only add if pattyCount is 2 or less, bc max is 3
        if (pattyCount < PATTY_MAX) {
            topStack.push(Ingredient.getIngredient(pattyType));
            pattyCount++;
        }

    }

    /**  Remove a patty from the burger. */
    public void removePatty() {

        // Only able to remove if patty is 2 or more. min is 1 patty.
        if (pattyCount != PATTY_MIN) {
            topStack.pop();
            pattyCount--;
        }

    }

    /**
     * Add category of ingredients to burger.
     * 
     * @param type Name of category.
     */
    public void addCategory(final String type) {

        if ("Cheese".equals(type)) {
            for (int i = 0; i < cheeseCount; i++) {
                botStack.pop();
            }
            botStack.push(Ingredient.CHEDDAR);
            botStack.push(Ingredient.MOZZARELLA);
            botStack.push(Ingredient.PEPPERJACK);
            cheeseCount = CHEESE_MAX;

        } else if ("Sauce".equals(type)) {

            // Add sauces
            final MyStack<Ingredient> tempStack = new MyStack<>();

            // Top stack sauces:
            topStack = flipStack(topStack);

            // Go through upside-down top stack, removing sauces and adding them
            while (!topStack.isEmpty()) {
                tempStack.push(topStack.pop());
                if ("Sauce".equals(tempStack.peek().getCategory())) {
                    tempStack.pop();
                }
                if ("Bun".equals(tempStack.peek().getCategory())) {
                    tempStack.push(Ingredient.MAYO);
                    tempStack.push(Ingredient.BARONSAUCE);
                }

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

            final MyStack<Ingredient> tempStack = new MyStack<>();

            // Top Stack veggies:
            for (int i = 0; i < pattyCount - 1; i++) {
                tempStack.push(topStack.pop());
            }
            // Remove exisiting veggies:
            while ("Veggies".equals(topStack.peek().getCategory())) {
                topStack.pop();
            }
            // Add all top stack veggies:
            topStack.push(Ingredient.LETTUCE);
            topStack.push(Ingredient.TOMATO);
            topStack.push(Ingredient.ONIONS);

            // Put top stack patties back on:
            for (int i = 0; i < pattyCount - 1; i++) {
                topStack.push(tempStack.pop());
            }
            // Add a pickle
            topStack = flipStack(topStack);
            if (!"Pickle".equals(topStack.peek().getName())) {
                topStack.push(Ingredient.PICKLE);
            }
            topStack = flipStack(topStack);

            // Bottom Stack veggies (mushroom only)
            for (int i = 0; i < cheeseCount + 1; i++) { // +1 mandatory patty
                tempStack.push(botStack.pop());
            }
            if (!"Mushrooms".equals(botStack.peek().getName())) {
                botStack.push(Ingredient.MUSHROOM);
            }
            for (int i = 0; i < cheeseCount + 1; i++) {
                botStack.push(tempStack.pop());
            }

        }

    }

    /**
     * Removes category of ingredients from burger.
     * 
     * @param type Name of category.
     */
    public void removeCategory(final String type) {
        
        // Remove all cheeses
        if ("Cheese".equals(type)) {
            while ("Cheese".equals(botStack.peek().getCategory())) {
                botStack.pop();
            }
            cheeseCount = 0;

            // Remove all veggies
        } else if ("Veggies".equals(type)) {

            // Remove top stack veggies
            for (int i = 0; i < pattyCount - 1; i++) {
                topStack.pop();
            }
            while ("Veggies".equals(topStack.peek().getCategory())) {
                topStack.pop();
            }
            for (int i = 0; i < pattyCount - 1; i++) {
                topStack.push(Ingredient.getIngredient(pattyType));
            }
            // Remove stupid pickle:
            topStack = flipStack(topStack);
            if ("Pickle".equals(topStack.peek().getName())) {
                topStack.pop();
            }
            topStack = flipStack(topStack);

            // Remove the lonely mushroom if present:
            final MyStack<Ingredient> tempStack = new MyStack<>();
            for (int i = 0; i < cheeseCount + 1; i++) {
                tempStack.push(botStack.pop());
            }
            if ("Mushrooms".equals(botStack.peek().getName())) {
                botStack.pop();
            }
            for (int i = 0; i < cheeseCount + 1; i++) {
                botStack.push(tempStack.pop());
            }

            // Remove all sauces
        } else if ("Sauce".equals(type)) {

            // Remove top stack sauces:
            final MyStack<Ingredient> tempStack = new MyStack<>();

            while (!topStack.isEmpty()) {
                tempStack.push(topStack.pop());
                if ("Sauce".equals(tempStack.peek().getCategory())) {
                    tempStack.pop();
                }
            }
            topStack = flipStack(tempStack);

            // removing bottom stack sauces:
            botStack = flipStack(botStack);
            botStack.pop(); // pop off bun
            for (int i = 0; i < 2; i++) {
                if ("Sauce".equals(botStack.peek().getCategory())) {
                    botStack.pop();
                }
            }
            botStack.push(Ingredient.BOTTOMBUN);
            botStack = flipStack(botStack);

        }
    }

    /**
     * Add ingredient to burger.
     * 
     * @param type Name of ingredient.
     */
    public void addIngredient(final String type) {

        // Update cheese count if cheese is ingredient to be added:
        if ("Cheese".equals(Ingredient.getIngredient(type))) {
            cheeseCount++;
        }
        // Special case: adding stupid Pickle:
        if ("Pickle".equals(type)) {
            topStack = flipStack(topStack);
            if (!"Pickle".equals(topStack.peek().getName())) {
                topStack.push(Ingredient.PICKLE);
            }
            topStack = flipStack(topStack);
        } else {
            final MyStack<Ingredient> tempStack = new MyStack<>();
            final int weight = Ingredient.getIngredient(type).getWeight();

            // Combine the stacks
            int count = topStack.size();
            if (weight > Ingredient.BEEF.getWeight()) {
                count++;
            }
            while (!topStack.isEmpty()) {
                botStack.push(topStack.pop());
            }

            while (botStack.peek().getWeight() >= weight) {
                if (botStack.peek().getWeight() == weight) {
                    break;
                }

                tempStack.push(botStack.pop());
                if (weight < tempStack.peek().getWeight()
                        && weight > botStack.peek().getWeight()) {
                    tempStack.push(Ingredient.getIngredient(type));
                    break;
                }
            }
            while (!tempStack.isEmpty()) {
                botStack.push(tempStack.pop());
            }

            for (int i = 0; i < count; i++) {
                topStack.push(botStack.pop());
            }

        }

    }

    /**
     * Remove ingredient from burger.
     * 
     * @param type  Name of ingredient.
     */
    public void removeIngredient(final String type) {

        // Update cheese count:
        if ("Cheese".equals(Ingredient.getIngredient(type))) {
            cheeseCount--;
        }
        // Iterate through the combined stack, removing the ingredient

        final int weight = Ingredient.getIngredient(type).getWeight();
        final MyStack<Ingredient> tempStack = new MyStack<>();
        // Iterate through top stack:
        if (weight > TOP_START) {
            // Iterate through stack and remove ingredient
            while (!topStack.isEmpty()) {
                if (Ingredient.getIngredient(type) == topStack.peek()) {
                    topStack.pop();
                } else {
                    tempStack.push(topStack.pop());
                }
            }
            // Restore top stack
            while (!tempStack.isEmpty()) {
                topStack.push(tempStack.pop());
            }
            // Iterate through bottom stack
        } else {
            // Iterate through stack and remove ingredient
            while (!botStack.isEmpty()) {
                if (Ingredient.getIngredient(type) == botStack.peek()) {
                    botStack.pop();
                } else {
                    tempStack.push(botStack.pop());
                }
            }
            // Restore bottom stack
            while (!tempStack.isEmpty()) {
                botStack.push(tempStack.pop());
            }
        }
    }

    @Override
    public String toString() {

        // Combine the top and bottom parts of the burger

        final StringBuilder sb = new StringBuilder("[");

        // Flip the top stack and print it out, then flip again to restore top
        final MyStack<Ingredient> temp = flipStack(topStack);
        sb.append(temp);
        topStack = flipStack(temp);
        sb.append(", ");
        sb.append(botStack.toString());

        sb.append("]");

        return sb.toString();
    }

    /**
     * Reverses the order of a stack.
     * 
     * @param theStack The stack to be flipped.
     * @return The flipped stack.
     */
    private MyStack<Ingredient> flipStack(final MyStack<Ingredient> theStack) {

        // Make a copy of the stack, but upside down

        final MyStack<Ingredient> tempStack = new MyStack<>();
        while (!theStack.isEmpty()) {
            tempStack.push(theStack.pop());
        }
        return tempStack;
    }

    /**
     * Create the top half of a baron burger.
     */
    private void baronTopStack() {
        topStack.push(Ingredient.PICKLE);
        topStack.push(Ingredient.TOPBUN);
        topStack.push(Ingredient.MAYO);
        topStack.push(Ingredient.BARONSAUCE);
        topStack.push(Ingredient.LETTUCE);
        topStack.push(Ingredient.TOMATO);
        topStack.push(Ingredient.ONIONS);
    }

    /**
     * Create the bottom half of a baron burger.
     */
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
