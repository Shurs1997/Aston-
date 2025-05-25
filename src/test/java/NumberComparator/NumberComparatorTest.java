package NumberComparator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class NumberComparatorTest {
    NumberComparator comp = new NumberComparator();

    @Test void testGreater() { assertEquals(1, comp.compare(5, 3)); }
    @Test void testEqual() { assertEquals(0, comp.compare(4, 4)); }
    @Test void testLess() { assertEquals(-1, comp.compare(2, 6)); }
}