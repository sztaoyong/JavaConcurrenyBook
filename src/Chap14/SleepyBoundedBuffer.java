package Chap14;

import net.jcip.annotations.ThreadSafe;

/**
 * Created by taoyong on 5/8/16.
 */
@ThreadSafe
public class SleepyBoundedBuffer<T> extends BaseBoundedBuffer<T> implements Buffer<T>{

    public SleepyBoundedBuffer(int capacity) {
        super(capacity);
    }

    @Override
    public void put(T t) throws InterruptedException{
        while(true) {
            synchronized (this) {
                if (!isFull()) {
                    doPut(t);
                    return;
                }
            }
            // lock is released before sleep
            Thread.sleep(500);
        }
    }

    @Override
    public T get() throws InterruptedException{
        while(true) {
            synchronized (this) {
                if (!isEmpty()) {
                    return doGet();
                }
            }
            Thread.sleep(500);
        }
    }
}
