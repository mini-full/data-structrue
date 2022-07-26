import org.junit.Test;
import static org.junit.Assert.*;



public class TestOffByN {

    static OffByN offby1 = new OffByN(5);

    @Test
    public void testOffByN() {
        assertTrue(offby1.equalChars('a', 'f'));
        assertTrue(offby1.equalChars('f', 'a')); // true
        assertFalse(offby1.equalChars('f', 'h')); // false
        assertTrue(offby1.equalChars('#', '('));
        assertFalse(offby1.equalChars('#', ')'));
    }
}
