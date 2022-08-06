package hw2;

import edu.princeton.cs.introcs.StdRandom;
import edu.princeton.cs.introcs.StdStats;
import java.util.function.ToLongBiFunction;
import javax.swing.text.StyledDocument;

public class PercolationStats {
    private int T;
    private double[] openSiteFractions;

    // perform T independent experiments on an NbyN grid.
    public PercolationStats(int N, int T, PercolationFactory pf) {
        if (N <= 0 || T <= 0) {
            throw new IllegalArgumentException();
        }
        this.T = T;
        openSiteFractions = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = pf.make(N);
            while (!percolation.percolates()) {
                int x, y;
                do {
                    x = StdRandom.uniform(N);
                    y = StdRandom.uniform(N);
                } while (percolation.isOpen(x, y));
                percolation.open(x, y);

            }
            openSiteFractions[i] = (double) percolation.numberOfOpenSites() / (N * N);
        }
    }

    public double mean() {
        return StdStats.mean(openSiteFractions);
    }

    public double stddev() {
        return StdStats.stddev(openSiteFractions);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt((double) T);
    }

    public double confidenceHigh() {
        return 2 * mean() - confidenceLow();
    }
}
