import org.junit.Test;
import static org.junit.Assert.*;



public class TestOffByOne {

    static CharacterComparator offby1 = new OffByOne();

    @Test
    public void testOffByOne() {
        assertTrue(offby1.equalChars('a', 'b'));
        assertTrue(offby1.equalChars('Y', 'Z')); // true
        assertFalse(offby1.equalChars('d', 'a')); // false
        assertTrue(offby1.equalChars('#', '$'));
        assertTrue(offby1.equalChars('#', '"'));
        assertFalse(offby1.equalChars('#', ')'));
    }
}
