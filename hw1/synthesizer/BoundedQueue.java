package synthesizer;
import java.util.Iterator;

interface BoundedQueue<T> extends Iterable<T>{
    /* Return size of the buffer. */
    int capacity();

    /* Return number of items currentlyin the buffer. */
    int fillCount();

    /* Add item x to the end.
     * @parameter x the item to be added.
     */
    void enqueue(T x);

    /* Delete and return item from the front. */
    T dequeue();

    /* Return (but not delete) item from the front. */
    T peek();

    /* Return a boolean value that indicates if the buffer is empty. */
    default boolean isEmpty() {
        return fillCount() == 0;
    }

    /* Return a boolean value that indicates if the buffer is full. */
    default boolean isFull() {
        return capacity() == fillCount();
    }
}