import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.IngredientType;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)

public class IngredientTypeTests {
    private final String type;

    public IngredientTypeTests(String type) {
        this.type = type;
    }

    @Parameterized.Parameters(name = "Ingredient type: {0}")
    public static Object[][] getTestData() {
        return new Object[][] {
                {"SAUCE"},
                {"FILLING"},
        };
    }

    @Test
    public void presenceIngredientTest() {
        System.out.println("Testing presence of ingredient type: " + type);
        IngredientType ingredientType = IngredientType.valueOf(type);
        System.out.println("IngredientType.valueOf(type): " + ingredientType);
        assertThat(ingredientType, is(notNullValue()));
    }
}
