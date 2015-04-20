public class Main {

    private String category;
    private String exceptions;
    private String omissions;

    public static void main(String[] args) {

        testBaronBurger();
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
        Burger myBaronBurger = new Burger(true);
        myBaronBurger.changePatties("Beef");
        myBaronBurger.changePatties("Chicken");
        myBaronBurger.changePatties("Beef");
        myBaronBurger.changePatties("Veggie");
        System.out.println(myBaronBurger);

        // Add two more patties
        myBaronBurger.addPatty();
        myBaronBurger.addPatty();
        System.out.println(myBaronBurger);

        // Attempt "4th" patty, nothing should happen
        myBaronBurger.addPatty();
        System.out.println(myBaronBurger);

        // Remove one patty, then another, and another (but won't work)
        myBaronBurger.removePatty();
        System.out.println(myBaronBurger);
        myBaronBurger.removePatty();
        System.out.println(myBaronBurger);
        myBaronBurger.removePatty();
        System.out.println(myBaronBurger);

    }

    private static void testBurger() {
        Burger boringBurger = new Burger(false);
        boringBurger.changePatties("Chicken");
        System.out.println(boringBurger);
        boringBurger.removePatty();
        boringBurger.addPatty();
        boringBurger.addPatty();
        boringBurger.addPatty();
        System.out.println(boringBurger);
        boringBurger.addCategory("Cheese");
        boringBurger.addCategory("Cheese");
        System.out.println(boringBurger);

        // Add sauce category
        boringBurger.addCategory("Sauce");
        System.out.println(boringBurger);

        boringBurger.addCategory("Sauce");
        System.out.println(boringBurger);

    }
}
