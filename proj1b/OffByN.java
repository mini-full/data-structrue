public class OffByN implements CharacterComparator {
    
    private int N = 0;

    public OffByN(int n) {
        N = n;
    }

    public boolean equalChars(char x, char y) {
        int diff = x - y;
        return diff == N || diff == -N;
    }

}
