public class Burger {

	MyStack<Ingredient> topStack = new MyStack<>();
	MyStack<Ingredient> botStack = new MyStack<>();
	MyStack<Ingredient> finalStack = new MyStack<>();

	private int orderNumber;
	private int pattyCount = 1;
	private String pattyType = Ingredient.BEEF.getName();

	public Burger(boolean theWorks) {

		if (theWorks) {
			// Construct the Burger Baron.
			baronTopStack();
			baronBottomStack();

		} else {
			// Construct plain burger.
			botStack.push(Ingredient.BOTTOMBUN);
			botStack.push(Ingredient.BEEF);
			botStack.push(Ingredient.TOPBUN);
		}
	}

	public void changePatties(String pattyType) {

		MyStack<Ingredient> tempStack = null;
		int count = 0;

		// Don't change patties if it's already of that type!
		if (!this.pattyType.equals(pattyType)) {

			// change the first patty
			// checking the bottom, go through the cheeses

			// pop of all the cheeses
			while (!botStack.peek().getCategory().equals("Patty")) {
				tempStack.push(botStack.pop());
				count++;
				botStack.push(Ingredient.valueOf(pattyType));
			}

			for (int i = 0; i < pattyCount; i++) {
				if (botStack.peek().getCategory().equals("Patty")) {
					tempStack.push(botStack.pop());
				} else {
					tempStack.push(botStack.pop());
				}
			}

			// If there are more
			// Change the other two patties
		}

	}

	public void addPatty() {

		// Only add if pattyCount is 2 or less, bc max is 3
		// while(pattyAmount)
		// pattyAmount++;
	}

	public void removePatty() {

		// Only able to remove if patty is 2 or more. min is 1 patty.

	}

	public void addCategory(String type) {

		if (type == "Cheese") {

		} else if (type == "Sauce") {

		} else if (type == "Veggies") {

		}

	}

	public void removeCategory(String type) {

	}

	public void addIngredient(String type) {

	}

	public void removeIngredient(String type) {

		// Iterate through the
	}

	@Override
	public String toString() {

		// Combine the top and bottom parts of the burger
		combineStacks();

		final StringBuilder sb = new StringBuilder("[");

		sb.append(botStack.toString());

		sb.append("]");

		return sb.toString();
	}

	private void combineStacks() {

		// Burger will be on bottom stack now
		while (!topStack.isEmpty()) {
			botStack.push(topStack.pop());
		}
	}

	private void baronTopStack() {
		topStack.push(Ingredient.PICKLE);
		topStack.push(Ingredient.TOPBUN);
		topStack.push(Ingredient.MAYO);
		topStack.push(Ingredient.BARONSAUCE);
		topStack.push(Ingredient.LETTUCE);
		topStack.push(Ingredient.TOMATO);
		topStack.push(Ingredient.ONIONS);
	}

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
