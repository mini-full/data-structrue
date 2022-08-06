// package hw2;

// import edu.princeton.cs.algs4.WeightedQuickUnionUF;

// public class Percolation {
//     private final int N;
//     private int openSites;
//     private WeightedQuickUnionUF sites;
//     private boolean[] open;
//     private boolean[] TopConnected;
//     private boolean[] BottomConnected;

//     // create N-by-N grid, with all sites initially blocked
//     public Percolation(int N) {
//         if (N <= 0) {
//             throw new java.lang.IllegalArgumentException();
//         }
//         this.N = N;
//         openSites = 0;
//         sites = new WeightedQuickUnionUF(N * N);

//         // Boolean array in java is initialized to false.
//         open = new boolean[N * N];
//         TopConnected = new boolean[N * N];
//         BottomConnected = new boolean[N * N];
//         for (int i = 0; i < N; i++) {
//             TopConnected[i] = true;
//         }
//         for (int i = 0; i < N; i++) {
//             BottomConnected[N * N - 1 - i] = true;
//         }
//         for (int i = 0; i < N; i++) {
//             for (int j = 0; j < N; j++) {
//                 open[xyTo1D(i, j)] = false;
//             }
//         }
//     }

//     // Union two adjoint sites if they are open, and set the full value.
//     private void connect(int row, int col, int row1, int col1) {
//         validate(row, col);
//         validate(row1, col1);
//         int src = xyTo1D(row, col);
//         int dst = xyTo1D(row1, col1);
//         if (isOpen(row, col) && isOpen(row1, col1)) {
//             sites.union(src, dst);
//             if (TopConnected[sites.find(dst)] || TopConnected[sites.find(src)] || TopConnected[dst]
//                     || TopConnected[src]) {
//                 TopConnected[sites.find(dst)] = TopConnected[sites.find(src)] = true;
//             }
//             if (BottomConnected[sites.find(dst)] || BottomConnected[sites.find(src)] || BottomConnected[dst]
//                     || BottomConnected[src]) {
//                 BottomConnected[sites.find(dst)] = BottomConnected[sites.find(src)] = true;
//             }
//         }
//     }

//     // open the site (row, col) if it is not open already
//     public void open(int row, int col) {
//         if (!isOpen(row, col)) {
//             validate(row, col);
//             open[xyTo1D(row, col)] = true;
//             openSites++;
//             if (row == 0) {
//                 TopConnected[xyTo1D(row, col)] = true;
//             } else if (row == N - 1) {
//                 BottomConnected[xyTo1D(row, col)] = true;
//             }
//             if (row > 0 && open[xyTo1D(row - 1, col)]) {
//                 connect(row, col, row - 1, col);
//             }
//             if (row < N - 1 && open[xyTo1D(row + 1, col)]) {
//                 connect(row, col, row + 1, col);
//             }
//             if (col > 0 && open[xyTo1D(row, col - 1)]) {
//                 connect(row, col, row, col - 1);
//             }
//             if (col < N - 1 && open[xyTo1D(row, col + 1)]) {
//                 connect(row, col, row, col + 1);
//             }
//         }
//     }

//     // is the site (row, col) open?
//     public boolean isOpen(int row, int col) {
//         validate(row, col);
//         return open[xyTo1D(row, col)];
//     }

//     private void validate(int row, int col) {
//         if (illegalIndex(row, col)) {
//             throw new java.lang.IndexOutOfBoundsException();
//         }
//     }

//     // is the site (row, col) full?
//     public boolean isFull(int row, int col) {
//         validate(row, col);
//         return TopConnected[sites.find(xyTo1D(row, col))] && open[xyTo1D(row, col)];
//     }

//     // number of open sites
//     public int numberOfOpenSites() {
//         return openSites;
//     }

//     // does the system percolate?
//     public boolean percolates() {
//         for (int i = 0; i < N; i++) {
//             int tar = sites.find(i);
//             if (BottomConnected[tar]) {
//                 return true;
//             }
//         }
//         return false;
//     }

//     // use for unit testing (not required)
//     public static void main(String[] args) {

//     }

//     private int xyTo1D(int row, int col) {
//         return row * N + col;
//     }

//     private boolean illegalIndex(int row, int col) {
//         return row < 0 || row >= N || col < 0 || col >= N;
//     }
// }

/************************************************************/
package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;
    private WeightedQuickUnionUF sites;
    // sites without bottomSite
    private WeightedQuickUnionUF sites2;
    private int topSite;
    private int bottomSite;
    private boolean[][] flagOpen;
    private int numOpen = 0;

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        topSite = N * N;
        bottomSite = N * N + 1;

        sites = new WeightedQuickUnionUF(N * N + 2);
        for (int i = 0; i < N; i++) {
            sites.union(topSite, xyTo1D(0, i));
        }
        for (int i = 0; i < N; i++) {
            sites.union(bottomSite, xyTo1D(N - 1, i));
        }

        sites2 = new WeightedQuickUnionUF(N * N + 1);
        for (int i = 0; i < N; i++) {
            sites2.union(topSite, xyTo1D(0, i));
        }

        flagOpen = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                flagOpen[i][j] = false;
            }
        }
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validateRange(row, col);
        if (flagOpen[row][col]) {
            return;
        }
        flagOpen[row][col] = true;
        numOpen++;
        unionOpenNeighbor(row, col, row + 1, col);
        unionOpenNeighbor(row, col, row - 1, col);
        unionOpenNeighbor(row, col, row, col + 1);
        unionOpenNeighbor(row, col, row, col - 1);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validateRange(row, col);
        return flagOpen[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validateRange(row, col);

        if (!isOpen(row, col)) {
            return false;
        }
        return sites2.connected(xyTo1D(row, col), topSite);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpen;
    }

    // does the system percolate?
    public boolean percolates() {
        if (numOpen == 0) {
            return false;
        }
        return sites.connected(topSite, bottomSite);
    }

    private int xyTo1D(int row, int col) {
        return row * N + col;
    }

    private void validateRange(int row, int col) {
        if (row < 0 || row >= N || col < 0 || col >= N) {
            throw new IndexOutOfBoundsException();
        }
    }

    private void unionOpenNeighbor(int row, int col, int newRow, int newCol) {
        if (newRow < 0 || newRow >= N || newCol < 0 || newCol >= N) {
            return;
        }
        if (flagOpen[newRow][newCol]) {
            sites.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
            sites2.union(xyTo1D(row, col), xyTo1D(newRow, newCol));
        }
    }

    // use for unit testing (not required)
    public static void main(String[] args) {
    }
}
