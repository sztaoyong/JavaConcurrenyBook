package Chap14;

/**
 * Created by taoyong on 5/8/16.
 */
public interface Buffer<T> {

    void put(T t) throws InterruptedException;
    T get() throws InterruptedException;
}
