import org.junit.Before;
import org.junit.Test;
import praktikum.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class BurgerTest {
    private List<Bun> buns;
    private List<Ingredient> ingredients;

    @Before
    public void setUp() {
        // Mock the database
        Database mockDatabase = mock(Database.class);

        // Create a list of buns and ingredients
        buns = new ArrayList<>();
        ingredients = new ArrayList<>();

        // Add buns to the list
        buns.add(new Bun("black bun", 100));
        buns.add(new Bun("white bun", 200));
        buns.add(new Bun("red bun", 300));

        // Add ingredients to the list
        ingredients.add(new Ingredient(IngredientType.SAUCE, "hot sauce", 100));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "sour cream", 200));
        ingredients.add(new Ingredient(IngredientType.SAUCE, "chili sauce", 300));
        ingredients.add(new Ingredient(IngredientType.FILLING, "cutlet", 100));
        ingredients.add(new Ingredient(IngredientType.FILLING, "dinosaur", 200));
        ingredients.add(new Ingredient(IngredientType.FILLING, "sausage", 300));

        // Mock the database responses
        when(mockDatabase.availableBuns()).thenReturn(buns);
        when(mockDatabase.availableIngredients()).thenReturn(ingredients);
    }

    @Test
    public void getPriceTest() {
        // Create a new burger and set its ingredients
        Burger burger = new Burger();
        burger.setBuns(buns.get(0)); // black bun (100)
        burger.addIngredient(ingredients.get(1)); // sour cream (200)
        burger.addIngredient(ingredients.get(4)); // dinosaur (200)
        burger.addIngredient(ingredients.get(3)); // cutlet (100)
        burger.addIngredient(ingredients.get(5)); // sausage (300)
        burger.moveIngredient(2, 1); // Move cutlet (100) to position 1
        burger.removeIngredient(3); // Remove sausage (300)

        // Output the state of the burger
        System.out.println("Burger with black bun and ingredients:");
        for (Ingredient ingredient : burger.ingredients) {
            System.out.println(ingredient.getType() + ": " + ingredient.getName() + " (" + ingredient.getPrice() + ")");
        }

        // Calculate and print the expected price
        float expectedPrice = 700.0f;
        System.out.println("Expected price: " + expectedPrice);
        System.out.println("Actual price: " + burger.getPrice());

        // Assert the price
        assertEquals(expectedPrice, burger.getPrice(), 0);
    }

    @Test
    public void getReceiptTest() {
        // Create a new burger and set its ingredients
        Burger burger = new Burger();
        burger.setBuns(buns.get(2)); // red bun (300)
        burger.addIngredient(ingredients.get(1)); // sour cream (200)
        burger.addIngredient(ingredients.get(4)); // dinosaur (200)

        // Output the state of the burger
        System.out.println("Burger with red bun and ingredients:");
        for (Ingredient ingredient : burger.ingredients) {
            System.out.println(ingredient.getType() + ": " + ingredient.getName() + " (" + ingredient.getPrice() + ")");
        }

        // Calculate and print the expected price
        double expectedPrice = 1000.0;
        double actualPrice = burger.getPrice();
        System.out.println("Expected price: " + expectedPrice);
        System.out.println("Actual price: " + actualPrice);

        // Assert the price
        assertEquals(expectedPrice, actualPrice, 0.1);

        // Print the receipt
        String receipt = burger.getReceipt();
        System.out.println("Receipt:\n" + receipt);
    }
}
