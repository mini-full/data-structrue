package lab11.graphs;

import edu.princeton.cs.algs4.Queue;

/**
 * @author Josh Hug
 */
public class MazeBreadthFirstPaths extends MazeExplorer {
    /*
     * Inherits public fields:
     * public int[] distTo;
     * public int[] edgeTo;
     * public boolean[] marked;
     */
    private static final int INFINITY = Integer.MAX_VALUE;
    /* Source */
    private int s;

    /* target */
    private int t;

    private boolean targetFound = false;

    private Maze m;

    public MazeBreadthFirstPaths(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        // Add more variables here!
        this.m = m;
        this.s = maze.xyTo1D(sourceX, sourceY);
        this.t = maze.xyTo1D(targetX, targetY);
        this.marked = new boolean[m.V()];
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Conducts a breadth first search of the maze starting at the source. */
    private void bfs() {
        // Your code here. Don't forget to update distTo, edgeTo, and marked, as well as
        // call announce()
        Queue<Integer> fringe = new Queue<Integer>();
        fringe.enqueue(s);
        marked[s] = true;
        announce();
        while (!fringe.isEmpty()) {
            int v = fringe.dequeue();
            for (int w : m.adj(v)) {
                if (!marked[w]) {
                    marked[w] = true;
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    announce();
                    if (w == t) {
                        return;
                    } else {
                        fringe.enqueue(w);
                    }
                }
            }
        }
    }

    @Override
    public void solve() {
        bfs();
    }
}
