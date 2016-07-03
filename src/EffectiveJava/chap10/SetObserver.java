package EffectiveJava.chap10;

/**
 * Created by taoyong on 6/26/16.
 */
public interface SetObserver<E> {
    void added(ObservableSet<E> set, E element);
}
