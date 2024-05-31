import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(Parameterized.class)
public class BunNameTest {
    private final String nameBun;
    private final String expected;
    private final boolean isPositiveTest;

    public BunNameTest(String name, String expected, boolean isPositiveTest) {
        this.nameBun = name;
        this.expected = expected;
        this.isPositiveTest = isPositiveTest;
    }

    @Parameterized.Parameters(name = "{index}: Test with nameBun={0}, expected={1}, isPositiveTest={2}")
    public static Object[][] nameParameters() {
        return new Object[][] {
                {"super black bun", "super black bun", true},
                {"not so white bun", "not so white bun", true},
                {"blue", "red", false}  // Negative test case
        };
    }

    @Test
    public void getBunName() {
        Bun bun = new Bun(nameBun, 10);
        String actual = bun.getName();
        if (isPositiveTest) {
            assertEquals("Names do not match", expected, actual);
        } else {
            assertNotEquals("Names should not match", expected, actual);
        }
    }
}
