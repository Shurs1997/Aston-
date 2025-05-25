package Factorial;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class FactorialTest {

    Factorial factorial = new Factorial();

    @Test
    public void testFactorial() {
        assertEquals(factorial.calculate(5), 120);
        assertEquals(factorial.calculate(0), 1);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegativeNumber() {
        factorial.calculate(-1);
    }
}
