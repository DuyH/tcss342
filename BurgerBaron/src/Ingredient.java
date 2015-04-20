public enum Ingredient {

    PICKLE("Pickle", "Veggie", 14), TOPBUN("Bun", "Bun", 13), MAYO(
            "Mayonnaise", "Sauce", 12), BARONSAUCE("Baron-Sauce", "Sauce", 11), LETTUCE(
            "Lettuce", "Veggie", 10), TOMATO("Tomato", "Veggie", 9), ONIONS(
            "Onions", "Veggie", 8), PEPPERJACK("Pepperjack", "Cheese", 7), MOZZARELLA(
            "Mozzarella", "Cheese", 6), CHEDDAR("Cheddar", "Cheese", 5), VEGGIE(
            "Veggie", "Patty", 4), CHICKEN("Chicken", "Patty", 4), BEEF("Beef",
            "Patty", 4), MUSHROOM("Mushroom", "Veggie", 3), MUSTARD("Mustard",
            "Sauce", 2), KETCHUP("Ketchup", "Sauce", 1), BOTTOMBUN("Bun",
            "Bun", 0);

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

    public int getOrder() {
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
