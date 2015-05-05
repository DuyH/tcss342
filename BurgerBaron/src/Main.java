/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 1 - Burger Baron
 * Main.java
 * 
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.acl.LastOwnerException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Testing by creating burger orders.
 * 
 * @author Duy Huynh
 * @version 6 April 2015
 *
 */
public class Main {

    /**
     * This main will test the Burger, MyStack class by parsing a text file full
     * of burger orders.
     * 
     * @param args
     *            Command line arguments.
     */
    public static void main(final String[] args) {

        // Testing my classes:
        testMyStack();
        testBurger();

        // Read in customer orders from a text file:
        try {
            final URL url = Main.class
                    .getResource("/Example files/customer2.txt");
            final File orderFile = new File(url.toURI());
            final Scanner fileInput = new Scanner(orderFile);

            // Read customer orders:
            int orderNum = 0;
            while (fileInput.hasNextLine()) {

                final String rawOrder = fileInput.nextLine();
                System.out.println("Processing Order " + orderNum++ + ": "
                        + rawOrder);
                parseLine(rawOrder);

            }
            fileInput.close();
        } catch (final FileNotFoundException | URISyntaxException e) {
            e.printStackTrace();
        }

    }

    /**
     * Creates a Burger from the given order.
     * 
     * @param line
     *            A String representing a Burger order.
     */
    private static void parseLine(final String line) {

        Burger burgerInProgress = new Burger(false);
        boolean baronFlag = false;
        String[] orderParsed = line.split(" ");

        // Change the burger if its a Baron Burger:
        for (String s : orderParsed) {
            if ("baron".equalsIgnoreCase(s)) {
                baronFlag = true;
                burgerInProgress = new Burger(true);
            }
        }

        // Correct the patties according to order:
        changePatties(orderParsed, burgerInProgress);

        // Now only work with the rest of the string:
        orderParsed = line.substring(line.lastIndexOf("Burger")).split(" ");

        // Parse + Print rest of order, adding/removing ingredients:
        parseBurger(orderParsed, burgerInProgress, baronFlag);
        System.out.println(burgerInProgress + "\n");

    }

    /** Modifies burger patties according to order. */
    private static void changePatties(String[] order, Burger burgerInProgress) {

        // Fix the patty type and counts
        for (String s : order) {

            // Change to Chicken or Veggie
            if ("Chicken".equals(s) || "Veggie".equals(s)) {
                burgerInProgress.changePatties(s);
            }

            // Change to Double or Triple
            if ("Double".equals(s)) {
                burgerInProgress.addPatty();
            } else if ("Triple".equals(s)) {
                burgerInProgress.addPatty();
                burgerInProgress.addPatty();
            }
        }
    }

    /** Modifies Baron Burger according to order. */
    private static void parseBurger(final String[] order,
            final Burger burgerInProgress, boolean baronFlag) {

        boolean butFlag = false;
        for (String s : order) {

            // Ignore anything not an Ingredient or Category
            if ("Burger".equals(s) || "with".equals(s) || "no".equals(s)) {
                continue;
            }

            // The exceptions are always after "but" is encountered:
            if ("but".equals(s)) {
                butFlag ^= true;
                continue;
            }

            if (butFlag) {
                // Handling EXCLUSIONS after "but" was encountered:
                if (baronFlag) {
                    // In a Baron burger, exclusions are ADDED
                    burgerInProgress.addIngredient(s);
                } else {
                    // In a regular burger, exclusions are REMOVED
                    burgerInProgress.removeIngredient(s);
                }
            } else {
                // Handling Ingredients/Categories prior to "but":
                if ("Cheese".equals(s) || "Sauce".equals(s)
                        || "Veggies".equals(s)) {
                    if (baronFlag) {
                        // Categories removed in Barons if shows up before "but"
                        burgerInProgress.removeCategory(s);
                    } else {
                        // Regular burgers' categories added if after "butt"
                        burgerInProgress.addCategory(s);
                    }
                } else {
                    // Ingredients removed in Barons if shows up before "but"
                    if (baronFlag) {
                        burgerInProgress.removeIngredient(s);
                    } else {
                        // Regular burgers' ingredients added if after "butt"
                        burgerInProgress.addIngredient(s);
                    }
                }
            }
        }
    }

    /** Test a MyStack. */
    private static void testMyStack() {

        final MyStack<Integer> myStack = new MyStack<Integer>();

        System.out.println("========START MYSTACK TEST=========");
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
        System.out.println("========END MYSTACK TEST=========\n\n");

        // If you try to pop it will give you an exception

    }

