/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 1 - Burger Baron
 * Main.java
 * 
 */

public class Main {

    private String category;
    private String exceptions;
    private String omissions;

    public static void main(String[] args) {

        testBurger();

    }

    private void parseLine(String line) {

        // Read in a file

        // Takes "line" and parses it
        // Create a new burger according to the line parse

        // For testing, output the burger to console

        // Write to outputfile the burger and order number

    }

    private void testMyStack() {

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
