public enum Ingredient {

    PICKLE("Pickle", "Veggie", 14), TOPBUN("Bun", "Bun", 13), MAYO(
            "Mayonaisse", "Sauce", 12), BARONSAUCE("Baron Sauce", "Sauce", 11), LETTUCE(
            "Lettuce", "Veggie", 10), TOMATO("Tomato", "Veggie", 9), ONIONS(
            "Onions", "Veggie", 8), PEPPERJACK("Pepperjack", "Cheese", 7), MOZZARELLA(
            "Mozzarella", "Cheese", 6), CHEDDAR("Cheddar", "Cheese", 5), VEGGIE(
            "Veggie", "Patty", 4), CHICKEN("Chicken", "Patty", 4), BEEF("Beef",
            "Patty", 4), MUSHROOM("Mushroom", "Veggie", 3), MUSTARD("Mustard",
            "Sauce", 2), KETCHUP("Ketchup", "Sauce", 1), BOTTOMBUN("Bun",
            "Bun", 0);

    private String name;
    private String category;
    private int orderNumber;

    private Ingredient(String name, String category, int order) {
        this.name = name;
        this.category = category;
        orderNumber = order;
    }

    public int getOrder() {
        return orderNumber;
    }

    public String getname() {
        return name;
    }

    @Override
    public String toString() {
        // only capitalize the first letter
        String s = super.toString();
        return s.substring(0, 1) + s.substring(1).toLowerCase();
    }

}
