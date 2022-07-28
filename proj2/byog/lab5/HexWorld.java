package byog.lab5;

import byog.TileEngine.TETile;
import org.junit.Test;

import javax.swing.text.Position;

import static org.junit.Assert.assertEquals;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {
    // public static void addHexagon(TETile[][] world, Position p, int s, TETile t)
    // {
    // for (int i = 0; i < 6; i++) {
    // Position q = p.add(HexDirection.values()[i].toPosition(s));
    // world[q.x][q.y] = t;
    // }
    // }

    /**
     * The process I used to write my hexagon building code:
     * 1. Realized that a hexagon is just a stack of rows of tiles with a very
     * specific pattern, where each row is determined by:
     * a. Its width.
     * b. Its xOffset.
     *
     * 2. Wrote methods for calculating width and xOffset of each row of a
     * hexagon, along with JUnit tests to verify that these worked.
     * The reason I chose to do JUnit tests is because it was really easy,
     * and I knew that it would make my life a lot better
     * when I started visually debugging my hexagon code
     * if I knew that I could trust these methods.
     *
     * 3. Wrote the addHexagon method with a double nested for loop
     * that tried to draw hexagons. Debugged for a while until it seemed to work.
     * Unlike above, I did not bother writing JUnit tests,
     * because writing the tests is just as hard as writing the actual method
     * (think about why this is). Instead, I just wrote a main method
     * that drew hexagons and made sure they looked good to my eyeballs.
     *
     * 4. Changed the signature of addHexagon to take Position objects instead of x
     * and y
     * coordinates, for reasons that will become clear in part 2 of this lab.
     *
     * 5. Revised my double for loop so that it uses a helper method that draws each
     * row,
     * so that my code is easier to read and maintain (and use for students).
     *
     * Note this solution uses exceptions, which we may not have covered at the time
     * you're reading this.
     */

    /**
     * Computes the width of row i for a size s hexagon.
     *
     * @param s The size of the hex.
     * @param i The row number where i = 0 is the bottom row.
     * @return the width of row i for a size s hexagon
     */
    public static int hexRowWidth(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }

        return s + 2 * effectiveI;
    }

    /**
     * Computes relative x coordinate of the leftmost tile in the ith
     * row of a hexagon, assuming that the bottom row has an x-coordinate
     * of zero. For example, if s = 3, and i = 2, this function
     * returns -2, because the row 2 up from the bottom starts 2 to the left
     * of the start position, e.g.
     * xxxx
     * xxxxxx
     * xxxxxxxx
     * xxxxxxxx <-- i = 2, starts 2 spots to the left of the bottom of the hex
     * xxxxxx
     * xxxx
     *
     * @param s size of the hexagon
     * @param i row num of the hexagon, where i = 0 is the bottom
     * @return x-coordinate of the leftmost tile in the ith row
     */
    public static int hexRowOffset(int s, int i) {
        int effectiveI = i;
        if (i >= s) {
            effectiveI = 2 * s - 1 - effectiveI;
        }
        return -effectiveI;
    }

    /**
     * Adds a row of the same tile.
     *
     * @param world the world to draw on
     * @param p     the leftmost position of the row
     * @param width the number of tiles wide to draw
     * @param t     the tile to draw
     */
    public static void addRow(TETile[][] world, Position p, int width, TETile t) {
        for (int xi = 0; xi < width; xi += 1) {
            int xCoord = p.x + xi;
            int yCoord = p.y;
            world[xCoord][yCoord] = TETile.colorVariant(t, 32, 32, 32, RANDOM);
        }
    }

    /**
     * Adds a hexagon to the world.
     *
     * @param world the world to draw on
     * @param p     the bottom left coordinate of the hexagon
     * @param s     the size of the hexagon
     * @param t     the tile to draw
     */
    public static void addHexagon(TETile[][] world, Position p, int s, TETile t) {

        if (s < 2) {
            throw new IllegalArgumentException("Hexagon must be at least size 2.");
        }

        // hexagons have 2*s rows. this code iterates up from the bottom row,
        // which we call row 0.
        for (int yi = 0; yi < 2 * s; yi += 1) {
            int thisRowY = p.y + yi;

            int xRowStart = p.x + hexRowOffset(s, yi);
            Position rowStartP = new Position(xRowStart, thisRowY);

            int rowWidth = hexRowWidth(s, yi);

            addRow(world, rowStartP, rowWidth, t);

        }
    }

    @Test
    public void testHexRowWidth() {
        assertEquals(3, hexRowWidth(3, 5));
        assertEquals(5, hexRowWidth(3, 4));
        assertEquals(7, hexRowWidth(3, 3));
        assertEquals(7, hexRowWidth(3, 2));
        assertEquals(5, hexRowWidth(3, 1));
        assertEquals(3, hexRowWidth(3, 0));
        assertEquals(2, hexRowWidth(2, 0));
        assertEquals(4, hexRowWidth(2, 1));
        assertEquals(4, hexRowWidth(2, 2));
        assertEquals(2, hexRowWidth(2, 3));
    }

    @Test
    public void testHexRowOffset() {
        assertEquals(0, hexRowOffset(3, 5));
        assertEquals(-1, hexRowOffset(3, 4));
        assertEquals(-2, hexRowOffset(3, 3));
        assertEquals(-2, hexRowOffset(3, 2));
        assertEquals(-1, hexRowOffset(3, 1));
        assertEquals(0, hexRowOffset(3, 0));
        assertEquals(0, hexRowOffset(2, 0));
        assertEquals(-1, hexRowOffset(2, 1));
        assertEquals(-1, hexRowOffset(2, 2));
        assertEquals(0, hexRowOffset(2, 3));
    }

}
