package hw4.puzzle;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private MinPQ<SearchNode> pq;
    private List<WorldState> solutions = new ArrayList<>();
    private Map<WorldState, Integer> edtgCaches = new HashMap<>();

    private class SearchNode {
        private WorldState state;

        // the number of moves made to reach this world state from the initial state.
        private int moves = 0;

        // a reference to the previous search node.
        private SearchNode prev;

        public SearchNode(WorldState state, int moves, SearchNode prev) {
            this.state = state;
            this.moves = moves;
            this.prev = prev;
        }
    }

    private class NodeComparator implements Comparator<SearchNode> {
        @Override
        public int compare(SearchNode o1, SearchNode o2) {
            int o1Edtg = getEdtg(o1);
            int o2Edtg = getEdtg(o2);
            int o1Priority = o1.moves + o1Edtg;
            int o2Priority = o2.moves + o2Edtg;
            return o1Priority - o2Priority;
        }

        private int getEdtg(SearchNode sn) {
            if (!edtgCaches.containsKey(sn.state)) {
                edtgCaches.put(sn.state, sn.state.estimatedDistanceToGoal());
            }
            return edtgCaches.get(sn.state);
        }
    }

    /**
     * Constructor which solves the puzzle, computing
     * everything necessary for moves() and solution() to
     * not have to solve the problem again. Solves the
     * puzzle using the A* algorithm. Assumes a solution exists.
     */
    public Solver(WorldState initial) {
        pq = new MinPQ<>(new NodeComparator());
        SearchNode currentNode = new SearchNode(initial, 0, null);
        pq.insert(currentNode);
        while (!pq.isEmpty()) {
            currentNode = pq.delMin();
            if (currentNode.state.isGoal()) {
                solutions.add(currentNode.state);
                while (currentNode.prev != null) {
                    currentNode = currentNode.prev;
                    solutions.add(currentNode.state);
                }
                break;
            }
            for (WorldState neighbor : currentNode.state.neighbors()) {
                SearchNode newNode = new SearchNode(neighbor, currentNode.moves + 1, currentNode);
                if (currentNode.prev == null || !neighbor.equals(currentNode.prev.state)) {
                    pq.insert(newNode);
                }
            }
        }
    }

    /**
     * Returns the minimum number of moves to solve the puzzle starting
     * at the initial WorldState.
     */
    public int moves() {
        return solutions.size() - 1;
    }

    /**
     * Returns a sequence of WorldStates from the initial WorldState
     * to the solution.
     * 
     * @return
     */
    public Iterable<WorldState> solution() {
        return solutions;
    }
}
