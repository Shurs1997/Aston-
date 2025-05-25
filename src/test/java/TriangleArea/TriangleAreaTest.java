package TriangleArea;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class TriangleAreaTest {

    TriangleArea area = new TriangleArea();

    @Test
    public void testArea() {
        assertEquals(area.calculate(5, 4), 10.0);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testNegative() {
        area.calculate(-5, 4);
    }
}
