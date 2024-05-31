import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class BunPriceTest {
    private final float inputPrice;
    private final float expectedPrice;
    private static final float DELTA = 0.001f;

    public BunPriceTest(float inputPrice, float expectedPrice) {
        this.inputPrice = inputPrice;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "{index}: Test with inputPrice={0}, expectedPrice={1}")
    public static Object[][] priceParameters() {
        return new Object[][] {
                {0.1f, 0.1f},
                {1.99f, 1.99f},
                {11.0f, 11.0f},
                {0.0f, 0.0f}, // Edge case: Zero price
                {-1.0f, -1.0f} // Edge case: Negative price
        };
    }

    @Test
    public void testGetBunPrice() {
        Bun bun = new Bun("TestBun", inputPrice);
        float actualPrice = bun.getPrice();
        System.out.println("Testing with inputPrice: " + inputPrice + ", expectedPrice: " + expectedPrice);
        assertEquals("Prices do not match", expectedPrice, actualPrice, DELTA);
    }
}