    /** Test a Baron Burger. */
    private static void testBaronBurger() {

        // Create a baron burger and print it out to console
        final Burger baronBurger = new Burger(true);
        System.out.println("========START BARON BURGER TEST=========");
        System.out.println(baronBurger);
        System.out.println();

        // Change to beef then chicken
        System.out.println("Changing patty types:");
        baronBurger.changePatties("Beef");
        baronBurger.changePatties("Chicken");
        System.out.println("Add Beef, then Chicken: " + baronBurger);
        baronBurger.changePatties("Veggie");
        System.out.println("Make it a veggie: " + baronBurger + "\n");

        // Add two more patties
        System.out.println("Changing patty counts:");
        baronBurger.addPatty();
        System.out.println("Add a patty (patty count 2 now): " + baronBurger);
        baronBurger.addPatty();
        System.out.println("Add a patty (patty count 3 now): " + baronBurger);

        // Attempt "4th" patty, nothing should happen
        baronBurger.addPatty();
        System.out.print("Attempt to add patty to illegal amount: ");
        System.out.println(baronBurger);

        // Remove one patty, then another, and another (but won't work)
        baronBurger.removePatty();
        System.out.println("Remove the third patty: " + baronBurger);
        baronBurger.removePatty();
        System.out.println("Remove the second patty: " + baronBurger);
        baronBurger.removePatty();
        System.out.println("Attempt removing last patty: " + baronBurger);
        System.out.println("========END BURGER TEST=========\n\n");

    }

    /** Test a plain Burger. */
    private static void testBurger() {

        testBaronBurger();

        final Burger boringBurger = new Burger(false);
        System.out.println("========START BURGER TEST=========");
        System.out.println(boringBurger);
        System.out.println();

        // Change patties
        boringBurger.changePatties("Chicken");
        System.out.println("Change to chicken: " + boringBurger);

        // Remove patty:
        boringBurger.removePatty();
        System.out.println("Attempt to remove a patty: " + boringBurger);

        // Add too many patties:
        boringBurger.addPatty();
        boringBurger.addPatty();
        boringBurger.addPatty();
        System.out.println("Try to add 3 more patties: " + boringBurger);
        System.out.println();

        // Add cheese categories (second call should have no effect)
        boringBurger.addCategory("Cheese");
        boringBurger.addCategory("Cheese");
        System.out.println("Try to add cheese category twice: " + boringBurger);

        // Add sauce category
        boringBurger.addCategory("Sauces");
        boringBurger.addCategory("Sauce");
        System.out.println("Try to add sauce category twice: " + boringBurger);

        // Add veggies (second call no effect)
        boringBurger.addCategory("Veggies");
        boringBurger.addCategory("Veggies");
        System.out.println("Try to add veggie category twice: " + boringBurger);
        System.out.println();

        // Remove sauce cat:
        boringBurger.removeCategory("Sauce");
        boringBurger.removeCategory("Sauce");
        System.out.println("Try to remove sauce category twice" + boringBurger);

        // Remove veggie cat:
        boringBurger.removeCategory("Veggies");
        boringBurger.removeCategory("Veggies");
        System.out.println("Try to remove veggie cat twice" + boringBurger);

        // Remove cheese cat:
        boringBurger.removeCategory("Cheese");
        boringBurger.removeCategory("Cheese");
        System.out.println("Try to remove cheese cat twice" + boringBurger);
        System.out.println();

        // Add ingredients:
        boringBurger.addIngredient("Mushrooms");
        System.out.println("Add Mushrooms: " + boringBurger);
        boringBurger.addIngredient("Lettuce");
        System.out.println("Add Lettuce: " + boringBurger);
        boringBurger.addIngredient("Pickle");
        System.out.println("Add Pickle: " + boringBurger);
        boringBurger.addIngredient("Pickle");
        System.out.println("Add Pickle: " + boringBurger);
        boringBurger.addIngredient("Pepperjack");
        System.out.println("Add Pepperjack: " + boringBurger);
        boringBurger.addIngredient("Mayonnaise");
        System.out.println("Add Mayonnaise: " + boringBurger);
        boringBurger.addIngredient("Ketchup");
        System.out.println("Add Ketchup: " + boringBurger);
        System.out.println();

        // Remove ingredients:
        boringBurger.removeIngredient("Pickle");
        System.out.println("Remove Pickle: " + boringBurger);
        boringBurger.removeIngredient("Pickle");
        System.out.println("Remove Pickle: " + boringBurger);
        boringBurger.removeIngredient("Mayonnaise");
        System.out.println("Remove Mayonnaise: " + boringBurger);
        boringBurger.removeIngredient("Pepperjack");
        System.out.println("Remove Pepperjack: " + boringBurger);
        boringBurger.removeIngredient("Onions");
        System.out.println("Remove Onions: " + boringBurger);
        System.out.println("========END BURGER TEST=========\n\n");
    }

}
