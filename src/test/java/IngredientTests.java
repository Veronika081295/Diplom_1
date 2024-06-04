import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.Burger;
import praktikum.Database;
import praktikum.Ingredient;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class IngredientTests {
    private final Burger burger = new Burger();
    @Mock
    Database database;
    public List<Ingredient> ingredients = new ArrayList<>();

    @Before
    public void setupMockDatabase() {
        ingredients.add(new Ingredient(SAUCE, "sauce", 100));
        ingredients.add(new Ingredient(FILLING, "cutlet", 300));
        Mockito.when(database.availableIngredients()).thenReturn(ingredients);
    }

    @Test
    public void addIngredientsTest() {
        burger.addIngredient(database.availableIngredients().get(0));
        System.out.println("Ingredients after adding: " + burger.ingredients);
        assertEquals(1, burger.ingredients.size());
    }

    @Test
    public void removeIngredientsTest() {
        burger.addIngredient(database.availableIngredients().get(0));
        System.out.println("Ingredients before removing: " + burger.ingredients);
        burger.removeIngredient(0);
        System.out.println("Ingredients after removing: " + burger.ingredients);
        assertEquals(0, burger.ingredients.size());
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(database.availableIngredients().get(1));
        burger.addIngredient(database.availableIngredients().get(0));
        System.out.println("Ingredients before moving: " + burger.ingredients);
        burger.moveIngredient(0, 1);
        System.out.println("Ingredients after moving: " + burger.ingredients);
        assertEquals(database.availableIngredients(), burger.ingredients);
    }
}
