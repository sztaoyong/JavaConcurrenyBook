package Chap14;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;


/**
 * Created by taoyong on 5/7/16.
 */
@ThreadSafe
public abstract class BaseBoundedBuffer<T> {
    @GuardedBy("this") private final T[] buf;
    @GuardedBy("this") private int tail;
    @GuardedBy("this") private int head;
    @GuardedBy("this") private int count;

    protected BaseBoundedBuffer(int capacity) {
        buf =  (T[]) new Object[capacity]; // cannot instantiate T directly.
        // when T has to be instantiated; use casting.
    }

    /**
     * put t into the tail of the buffer.
     * if buffer is full; this new t will overwrite the previous value.
     * @param t
     */
    protected synchronized final void doPut(T t) {
        buf[tail] = t;
        if (++tail == buf.length) {
            tail = 0;
        }
        count++;
    }

    /**
     * @return the head of the buffer. return null if buffer is empty.
     */
    protected synchronized final T doGet() {
        T res = buf[head];
        buf[head] = null;
        if (++head == buf.length) {
            head = 0;
        }
        count--;
        return res;
    }

    public synchronized final boolean isFull() {
        return count == buf.length;
    }

    public synchronized final boolean isEmpty() {
        return count == 0;
    }

}
