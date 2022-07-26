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

    @Test
    public void testOffBy1() {
        OffByN offBy1 = new OffByN(1);
        assertTrue(offBy1.equalChars('a', 'b'));
        assertTrue(offBy1.equalChars('r', 'q'));
        assertTrue(offBy1.equalChars('&', '%'));
        assertFalse(offBy1.equalChars('a', 'e'));
        assertFalse(offBy1.equalChars('z', 'a'));
        assertFalse(offBy1.equalChars('a', 'a'));
    }

    @Test
    public void testOffBy0() {
        OffByN offBy0 = new OffByN(0);
        assertTrue(offBy0.equalChars('a', 'a'));
        assertFalse(offBy0.equalChars('a', 'b'));
    }
}
