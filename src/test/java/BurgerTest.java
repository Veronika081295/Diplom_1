import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTest {
    private Burger burger;
    private static final float DELTA = 0.001f;

    @Mock
    private Bun bun;

    @Mock
    private Ingredient ingredient;

    @Before
    public void setUp() {
        burger = new Burger();
        MockitoAnnotations.openMocks(this);
        System.out.println("Setup complete. Burger initialized.");
    }

    @Test
    public void shouldSetBunForBurger() {
        System.out.println("Test: shouldSetBunForBurger");
        burger.setBuns(bun);
        assertNotNull("Bun should not be null", burger.bun);
        System.out.println("Bun set successfully: " + burger.bun);
    }

    @Test
    public void shouldAddIngredientToBurger() {
        System.out.println("Test: shouldAddIngredientToBurger");
        burger.addIngredient(ingredient);
        assertFalse("Ingredients list should not be empty", burger.ingredients.isEmpty());
        assertEquals("Ingredients list should contain 1 ingredient", 1, burger.ingredients.size());
        System.out.println("Ingredient added successfully. Ingredients list: " + burger.ingredients);
    }

    @Test
    public void shouldRemoveIngredientFromBurger() {
        System.out.println("Test: shouldRemoveIngredientFromBurger");
        burger.addIngredient(ingredient);
        int index = burger.ingredients.indexOf(ingredient);
        burger.removeIngredient(index);
        assertTrue("Ingredients list should be empty", burger.ingredients.isEmpty());
        System.out.println("Ingredient removed successfully. Ingredients list: " + burger.ingredients);
    }

    @Test
    public void shouldMoveIngredientInBurger() {
        System.out.println("Test: shouldMoveIngredientInBurger");
        burger.addIngredient(ingredient);
        Ingredient secondIngredient = new Ingredient(SAUCE, "Ketchup", 0.5F);
        burger.addIngredient(secondIngredient);

        int initialIndex = burger.ingredients.indexOf(ingredient);
        int newIndex = burger.ingredients.indexOf(secondIngredient);

        burger.moveIngredient(newIndex, initialIndex);

        assertEquals("First ingredient should be the moved one", ingredient, burger.ingredients.get(newIndex));
        System.out.println("Ingredient moved successfully. Ingredients list: " + burger.ingredients);
    }


    @Test
    public void calculateBurgerPriceWithIngredients() {
        // Arrange
        float priceBun = 0.25f;
        float priceIngredient = 0.7f;
        float secondIngredientPrice = 0.5f;
        float expectedPrice = priceBun * 2 + secondIngredientPrice + priceIngredient;

        // Stubbing
        when(bun.getPrice()).thenReturn(priceBun);
        when(ingredient.getPrice()).thenReturn(priceIngredient);

        // Act
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        Ingredient secondIngredient = new Ingredient(SAUCE, "Patty", secondIngredientPrice);
        burger.addIngredient(secondIngredient);

        // Log Outputs
        System.out.println("Burger price calculation test started.");
        System.out.println("Price of bun: " + priceBun);
        System.out.println("Price of first ingredient: " + priceIngredient);
        System.out.println("Price of second ingredient: " + secondIngredientPrice);

        // Assert
        float actualPrice = burger.getPrice();
        System.out.println("Calculated burger price: " + actualPrice);
        assertEquals("Calculated burger price does not match expected", expectedPrice, actualPrice, DELTA);

        // Verify interactions
        Mockito.verify(bun, Mockito.times(1)).getPrice();
        Mockito.verify(ingredient, Mockito.times(1)).getPrice();
        Mockito.verifyNoMoreInteractions(bun, ingredient);

        // Log Outputs
        System.out.println("Price calculation test completed successfully.");
    }


    @Test
    public void shouldGetRecipeOfBurger() {
        System.out.println("Test: shouldGetRecipeOfBurger");
        float priceBun = 0.25f;
        float priceIngredient = 0.7f;
        Ingredient secondIngredient = new Ingredient(SAUCE, "Ketchup", 0.1f);
        when(bun.getName()).thenReturn("myFirstBun");
        when(bun.getPrice()).thenReturn(priceBun);
        when(ingredient.getType()).thenReturn(FILLING);
        when(ingredient.getName()).thenReturn("Patty");
        when(ingredient.getPrice()).thenReturn(priceIngredient);
        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(secondIngredient);
        System.out.println("Burger receipt:\n" + burger.getReceipt());
        Mockito.verify(bun, Mockito.times(2)).getName();
        Mockito.verify(ingredient, Mockito.times(1)).getType();
        Mockito.verify(ingredient, Mockito.times(1)).getName();
    }
}
