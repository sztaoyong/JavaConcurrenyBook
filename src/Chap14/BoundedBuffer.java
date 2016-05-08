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
    public void put(T t) throws InterruptedException {
        synchronized (this) {
            while (isFull()) {
                this.wait();
            }
            doPut(t);
            this.notifyAll();
        }
    }

    // BLOCKS-UNTIL: not-empty
    @Override
    public T get() throws InterruptedException {
        synchronized (this) {
            while (isEmpty()) {
                this.wait();
            }
            T res = doGet();
            this.notifyAll();
            return res;
        }
    }


}
