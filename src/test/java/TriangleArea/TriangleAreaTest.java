package TriangleArea;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TriangleAreaTest {
    @Test
    void testArea() {
        TriangleArea area = new TriangleArea();
        assertEquals(10.0, area.calculate(5, 4));
    }

    @Test
    void testNegative() {
        TriangleArea area = new TriangleArea();
        assertThrows(IllegalArgumentException.class, () -> area.calculate(-5, 4));
    }
}
