package Factorial;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {
    @Test
    void testFactorial() {
        Factorial factorial = new Factorial();
        assertEquals(120, factorial.calculate(5));
        assertEquals(1, factorial.calculate(0));
    }

    @Test
    void testNegativeNumber() {
        Factorial factorial = new Factorial();
        assertThrows(IllegalArgumentException.class, () -> factorial.calculate(-1));
    }
}
