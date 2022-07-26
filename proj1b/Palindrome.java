public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ret = new LinkedListDeque<Character>();
        for (int i = 0; i < word.length(); i++) {
            ret.addLast(word.charAt(i));
        }
        return ret;
    }

    public boolean isPalindrome(String word) {
        Deque<Character> wordDeque = wordToDeque(word);
        return isPalindromeHelper(wordDeque);
    }

    private boolean isPalindromeHelper(Deque<Character> wordDeque) {
        if (wordDeque.size() <= 1) {
            return true;
        }
        char front = wordDeque.removeFirst();
        char rear = wordDeque.removeLast();
        if (front != rear) {
            return false;
        } else {
            return isPalindromeHelper(wordDeque);
        }
    }

    public static void main(String[] args) {
        Palindrome palindrome = new Palindrome();
        boolean ret = palindrome.isPalindrome("static");
    }
}
