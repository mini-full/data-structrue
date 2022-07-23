public class ArrayDeque<T> {

    private T[] a;
    private int left; // a[left] is accessible
    private int right; // a[right] is not accessible
    private int capacity = 8;

    public ArrayDeque() {
        a = (T[]) new Object[capacity];
        left = right = 0;
    }

    private void resize(int newCapacity) {
        T[] newArray = (T[]) new Object[newCapacity];
        int length = right - left;
        System.arraycopy(this.a, this.left, newArray, newCapacity / 4, length);
        left = newCapacity / 4;
        right = left + length;
        a = newArray;
        capacity = newCapacity;
    }

    private boolean isLowUsageRate() {
        return capacity >= 16 && size() / (double) capacity < 0.25;
    }

    public void addFirst(T item) {
        if (right == capacity || left == 0) {
            resize(2 * capacity);
        }
        a[--left] = item;
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
    }

    public void addLast(T item) {
        if (right == capacity) {
            resize(2 * capacity);
        }
        this.a[right++] = item;
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
    }

    public boolean isEmpty() {
        return right == left;
    }

    public int size() {
        return right - left;
    }

    public void printDeque() {
        for (int i = left; i < right; i++) {
            System.out.print(a[i] + " ");
        }
    }

    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T ret = a[right - 1];
        a[right - 1] = null; // avoid loitering
        right--;
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
        return ret;
    }

    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T ret = a[left];
        a[left] = null;
        left++;
        if (isLowUsageRate()) {
            resize((int) (capacity * 0.5));
        }
        return ret;
    }

    public T get(int index) {
        if (size() == 0 || size() <= index || index < 0) {
            return null;
        }
        return a[left + index];
    }
}
