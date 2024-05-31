import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Ingredient;

import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.SAUCE;

@RunWith(Parameterized.class)
public class IngredientPriceTest {
    private final float priceIngredient;
    private final float expectedPrice;
    private static final float DELTA = 0.001f; // Use static final for constants

    public IngredientPriceTest(float priceIngredient, float expectedPrice) {
        this.priceIngredient = priceIngredient;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters
    public static Object[][] priceParameters() {
        return new Object[][] {
                {0.01f, 0.01f},
                {1.99f, 1.99f},
                {11.00f, 11.00f} // Ensure consistency with floating-point numbers
        };
    }

    @Test
    public void testGetIngredientPrice() {
        Ingredient ingredient = new Ingredient(SAUCE, "beef", priceIngredient);
        float actualPrice = ingredient.getPrice();
        assertEquals("Ingredient price does not match", expectedPrice, actualPrice, DELTA);
    }
}
