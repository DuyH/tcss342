/*
 * Duy Huynh
 * TCSS 342 - Spring '15
 * Assignment 1 - Burger Baron
 * Ingredient.java
 * 
 */

public enum Ingredient {

    PICKLE("Pickle", "Veggies", 14), TOPBUN("Bun", "Bun", 13), MAYO(
            "Mayonnaise", "Sauce", 12), BARONSAUCE("Baron-Sauce", "Sauce", 11), LETTUCE(
            "Lettuce", "Veggies", 10), TOMATO("Tomato", "Veggies", 9), ONIONS(
            "Onions", "Veggies", 8), PEPPERJACK("Pepperjack", "Cheese", 7), MOZZARELLA(
            "Mozzarella", "Cheese", 6), CHEDDAR("Cheddar", "Cheese", 5), VEGGIE(
            "Veggie", "Patty", 4), CHICKEN("Chicken", "Patty", 4), BEEF("Beef",
            "Patty", 4), MUSHROOM("Mushrooms", "Veggies", 3), MUSTARD(
            "Mustard", "Sauce", 2), KETCHUP("Ketchup", "Sauce", 1), BOTTOMBUN(
            "Bun", "Bun", 0);

    private String name;
    private String category;
    private int weight;

    private Ingredient(String theName, String theCategory, int theWeight) {
        name = theName;
        category = theCategory;
        weight = theWeight;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {

        return name;

    }

    public static Ingredient getIngredient(String ingredientName) {
        for (Ingredient i : values()) {
            if (i.getName().equalsIgnoreCase(ingredientName)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Ingredient \"" + ingredientName
                + "\" not found!");
    }
}
