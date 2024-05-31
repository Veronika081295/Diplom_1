import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;

@RunWith(Parameterized.class)
public class IngredientNameTest {
    private final String inputName;
    private final String expectedName;

    public IngredientNameTest(String inputName, String expectedName) {
        this.inputName = inputName;
        this.expectedName = expectedName;
    }

    @Parameterized.Parameters
    public static Object[][] nameParameters() {
        return new Object[][] {
                {"patty", "patty"},
                {"Cucumber", "Cucumber"},
                {"Spicy Burger Sauce", "Spicy Burger Sauce"},
                {"ketchup", "ketchup"}
        };
    }

    @Test
    public void testGetIngredientName() {
        Ingredient ingredient = new Ingredient(FILLING, inputName, 0.5f);
        String actualName = ingredient.getName();
        assertEquals("Ingredient name does not match", expectedName, actualName);
    }
}
