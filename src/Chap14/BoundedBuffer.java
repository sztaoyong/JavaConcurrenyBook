package Chap14;

import net.jcip.annotations.ThreadSafe;

/**
 * Created by taoyong on 5/8/16.
 */
@ThreadSafe
public class BoundedBuffer<T> extends BaseBoundedBuffer<T> implements Buffer<T>{

    // CONDITION PREDICATE: not-full (!isFull())
    // CONDITION PREDICATE: not-empty (!isEmpty())

    public BoundedBuffer(int capacity) {
        super(capacity);
    }

    // BLOCKS-UNTIL: not-full
    @Override
    public synchronized void put(T t) throws InterruptedException {
        while (isFull()) {
            this.wait();
        }
        boolean wasEmpty = isEmpty();
        doPut(t);
        if (wasEmpty) {
            this.notifyAll();
        }
    }

    // BLOCKS-UNTIL: not-empty
    @Override
    public synchronized T get() throws InterruptedException {
        while (isEmpty()) {
            this.wait();
        }
        boolean wasFull = isFull();
        T res = doGet();
        if (wasFull) {
            this.notifyAll();
        }
        return res;
    }


}
