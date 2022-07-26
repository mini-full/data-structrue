public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ret = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++){
            ret.addLast(word.charAt(i));
        }
        return ret;
    }

}
