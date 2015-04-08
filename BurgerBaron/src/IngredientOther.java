public abstract class IngredientOther {

    private String name;

    private int stackOrder;

    public IngredientOther(String theName, int theStackOrder) {
        name = theName;
        stackOrder = theStackOrder;
    }

    /**
     * The name of this ingredient.
     * 
     * @return Name of the ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * The location in which this ingredient occupies in a Baron Burger.
     * 
     * @return The spot this ingredient occupies in a Baron Burger stack.
     */
    public int getStackOrder() {
        return stackOrder;
    }

    public void setStackOrder(final int theStackOrder) {
        stackOrder = theStackOrder;
    }
    
    private void assignOrder(){
        
    }
}
