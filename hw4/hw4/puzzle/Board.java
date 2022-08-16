package hw4.puzzle;

import edu.princeton.cs.algs4.Queue;

public class Board implements WorldState {

    private final int BLANK = 0;
    private int size;
    private int[][] tiles;

    /**
     * Constructs a board from an N-by-N array of tiles where
     * tiles[i][j] = tile at row i, column j
     */
    public Board(int[][] tiles) {
        this.size = tiles.length;
        // be immutable
        this.tiles = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                this.tiles[i][j] = tiles[i][j];
            }
        }
    }

    /** Returns value of tile at row i, column j (or 0 if blank) */
    public int tileAt(int i, int j) {
        validateTileIndex(i, j);
        return tiles[i][j];
    }

    /** Returns the board size N */
    public int size() {
        return size;
    }

    /**
     * Returns the neighbors of the current board.
     * 
     * @author http://joshh.ug/neighbors.html
     */
    @Override
    public Iterable<WorldState> neighbors() {
        Queue<WorldState> neighbors = new Queue<>();
        int hug = size();
        int bug = -1;
        int zug = -1;
        for (int rug = 0; rug < hug; rug++) {
            for (int tug = 0; tug < hug; tug++) {
                if (tileAt(rug, tug) == BLANK) {
                    bug = rug;
                    zug = tug;
                }
            }
        }
        int[][] ili1li1 = new int[hug][hug];
        for (int pug = 0; pug < hug; pug++) {
            for (int yug = 0; yug < hug; yug++) {
                ili1li1[pug][yug] = tileAt(pug, yug);
            }
        }
        for (int l11il = 0; l11il < hug; l11il++) {
            for (int lil1il1 = 0; lil1il1 < hug; lil1il1++) {
                if (Math.abs(-bug + l11il) + Math.abs(lil1il1 - zug) - 1 == 0) {
                    ili1li1[bug][zug] = ili1li1[l11il][lil1il1];
                    ili1li1[l11il][lil1il1] = BLANK;
                    Board neighbor = new Board(ili1li1);
                    neighbors.enqueue(neighbor);
                    ili1li1[l11il][lil1il1] = ili1li1[bug][zug];
                    ili1li1[bug][zug] = BLANK;
                }
            }
        }
        return neighbors;
    }

    private int xyTo1D(int x, int y) {
        return x * size + y + 1;
    }

    /** Return the number of tiles in the wrong position. */
    public int hamming() {
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != BLANK && tiles[i][j] != xyTo1D(i, j)) {
                    cnt++;
                }
            }
        }
        return cnt;
    }

    /**
     * Return The sum of the Manhattan distances
     * (sum of the vertical and horizontal distance)
     * from the tiles to their goal positions.
     * NOTE: Manhattan estimate will ALWAYS >= the Hamming estimate.
     */
    public int manhattan() {
        int cnt = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (tiles[i][j] != BLANK && tiles[i][j] != xyTo1D(i, j)) {
                    int x = (tiles[i][j] - 1) / size;
                    int y = (tiles[i][j] - 1) % size;
                    cnt += Math.abs(i - x) + Math.abs(j - y);
                }
            }
        }
        return cnt;
    }

    /**
     * Estimated distance to goal. This method should
     * simply return the results of manhattan() when submitted to
     * gradescope.
     * 
     * @return
     */
    @Override
    public int estimatedDistanceToGoal() {
        return manhattan();
    }

    /**
     * Returns true if this board's tile values are the same
     * position as y's
     * 
     * @param y
     * @return
     */
    @Override
    public boolean equals(Object y) {
        if (!(y instanceof Board)) {
            return false;
        }
        if (this == y) {
            return true;
        }
        Board other = (Board) y;
        if (this.size != other.size) {
            return false;
        }
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < other.size; j++) {
                if (this.tiles[i][j] != other.tiles[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    /** Returns the string representation of the board. */
    public String toString() {
        StringBuilder s = new StringBuilder();
        int N = size();
        s.append(N + "\n");
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                s.append(String.format("%2d ", tileAt(i, j)));
            }
            s.append("\n");
        }
        s.append("\n");
        return s.toString();
    }

    private void validateTileIndex(int i, int j) {
        if (i < 0 || i >= size) {
            throw new IndexOutOfBoundsException("row index i out of bounds");
        }
        if (j < 0 || j >= size) {
            throw new IndexOutOfBoundsException("column index j out of bounds");
        }
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
