package Calculator;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class CalculatorTest {

    Calculator calc = new Calculator();

    @Test public void testAdd() { assertEquals(calc.add(2, 3), 5); }
    @Test public void testSubtract() { assertEquals(calc.subtract(4, 3), 1); }
    @Test public void testMultiply() { assertEquals(calc.multiply(4, 3), 12); }
    @Test public void testDivide() { assertEquals(calc.divide(6, 3), 2); }

    @Test(expectedExceptions = ArithmeticException.class)
    public void testDivideByZero() {
        calc.divide(5, 0);
    }
}
