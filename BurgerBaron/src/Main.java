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

        testMyStack();
        testBurger();

        // Read in customer orders from a text file:
        try {
            final URL url = Main.class
                    .getResource("/Example files/customer.txt");
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
    private static void parseLine(String line) {

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

        // Fix the patty type and counts
        for (String s : orderParsed) {

            // Change to Chicken or Veggie
            if ("Chicken".equals(s) || "Veggie".equals(s)) {
                burgerInProgress.changePatties(s);

                // Change to Double or Triple
                if ("Double".equals(s)) {
                    burgerInProgress.addPatty();
                } else if ("Triple".equals(s)) {
                    burgerInProgress.addPatty();
                    burgerInProgress.addPatty();
                }
            }
        }

        // Now only work with the rest of the string:
        orderParsed = line.substring(line.lastIndexOf("Burger")).split(" ");

        int startIndex = 3;
        boolean omissionFlag = true;

        // Omissions/Additions for Baron Burger:
        if (baronFlag) {

            // "3" is the index of string after "Burger with no"

            for (int i = startIndex; i < orderParsed.length; i++) {
                if ("but".equals(orderParsed[i])) {
                    omissionFlag = false;
                }

                // Exclude the category or ingredient
                if (omissionFlag) {
                    if ("Cheese".equals(orderParsed[i])
                            || "Sauce".equals(orderParsed[i])
                            || "Veggies".equals(orderParsed[i])) {
                        burgerInProgress.removeCategory(orderParsed[i]);
                    } else {
                        burgerInProgress.removeIngredient(orderParsed[i]);
                    }

                    // Add the ingredient:
                } else if (!"but".equals(orderParsed[i])) {
                    burgerInProgress.addIngredient(orderParsed[i]);
                }
            }

            // Omissions/Additions for plain Burger:
            omissionFlag = false;
        } else {

            for (int i = startIndex - 1; i < orderParsed.length; i++) {
                if (omissionFlag) {
                    if (!"but".equals(orderParsed[i])
                            || !"no".equals(orderParsed[i])) {
                        burgerInProgress.removeIngredient(orderParsed[i]);
                    }
                } else {
                    if ("Cheese".equals(orderParsed[i])
                            || "Sauce".equals(orderParsed[i])
                            || "Veggies".equals(orderParsed[i])) {
                        burgerInProgress.addCategory(orderParsed[i]);
                    } else {
                        burgerInProgress.addIngredient(orderParsed[i]);
                    }
                }
            }

        }

        System.out.println(burgerInProgress + "\n");

    }

    private static void testMyStack() {

    }

    private static void testBaronBurger() {

        // Create a baron burger and print it out to console
        final Burger baronBurger = new Burger(true);
        baronBurger.changePatties("Beef");
        baronBurger.changePatties("Chicken");
        baronBurger.changePatties("Beef");
        baronBurger.changePatties("Veggie");
        System.out.println(baronBurger);

        // Add two more patties
        baronBurger.addPatty();
        baronBurger.addPatty();
        System.out.println(baronBurger);

        // Attempt "4th" patty, nothing should happen
        baronBurger.addPatty();
        System.out.println(baronBurger);

        // Remove one patty, then another, and another (but won't work)
        baronBurger.removePatty();
        System.out.println(baronBurger);
        baronBurger.removePatty();
        System.out.println(baronBurger);
        baronBurger.removePatty();
        System.out.println(baronBurger);

    }

    private static void testBurger() {

        testBaronBurger();

        final Burger boringBurger = new Burger(false);

        // Change patties
        boringBurger.changePatties("Chicken");
        System.out.println(boringBurger);

        // Remove patty:
        boringBurger.removePatty();
        System.out.println(boringBurger);

        // Add too many patties:
        boringBurger.addPatty();
        boringBurger.addPatty();
        boringBurger.addPatty();
        System.out.println(boringBurger);

        // Add cheese categories (second call should have no effect)
        boringBurger.addCategory("Cheese");
        boringBurger.addCategory("Cheese");
        System.out.println(boringBurger);

        // Add sauce category
        boringBurger.addCategory("Sauces");
        System.out.println(boringBurger);

        // Add sauce again, should have no effect
        boringBurger.addCategory("Sauce");
        System.out.println(boringBurger);

        // Add veggies (second call no effect)
        boringBurger.addCategory("Veggies");
        boringBurger.addCategory("Veggies");
        System.out.println(boringBurger);

        // Remove:
        boringBurger.removeCategory("Sauce");
        boringBurger.removeCategory("Sauce");
        System.out.println(boringBurger);

        boringBurger.removeCategory("Veggies");
        boringBurger.removeCategory("Veggies");
        System.out.println(boringBurger);

        boringBurger.removeCategory("Cheese");
        boringBurger.removeCategory("Cheese");
        System.out.println(boringBurger);

        boringBurger.removePatty();
        boringBurger.removePatty();
        boringBurger.removePatty();
        boringBurger.removePatty();
        System.out.println(boringBurger);

        boringBurger.addIngredient("Mushrooms");
        boringBurger.addIngredient("Lettuce");
        boringBurger.addIngredient("Pickle");
        boringBurger.addIngredient("Pickle");
        boringBurger.addIngredient("Pepperjack");
        boringBurger.addIngredient("Mayonnaise");
        System.out.println(boringBurger);

        boringBurger.removeIngredient("Pickle");
        boringBurger.removeIngredient("Pickle");
        boringBurger.removeIngredient("Mayonnaise");
        boringBurger.removeIngredient("Pepperjack");
        System.out.println(boringBurger);
    }

}
