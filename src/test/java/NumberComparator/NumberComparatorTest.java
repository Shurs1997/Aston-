package NumberComparator;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class NumberComparatorTest {

    NumberComparator comp = new NumberComparator();

    @Test public void testGreater() { assertEquals(comp.compare(5, 3), 1); }
    @Test public void testEqual() { assertEquals(comp.compare(4, 4), 0); }
    @Test public void testLess() { assertEquals(comp.compare(2, 6), -1); }
}