import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;
import praktikum.IngredientType;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.FILLING;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientTypeTest {
    private final IngredientType inputType;
    private final IngredientType expectedType;

    public IngredientTypeTest(IngredientType inputType, IngredientType expectedType) {
        this.inputType = inputType;
        this.expectedType = expectedType;
    }

    @Parameterized.Parameters
    public static Object[][] ingredientTypeParameters() {
        return new Object[][] {
                {SAUCE, SAUCE},
                {FILLING, FILLING}
        };
    }

    @Test
    public void testGetIngredientType() {
        Ingredient ingredient = new Ingredient(inputType, "beef", 0.5f);
        IngredientType actualType = ingredient.getType();
        assertEquals("Ingredient type does not match", expectedType, actualType);
    }
}
