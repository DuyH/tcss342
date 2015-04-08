public enum Ingredient {

    PICKLE("Pickle", 14), TOPBUN("Bun", 13), MAYO("Mayonaisse", 12), BARONSAUCE(
            "Baron Sauce", 11), LETTUCE("Lettuce", 10), TOMATO("Tomato", 9), ONIONS(
            "Onions", 8), PEPPERJACK("Pepperjack", 7), MOZZARELLA("Mozzarella",
            6), CHEDDAR("Cheddar", 5), VEGGIE("Veggie", 4), CHICKEN("Chicken",
            4), BEEF("Beef", 4), MUSHROOM("Mushroom", 3), MUSTARD("Mustard", 2), KETCHUP(
            "Ketchup", 1), BOTTOMBUN("Bun", 0);

    private String name;
    private int orderNumber;

    private Ingredient(String name, int order) {
        this.name = name;
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
