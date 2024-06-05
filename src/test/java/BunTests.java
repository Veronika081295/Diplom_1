import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

@RunWith(Parameterized.class)
public class BunTests {
    private final String name;
    private final float price;
    private final static float TOLERANCE = 0.01F;

    public BunTests(String name, float price) {
        this.name = name;
        this.price = price;
    }

    @Parameterized.Parameters(name = "{index}: Test with name={0}, price={1}")
    public static Object[][] params() {
        return new Object[][]{
                {"BlackBun", 121.2F},
                {"GreyBun", 234.5F},
                {"RedBun", 345.6F},
                {"WhiteBun", 555.5F}
        };
    }

    @Test
    public void getNameTest() {
        System.out.println("Running getNameTest with name: " + name);
        Bun bun = new Bun(name, price);
        Assert.assertEquals(name, bun.getName());
    }

    @Test
    public void getPriceTest() {
        System.out.println("Running getPriceTest with price: " + price);
        Bun bun = new Bun(name, price);
        Assert.assertEquals(price, bun.getPrice(), TOLERANCE);
    }
}
